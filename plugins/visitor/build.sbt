name := "visitor"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  specs2 % Test,
  "mysql" % "mysql-connector-java" % "5.1.37",
  "com.typesafe.play" %% "play-slick" % "1.1.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1"
)

routesGenerator := InjectedRoutesGenerator
