package main

import parser.Parser

import scala.collection.immutable.List
import scala.io.Source

/**
  * Created by Johan on 2015-12-11.
  */
class Runner {

  def parseRange(rangeString: String): (Int, Int) = {
    val values = rangeString.split(",").map(_.toInt)
    (values(0), values(1))
  }

  def parseRanges(begin: String, end: String): ((Int, Int), (Int, Int)) = {
    (parseRange(begin), parseRange(end))
  }

  def interpret(line: String, parser : Parser) = {
    for (prefix <- prefixes) {
      if (line.startsWith(prefix)) {
        val ranges = line.replace(prefix, "").split("through").map(_.trim)
        val (begin, end) = parseRanges(ranges(0), ranges(1))
        lights.performAction(prefix, begin, end)
      }
    }
  }

  def runFile(parser: Parser, file: Source): Int = {
    for (line <- file getLines) {
      parser.parse(line)
    }
    parser.get("a")
  }

}