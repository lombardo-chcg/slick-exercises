package com.github.lombardo.chcg.database
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables
    extends {
      val profile = slick.jdbc.PostgresProfile
    }
    with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this
  * late.)
  */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{ GetResult => GR }

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Bookings.schema ++ Facilities.schema ++ Members.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Bookings
    * @param bookid
    *   Database column bookid SqlType(int4), PrimaryKey
    * @param facid
    *   Database column facid SqlType(int4)
    * @param memid
    *   Database column memid SqlType(int4)
    * @param starttime
    *   Database column starttime SqlType(timestamp)
    * @param slots
    *   Database column slots SqlType(int4)
    */
  case class BookingsRow(bookid: Int, facid: Int, memid: Int, starttime: java.sql.Timestamp, slots: Int)

  /** GetResult implicit for fetching BookingsRow objects using plain SQL queries */
  implicit def GetResultBookingsRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp]): GR[BookingsRow] = GR { prs =>
    import prs._
    BookingsRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[Int]))
  }

  /** Table description of table bookings. Objects of this class serve as prototypes for rows in queries. */
  class Bookings(_tableTag: Tag) extends profile.api.Table[BookingsRow](_tableTag, Some("cd"), "bookings") {
    def * = (bookid, facid, memid, starttime, slots) <> (BookingsRow.tupled, BookingsRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(bookid), Rep.Some(facid), Rep.Some(memid), Rep.Some(starttime), Rep.Some(slots)).shaped.<>(
      { r => import r._; _1.map(_ => BookingsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get))) },
      (_: Any) => throw new Exception("Inserting into ? projection not supported.")
    )

    /** Database column bookid SqlType(int4), PrimaryKey */
    val bookid: Rep[Int] = column[Int]("bookid", O.PrimaryKey)

    /** Database column facid SqlType(int4) */
    val facid: Rep[Int] = column[Int]("facid")

    /** Database column memid SqlType(int4) */
    val memid: Rep[Int] = column[Int]("memid")

    /** Database column starttime SqlType(timestamp) */
    val starttime: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("starttime")

    /** Database column slots SqlType(int4) */
    val slots: Rep[Int] = column[Int]("slots")

    /** Foreign key referencing Facilities (database name fk_bookings_facid) */
    lazy val facilitiesFk = foreignKey("fk_bookings_facid", facid, Facilities)(
      r => r.facid,
      onUpdate = ForeignKeyAction.NoAction,
      onDelete = ForeignKeyAction.NoAction
    )

    /** Foreign key referencing Members (database name fk_bookings_memid) */
    lazy val membersFk = foreignKey("fk_bookings_memid", memid, Members)(
      r => r.memid,
      onUpdate = ForeignKeyAction.NoAction,
      onDelete = ForeignKeyAction.NoAction
    )

    /** Index over (facid,memid) (database name bookings.facid_memid) */
    val index1 = index("bookings.facid_memid", (facid, memid))

    /** Index over (facid,starttime) (database name bookings.facid_starttime) */
    val index2 = index("bookings.facid_starttime", (facid, starttime))

    /** Index over (memid,facid) (database name bookings.memid_facid) */
    val index3 = index("bookings.memid_facid", (memid, facid))

    /** Index over (memid,starttime) (database name bookings.memid_starttime) */
    val index4 = index("bookings.memid_starttime", (memid, starttime))

    /** Index over (starttime) (database name bookings.starttime) */
    val index5 = index("bookings.starttime", starttime)
  }

  /** Collection-like TableQuery object for table Bookings */
  lazy val Bookings = new TableQuery(tag => new Bookings(tag))

  /** Entity class storing rows of table Facilities
    * @param facid
    *   Database column facid SqlType(int4), PrimaryKey
    * @param name
    *   Database column name SqlType(varchar), Length(100,true)
    * @param membercost
    *   Database column membercost SqlType(numeric)
    * @param guestcost
    *   Database column guestcost SqlType(numeric)
    * @param initialoutlay
    *   Database column initialoutlay SqlType(numeric)
    * @param monthlymaintenance
    *   Database column monthlymaintenance SqlType(numeric)
    */
  case class FacilitiesRow(
      facid: Int,
      name: String,
      membercost: scala.math.BigDecimal,
      guestcost: scala.math.BigDecimal,
      initialoutlay: scala.math.BigDecimal,
      monthlymaintenance: scala.math.BigDecimal
  )

  /** GetResult implicit for fetching FacilitiesRow objects using plain SQL queries */
  implicit def GetResultFacilitiesRow(implicit
      e0: GR[Int],
      e1: GR[String],
      e2: GR[scala.math.BigDecimal]
  ): GR[FacilitiesRow] = GR { prs =>
    import prs._
    FacilitiesRow.tupled(
      (
        <<[Int],
        <<[String],
        <<[scala.math.BigDecimal],
        <<[scala.math.BigDecimal],
        <<[scala.math.BigDecimal],
        <<[scala.math.BigDecimal]
      )
    )
  }

  /** Table description of table facilities. Objects of this class serve as prototypes for rows in queries. */
  class Facilities(_tableTag: Tag) extends profile.api.Table[FacilitiesRow](_tableTag, Some("cd"), "facilities") {
    def * = (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance) <> (
      FacilitiesRow.tupled,
      FacilitiesRow.unapply
    )

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (
      Rep.Some(facid),
      Rep.Some(name),
      Rep.Some(membercost),
      Rep.Some(guestcost),
      Rep.Some(initialoutlay),
      Rep.Some(monthlymaintenance)
    ).shaped.<>(
      { r => import r._; _1.map(_ => FacilitiesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get))) },
      (_: Any) => throw new Exception("Inserting into ? projection not supported.")
    )

    /** Database column facid SqlType(int4), PrimaryKey */
    val facid: Rep[Int] = column[Int]("facid", O.PrimaryKey)

    /** Database column name SqlType(varchar), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100, varying = true))

    /** Database column membercost SqlType(numeric) */
    val membercost: Rep[scala.math.BigDecimal] = column[scala.math.BigDecimal]("membercost")

    /** Database column guestcost SqlType(numeric) */
    val guestcost: Rep[scala.math.BigDecimal] = column[scala.math.BigDecimal]("guestcost")

    /** Database column initialoutlay SqlType(numeric) */
    val initialoutlay: Rep[scala.math.BigDecimal] = column[scala.math.BigDecimal]("initialoutlay")

    /** Database column monthlymaintenance SqlType(numeric) */
    val monthlymaintenance: Rep[scala.math.BigDecimal] = column[scala.math.BigDecimal]("monthlymaintenance")
  }

  /** Collection-like TableQuery object for table Facilities */
  lazy val Facilities = new TableQuery(tag => new Facilities(tag))

  /** Entity class storing rows of table Members
    * @param memid
    *   Database column memid SqlType(int4), PrimaryKey
    * @param surname
    *   Database column surname SqlType(varchar), Length(200,true)
    * @param firstname
    *   Database column firstname SqlType(varchar), Length(200,true)
    * @param address
    *   Database column address SqlType(varchar), Length(300,true)
    * @param zipcode
    *   Database column zipcode SqlType(int4)
    * @param telephone
    *   Database column telephone SqlType(varchar), Length(20,true)
    * @param recommendedby
    *   Database column recommendedby SqlType(int4), Default(None)
    * @param joindate
    *   Database column joindate SqlType(timestamp)
    */
  case class MembersRow(
      memid: Int,
      surname: String,
      firstname: String,
      address: String,
      zipcode: Int,
      telephone: String,
      recommendedby: Option[Int] = None,
      joindate: java.sql.Timestamp
  )

  /** GetResult implicit for fetching MembersRow objects using plain SQL queries */
  implicit def GetResultMembersRow(implicit
      e0: GR[Int],
      e1: GR[String],
      e2: GR[Option[Int]],
      e3: GR[java.sql.Timestamp]
  ): GR[MembersRow] = GR { prs =>
    import prs._
    MembersRow.tupled(
      (<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[String], <<?[Int], <<[java.sql.Timestamp])
    )
  }

  /** Table description of table members. Objects of this class serve as prototypes for rows in queries. */
  class Members(_tableTag: Tag) extends profile.api.Table[MembersRow](_tableTag, Some("cd"), "members") {
    def * = (memid, surname, firstname, address, zipcode, telephone, recommendedby, joindate) <> (
      MembersRow.tupled,
      MembersRow.unapply
    )

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (
      Rep.Some(memid),
      Rep.Some(surname),
      Rep.Some(firstname),
      Rep.Some(address),
      Rep.Some(zipcode),
      Rep.Some(telephone),
      recommendedby,
      Rep.Some(joindate)
    ).shaped.<>(
      { r => import r._; _1.map(_ => MembersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8.get))) },
      (_: Any) => throw new Exception("Inserting into ? projection not supported.")
    )

    /** Database column memid SqlType(int4), PrimaryKey */
    val memid: Rep[Int] = column[Int]("memid", O.PrimaryKey)

    /** Database column surname SqlType(varchar), Length(200,true) */
    val surname: Rep[String] = column[String]("surname", O.Length(200, varying = true))

    /** Database column firstname SqlType(varchar), Length(200,true) */
    val firstname: Rep[String] = column[String]("firstname", O.Length(200, varying = true))

    /** Database column address SqlType(varchar), Length(300,true) */
    val address: Rep[String] = column[String]("address", O.Length(300, varying = true))

    /** Database column zipcode SqlType(int4) */
    val zipcode: Rep[Int] = column[Int]("zipcode")

    /** Database column telephone SqlType(varchar), Length(20,true) */
    val telephone: Rep[String] = column[String]("telephone", O.Length(20, varying = true))

    /** Database column recommendedby SqlType(int4), Default(None) */
    val recommendedby: Rep[Option[Int]] = column[Option[Int]]("recommendedby", O.Default(None))

    /** Database column joindate SqlType(timestamp) */
    val joindate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("joindate")

    /** Foreign key referencing Members (database name fk_members_recommendedby) */
    lazy val membersFk = foreignKey("fk_members_recommendedby", recommendedby, Members)(
      r => Rep.Some(r.memid),
      onUpdate = ForeignKeyAction.NoAction,
      onDelete = ForeignKeyAction.SetNull
    )

    /** Index over (joindate) (database name members.joindate) */
    val index1 = index("members.joindate", joindate)
  }

  /** Collection-like TableQuery object for table Members */
  lazy val Members = new TableQuery(tag => new Members(tag))
}
