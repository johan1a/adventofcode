package scala.main

import scala.io.Source
import scala.parser.{ParseResult, Parser}

/**
  * Created by Johan on 2015-12-11.
  */
class Runner {

  def sumResults(list: List[ParseResult]): ParseResult = list match {
    case List(a: ParseResult) => a
    case _ => list.head + sumResults(list.tail)
  }

  def runFile(parser: Parser, file: Source): ParseResult = {
    val results: List[ParseResult] = file.getLines.map(parser.parse).toList
    val result: ParseResult = sumResults(results)
    result
  }

}