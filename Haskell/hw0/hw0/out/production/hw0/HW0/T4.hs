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
fib = fix $ \recurse n -> case n of
        0 -> 0
        1 -> 1
        _ -> recurse (n - 1) + recurse (n - 2)

fac :: Natural -> Natural
fac = fix $ \recurse n -> case n of
              0 -> 1
              _ -> n * recurse (n - 1)
