package scala.test

import java.net.URL

import org.scalatest.FunSuite

import scala.adventofcode.RouteCalculator
import scala.io.{BufferedSource, Source}


class TestRouteCalculatorSpec extends FunSuite {

  def testFile(file: String, expected: Int): Any = {
    val calc = new RouteCalculator
    val shortest: Int = calc.calculateShortest(Source.fromURL(getClass.getResource("/" + file)))
    assert(shortest == expected)
  }

  test("Final scala.test") {
    testFile("input", 1)
  }
}

