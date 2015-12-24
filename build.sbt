name := """pai-dashboard"""

version := "0.1-SNAPSHOT"

lazy val webmodules = (project in file("plugins/web-modules-widget")).enablePlugins(PlayScala)

lazy val root = (project in file(".")).enablePlugins(PlayScala).dependsOn(webmodules).aggregate(webmodules)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "mysql" % "mysql-connector-java" % "5.1.37"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
