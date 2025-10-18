package com.github.lombardo.chcg

import com.github.lombardo.chcg.database.Connection.db
import slick.dbio.{DBIOAction, NoStream}

import java.util.concurrent.TimeUnit.SECONDS
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object TestHelper {
  val defaultDuration = Duration(10, SECONDS)

  def executeQuery[R](dbAction: DBIOAction[R, NoStream, Nothing]) = {
    Await.result(db.run(dbAction), defaultDuration)
  }
}
