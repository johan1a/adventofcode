package scala.test

import org.scalatest
import org.scalatest.FunSuite

import scala.encoder.Encoder
import scala.io.Source
import scala.main.Runner
import scala.parser.ParseResult

/**
  * Created by Johan on 2015-12-12.
  */
class EncoderSpec extends FunSuite {

  val runner = new Runner()

  def runTestFile(file: String): ParseResult = {
    runner.runFile(new Encoder(), Source.fromURL(getClass.getResource("/" + file)))
  }


  test("encode 1") {
    testString("\"\"", 6)
  }

  test("encode 2") {
    testString("\"abc\"", 9)
  }

  test("encode 3") {
    testString("\"aaa\\\"aaa\"", 16)
  }

  test("encode 4") {
    testString("\"\\x27\"", 11)
  }

  test("test file") {
    testFile("input", 6489, 8606)
  }

  def testFile(fileName: String, expectedCode: Int, expectedMemory: Int): Unit = {
    val result = runTestFile(fileName)
    val memoryCharCount: Int = result.memoryCharCount
    val codeCharCount: Int = result.codeCharCount
    assert(codeCharCount == expectedCode)
    assert(memoryCharCount == expectedMemory)
  }

  def testString(input: String, expectedMemchars: Int): scalatest.Assertion = {
    val encoder = new Encoder
    val result = encoder.encode(input)
    println(result.processed)
    assert(result.memoryCharCount == expectedMemchars)
  }
}
