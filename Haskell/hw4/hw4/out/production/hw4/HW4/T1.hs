module HW4.T1
  ( EvaluationError (..),
    ExceptState (..),
    mapExceptState,
    wrapExceptState,
    joinExceptState,
    modifyExceptState,
    throwExceptState,
    eval,
  )
where

import Control.Monad
import HW4.Types

newtype ExceptState e s a = ES {runES :: s -> Except e (Annotated s a)}

mapExceptState :: (a -> b) -> ExceptState e s a -> ExceptState e s b
mapExceptState mp = ES . \state -> mapExcept (mapAnnotated mp) . runES state

wrapExceptState :: a -> ExceptState e s a
wrapExceptState = ES . \x -> Success . (x :#)

joinExceptState :: ExceptState e s (ExceptState e s a) -> ExceptState e s a
joinExceptState =
  ES . \twice x -> case runES twice x of
    (Error e) -> Error e
    (Success (state :# xx)) -> runES state xx

modifyExceptState :: (s -> s) -> ExceptState e s ()
modifyExceptState = ES . \f -> Success . (:#) () . f

throwExceptState :: e -> ExceptState e s a
throwExceptState = ES . \er _ -> Error er

instance Functor (ExceptState e s) where
  fmap = mapExceptState

instance Applicative (ExceptState e s) where
  pure = wrapExceptState
  (<*>) = Control.Monad.ap

instance Monad (ExceptState e s) where
  (>>=) x = joinExceptState . flip mapExceptState x

data EvaluationError = DivideByZero
  deriving (Show)

eval :: Expr -> ExceptState EvaluationError [Prim Double] Double
eval (Val x) = return x
eval (Op (Add x y)) = calcBi x y (+) Add
eval (Op (Sub x y)) = calcBi x y (-) Sub
eval (Op (Mul x y)) = calcBi x y (*) Mul
eval (Op (Div x y)) = case runES (eval y) [] of
  (Success (0 :# _)) -> throwExceptState DivideByZero
  _ -> calcBi x y (/) Div
eval (Op (Abs x)) = calcUn x abs Abs
eval (Op (Sgn x)) = calcUn x signum Sgn

calcBi ::
  Expr ->
  Expr ->
  (Double -> Double -> Double) ->
  (Double -> Double -> Prim Double) ->
  ExceptState EvaluationError [Prim Double] Double
calcBi x y binaryOp binaryCtr = do
  x1 <- eval x
  y1 <- eval y
  modifyExceptState (binaryCtr x1 y1 :)
  return $ binaryOp x1 y1

calcUn ::
  Expr ->
  (Double -> Double) ->
  (Double -> Prim Double) ->
  ExceptState EvaluationError [Prim Double] Double
calcUn x unaryOp unaryCtr = do
  x1 <- eval x
  modifyExceptState (unaryCtr x1 :)
  return $ unaryOp x1
    


