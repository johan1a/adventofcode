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

  test("Should be 2000") {
    assert(runner.runString(new IncreasableLights(), "toggle 0,0 through 999,999") == 2000000)
  }


}
