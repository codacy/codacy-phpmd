import com.typesafe.sbt.packager.docker.Cmd

name := "codacy-phpmd"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.8.1" withSources (),
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0" withSources (),
  "com.codacy" %% "codacy-engine-scala-seed" % "5.0.0",
  "com.github.pathikrit" %% "better-files" % "3.8.0"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

mappings in Universal ++= (resourceDirectory in Compile).map { resourceDir =>
  val src = resourceDir / "docs"
  val dest = "/docs"

  for {
    path <- src.allPaths.get if !path.isDirectory
  } yield path -> path.toString.replaceFirst(src.toString, dest)
}.value

val dockerUser = "docker"
val dockerGroup = "docker"

daemonUser in Docker := dockerUser

daemonGroup in Docker := dockerGroup

dockerBaseImage := "codacy-phpmd-base"

dockerCommands := dockerCommands.value.flatMap {
  case cmd @ Cmd("ADD", _) =>
    List(
      Cmd("RUN", s"adduser -u 2004 -D $dockerUser"),
      cmd,
      Cmd("RUN", "mv /opt/docker/docs /docs"),
      Cmd("RUN", s"chown -R $dockerUser:$dockerUser /docs")
    )
  case other => List(other)
}
