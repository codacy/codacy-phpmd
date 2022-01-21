name := "codacy-phpmd"

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
  "com.codacy" %% "codacy-engine-scala-seed" % "5.0.3",
  "com.github.pathikrit" %% "better-files" % "3.8.0",
  "org.scalatest" %% "scalatest-wordspec" % "3.2.9" % Test
)

enablePlugins(JavaAppPackaging)
