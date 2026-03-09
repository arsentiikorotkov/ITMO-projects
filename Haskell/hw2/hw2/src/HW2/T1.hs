module HW2.T1
  ( Tree (..)
  , tfoldr
  ) where

data Tree a = Leaf | Branch Int (Tree a) a (Tree a)
  deriving (Show)

tfoldr :: (a -> b -> b) -> b -> Tree a -> b
tfoldr _ z Leaf = z
tfoldr f z (Branch _ l h r) = tfoldr f (f h $ tfoldr f z r) l
