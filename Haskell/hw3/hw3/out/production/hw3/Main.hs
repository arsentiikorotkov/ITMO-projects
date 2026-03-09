module Main (main) where

import HW3.T4
  
main :: IO ()
main = putStrLn $ show (runS (eval (2 + 3 * 5 - 7)) [])
