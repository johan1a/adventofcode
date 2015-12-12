package adventofcode

import scala.collection.mutable
import scala.io.Source

/**
  * Created by Johan on 2015-12-12.
  */
class RouteCalculator {

  val distances = new mutable.HashMap[String, mutable.HashMap[String, Int]]

  def places = distances.keys

  def addDistance(from: String, to: String, distance: Int) = {
    if (!distances.contains(from)) {
      distances(from) = new mutable.HashMap()
    }
    distances(from)(to) = distance
  }

  def parseRoute(s: String) = {
    val splitted = s split "="
    val lhs = splitted(0) split " to "
    val from = lhs(0).trim
    val to = lhs(1).trim
    val distance = splitted(1).trim.toInt
    addDistance(from, to, distance)
    addDistance(to, from, distance)
  }

  def parseInput(file: Source) = {
    file.getLines().foreach(parseRoute)
  }

  def calculateDistance(places: List[String]) : Int = places match  {
    case List(place) => 0
    case ss =>
      val first = ss.head
      val second = ss(1)
      val distance = distances(first)(second)
      distance + calculateDistance(places.drop(1))
  }

  def calculateBruteForce() = {
    val places = distances.keys.toList
    val permutations = places.permutations
    permutations.map(calculateDistance).min
  }

  def calculateShortest(file: Source): Int = {
    parseInput(file)
    calculateBruteForce()
  }

}
