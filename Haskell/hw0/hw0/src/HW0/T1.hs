{-# LANGUAGE TypeOperators #-}

module HW0.T1
  ( type (<->) (Iso)
  , assocEither
  , assocPair
  , distrib
  , flipIso
  , runIso
  ) where

data a <-> b = Iso (a -> b) (b -> a)

distrib :: Either a (b, c) -> (Either a b, Either a c)
distrib (Left x) = (Left x, Left x)
distrib (Right (y, z)) = (Right y, Right z)

flipIso :: (a <-> b) -> (b <-> a)
flipIso (Iso f g) = Iso g f

runIso :: (a <-> b) -> (a -> b)
runIso (Iso f _) = f

assocPair :: (a, (b, c)) <-> ((a, b), c)
assocPair = Iso (\(x, (y, z)) -> ((x, y), z)) (\((x, y), z) -> (x, (y, z)))

assocEither :: Either a (Either b c) <-> Either (Either a b) c
assocEither = Iso assocEither1 assocEither2
  where
    assocEither1 :: Either a (Either b c) -> Either (Either a b) c
    assocEither1 (Left x) = Left $ Left x
    assocEither1 (Right (Left y)) = Left $ Right y
    assocEither1 (Right (Right z)) = Right z
    
    assocEither2 :: Either (Either a b) c -> Either a (Either b c)
    assocEither2 (Left (Left x)) = Left x
    assocEither2 (Left (Right y)) = Right $ Left y
    assocEither2 (Right z) = Right $ Right z
