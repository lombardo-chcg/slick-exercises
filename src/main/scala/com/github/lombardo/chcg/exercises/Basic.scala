package com.github.lombardo.chcg.exercises

import com.github.lombardo.chcg.database.Connection.{ db, defaultDuration }
import com.github.lombardo.chcg.database.Tables.{ Facilities, FacilitiesRow }
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await

object Basic {
  def `Retrieve everything from a table`: Seq[FacilitiesRow] = {
    /*
      This first example shows the pattern to use for each problem:
        - compose a query using the Slick DSL.  This example is trivial but the Slick docs will guide you
        - create a DBIOAction by calling `.result` on the query
        - run the query using the provided DatabaseDef: `com.github.lombardo.chcg.database.Connection.db`
        - Await the result and return it
     */
    val query    = Facilities
    val dbAction = query.result
    Await.result(db.run(dbAction), defaultDuration)
  }

  def `Retrieve specific columns from a table`: Seq[(String, BigDecimal)] = {
    ???
  }

  def `Control which rows are retrieved`: Seq[FacilitiesRow] = {
    ???
  }

  def `Control which rows are retrieved - part 2`: Seq[(Int, String, BigDecimal, BigDecimal)] = {
    ???
  }

  def `Basic string searches`: Seq[FacilitiesRow] = {
    ???
  }

  def `Matching against multiple possible values`: Seq[FacilitiesRow] = {
    ???
  }

  def `Classify results into buckets`: Seq[(String, String)] = {
    ???
  }

  def `Working with dates`: Seq[(Int, String, String, java.sql.Timestamp)] = {
    ???
  }

  def `Removing duplicates, and ordering results`: Seq[String] = {
    ???
  }

  def `Combining results from multiple queries`: Seq[String] = {
    ???
  }

  def `Simple aggregation`: Option[java.sql.Timestamp] = {
    ???
  }

  def `More aggregation`: Seq[(String, String, java.sql.Timestamp)] = {
    ???
  }
}
