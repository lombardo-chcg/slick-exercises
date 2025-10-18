package com.github.lombardo.chcg.solutions

import com.github.lombardo.chcg.database.Connection.{db, defaultDuration}
import com.github.lombardo.chcg.database.Tables.{Bookings, Facilities, Members}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await

/*
  SPOILER ALERT!!!!!!!

  You are about to view solutions to the Joins and Subqueries problems.

  Proceed at your own risk.
 */

object JoinsAndSubqueriesSolutions {

  def `Retrieve the start times of members bookings`: Seq[java.sql.Timestamp] = {
    val query = for {
      mem <- Members.filter(m => m.firstname === "David" && m.surname === "Farrell")
      bk  <- Bookings.filter(_.memid === mem.memid)
    } yield bk.starttime
    Await.result(db.run(query.result), defaultDuration)
  }

  def `Work out the start times of bookings for tennis courts`: Seq[(java.sql.Timestamp, String)] = {
    val query = for {
      bks <- Bookings.filter(b =>
        b.starttime >= java.sql.Timestamp.valueOf("2012-09-21 00:00:00") &&
          b.starttime < java.sql.Timestamp.valueOf("2012-09-22 00:00:00")
      )
      facs <- Facilities.filter(f => (f.facid === bks.facid) && (f.name.like("Tennis%")))
    } yield (bks.starttime, facs.name)

    val dbAction = query.sortBy(_._1).result
    Await.result(db.run(dbAction), defaultDuration)
  }

  def `Produce a list of all members who have recommended another member`: Seq[(String, String)] = {
    val query = for {
      mem         <- Members
      recommender <- Members.filter(_.memid === mem.recommendedby)
    } yield (recommender.firstname, recommender.surname)

    val dbAction = query.distinct.sortBy(m => (m._2, m._1)).result
    Await.result(db.run(dbAction), defaultDuration)
  }

  def `Produce a list of all members, along with their recommender`
      : Seq[(String, String, Option[String], Option[String])] = {
    val query = for {
      (mems, recs) <- Members.joinLeft(Members).on(_.recommendedby === _.memid)
    } yield (mems.firstname, mems.surname, recs.map(_.firstname), recs.map(_.surname))

    val dbAction = query.sortBy(m => (m._2, m._1)).result
    Await.result(db.run(dbAction), defaultDuration)
  }

  // NOTE! to get around sort order issues related to Postgres locale setting,
  // order your result by (member name, facility name) instead of just member name
  def `Produce a list of all members who have used a tennis court`: Seq[(String, String)] = {
    val query = for {
      mems <- Members
      bks  <- Bookings.filter(_.memid === mems.memid)
      facs <- Facilities.filter(f => (f.facid === bks.facid) && (f.name.like("Tennis%")))
    } yield (mems.firstname ++ " " ++ mems.surname, facs.name)

    val dbAction = query.distinct.sortBy(r => (r._1, r._2)).result
    Await.result(db.run(dbAction), defaultDuration)
  }

  def `Produce a list of costly bookings`: Seq[(String, String, BigDecimal)] = {
    val query = for {
      bks <- Bookings.filter(b =>
        b.starttime >= java.sql.Timestamp.valueOf("2012-09-14 00:00:00") &&
          b.starttime < java.sql.Timestamp.valueOf("2012-09-15 00:00:00")
      )
      facs                            <- Facilities.filter(_.facid === bks.facid)
      memberNameFacilityNameCostTuple <- Members
        .filter(_.memid === bks.memid)
        .map(m => {
          val numSlots = bks.slots.asColumnOf[BigDecimal]
          val cost     = Case
            .If(m.memid === 0)
            .Then(numSlots * facs.guestcost)
            .Else(numSlots * facs.membercost)
          (m.firstname ++ " " ++ m.surname, facs.name, cost)
        })
    } yield memberNameFacilityNameCostTuple

    val dbAction = query.filter(_._3 > BigDecimal(30)).sortBy(_._3.desc).result
    Await.result(db.run(dbAction), defaultDuration)
  }
}
