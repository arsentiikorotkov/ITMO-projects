module HW0.T5
  ( Nat
  , nFromNatural
  , nmult
  , nplus
  , ns
  , nToNum
  , nz
  ) where

import Numeric.Natural

type Nat a = (a -> a) -> a -> a

nz :: Nat a
nz = undefined

ns :: Nat a -> Nat a
ns = undefined

nplus :: Nat a -> Nat a -> Nat a
nplus = undefined

nmult :: Nat a -> Nat a -> Nat a
nmult = undefined

nFromNatural :: Natural -> Nat a
nFromNatural = undefined

nToNum :: Num a => Nat a -> a
nToNum = undefined
