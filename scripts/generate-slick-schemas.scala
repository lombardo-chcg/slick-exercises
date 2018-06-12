/*
This is an Ammonite Script
http://ammonite.io/#Ammonite
 */

import $ivy.`com.typesafe.slick:slick-codegen_2.12:3.2.3`
import $ivy.`com.typesafe.slick:slick_2.12:3.2.3`
import $ivy.`org.postgresql:postgresql:9.4.1212`

import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.PostgresProfile

val	profile = "slick.jdbc.PostgresProfile"
val	jdbcDriver = "org.postgresql.Driver"
val	url = "jdbc:postgresql://localhost:5432/exercises"
val	outputFolder = s"""${sys.props.getOrElse("user.dir", "/usr")}/src/main/scala"""
val	pkg = "com.github.lombardo.chcg.database"
val	user = "postgres"
val	password = "postgres"

slick.codegen.SourceCodeGenerator.main(
  Array(profile, jdbcDriver, url, outputFolder, pkg, user, password)
)