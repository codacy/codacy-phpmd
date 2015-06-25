package codacy.jshint

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, StandardOpenOption}

import codacy.dockerApi._
import codacy.jshint.JsHintPattern._
import play.api.libs.json.Reads.StringReads
import play.api.libs.json.Writes.StringWrites
import play.api.libs.json._

import scala.sys.process._
import scala.util.Try

object Tool extends ((Path,Seq[PatternDef]) => Try[Iterable[Result]]){

  private[this] implicit class PatternIdentifier(ruleId: PatternId){
    def asJsHintPattern:Option[JsHintPattern] = JsHintPattern.values.find(_.toString() == ruleId.value)
  }

  private[this] lazy val RegMatch = """(.*):[ line]*([0-9]*),[ col]*([0-9]*),(.*)\((.*)\)""".r

  private[this] lazy val minusPrefix = "$minus"

  def apply(sourcePath:Path, patterns:Seq[PatternDef]): Try[Iterable[Result]] = {
    lazy val ruleIds = patterns.map(_.patternId).toSet

    fileForConfig(configFromPatterns(patterns)).map{ case configFile =>

      val configPath = configFile.toAbsolutePath().toString
      val cmd = Seq("jshint", "--config", configPath, "--verbose") ++
        //ignoreFile.toOption.fold(Seq.empty[String]){ case ignore => Seq("--exclude-path", ignore.toAbsolutePath().toString) } ++
        Seq(sourcePath.toAbsolutePath.toString)

      cmd.lineStream_!.map( outputLineAsResult ).
      collect{ case Some(result) if ruleIds.contains(result.ruleId) => result }
    }
  }

  private[this] def outputLineAsResult(line:String):Option[Result] = Option(line).collect{
    case raw @ RegMatch(file, line, _, message, error) =>
      Try(ResultLine(line.toInt)).toOption.flatMap{ case line =>
        ruleIdAndMessage(message,error).map{ case (ruleId,message) =>
            Result( SourcePath(file), message, ruleId, line)
        }
      }
  }.flatten

  private[this] def configFromPatterns(patterns:Seq[PatternDef]): JsObject = {
    val settings = patterns.foldLeft( BaseSettings ){ (settings,pattern) =>

      def settingSet[A](param:JsHintPattern, value:A = true )(implicit writes: Writes[A]) =
        settings.+((param,Json.toJson(value)))

      def settingWithParamValue[A](paramName:JsHintPattern,default:A)(implicit fmt: Format[A]) = {

        val value = pattern.parameters.collectFirst{
          case paramDef if paramDef.name == ParameterName(paramName.toString) => paramDef.value
        }.getOrElse( Json.toJson(default) )

        settingSet(paramName,value)
      }

      pattern.patternId.asJsHintPattern.collect{
        case v @ `bitwise` =>
          settingSet(v) - `-W016`
        case v @ `camelcase` =>
          settingSet(v) - `-W106`
        case v @ `curly` =>
          settingSet(v) - `-W116`
        case v @ `eqeqeq` =>
          settingSet(v) - `-W116`
        case v @ `forin` =>
          settingSet(v) - `-W089`
        case v @ `freeze` =>
          settingSet(v) - `-W121`
        case v @ `immed` =>
          settingSet(v) - `-W062`
        case v @ `latedef` =>
          settingWithParamValue(v, "nofunc") - `-W003`
        case v @ `newcap` =>
          settingSet(v) - `-W055`
        case v @ `noarg` =>
          settingSet(v) - `-W059`
        case v @ `nonew` =>
          settingSet(v) - `-W031`
        case v @ `plusplus` =>
          settingSet(v) - `-W016`
        case v @ `quotmark` =>
          settingSet(v) - `-W110`
        case v @ `undef` =>
          settingSet(v) - `-W117`
        case v @ `unused` =>
          settingSet(v) - `-W098`
        case v @ `maxlen` =>
          settingWithParamValue(v,200) - `-W101`
        case v @ `trail` =>
          settingSet(v) - `-W044`
        case v @ `maxparams` =>
          settingWithParamValue(v, 7) - `-W072`
        case v @ `maxdepth` =>
          settingSet(v,3) - `-W073`
        case v @ `maxstatements` =>
          settingWithParamValue(v, 7) - `-W071`
        case v @ `maxcomplexity` =>
          settingWithParamValue(v, 7) - `-W074`
        case v @ `indent` =>
          settingWithParamValue(v, 2) - `-W015`
        case v @ `asi` =>
          settingSet(v, false) - `-W033`
        case v @ `boss` =>
          settingSet(v, false) - `-W084` - `-W093`
        case v @ `debug` =>
          settingSet(v, false) - `-W087`
        case v @ `null` =>
          settingSet(v, false) - `-W041`
        case v @ `evil` =>
          settingSet(v, false) - `-W054` - `-W060` - `-W061` - `-W066`
        case v @ `expr` =>
          settingSet(v, false) - `-W030`
        case v @ `funcscope` =>
          settingSet(v, false) - `-W038` - `-W091`
        case v @ `iterator` =>
          settingSet(v, false) - `-W104`
        case v @ `loopfunc` =>
          settingSet(v, false) - `-W083`
        case v @ `multistr` =>
          settingSet(v, false) - `-W043`
        case v @ `notypeof` =>
          settingSet(v, false) - `-W122`
        case v @ `shadow` =>
          settingSet(v, false) - `-E044` - `-W004`
        case v @ `proto` =>
          settingSet(v, false) - `-W103`
        case v @ `sub` =>
          settingSet(v, false) - `-W069`
        case v @ `supernew` =>
          settingSet(v, false) - `-W057`
      }.getOrElse(settings)
    }

    JsObject(
      settings.map{ case (key,value) =>
        val rawKey = key.toString
        val correctedKey = if(rawKey.startsWith(minusPrefix)) s"-${rawKey.drop(minusPrefix.length)}" else rawKey
        (correctedKey,value)
      }.toSeq
    )
  }

