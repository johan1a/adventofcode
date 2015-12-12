package test

import org.scalatest

import main.Runner
import org.scalatest._

import scala.io.Source
import scala.parser.{ParseResult, Parser}

/**
  * created by johan on 2015-12-11.
  */
class ParserSpec extends FunSuite {

  val runner = new Runner()

  def runTestFile(file: String): ParseResult = {
    runner.runFile(new Parser(), Source.fromURL(getClass.getResource("/" + file)))
  }


  test("Test file 1") {
    testFile("test1", 16, 7)
  }

  test("Test file 2") {
    testFile("test2", 10, 7)
  }

  test("Test file 3") {
    testFile("test3", 6, 1)
  }
/*
  test("Test final ") {
    val result = runTestFile("input")
    val codeCharCount: Int = result.codeCharCount
    val memoryCharCount: Int = result.memoryCharCount
    assert(codeCharCount == 6489)
    assert(memoryCharCount == 45150)
    assert(codeCharCount - memoryCharCount == 2)
  }
*/
  test("Test empty ") {
    testString("\"\"", 2, 0)
  }

  test("Test single char ") {
    testString("\"a\"", 3, 1)
  }

  test("Test hex char ") {
    testString("\"\\x42\"", 6, 1)
  }

  test("Test slash") {
    testString("\"\\\\\"", 4, 1)
  }

  def testFile(fileName: String, expectedCode: Int, expectedMemory: Int): Unit = {
    val result = runTestFile(fileName)
    val codeCharCount: Int = result.codeCharCount
    val memoryCharCount: Int = result.memoryCharCount
    assert(codeCharCount == expectedCode)
    assert(memoryCharCount == expectedMemory)
  }

  def testString(input: String, expectedCode: Int, expectedMemory: Int): scalatest.Assertion = {
    val parser = new Parser
    val result = parser.parse(input)
    assert(result.codeCharCount == expectedCode)
    assert(result.memoryCharCount == expectedMemory)
  }
}
