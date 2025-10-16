name := "slick-exercises"
version := "1.0"
scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  // Scala
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  
  // Logging
  "org.apache.logging.log4j" % "log4j-api" % "2.20.0",
  "org.apache.logging.log4j" % "log4j-core" % "2.20.0",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.20.0",
  
  // Database
  "com.typesafe.slick" %% "slick" % "3.5.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.5.0",
  "org.postgresql" % "postgresql" % "42.7.1",
  
  // Testing
  "org.scalatest" %% "scalatest" % "3.2.17" % Test
)

// Compiler options
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlint"
)

// Test options
Test / logBuffered := false
Test / parallelExecution := false
