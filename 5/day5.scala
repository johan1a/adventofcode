import scala.io._

val vowels = Set("a", "e", "i", "o", "u")
val forbidden = Set("ab", "cd", "pq", "xy")

def containsForbiddenToken(string : String) : Boolean = {
    forbidden.map ((t) => string.contains(t)) contains(true)
}
def containsThreeVowels(string : String) = {
    var sum = 0
    for(vowel <- vowels){
        sum += countSubstring(string, vowel)
    }
    sum >= 3
}

def containsRepeated(string : String ) : Boolean= {
    if ( string.length() == 1 ) false
    else if(string.charAt(0) == string.charAt(1)) {
        true
    }else{
        containsRepeated(string substring(1))
    }
}

def containsSpacedPair(string : String) : Boolean = {
    if(string.length() >= 3){
        if(string.charAt(0) == string.charAt(2)){
            true
        }else{
            containsSpacedPair(string.drop(1))
        }
    }else{
        false
    }
}

def containsDoublePair(string : String ) : Boolean = {
    if(string.length() >= 4){
        val token = string.take(2)
        val tail = string.drop(2)
        if(tail.contains(token)){
            true 
        }else{
            containsDoublePair(string.drop(1))
        }
    }else{
        false
    }
}

def isNice(string : String) : Boolean = {
    !containsForbiddenToken(string) &&
        containsRepeated(string) &&
        containsThreeVowels(string)
}

def isNice2(string : String) : Boolean = {
    containsDoublePair(string) && containsSpacedPair(string)
}


def countSubstring( str:String, substr:String ) = substr.r.findAllMatchIn(str).length


def checkStrings(strings :List[String], func : String => Boolean) = {
    for(string <- strings){
        if(func(string)){
            println(s"$string is nice.")
        }else{
            println(s"$string is naughty!")
        }
    }
}

def checkFile(func : String => Boolean) = {
    var nbrNice = 0
    for (line <- Source.fromFile("day5.input") getLines) {
        if (func(line)){
            nbrNice += 1
        }
    }
    println(s"There are $nbrNice nice strings.")
}

val strings = List("ugknbfddgicrmopn", "aaa", "jchzalrnumimnmhp", "haegwjzuvuyypxyu", "dvszwmarrgswjxmb")
val strings2 = List("qjhvhtzxzqqjkmpb", "xxyxx", "uurcxstgmygtbstg", "ieodomkazucvgmuy")

checkStrings(strings, isNice)
checkFile(isNice)

println("")

checkStrings(strings2, isNice2)
checkFile(isNice2)

println("")

checkStrings(strings2, containsSpacedPair)