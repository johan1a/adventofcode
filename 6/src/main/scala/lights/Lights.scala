package lights

import util.Constants
import Constants._

/**
  * Created by Johan on 2015-12-11.
  */
abstract class Lights {
  def performAction(action: String, begin: (Int, Int), end: (Int, Int)) = action match {
    case `onPrefix` => turnOn(begin, end)
    case `offPrefix` => turnOff(begin, end)
    case `togglePrefix` => toggle(begin, end)
  }

  def iterate(begin: (Int, Int), end: (Int, Int), func: ((Int, Int) => Unit)) = {
    for (x <- begin._1 to end._1) {
      for (y <- begin._2 to end._2) {
        func(x, y)
      }
    }
  }

  def toggleFunc(x: Int, y: Int): Unit
  def turnOnFunc(x: Int, y: Int): Unit
  def turnOffFunc(x: Int, y: Int): Unit

  def toggle(begin: (Int, Int), end: (Int, Int)) = {
    iterate(begin, end, (x: Int, y: Int) => toggleFunc(x, y))
  }

  def turnOn(begin: (Int, Int), end: (Int, Int)) = {
    iterate(begin, end, (x: Int, y: Int) => turnOnFunc(x, y))
  }

  def turnOff(begin: (Int, Int), end: (Int, Int)) = {
    iterate(begin, end, (x: Int, y: Int) => turnOffFunc(x, y))
  }

  def brightness : Int

}
