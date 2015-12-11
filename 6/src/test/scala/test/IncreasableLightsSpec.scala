package test

import lights.{Lights, IncreasableLights}
import main.Runner
import org.scalatest._

import scala.io.Source

/**
  * created by johan on 2015-12-11.
  */
class IncreasableLightsSpec extends FunSuite {
  val testPath = "resources/"

  val runner = new Runner()

  def runTestFile(lights: Lights, file: String): Int = {
    runner.runFile(lights, Source.fromURL(getClass.getResource(file)))
  }

  test("Brightness should be 1") {
    val lights = new IncreasableLights()
    assert(runTestFile(lights, "/test1.input") == 1)
  }

  test("Should be 2 000 000") {
    assert(runner.runString(new IncreasableLights(), "toggle 0,0 through 999,999") == 2000000)
  }

  test("Should be 2 000 001") {
    val input = List("toggle 0,0 through 999,999", "turn on 0,0 through 0,0")
    assert(runner.runList(new IncreasableLights(), input) == 2000001)
  }

  test("Turn on1") {
    val input = List("turn on 0,0 through 999,999")
    assert(runner.runList(new IncreasableLights(), input) == 1000000)
  }

  test("Turn on -> off") {
    val input = List("turn on 0,0 through 999,999", "turn off 0,0 through 999,999")
    assert(runner.runList(new IncreasableLights(), input) == 0)
  }

  test("15 square") {
    val input = List("turn on 1,4 through 5,6")
    assert(runner.runList(new IncreasableLights(), input) == 15)
  }

  test("toggle 15") {
    val input = List("toggle 1,4 through 5,6")
    assert(runner.runList(new IncreasableLights(), input) == 30)
  }
  test("1000 square") {
    val input = List("turn on 10,20 through 109,29")
    assert(runner.runList(new IncreasableLights(), input) == 1000)
  }

  test("Add twice") {
    val input = List("turn on 10,20 through 109,29", "turn on 10,20 through 109,29")
    assert(runner.runList(new IncreasableLights(), input) == 2000)
  }

  test("One line") {
    val input = List("turn on 887,9 through 959,629")
    assert(runner.runList(new IncreasableLights(), input) == 45333)
  }

  test("Two lines") {
    val lights = new IncreasableLights()
    assert(runTestFile(lights, "/lines2.input") == 65274) //45333 + 19941
  }

  test("Two line sub"){
    val lights = new IncreasableLights()
    assert(runTestFile(lights, "/lines2sub.input") == 25392) //45333 - 19941
  }

  test("real input") {
    val lights = new IncreasableLights()
    assert(runTestFile(lights, "/day6.input") == 1)
  }

}
