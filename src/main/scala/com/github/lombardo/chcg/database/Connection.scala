package com.github.lombardo.chcg.database

import java.util.concurrent.TimeUnit.SECONDS

import slick.jdbc.JdbcBackend.Database

import scala.concurrent.duration.Duration

object Connection {
  private val POSTGRES_HOST     = sys.env.getOrElse("POSTGRES_HOST", "localhost")
  private val POSTGRES_PORT     = sys.env.getOrElse("POSTGRES_PORT", "5432")
  private val POSTGRES_PASSWORD = sys.env.getOrElse("POSTGRES_PASSWORD", "postgres")
  private val POSTGRES_USER     = sys.env.getOrElse("POSTGRES_PASSWORD", "postgres")

  private val pgUrl    = s"jdbc:postgresql://$POSTGRES_HOST:$POSTGRES_PORT/exercises"
  private val pgDriver = "org.postgresql.Driver"

  val defaultDuration = Duration(10, SECONDS)
  val db              = Database.forURL(
    url = pgUrl,
    user = POSTGRES_USER,
    password = POSTGRES_PASSWORD,
    driver = pgDriver
  )
}
