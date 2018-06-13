package com.github.lombardo.chcg.exercises

import com.github.lombardo.chcg.TestHelper.executeQuery
import org.scalatest.{FlatSpec, Matchers}
import slick.jdbc.PostgresProfile.api._

class JoinsAndSubqueriesSpec extends FlatSpec with Matchers {

  "Joins and Subqueries" should "Retrieve the start times of members bookings" in {
    val solutionQuery =
      sql"""
        select bks.starttime
        from
        cd.bookings bks
          inner join cd.members mems
          on mems.memid = bks.memid
        where
        mems.firstname='David'
        and mems.surname='Farrell';
      """.as[java.sql.Timestamp]
    val expectedResult = executeQuery(solutionQuery)
    val result = JoinsAndSubqueries.`Retrieve the start times of members bookings`
    result should be(expectedResult)
  }

  it should "Work out the start times of bookings for tennis courts" in {
    val solutionQuery =
      sql"""
        select bks.starttime as start, facs.name as name
        from
        cd.facilities facs
          inner join cd.bookings bks
          on facs.facid = bks.facid
        where
        facs.facid in (0,1) and
          bks.starttime >= '2012-09-21' and
        bks.starttime < '2012-09-22'
        order by bks.starttime;
      """.as[(java.sql.Timestamp, String)]
    val expectedResult = executeQuery(solutionQuery)
    val result = JoinsAndSubqueries.`Work out the start times of bookings for tennis courts`
    result should be(expectedResult)
  }

  it should "Produce a list of all members who have recommended another member" in {
    val solutionQuery =
      sql"""
        select distinct recs.firstname as firstname, recs.surname as surname
        from
        cd.members mems
          inner join cd.members recs
          on recs.memid = mems.recommendedby
        order by surname, firstname;
      """.as[(String, String)]
    val expectedResult = executeQuery(solutionQuery)
    val result = JoinsAndSubqueries.`Produce a list of all members who have recommended another member`
    result should be(expectedResult)
  }

  it should "Produce a list of all members, along with their recommender" in {
    val solutionQuery =
      sql"""
        select
          mems.firstname as memfname,
          mems.surname as memsname,
          recs.firstname as recfname,
          recs.surname as recsname
        from
        cd.members mems
          left outer join cd.members recs
          on recs.memid = mems.recommendedby
        order by memsname, memfname;
      """.as[(String, String, Option[String], Option[String])]
    val expectedResult = executeQuery(solutionQuery)
    val result = JoinsAndSubqueries.`Produce a list of all members, along with their recommender`
    result should be(expectedResult)
  }

  it should "Produce a list of all members who have used a tennis court" in {
    val solutionQuery =
      sql"""
        select distinct mems.firstname || ' ' || mems.surname as member, facs.name as facility
        from
          cd.members mems
            inner join cd.bookings bks
            on mems.memid = bks.memid
          inner join cd.facilities facs
            on bks.facid = facs.facid
        where
          bks.facid in (0,1)
        order by member, facility
      """.as[(String, String)]
    val expectedResult = executeQuery(solutionQuery)
    val result = JoinsAndSubqueries.`Produce a list of all members who have used a tennis court`
    result should be(expectedResult)
  }

  it should "Produce a list of costly bookings" in {
    val solutionQuery =
      sql"""
        select mems.firstname || ' ' || mems.surname as member,
        facs.name as facility,
        case
          when mems.memid = 0 then
          bks.slots*facs.guestcost
        else
          bks.slots*facs.membercost
        end as cost
        from
        cd.members mems
          inner join cd.bookings bks
          on mems.memid = bks.memid
        inner join cd.facilities facs
          on bks.facid = facs.facid
        where
        bks.starttime >= '2012-09-14' and
        bks.starttime < '2012-09-15' and (
          (mems.memid = 0 and bks.slots*facs.guestcost > 30) or
            (mems.memid != 0 and bks.slots*facs.membercost > 30)
        )
        order by cost desc;
    """.as[(String, String, BigDecimal)]
    val expectedResult = executeQuery(solutionQuery)
    val result = JoinsAndSubqueries.`Produce a list of costly bookings`
    result should be(expectedResult)
  }
}
