package scala.encoder

import scala.parser.ParseResult

/**
  * Created by Johan on 2015-12-12.
  */
class Encoder {
  var codedString = new scala.collection.mutable.ListBuffer[String]()

  def encode_(input: String): Unit = input.length() match {
    case 0 =>
    case _ =>
      input.head match {
        case '\\' =>
          push("\\")
          push("\\")
        case '\"' =>
          push("\\")
          push("\"")
        case c: Char => push(c.toString)
      }
      encode_(input.drop(1))
  }

  def encode(input: String): ParseResult = {
    codedString = new scala.collection.mutable.ListBuffer[String]()
    push("\"")
    encode_(input)
    push("\"")
    new ParseResult(input, codedString.toList)
  }

  def push(s: String): Unit = {
    codedString += s
  }
}
