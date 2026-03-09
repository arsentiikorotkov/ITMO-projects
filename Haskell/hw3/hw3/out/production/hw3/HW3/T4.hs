module HW3.T4
  ( State (..),
    Prim (..),
    Expr (..),
    mapState,
    wrapState,
    joinState,
    modifyState,
    eval,
  )
where

import Control.Monad (ap)
import HW3.T1

newtype State s a = S {runS :: s -> Annotated s a}

mapState :: (a -> b) -> State s a -> State s b
mapState f = S . \state -> mapAnnotated f . runS state

wrapState :: a -> State s a
wrapState = S . \x -> (x :#)

joinState :: State s (State s a) -> State s a
joinState = S . \twice x -> let (S f :# e) = runS twice x in f e

modifyState :: (s -> s) -> State s ()
modifyState = S . \f -> (() :#) . f

instance Functor (State s) where
  fmap = mapState

instance Applicative (State s) where
  pure = wrapState
  (<*>) = Control.Monad.ap

instance Monad (State s) where
  (>>=) x = joinState . flip mapState x

data Prim a
  = Add a a
  | Sub a a
  | Mul a a
  | Div a a
  | Abs a
  | Sgn a
  deriving (Show)

data Expr = Val Double | Op (Prim Expr)
  deriving (Show)

instance Num Expr where
  (+) x = Op . Add x
  (-) x = Op . Sub x
  (*) x = Op . Mul x
  abs = Op . Abs
  signum = Op . Sgn
  fromInteger = Val . fromInteger

instance Fractional Expr where
  (/) x = Op . Div x
  fromRational = Val . fromRational

eval :: Expr -> State [Prim Double] Double
eval (Val x) = return x
eval (Op (Add x y)) = calcBi x y (+) Add
eval (Op (Sub x y)) = calcBi x y (-) Sub
eval (Op (Mul x y)) = calcBi x y (*) Mul
eval (Op (Div x y)) = calcBi x y (/) Div
eval (Op (Abs x)) = calcUn x abs Abs
eval (Op (Sgn x)) = calcUn x signum Sgn

calcBi ::
  Expr ->
  Expr ->
  (Double -> Double -> Double) ->
  (Double -> Double -> Prim Double) ->
  State [Prim Double] Double
calcBi x y binaryOp binaryCtr = do
  x1 <- eval x
  y1 <- eval y
  modifyState (binaryCtr x1 y1 :)
  return $ binaryOp x1 y1

calcUn :: Expr -> (Double -> Double) -> (Double -> Prim Double) -> State [Prim Double] Double
calcUn x unaryOp unaryCtr = do
  x1 <- eval x
  modifyState (unaryCtr x1 :)
  return $ unaryOp x1
