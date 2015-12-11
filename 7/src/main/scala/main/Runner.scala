package main

import parser.Parser

import scala.collection.immutable.List
import scala.io.Source

/**
  * Created by Johan on 2015-12-11.
  */
class Runner {


  def runFile(parser: Parser, file: Source): Int = {
    for (line <- file getLines) {
      parser.parse(line)
    }
    parser.get("a")
  }

}