package com.github.lombardo.chcg

import org.scalatest.{FlatSpec, Matchers}

class SampleTest extends FlatSpec with Matchers {
  it should "run a sample test" in {
    7 + 9 should be(16)
  }
}
