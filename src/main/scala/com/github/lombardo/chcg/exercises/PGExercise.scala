package com.github.lombardo.chcg.exercises

import com.github.lombardo.chcg.database.Tables

import java.util.concurrent.TimeUnit.SECONDS

import scala.concurrent.duration.Duration

trait PGExercise {
  val members = Tables.Members
  val bookings = Tables.Bookings
  val facilities = Tables.Facilities
  val defaultDuration = Duration(10, SECONDS)
}
