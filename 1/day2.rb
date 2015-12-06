#--- Day 2: I Was Told There Would Be No Math ---

def shortestPath(l, w, h)
    path1 = 2*l + 2*h
    path2 = 2*h + 2*w
    path3 = 2*l + 2*w
    return [path1, path2, path3].min
end

def area(l, w, h)

    longSide = l*h
    top = l*w
    wideSide = w*h

    2 * (top + longSide + wideSide) + [top, longSide, wideSide].min
end

def volume(l, w, h)
    return l * w * h
end

def bowLength(l, w, h)
    shortestPath(l, w, h) + volume(l, w, h) 
end


areaSum = 0
bowLengthSum = 0

IO.read("day2.input").gsub( /\r\n/, "\n" ).split("\n").each do |line|
    l, w, h = line.split("x").map{ |x| x.to_i }

    area = area(l, w, h)
    bowLength = bowLength(l, w, h)
    bowLengthSum += bowLength
    areaSum += area(l, w, h)
end

puts "Total area: #{areaSum}"
puts "Total bowLength: #{bowLengthSum}"
