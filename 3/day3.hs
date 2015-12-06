import Data.Set (Set)
import qualified Data.Set as Set

type Coord = (Int, Int)
type State = (Coord, Set Coord)

main = do
    input <- readFile "day3.input"
    santaVisited <- deliverPresents input
    santaUnique <- nbrUniqueVisited santaVisited
    roboVisited <- mirror santaVisited
    totalVisited <- nbrUniqueVisited $ Set.union santaVisited roboVisited
    return $ (santaUnique, totalVisited)
    


mirror :: Set Coord -> Set Coord
mirror set = Set.fromList $ map mirrorCord set

mirrorCord :: Coord -> Coord
mirrorCord (a, b) = (-a, -b)


nbrUniqueVisited :: IO State -> Int
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
