module HW2.T3
  ( epart
  , mcat
  ) where

mcat :: Monoid a => [Maybe a] -> a
mcat = foldMap extractMaybe

extractMaybe :: Monoid a => Maybe a -> a
extractMaybe (Just a) = a
extractMaybe Nothing = mempty

epart :: (Monoid a, Monoid b) => [Either a b] -> (a, b)
epart lst = (foldMap extractLeft lst, foldMap extractRight lst)

extractLeft :: Monoid a => Either a b -> a
extractLeft (Left x) = x
extractLeft (Right _) = mempty

extractRight :: Monoid b => Either a b -> b
extractRight (Left _) = mempty
extractRight (Right x) = x
