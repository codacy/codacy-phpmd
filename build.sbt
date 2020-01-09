import com.typesafe.sbt.packager.docker.Cmd

name := "codacy-phpmd"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.8.1" withSources (),
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0" withSources (),
  "com.codacy" %% "codacy-engine-scala-seed" % "3.1.0",
  "com.github.pathikrit" %% "better-files" % "3.8.0"
)

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

version in Docker := "1.0"

val installAll =
  s"""
     |export COMPOSER_HOME=/opt/composer &&
     |mkdir -p $$COMPOSER_HOME &&
     |apk --no-cache add bash curl git php php-xml php-cli php-pdo php-curl php-json php-phar php-ctype php-openssl php-dom &&
     |curl -sS https://getcomposer.org/installer | php -- --install-dir=/bin --filename=composer &&
     |composer global require "phpmd/phpmd=2.6.0" &&
     |chmod -R 777 /opt &&
     |ln -s /opt/composer/vendor/bin/phpmd /bin/phpmd &&
     |apk del curl git
     |rm -rf /var/cache/apk/*
   """.stripMargin.replaceAll(System.lineSeparator(), " ")

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

dockerBaseImage := "develar/java"

dockerCommands := dockerCommands.value.flatMap {
  case cmd @ Cmd("ADD", _) =>
    List(
      Cmd("RUN", s"adduser -u 2004 -D $dockerUser"),
      cmd,
      Cmd("RUN", installAll),
      Cmd("RUN", "mv /opt/docker/docs /docs"),
      Cmd("RUN", s"chown -R $dockerUser:$dockerUser /docs")
    )
  case other => List(other)
}
