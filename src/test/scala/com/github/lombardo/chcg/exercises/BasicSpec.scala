package com.github.lombardo.chcg.exercises

import com.github.lombardo.chcg.TestHelper.executeQuery
import com.github.lombardo.chcg.database.Tables.FacilitiesRow
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import slick.jdbc.PostgresProfile.api._

class BasicSpec extends AnyFlatSpec with Matchers {

  "Basic Exercises" should "Retrieve everything from a table" in {
    val solutionQuery =
      sql"""
           select * from cd.facilities;
        """.as[FacilitiesRow]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Retrieve everything from a table`
    result should be(expectedResult)
  }

  it should "Retrieve specific columns from a table" in {
    val solutionQuery =
      sql"""
        select name, membercost from cd.facilities;
      """.as[(String, BigDecimal)]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Retrieve specific columns from a table`
    result should be(expectedResult)
  }

  it should "Control which rows are retrieved" in {
    val solutionQuery =
      sql"""
        select * from cd.facilities where membercost > 0;
      """.as[FacilitiesRow]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Control which rows are retrieved`
    result should be(expectedResult)
  }

  it should "Control which rows are retrieved - part 2" in {
    val solutionQuery =
      sql"""
        select facid, name, membercost, monthlymaintenance
          from cd.facilities
          where
            membercost > 0 and
            (membercost < monthlymaintenance/50.0);
      """.as[(Int, String, BigDecimal, BigDecimal)]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Control which rows are retrieved - part 2`
    result should be(expectedResult)
  }


  it should "Basic string searches" in {
    val solutionQuery =
      sql"""
        select *
            from cd.facilities
            where
              name like '%Tennis%';
      """.as[FacilitiesRow]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Basic string searches`
    result should be(expectedResult)
  }

  it should "Matching against multiple possible values" in {
    val solutionQuery =
      sql"""
        select *
            from cd.facilities
            where
              facid in (1,5);
      """.as[FacilitiesRow]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Matching against multiple possible values`
    result should be(expectedResult)
  }

  it should "Classify results into buckets" in {
    val solutionQuery =
      sql"""
        select name,
          case when (monthlymaintenance > 100) then
            'expensive'
          else
            'cheap'
          end as cost
          from cd.facilities;
      """.as[(String, String)]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Classify results into buckets`
    result should be(expectedResult)
  }

  it should "Working with dates" in {
    val solutionQuery =
      sql"""
        select memid, surname, firstname, joindate
          from cd.members
          where joindate >= '2012-09-01';
      """.as[(Int, String, String, java.sql.Timestamp)]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Working with dates`
    result should be(expectedResult)
  }


  it should "Removing duplicates, and ordering results" in {
    val solutionQuery =
      sql"""
        select distinct surname
          from cd.members
        order by surname
        limit 10;
      """.as[String]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Removing duplicates, and ordering results`
    result should be(expectedResult)
  }

  it should "Combining results from multiple queries" in {
    val solutionQuery =
      sql"""
        select surname
          from cd.members
        union
        select name
          from cd.facilities;
      """.as[String]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Combining results from multiple queries`
    result should be(expectedResult)
  }

  it should "Simple aggregation" in {
    val solutionQuery =
      sql"""
        select max(joindate) as latest
          from cd.members;
      """.as[java.sql.Timestamp]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`Simple aggregation`
    result should be(Option(expectedResult.head))
  }

  it should "More aggregation" in {
    val solutionQuery =
      sql"""
        select firstname, surname, joindate
        from cd.members
        where joindate =
          (select max(joindate)
            from cd.members);
        """.as[(String, String, java.sql.Timestamp)]
    val expectedResult = executeQuery(solutionQuery)
    val result = Basic.`More aggregation`
    result should be(expectedResult)
  }
}
