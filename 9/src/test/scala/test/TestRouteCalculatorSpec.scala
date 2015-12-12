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

  test("test inputD") {
    testFile("inputD", 141)
  }

  test("test inputA") {
    testFile("inputA", 117)
  }
  test("test single1") {
    testFile("test1", 129)
  }

  test("test 2") {
    testFile("test2", 151)
  }

  test("test 3") {
    testFile("test3", 605)
  }

  test("Final test") {
    testFile("input", 207)
  }
}

