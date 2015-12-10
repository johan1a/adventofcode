import scala.io._





val vowels = Set("a", "e", "i", "o", "u")
val forbidden = Set("ab", "cd", "pq", "xy")

def isNice(string : String) : Boolean = {
    !containsForbiddenToken(string) &&
        containsRepeated(string) &&
        containsThreeVowels(string)
}

def containsForbiddenToken(string : String) : Boolean = {
    forbidden.map ((t) => string.contains(t)) contains(true)
}

def containsRepeated(string : String ) : Boolean= {
    if ( string.length() == 1 ) false
    else if(string.charAt(0) == string.charAt(1)) {
        true
    }else{
        containsRepeated(string substring(1))
    }
}

def containsThreeVowels(string : String) = {
    var sum = 0
    for(vowel <- vowels){
        sum += countSubstring(string, vowel)
    }
    sum >= 3
}

def countSubstring( str:String, substr:String ) = substr.r.findAllMatchIn(str).length


val strings = List("ugknbfddgicrmopn", "aaa", "jchzalrnumimnmhp", "haegwjzuvuyypxyu", "dvszwmarrgswjxmb")

for(string <- strings){
    if(isNice(string)){
        println(s"$string is nice.")
    }else{
        println(s"$string is naughty!")
    }
}

var nbrNice = 0
for (line <- Source.fromFile("day5.input") getLines) {
    if (isNice(line)){
        nbrNice += 1
    }
}

println(s"There are $nbrNice nice strings.")