
import lights.{ToggleableLights, IncreasableLights, Lights}
import main.Constants
import main.Constants._

import scala.io._

object Day6 {

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

  def runFile(lights: Lights) = {
    for (line <- Source.fromFile("day6.input") getLines) {
      println(line)
      interpret(line, lights)
    }
    val count = lights.count
    println(s"Nbr lights on: $count\n")
  }

  def test(lights: Lights, input: List[String]) = {
    for (line <- input) {
      println(line)
      interpret(line, lights)
    }
    val count = lights.count
    println(s"Nbr lights on: $count\n")
  }

  def part1 = {
    runFile(new ToggleableLights())
  }

  def part2 = {
    runFile(new IncreasableLights())
  }

  def test2 = {
    val input = List("turn on 0,0 through 0,0", "toggle 0,0 through 999,999")
    test(new IncreasableLights(), input)
  }

}


