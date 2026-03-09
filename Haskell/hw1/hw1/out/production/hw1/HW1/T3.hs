module HW1.T3
  ( Tree (..),
    tsize,
    tdepth,
    tmember,
    tinsert,
    tFromList,
  )
where

type Meta = (Int, Int)

data Tree a = Leaf | Branch Meta (Tree a) a (Tree a)
  deriving (Show)

tsize :: Tree a -> Int
tsize Leaf = 0
tsize (Branch (size, _) _ _ _) = size

tdepth :: Tree a -> Int
tdepth Leaf = 0
tdepth (Branch (_, depth) _ _ _) = depth

tmember :: Ord a => a -> Tree a -> Bool
tmember _ Leaf = False
tmember x (Branch _ l h r) = case compare x h of
  EQ -> True
  LT -> tmember x l
  _ -> tmember x r

tinsert :: Ord a => a -> Tree a -> Tree a
tinsert x Leaf = mkBranch Leaf x Leaf
tinsert x t@(Branch _ l h r) =
  if x == h
  then t
  else case compare x h of
    LT -> balance $ mkBranch (tinsert x l) h r
    _ -> balance $ mkBranch l h $ tinsert x r

mkBranch :: Tree a -> a -> Tree a -> Tree a
mkBranch l h r = Branch (tsize l + tsize r + 1, (+1) $ max (tdepth l) $ tdepth r) l h r

diff :: Tree a -> Tree a -> Int
diff l r = tdepth l - tdepth r

balance :: Tree a -> Tree a
balance Leaf = Leaf
balance t@(Branch _ l h r)
  | diff l r == 2 = rotateL l h r
  | diff l r == -2 = rotateR l h r
  | otherwise = t

rotateL :: Tree a -> a -> Tree a -> Tree a
rotateL (Branch _ ll lh lr) h r = if diff ll lr > 0
  then rotateLL ll lh lr h r
  else rotateLR ll lh lr h r 
rotateL _ _ _ = Leaf 

rotateLL :: Tree a -> a -> Tree a -> a -> Tree a -> Tree a
rotateLL ll lh lr h r = mkBranch ll lh $ mkBranch lr h r 

rotateLR :: Tree a -> a -> Tree a -> a -> Tree a -> Tree a
rotateLR ll lh (Branch _ lrl lrh lrr) h r = mkBranch (mkBranch ll lh lrl) lrh (mkBranch lrr h r)
rotateLR _ _ _ _ _ = Leaf

rotateR :: Tree a -> a -> Tree a -> Tree a
rotateR l h (Branch _ rl rh rr) = if diff rr rl > 0
  then rotateRR l h rl rh rr
  else rotateRL l h rl rh rr
rotateR _ _ _ = Leaf

rotateRR :: Tree a -> a -> Tree a -> a -> Tree a -> Tree a
rotateRR l h rl = mkBranch (mkBranch l h rl)

rotateRL :: Tree a -> a -> Tree a -> a -> Tree a -> Tree a
rotateRL l h (Branch _ rll rlh rlr) rh rr = mkBranch (mkBranch l h rll) rlh (mkBranch rlr rh rr)
rotateRL _ _ _ _ _ = Leaf

tFromList :: Ord a => [a] -> Tree a
tFromList = foldr tinsert Leaf



