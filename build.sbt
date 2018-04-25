name := "dolphin-dollars"
version := "0.1"
scalaVersion := "2.12.4"

// https://mvnrepository.com/artifact/uk.co.jhc/sqlest
libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.3",
  "uk.co.jhc" %% "sqlest" % "0.8.10",
  //"org.json4s" %% "json4s-native" % "3.5.1",
  //"org.json4s" %% "json4s-ext" % "3.5.1",  
  //"com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  //"org.scalatest" %% "scalatest" % "3.0.1" % "test",
  //"org.scalactic" %% "scalactic" % "3.0.1",
  "com.h2database" % "h2" % "1.4.197",
  //"org.http4s" %% "http4s-dsl" % "0.15.8",
  //"org.http4s" %% "http4s-blaze-server" % "0.15.8"
  "com.github.t3hnar" %% "scala-bcrypt" % "3.1"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/"