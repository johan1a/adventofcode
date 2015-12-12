package scala.parser

import java.text.ParseException


/**
  * Created by Johan on 2015-12-11.
  */
class Parser {

  var memoryChars = new scala.collection.mutable.ListBuffer[String]()

  def acceptChar(char: Char, input: StringBuilder) = {
    accept(char, input)
    memoryChars += char.toString
  }

  def accept(char: Char, input: StringBuilder) = {
    if (input.head == char) {
      input.delete(0, 1)
    } else {
      throw new RuntimeException
    }
  }

  def acceptHex(input: StringBuilder) = {
    input.delete(0, 1)
  }

  def parseAscii(input: StringBuilder) = {
    memoryChars += input take 3 toString()
    accept('x', input)
    acceptHex(input)
    acceptHex(input)
  }

  def parseEscaped(input: StringBuilder) = {
    accept('\\', input)
    input.head match {
      case '\\' =>
        accept('\\', input)
        memoryChars += "\\"
      case '\"' =>
        accept('\"', input)
        memoryChars += "\""
      case 'x' => parseAscii(input)
    }
  }

  def parseStringLiteral(input: StringBuilder) = {
    accept('\"', input)
    while (input.head != '\"') {
      input.head match {
        case '\\' => parseEscaped(input)
        case c: Char => acceptChar(c, input)
      }
    }
    accept('\"', input)
  }

  def parse(input: String): ParseResult = {
    memoryChars = new scala.collection.mutable.ListBuffer[String]()
    parseStringLiteral(new StringBuilder(input filter (_ >= ' ')))
    ParseResult(memoryChars.toList, input.length)
  }
}

case class ParseResult(memChars : List[String], codeCharCount: Int) {
  def memoryCharCount = memChars.length

  def +(that: ParseResult): ParseResult =
    ParseResult(this.memChars ++ that.memChars, this.codeCharCount + that.codeCharCount)
}
