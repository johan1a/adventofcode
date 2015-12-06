import Data.Set (Set)
import qualified Data.Set as Set

type Coord = (Int, Int)
type State = (Coord, Set Coord)

part1 = do
    input <- readFile "day3.input"
    return $ getNbrVisited input

part2 = do
      input <- readFile "day3.input"
      return $ getNbrBothVisited input

everyOther xs = case drop 1 xs of
              (y:ys) -> y : everyOther ys
              [] -> []

getNbrVisited :: String -> Int
getNbrVisited input = nbrUniqueVisited $ deliverPresents input 

getNbrBothVisited :: String -> Int
getNbrBothVisited s = Set.size $ getBothVisited s

getBothVisited :: String -> Set Coord
getBothVisited input = Set.union santa robot
            where santa = snd $ deliverPresents $ santaInput input
                  robot = snd $ deliverPresents $ robotInput input

santaInput input = everyOther $ ' ' : input
robotInput input = everyOther input

getRoboVisited :: Set Coord -> Set Coord
getRoboVisited santaVisited = mirror $ santaVisited

mirror :: Set Coord -> Set Coord
mirror set = Set.fromList $ map mirrorCord $ Set.toList set

mirrorCord :: Coord -> Coord
mirrorCord (a, b) = (-a, -b)


nbrUniqueVisited :: State -> Int
nbrUniqueVisited state = Set.size $ snd state

deliverPresents :: String -> State
deliverPresents input = move input startingState

startingState :: State
startingState = (origin, Set.fromList[origin])

origin :: Coord
origin = (0,0)

move :: String -> State -> State
move [] state = state
move (s:ss) (loc, state) = move ss (newLoc, Set.insert newLoc state)
    where newLoc = myAdd loc diff
          diff = case s of
                '^' -> (0, 1)
                '>' -> (1, 0)
                'v' -> (0, -1)
                '<' -> (-1, 0)
                _   -> (0, 0)

myAdd :: Coord -> Coord -> Coord
myAdd (a,b) (c,d) = (a + c, b + d)
