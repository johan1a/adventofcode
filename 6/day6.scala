
import scala.io._


var lights = new collection.mutable.HashMap[(Int, Int), Boolean]()

     
def iterate(begin : (Int, Int), end : (Int, Int), func : ((Int, Int , collection.mutable.HashMap[(Int, Int), Boolean]) => Unit)) = {
    for(x <- begin._1 to end._1){
        for(y <- begin._2 to end._2){
          func(x, y, lights)
        }
    }
}    

def toggle(begin : (Int, Int), end : (Int, Int)) = {
    iterate(begin, end, (x, y, light) => lights.put((x, y), !lights(x, y)))
}

def turnOn(begin : (Int, Int), end : (Int, Int)) = {
    iterate(begin, end, (x, y, light) => (lights.put((x,y), true)))
}

def turnOff(begin : (Int, Int), end : (Int, Int)) = {
    iterate(begin, end, (x, y, light) => (lights.put((x,y), false)))
}

def parseRange(rangeString : String) : (Int, Int) = {
    val values = rangeString.split(",").map(_.toInt)
    (values(0), values(1))
}

def parseRanges(begin : String, end : String) : ((Int, Int), (Int, Int)) = {
    (parseRange(begin), parseRange(end))
}

val onPrefix : String = "turn on "
val offPrefix : String  = "turn off "
val togglePrefix : String  = "toggle "

val prefixes = List(onPrefix, offPrefix, togglePrefix)

def interact(action : String, begin : (Int, Int), end : (Int, Int)) = action match {
    case `onPrefix` => turnOn(begin, end)
    case `offPrefix` => turnOff(begin, end)
    case `togglePrefix` => toggle(begin, end)
}

def interpret(line : String) = {
    for(prefix <- prefixes){
        if(line.startsWith(prefix)){
            val ranges = line.replace(prefix, "").split("through").map(_.trim)
            val (begin, end) = parseRanges(ranges(0), ranges(1))
            interact(prefix, begin, end)
        }
    }
}



def resetLights = {
    lights = new collection.mutable.HashMap[(Int, Int), Boolean]()
    for(x <- 0 to 999){
        for(y <- 0 to 999){
          lights.put((x, y), false)
        }
    }
}

def printCount = {
    val count = lights.filter((a) => a._2).size
    println(s"Nbr lights on: $count\n")
}

def part1 = {
    resetLights
    for (line <- Source.fromFile("day6.input") getLines) {
        println(line)
        interpret(line)
    }
    printCount
}

val lines  = List("turn on 0,0 through 999,999", "toggle 0,0 through 999,0", "turn off 499,499 through 500,500")

def test = {
    resetLights
    for (line <- lines) {
        println(line)
        interpret(line)
    
    }
    printCount
}


part1