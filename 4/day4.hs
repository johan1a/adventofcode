--import Crypto.Hash.MD5 (MD5)
--import qualified Crypto.Hash.MD5 as MD5
import qualified Data.ByteString.Lazy as LB
import Data.ByteString.Internal.ByteString
import Data.Digest.Pure.MD5 (md5) 
import Data.ByteString.Char8(unpack)
import Data.String.Utils(startswith)
import Data.ByteString.Char8(pack)
import Data.Char(chr)

main :: IO ()
main = do
    key <- LB.readFile "day4.input"
    print $ getLowest key


getLowest input = getLowest_ input 0

getLowest_ input n
    | hasLeadingZeroes (md5 (input + pack $ [chr n])) = n
    | otherwise = getLowest_ input (n + 1)

hasLeadingZeroes s = startswith "00000" $ unpack s