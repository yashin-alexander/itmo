name := """9lab"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4",
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
)

excludeDependencies += "javax.persistence" % "persistence-api"

fork in run := true
