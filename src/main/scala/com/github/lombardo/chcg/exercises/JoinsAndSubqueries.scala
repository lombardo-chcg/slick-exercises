package com.github.lombardo.chcg.exercises

import com.github.lombardo.chcg.database.Connection.{db, defaultDuration}
import com.github.lombardo.chcg.database.Tables.{Bookings, Facilities, Members}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await

object JoinsAndSubqueries {

  def `Example of how a implementation should look`: Seq[BigDecimal] = {
    val query = Facilities.filter(_.name like "%Tennis%").map(_.monthlymaintenance)
    val dbAction = query.result
    Await.result(db.run(dbAction), defaultDuration)
  }

  def `Retrieve the start times of members bookings`: Seq[java.sql.Timestamp] = {
    ???
  }

  def `Work out the start times of bookings for tennis courts`: Seq[(java.sql.Timestamp, String)] = {
    ???
  }

  def `Produce a list of all members who have recommended another member`: Seq[(String, String)] = {
    ???
  }

  def `Produce a list of all members, along with their recommender`: Seq[(String, String, Option[String], Option[String])] = {
    ???
  }

  // NOTE!!!! to get around sort order issues related to Postgres locale setting,
  // order your result by (member name, facility name) instead of just member name
  def `Produce a list of all members who have used a tennis court`: Seq[(String, String)] = {
    ???
  }

  def `Produce a list of costly bookings`: Seq[(String, String, BigDecimal)] = {
    ???
  }
}
