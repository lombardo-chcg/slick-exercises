/*
This is an Ammonite Script
http://ammonite.io/#Ammonite

It's purpose is to run Slick's "Type-Checked SQL Statements"
When run in the amm REPL it is handy for determining types returned from a pre-written sql query
 */

import $ivy.`com.typesafe.slick:slick_2.12:3.2.3`
import $ivy.`com.typesafe.slick:slick-hikaricp_2.12:3.2.3`
import $ivy.`org.postgresql:postgresql:9.4.1212`

import java.util.concurrent.TimeUnit.SECONDS

import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcBackend.Database
import slick.basic.StaticDatabaseConfig
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object TestUtils {
  val pgUrl = "jdbc:postgresql://localhost:5432/exercises"
  val pgUser = "postgres"
  val pgPassword = "postgres"
  val pgDriver = "org.postgresql.Driver"

  val db = Database.forURL(
    url = pgUrl,
    user = pgUser,
    password = pgPassword,
    driver = pgDriver
  )

  val defaultDuration = Duration(10, SECONDS)

  def executeQuery[R](dbAction: DBIOAction[R, NoStream, Nothing]) = {
    Await.result(db.run(dbAction), defaultDuration)
  }
}

// s"""${sys.props("user.dir")}/scripts/application.conf#tsql"""
@StaticDatabaseConfig("file:scripts/application.conf#tsql")
object TSQLRunner {
  import TestUtils._
  def execute() = {
    val solutionQuery =
      tsql"""
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
      """
    val res = executeQuery(solutionQuery)
    println(res.getClass)
    println(res)
  }
}

TSQLRunner.execute()
