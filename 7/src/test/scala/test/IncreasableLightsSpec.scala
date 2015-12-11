package test

import main.Runner
import org.scalatest._

import scala.io.Source
import scala.parser.{Parser, Memory}

/**
  * created by johan on 2015-12-11.
  */
class IncreasableLightsSpec extends FunSuite {

  val runner = new Runner()

  def runTestFile(file: String): Memory = {
    runner.runFile(new Parser(), Source.fromURL(getClass.getResource(file)))
  }

  test("x should be 123") {
    val memory: Memory = runTestFile("/test1")
    assert(memory.get("x")== 123)
  }


}
