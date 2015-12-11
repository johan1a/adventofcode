package test

import _root_.parser.Parser
import main.Runner
import org.scalatest._

import scala.io.Source

/**
  * created by johan on 2015-12-11.
  */
class IncreasableLightsSpec extends FunSuite {

  val runner = new Runner()

  def runTestFile(file: String): Int = {
    runner.runFile(new Parser(), Source.fromURL(getClass.getResource(file)))
  }

  test("Brightness should be 1") {
    assert(runTestFile("/test1.input") == 1)
  }


}
