package test

import _root_.adventofcode.RouteCalculator
import org.scalatest.FunSuite

import scala.io.Source


class TestRouteCalculator extends FunSuite {

  def testFile(file: String, expected: Int): Any = {
    val calc = new RouteCalculator
    calc.calculateShortest(Source.fromURL(getClass.getResource(file)))
  }

  test("Final test") {
    testFile("input", 1)
  }
}

