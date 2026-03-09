module Main (main) where

-- import HW3.T4
  
-- foldR :: (a -> b -> b) -> b -> [a] -> b
-- foldR _ z [] = z
-- foldR f z (x:xs) = foldR f (f x z) xs

foldL :: (b -> a -> b) -> b -> [a] -> b
foldL _ z [] = z
foldL f z (x:xs) = foldL f (f z x) xs


main :: IO ()
main = putStrLn $ show (foldL (-) (0 :: Integer) ([1, 2, 3] :: [Integer]))
