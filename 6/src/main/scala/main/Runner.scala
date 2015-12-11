package main

import lights.Lights
import util.Constants

import scala.collection.immutable.List
import scala.io.Source
import Constants._

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

  def interpret(line: String, lights: Lights) = {
    for (prefix <- prefixes) {
      if (line.startsWith(prefix)) {
        val ranges = line.replace(prefix, "").split("through").map(_.trim)
        val (begin, end) = parseRanges(ranges(0), ranges(1))
        lights.performAction(prefix, begin, end)
      }
    }
  }

  def runFile(lights: Lights, file: Source): Int = {
    for (line <- file getLines) {
      println(line)
      interpret(line, lights)
    }
    lights.brightness
  }

  def runString(lights: Lights, input: String): Int = {
    interpret(input, lights)
    lights.brightness
  }

  def test(lights: Lights, input: List[String]) = {
    for (line <- input) {
      println(line)
      interpret(line, lights)
    }
    val count = lights.brightness
    println(s"Nbr lights on: $count\n")
  }
}