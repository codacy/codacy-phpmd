import java.nio.file.Paths

import com.typesafe.sbt.SbtNativePackager.packageArchetype
import com.typesafe.sbt.packager.docker._
import Path.relativeTo

name := """codacy-engine-jshint"""

version := "1.0-SNAPSHOT"

val languageVersion = "2.11.7"

scalaVersion := languageVersion

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.3.8"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

val JAVA_VERSION_MAJOR = 8

val JAVA_VERSION_MINOR = 45

val JAVA_VERSION_BUILD = 14

val JAVA_PACKAGE       = "server-jre"

val jreFilenameGzip = s"$JAVA_PACKAGE-$JAVA_VERSION_MAJOR" + s"u$JAVA_VERSION_MINOR-linux-x64.tar.gz"

val jreUrl = s"http://download.oracle.com/otn-pub/java/jdk/$JAVA_VERSION_MAJOR" + s"""u$JAVA_VERSION_MINOR""" + s"""-b$JAVA_VERSION_BUILD/$jreFilenameGzip"""

val jreExtractedFolderName = s"""jdk1.$JAVA_VERSION_MAJOR.0_$JAVA_VERSION_MINOR/jre"""

val installAll =
  s"""apk-install bash curl ca-certificates nodejs python make gcc g++ libc-dev &&
    |npm install -g jshint &&
    |cd /tmp &&
    |curl -o glibc-2.21-r2.apk "https://circle-artifacts.com/gh/andyshinn/alpine-pkg-glibc/6/artifacts/0/home/ubuntu/alpine-pkg-glibc/packages/x86_64/glibc-2.21-r2.apk" &&
    |apk add --allow-untrusted glibc-2.21-r2.apk &&
    |curl -o glibc-bin-2.21-r2.apk "https://circle-artifacts.com/gh/andyshinn/alpine-pkg-glibc/6/artifacts/0/home/ubuntu/alpine-pkg-glibc/packages/x86_64/glibc-bin-2.21-r2.apk" &&
    |apk add --allow-untrusted glibc-bin-2.21-r2.apk &&
    |/usr/glibc/usr/bin/ldconfig /lib /usr/glibc/usr/lib &&
    |curl -L -O -H "Cookie: oraclelicense=accept-securebackup-cookie" -k "$jreUrl" &&
    |gunzip -c $jreFilenameGzip | tar -xf - && mv $jreExtractedFolderName /jre && rm /jre/bin/jjs &&
    |rm /jre/bin/keytool &&
    |rm /jre/bin/orbd &&
    |rm /jre/bin/pack200 &&
    |rm /jre/bin/policytool &&
    |rm /jre/bin/rmid &&
    |rm /jre/bin/rmiregistry &&
    |rm /jre/bin/servertool &&
    |rm /jre/bin/tnameserv &&
    |rm /jre/bin/unpack200 &&
    |rm /jre/lib/ext/nashorn.jar &&
    |rm /jre/lib/jfr.jar &&
    |rm -rf /jre/lib/jfr &&
    |rm -rf /jre/lib/oblique-fonts &&
    |rm -rf /tmp/* /var/cache/apk/* &&
    |apk del curl ca-certificates &&
    |echo 'hosts: files mdns4_minimal [NOTFOUND=return] dns mdns4' >> /etc/nsswitch.conf""".stripMargin.replaceAll(System.lineSeparator()," ")

mappings in Universal <++= (resourceDirectory in Compile) map { (resourceDir: File) =>
  val src = resourceDir / "docs"
  val dest = "/docs"

  for {
      path <- (src ***).get
      if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
}

dockerCommands := Seq(
  Cmd("FROM","gliderlabs/alpine:3.2"),
  Cmd("RUN", installAll),
  Cmd("ENV","JAVA_HOME /jre"),
  Cmd("ENV", "PATH ${PATH}:${JAVA_HOME}/bin"),
  Cmd("ENV", "ENV LANG C.UTF-8")
) ++ dockerCommands.value.map(cmd => List(cmd)).collect{
    case Cmd("FROM",_) :: _ => Nil //aka drop it
    case (add@Cmd("ADD","opt /opt")) :: Nil => List(add,Cmd("RUN","mv opt/docker/docs /docs"))
}.flatten