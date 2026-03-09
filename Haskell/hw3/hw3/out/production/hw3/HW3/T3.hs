module HW3.T3
  ( joinOption
  , joinExcept
  , joinAnnotated
  , joinList
  , joinFun
  ) where

import HW3.T1

joinOption :: Option (Option a) -> Option a
joinOption (Some (Some x)) = Some x
joinOption _ = None

joinExcept :: Except e (Except e a) -> Except e a
joinExcept (Error x) = Error x
joinExcept (Success except) = except

joinAnnotated :: Semigroup e => Annotated e (Annotated e a) -> Annotated e a
joinAnnotated ((x1 :# e1) :# e2) = x1 :# e2 <> e1

joinList :: List (List a) -> List a
joinList Nil = Nil
joinList (lx :. lxs) = extend lx $ joinList lxs

joinFun :: Fun i (Fun i a) -> Fun i a
joinFun = F . \fun x -> apply (apply fun x) x

apply :: Fun i a -> i -> a
apply (F f) = f
