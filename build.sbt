import com.typesafe.sbt.SbtNativePackager.packageArchetype
import com.typesafe.sbt.packager.docker._

name := """codacy-engine-jshint"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.8"

libraryDependencies += "com.typesafe.play" %% "play-ws" % "2.3.8"

enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

dockerBaseImage := "codacy/jshint"
