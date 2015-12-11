package scala.main


import scala.io.Source
import scala.parser.{Parser, Memory}

/**
  * Created by Johan on 2015-12-11.
  */
class Runner {

  def runFile(parser: Parser, file: Source): Memory = {
    for (line <- file getLines) {
      parser.parse(line)
    }
    parser.memory
  }

}