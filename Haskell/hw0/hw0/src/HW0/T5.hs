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
nz _ z = z

ns :: Nat a -> Nat a
ns nat f = nat f . f

nplus :: Nat a -> Nat a -> Nat a
nplus nat1 nat2 f = nat1 f . nat2 f

nmult :: Nat a -> Nat a -> Nat a
nmult nat1 nat2 f = nat1 (nat2 f)

nFromNatural :: Natural -> Nat a
nFromNatural 0 = nz
nFromNatural n = ns $ nFromNatural (n - 1)

nToNum :: Num a => Nat a -> a
nToNum nat = nat (+1) 0
