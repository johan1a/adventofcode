package test

import main.Runner
import org.scalatest._

import scala.io.Source
import scala.parser.Language.{Not, Num, Id}
import scala.parser.{Parser, Memory}

/**
  * created by johan on 2015-12-11.
  */
class IncreasableLightsSpec extends FunSuite {

  val runner = new Runner()

  def runTestFile(file: String): Memory = {
    runner.runFile(new Parser(), Source.fromURL(getClass.getResource("/" +file)))
  }

  test("store Int") {
    val memory = new Memory
    memory.put(Id("x"), Num(123))
    assert(memory.eval("x") == 123)
  }

  test("and") {
    val memory = runTestFile("and")
    assert(memory.eval("x") == 0)
  }

  test("not") {
    val memory = runTestFile("not")
    assert(memory.eval("x") == 65534)
  }

  test("x should be 123") {
    val memory: Memory = runTestFile("test1")
    assert(memory.eval("d") == 72)
    assert(memory.eval("e") == 507)
    assert(memory.eval("f") == 492)
    assert(memory.eval("g") == 114)
    assert(memory.eval("h") == 65412)
    assert(memory.eval("i") == 65079)
    assert(memory.eval("x") == 123)
    assert(memory.eval("y") == 456)
  }
  test("day7"){
    val memory: Memory = runTestFile("day7.input")
    assert(memory.eval("a") == 16076)
  }

  test("day7b"){
    val memory: Memory = runTestFile("day7b.input")
    assert(memory.eval("a") == 2797)
  }
}
