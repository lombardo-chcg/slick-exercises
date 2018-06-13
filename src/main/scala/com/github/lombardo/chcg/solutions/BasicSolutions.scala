package com.github.lombardo.chcg.solutions

import com.github.lombardo.chcg.database.Connection.{db, defaultDuration}
import com.github.lombardo.chcg.database.Tables.{Bookings, Facilities, FacilitiesRow, Members}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await

/*
  SPOILER ALERT!!!!!!!

  You are about to view solutions to the Basic Query problems.

  Proceed at your own risk.
 */

object BasicSolutions {
  def `Retrieve everything from a table`: Seq[FacilitiesRow] = {
    val query = Facilities.result
    Await.result(db.run(query), defaultDuration)
  }

  def `Retrieve specific columns from a table`: Seq[(String, BigDecimal)] = {
    val query = Facilities.map(f => (f.name, f.membercost)).result
    Await.result(db.run(query), defaultDuration)
  }

  def `Control which rows are retrieved`: Seq[FacilitiesRow] = {
    val query = Facilities.filter(_.membercost > BigDecimal(0.00)).result
    Await.result(db.run(query), defaultDuration)
  }

  def `Control which rows are retrieved - part 2`: Seq[(Int, String, BigDecimal, BigDecimal)] = {
    val query = Facilities
      .filter(f => f.membercost > BigDecimal(0.00) && f.membercost < f.monthlymaintenance/BigDecimal(50.0))
      .map(f => (f.facid, f.name, f.membercost, f.monthlymaintenance))
      .result
    Await.result(db.run(query), defaultDuration)
  }

  def `Basic string searches`: Seq[FacilitiesRow] = {
    val query = Facilities.filter(_.name like "%Tennis%").result
    Await.result(db.run(query), defaultDuration)
  }

  def `Matching against multiple possible values`: Seq[FacilitiesRow] = {
    val targetValues = Set(1, 5)
    val query = Facilities.filter(_.facid inSet targetValues).result
    Await.result(db.run(query), defaultDuration)
  }

  def `Classify results into buckets`: Seq[(String, String)] = {
    val query = Facilities.map(f =>
      (
        f.name,
        Case
          If(f.monthlymaintenance > BigDecimal(100)) Then "expensive"
          Else "cheap"
      )
    ).result
    Await.result(db.run(query), defaultDuration)
  }

  def `Working with dates`: Seq[(Int, String, String, java.sql.Timestamp)] = {
    val query = Members
      .filter(_.joindate > java.sql.Timestamp.valueOf("2012-09-01 00:00:00"))
      .map(m => (m.memid, m.surname, m.firstname, m.joindate))
      .result
    Await.result(db.run(query), defaultDuration)
  }

  def `Removing duplicates, and ordering results`: Seq[String] = {
    val query = Members.map(_.surname).distinct.sorted.take(10).result
    Await.result(db.run(query), defaultDuration)
  }

  def `Combining results from multiple queries`: Seq[String] = {
    val query = (Members.map(_.surname) union Facilities.map(_.name)).result
    Await.result(db.run(query), defaultDuration)
  }

  def `Simple aggregation`: Option[java.sql.Timestamp] = {
    val query = Members.map(_.joindate).max.result
    Await.result(db.run(query), defaultDuration)
  }

  def `More aggregation`: Seq[(String, String, java.sql.Timestamp)] = {
    val mostRecentMembersQuery = for {
      member <- Members
      mostRecentJoinDate <- Query(Members.map(_.joindate).max)
      if member.joindate === mostRecentJoinDate
    } yield (member.firstname, member.surname, member.joindate)

    val query = mostRecentMembersQuery.result

    Await.result(db.run(query), defaultDuration)
  }
}
