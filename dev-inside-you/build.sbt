version := "0.0.1-SNAPSHOT"
organization := "dev-inside-you"
scalaVersion := "2.12.8"

triggeredMessage := Watched.clearWhenTriggered
initialCommands in console := "import homegrown.collections._"

addCommandAlias("testc", ";clean;coverage;test;coverageReport")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.7" % Test
)