
import scala.io._

object Day6 {

    val onPrefix : String = "turn on "
    val offPrefix : String  = "turn off "
    val togglePrefix : String  = "toggle "

    val prefixes = List(onPrefix, offPrefix, togglePrefix)

    class Lights() {
        val lights = init

        def init = {
            val lights = new collection.mutable.HashMap[(Int, Int), Boolean]()
            for(x <- 0 to 999){
                for(y <- 0 to 999){
                  lights.put((x, y), false)
                }
            }
            lights
        }

        def interpret(action : String, begin : (Int, Int), end : (Int, Int)) = action match {
            case `onPrefix` => turnOn(begin, end)
            case `offPrefix` => turnOff(begin, end)
            case `togglePrefix` => toggle(begin, end)
        }

        def toggle(begin : (Int, Int), end : (Int, Int)) = {
            iterate(begin, end, (x : Int, y : Int) => toggleFunc(x, y))
        }

        def turnOn(begin : (Int, Int), end : (Int, Int)) = {
            iterate(begin, end, (x : Int, y : Int) => turnOnFunc(x, y))
        }

        def turnOff(begin : (Int, Int), end : (Int, Int)) = {
            iterate(begin, end, (x : Int, y : Int) => turnOffFunc(x, y))
        }

        def toggleFunc(x : Int, y : Int) : Unit  = {
            lights.put((x, y), !lights(x, y))
        }

        def turnOnFunc(x : Int, y : Int) : Unit  = {
            lights.put((x,y), true)
        }

        def turnOffFunc(x : Int, y : Int) : Unit = {
            lights.put((x,y), false)
        }

        def iterate(begin : (Int, Int), end : (Int, Int), func : ((Int, Int) => Unit)) = {
            for(x <- begin._1 to end._1){
                for(y <- begin._2 to end._2){
                  func(x, y)
                }
            }
        }    

        def count = {
            lights.filter((a) => a._2).size
        }
    }


    class IncreasableLights() extends Lights(){
        val myLights = {
            val lights_ = new collection.mutable.HashMap[(Int, Int), Int]()
            for(x <- 0 to 999){
                for(y <- 0 to 999){
                  lights_.put((x, y), 0)
                }
            }
            lights_
        }

        override def toggleFunc(x : Int, y : Int) : Unit  = {
            myLights.put((x, y), myLights(x, y) + 2)
        }

        override def turnOnFunc(x : Int, y : Int) : Unit  = {
            myLights.put((x,y), myLights(x, y) + 1)
        }

        override def turnOffFunc(x : Int, y : Int) : Unit = {
            myLights.put((x,y), myLights(x, y) - 1)
        }

        override def count = {
            myLights.map((a) => a._2).sum
        }
    }

    def parseRange(rangeString : String) : (Int, Int) = {
        val values = rangeString.split(",").map(_.toInt)
        (values(0), values(1))
    }

    def parseRanges(begin : String, end : String) : ((Int, Int), (Int, Int)) = {
        (parseRange(begin), parseRange(end))
    }

    def interpret(line : String, lights : Lights) = {
        for(prefix <- prefixes){
            if(line.startsWith(prefix)){
                val ranges = line.replace(prefix, "").split("through").map(_.trim)
                val (begin, end) = parseRanges(ranges(0), ranges(1))
                lights.interpret(prefix, begin, end)
            }
        }
    }

    def calculate(lights : Lights) = {
        for (line <- Source.fromFile("day6.input") getLines) {
            println(line)
            interpret(line, lights)
        }
        val count = lights.count
        println(s"Nbr lights on: $count\n")
    }

    def part1 = {
        val lights = new Lights()
        lights.init
        calculate(lights)
    }

    def part2 = {
        val lights = new IncreasableLights()
        lights.init
        calculate(lights)
    }

}


Day6.part2