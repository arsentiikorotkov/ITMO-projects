module Main (main) where
import HW2.T1
import HW2.T2
import Data.List.NonEmpty (NonEmpty ((:|)))
import HW2.T3
import Data.Monoid

main :: IO ()

main = putStrLn $ show $ "import " ++ joinWith '.' ("Data" :| "List" : "NonEmpty" : [])
--main = putStrLn $ show $ epart [Left (Sum 3), Right [1,2,3], Left (Sum 5), Right [4,5]]