  private[this] def ruleIdAndMessage(message: String, error: String): Option[(PatternId, ResultMessage)] = {
    val trimmed = message.trim

    JsHintPattern.values.find(_.toString == s"$minusPrefix$error").map( (_, trimmed) ).collect{
      case (`-W116`, msg) if msg.matches( """Expected '\{'.*""")                   => (`curly`,     None)
      case (`-W116`, msg) if msg.matches( """Expected '(===|!==).*""")             => (`eqeqeq`,    None)
      case (`-W016`, msg) if msg.matches( """.*use of '(&|\|)'.*""")               => (`bitwise`,   None)
      case (`-W106`, msg) if msg.matches( """.*not in camel case.*""")             => (`camelcase`, None)
      case (`-W003`, msg) if msg matches """.*was used before it was defined.*"""  => (`latedef`,   None)
      case (`-W059`, msg) if msg matches """.*(caller|callee).*"""                 => (`noarg`,     None)
      case (`-W031`, msg) if msg matches """Do not use 'new' for side effects.*""" => (`nonew`,     None)
      case (`-W016`, msg) if msg matches """Unexpected use of '(\+\+|--)'.*"""     => (`plusplus`,  None)
      case (`-W117`, msg) if msg matches """.*is not defined.*"""                  => (`undef`,     None)
      case (`-W098`, msg) if msg matches """.*is defined but never used.*"""       => (`unused`,    None)
      case (`-W055`, _) => (`newcap`,        None)
      case (`-W089`, _) => (`forin`,         None)
      case (`-W121`, _) => (`freeze`,        None)
      case (`-W062`, _) => (`immed`,         Some("You should wrap an immediate function invocation in parenthesis."))
      case (`-W101`, _) => (`maxlen`,        None)
      case (`-W110`, _) => (`quotmark`,      None)
      case (`-W044`, _) => (`trail`,         None)
      case (`-W072`, _) => (`maxparams`,     None)
      case (`-W073`, _) => (`maxdepth`,      None)
      case (`-W071`, _) => (`maxstatements`, None)
      case (`-W015`, _) => (`indent`,        None)
      case (`-W074`, _) => (`maxcomplexity`, None)
      case (`-W033`, _) => (`asi`,           None)
      case (`-W084`, _) => (`boss`,          None)
      case (`-W093`, _) => (`boss`,          None)
      case (`-W087`, _) => (`debug`,         None)
      case (`-W041`, _) => (`null`,          None)
      case (`-W054`, _) => (`evil`,          None)
      case (`-W060`, _) => (`evil`,          None)
      case (`-W061`, _) => (`evil`,          None)
      case (`-W066`, _) => (`evil`,          None)
      case (`-W030`, _) => (`expr`,          None)
      case (`-W038`, _) => (`funcscope`,     None)
      case (`-W091`, _) => (`funcscope`,     None)
      case (`-W104`, _) => (`iterator`,      None)
      case (`-W083`, _) => (`loopfunc`,      None)
      case (`-W043`, _) => (`multistr`,      None)
      case (`-W122`, _) => (`notypeof`,      None)
      case (`-W004`, _) => (`shadow`,        None)
      case (`-E044`, _) => (`shadow`,        None)
      case (`-W103`, _) => (`proto`,         None)
      case (`-W069`, _) => (`sub`,           None)
      case (`-W057`, _) => (`supernew`,      None)
    }.map{ case (rawId,msg) => (PatternId(rawId.toString),ResultMessage(msg.getOrElse(trimmed)))  }
  }

  private[this] def fileForConfig(config:JsObject) = tmpfile(".jshintrc",Json.stringify(config))

  private[this] def tmpfile(prefix:String,content:String) = {
    Try(Files.write(
      Files.createTempFile(prefix,""),
      content.getBytes(StandardCharsets.UTF_8),
      StandardOpenOption.CREATE
    ))
  }
}