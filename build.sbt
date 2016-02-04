name := """pai-dashboard"""

version := "0.1-SNAPSHOT"

lazy val webmodules = (project in file("plugins/web-modules-widget")).enablePlugins(PlayScala, SbtWeb)

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb).dependsOn(webmodules).aggregate(webmodules)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "mysql" % "mysql-connector-java" % "5.1.37",
  "com.typesafe.play" %% "play-slick" % "1.1.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.0",
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "react" % "0.14.3",
  "org.webjars" % "momentjs" % "2.11.1",
  "org.webjars" % "d3js" % "3.5.12"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
