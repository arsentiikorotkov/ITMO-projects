module HW2.T4
  ( DotString (..)
  , Fun (..)
  , Inclusive (..)
  , ListPlus (..)
  ) where

data ListPlus a = a :+ ListPlus a | Last a
  deriving Show

infixr 5 :+

instance Semigroup (ListPlus a) where
  (<>) (Last x) = (:+) x
  (<>) (x :+ y) = (:+) x . (<>) y

data Inclusive a b = This a | That b | Both a b
  deriving Show

-- You may necessary constraints there
instance (Semigroup a, Semigroup b) => Semigroup (Inclusive a b) where
  (<>) (This x1) (This y1) = This $ x1 <> y1
  (<>) (This x1) (That y2) = Both x1 y2
  (<>) (This x1) (Both y1 y2) = Both (x1 <> y1) y2
  (<>) (That x2) (That y2) = That $ x2 <> y2
  (<>) (That x2) (Both y1 y2) = Both y1 $ x2 <> y2
  (<>) (Both x1 x2) (Both y1 y2) = Both (x1 <> y1) $ x2 <> y2
  (<>) l r = r <> l

newtype DotString = DS String
  deriving Show

instance Semigroup DotString where
  (<>) (DS "") dsY = dsY
  (<>) dsX (DS "") = dsX
  (<>) (DS x) (DS y) = DS $ (++) x $ '.' : y

instance Monoid DotString where
  mempty = DS ""

newtype Fun a = F (a -> a)

instance Semigroup (Fun a) where
  (<>) (F f) (F g) = F $ f . g

instance Monoid (Fun a) where
  mempty = F id
