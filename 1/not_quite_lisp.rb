
input = ARGV[0]
input = IO.read("not_quite_lisp_input.txt")

floors = 0
position = 1
firstMinusOnePosition = -1
foundMinusOne = false

input.each_char do |paren|
	if paren == "("
		floors += 1
	elsif paren == ")"
		floors -= 1
	end
    if floors == -1 and !foundMinusOne
        firstMinusOnePosition = position
        foundMinusOne = true
    end

    position += 1
end

puts("Floors: #{floors}")
puts("First minus one position: #{firstMinusOnePosition}")

