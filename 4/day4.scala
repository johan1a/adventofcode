import java.security.MessageDigest
 
val digest = MessageDigest.getInstance("MD5")
 


val key = "yzbqklnj"
def md5hash1(input : String) = digest.digest(input.getBytes).map("%02x".format(_)).mkString

def part1 = findLowest(key, 0, "00000")
def part2 = findLowest(key, 0, "000000")

def findLowest(key : String, n : Int, prefix : String) : Int =  {
    if (md5hash1(key + n).startsWith(prefix)) n
    else findLowest(key, n + 1, prefix)
}

val start = System.currentTimeMillis
val lowest = part1
val elapsed = System.currentTimeMillis - start
println(s"Lowest 1: $lowest, found in $elapsed millis")


val start2 = System.currentTimeMillis
val lowest2 = part2
val elapsed2 = System.currentTimeMillis - start2
println(s"Lowest 2: $lowest2, found ins $elapsed2 millis")