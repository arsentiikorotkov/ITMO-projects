module HW2.T2
  ( joinWith
  , splitOn
  ) where

import Data.List.NonEmpty (NonEmpty ((:|)))

splitOn :: Eq a => a -> [a] -> NonEmpty [a]
splitOn sep = foldr (splitAccum sep) $ [] :| []

splitAccum :: Eq a => a -> a -> NonEmpty [a] -> NonEmpty [a]
splitAccum sep item (subLst :| rest)
  | item == sep = [] :| subLst : rest
  | otherwise = (item : subLst) :| rest

joinWith :: a -> NonEmpty [a] -> [a]
--joinWith con (h :| t)  = init $ foldr (joinAccum con) [] $ h : t
joinWith con = foldr1 (joinAccum con)

joinAccum :: a -> [a] -> [a] -> [a]
joinAccum con lstForJoin rest = lstForJoin ++ con : rest
