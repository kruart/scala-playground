name := "akka-http-quickstart"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.22",
  "com.typesafe.akka" %% "akka-stream" % "2.5.22",
  "com.typesafe.akka" %% "akka-http" % "10.1.8",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.8" % Test,
  "com.typesafe.akka" %% "akka-testkit" % "2.5.22" % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.22" % Test,

  "io.circe" %% "circe-core" % "0.11.1",
  "io.circe" %% "circe-generic" % "0.11.1",
  "io.circe" %% "circe-parser" % "0.11.1",
  "de.heikoseeberger" %% "akka-http-circe" % "1.25.2",

  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)