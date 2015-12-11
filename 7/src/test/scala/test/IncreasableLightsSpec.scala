package test

import main.Runner
import org.scalatest._

import scala.io.Source
import scala.parser.Language.{Num, Id}
import scala.parser.{Parser, Memory}

/**
  * created by johan on 2015-12-11.
  */
class IncreasableLightsSpec extends FunSuite {

  val runner = new Runner()

  def runTestFile(file: String): Memory = {
    runner.runFile(new Parser(), Source.fromURL(getClass.getResource(file)))
  }

  test("store Int"){
    val memory = new Memory
    memory.put(Id("x"), Num(123))
  }

  test("x should be 123") {
    val memory: Memory = runTestFile("/test1")
    assert(memory.get("d") == 72)
    assert(memory.get("e") == 507)
    assert(memory.get("f") == 492)
    assert(memory.get("g") == 114)
    assert(memory.get("h") == 65412)
    assert(memory.get("i") == 65079)
    assert(memory.get("x") == 123)
    assert(memory.get("y") == 456)
  }


}
