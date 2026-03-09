module HW1.T2
  ( N (..)
  , nplus
  , nmult
  , nsub
  , nFromNatural
  , nToNum
  , ncmp
  , nEven
  , nOdd
  , ndiv
  , nmod
  ) where

import Numeric.Natural ( Natural )

data N = Z | S N

nplus :: N -> N -> N
nplus Z = id
nplus (S n) = S . nplus n 

nmult :: N -> N -> N
nmult Z _ = Z
nmult _ Z = Z
nmult n (S m_1) = nplus n $ nmult n m_1

nsub :: N -> N -> Maybe N
nsub n Z = Just n
nsub Z _ = Nothing
nsub (S n) (S m) = nsub n m 

ncmp :: N -> N -> Ordering
ncmp n m = case nsub n m of
  Nothing -> LT
  Just Z -> EQ
  _ -> GT


nFromNatural :: Natural -> N
nFromNatural 0 = Z
nFromNatural n = S $ nFromNatural (n - 1)

nToNum :: Num a => N -> a
nToNum Z = 0
nToNum (S n) = (+1) $ nToNum n

nEven :: N -> Bool
nEven Z = True
nEven (S Z) = False
nEven (S (S n)) = nEven n

nOdd :: N -> Bool
nOdd = not . nEven

ndiv :: N -> N -> N
ndiv _ Z = undefined
ndiv Z _ = Z
ndiv n m = case nsub n m of
  Nothing -> Z
  Just res -> S (ndiv res m)

nmod :: N -> N -> N
nmod _ Z = undefined
nmod n m = case nsub n m of
  Nothing -> n
  Just res -> nmod res m
