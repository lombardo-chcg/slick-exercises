package com.github.lombardo.chcg

import java.util.concurrent.TimeUnit.SECONDS

import com.github.lombardo.chcg.database.Connection.db
import slick.dbio.{DBIOAction, NoStream}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object TestHelper {
  val defaultDuration = Duration(10, SECONDS)

  def executeQuery[R](dbAction: DBIOAction[R, NoStream, Nothing]) = {
    Await.result(db.run(dbAction), defaultDuration)
  }
}
