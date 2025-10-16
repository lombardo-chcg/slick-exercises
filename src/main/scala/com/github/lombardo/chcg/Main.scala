package com.github.lombardo.chcg

import org.apache.logging.log4j.LogManager

object Main {
  val logger = LogManager.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("Welcome to the Slick Gym")
  }
}