module HW0.T4
  ( fac
  , fib
  , map'
  , repeat'
  ) where

import Numeric.Natural (Natural)
import Data.Function (fix)

repeat' :: a -> [a]
repeat' x = fix (x:)

map' :: (a -> b) -> [a] -> [b]
map' f = fix $ \recurse lst -> case lst of
  [] -> []
  (x : xs) -> f x : recurse xs

fib :: Natural -> Natural
fib = fix (\recurse second first n -> case n of
        0 -> second
        _ -> recurse first (second + first) $ n - 1) 0 1
        
fac :: Natural -> Natural
fac = fix $ \recurse n -> case n of
              0 -> 1
              _ -> n * recurse (n - 1)
