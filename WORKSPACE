@StaticDatabaseConfig("file:/Users/lombardo/Desktop/dev_projects/slick-exercises/src/test/resources/application.conf#tsql")
object TestClass {
  import TestUtils._
  def ex = {
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
    executeQuery(solutionQuery)
  }
}

TestClass.ex

