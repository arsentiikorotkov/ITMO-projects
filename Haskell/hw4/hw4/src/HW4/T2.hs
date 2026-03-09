{-# LANGUAGE DerivingStrategies #-}
{-# LANGUAGE GeneralisedNewtypeDeriving #-}

module HW4.T2
  ( ParseError (..),
    runP,
    pChar,
    parseError,
    parseExpr,
    pEof,
  )
where
  

import Control.Applicative
import Control.Monad
import Data.Char (digitToInt, isDigit, isSpace)
import HW4.T1 (ExceptState (..))
import HW4.Types
import Numeric.Natural (Natural)

newtype ParseError = ErrorAtPos Natural
  deriving (Show)

newtype Parser a = P (ExceptState ParseError (Natural, String) a)
  deriving newtype (Functor, Applicative, Monad)

runP :: Parser a -> String -> Except ParseError a
runP (P state) str = case runES state (0, str) of
  (Error er) -> Error er
  (Success (res :# _)) -> Success res

-- Just an example of parser that may be useful
-- in the implementation of 'parseExpr'
pChar :: Parser Char
pChar = P $
  ES $ \(pos, s) ->
    case s of
      [] -> Error (ErrorAtPos pos)
      (c : cs) -> Success (c :# (pos + 1, cs))

parseError :: Parser a
parseError = P $ ES $ \(pos, _) -> Error (ErrorAtPos pos)

instance Alternative Parser where
  empty = parseError
  P stp <|> P stq = P $
    ES $ \pair -> case runES stp pair of
      Error _ -> runES stq pair
      res -> res

-- No metohds
instance MonadPlus Parser

pEof :: Parser ()
pEof = P $
  ES $ \pair@(pos, str) -> case str of
    [] -> Success $ () :# pair
    _ -> Error $ ErrorAtPos pos

-- E -> T E'                                     ЕСТЬ
-- E' -> eps | + T E' | - T E'                   ЕСТЬ
-- T -> F T'                                     ЕСТЬ
-- T' -> eps | * F T' | / F T'                   ЕСТЬ
-- F -> double | (E)                             ЕСТЬ
-- double: nat '.'nat?                           ЕСТЬ
-- nat: 0 | [1-9] [0-9]*                         ЕСТЬ

parseExpr :: String -> Except ParseError Expr
parseExpr = runP pExpr

pSkipWS :: Parser ()
pSkipWS = void $ many $ mfilter isSpace pChar

pExpr :: Parser Expr
pExpr = do
  res <- pE
  pSkipWS
  pEof
  return res

pE :: Parser Expr
pE = do
  expr <- pT
  f <- pE'
  return (f expr)

pT :: Parser Expr
pT = do
  expr <- pF
  f <- pT'
  return (f expr)

pF :: Parser Expr
pF =
  pSkipWS
    >> pDouble <|> do
      void $ getPch '('
      expr <- pE
      pSkipWS
      void $ getPch ')'
      return expr

pDouble :: Parser Expr
pDouble = do
  intPart <- pNat
  dot <- optional $ getPch '.'
  case dot of
    Just '.' -> do
      fracPart <- pNat
      return $ Val $ fromInteger (toInt intPart) + toFrac fracPart
    _ -> return $ Val $ fromInteger $ toInt intPart

pNat :: Parser String
pNat = some $ mfilter isDigit pChar

getPch :: Char -> Parser Char
getPch ch = mfilter (== ch) pChar

toInt :: String -> Integer
toInt = fromIntegral . foldl (\res ch -> res * 10 + digitToInt ch) 0

toFrac :: String -> Double
toFrac = (/ 10) . foldr (\ch res -> fromIntegral (digitToInt ch) + res / 10) 0

pE' :: Parser (Expr -> Expr)
pE' = getpE' '+' (+) <|> getpE' '-' (-) <|> return id

getpE' :: Char -> (Expr -> Expr -> Expr) -> Parser (Expr -> Expr)
getpE' = getp' pT pE'

pT' :: Parser (Expr -> Expr)
pT' = getpT' '*' (*) <|> getpT' '/' (/) <|> return id

getpT' :: Char -> (Expr -> Expr -> Expr) -> Parser (Expr -> Expr)
getpT' = getp' pF pT'

getp' ::
  Parser Expr ->
  Parser (Expr -> Expr) ->
  Char ->
  (Expr -> Expr -> Expr) ->
  Parser (Expr -> Expr)
getp' p1 p2 ch ctr = do
  pSkipWS
  void $ getPch ch
  expr <- p1
  f <- p2
  return (flip ctr $ f expr)
