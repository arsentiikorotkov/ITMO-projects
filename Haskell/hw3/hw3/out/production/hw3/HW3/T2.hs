module HW3.T2
  ( distOption
  , wrapOption
  , distPair
  , wrapPair
  , distQuad
  , wrapQuad
  , distAnnotated
  , wrapAnnotated
  , distExcept
  , wrapExcept
  , distPrioritised
  , wrapPrioritised
  , distStream
  , wrapStream
  , distList
  , wrapList
  , distFun
  , wrapFun
  ) where

import HW3.T1

distOption :: (Option a, Option b) -> Option (a, b)
distOption (Some x, Some y) = Some (x, y)
distOption _ = None

wrapOption :: a -> Option a
wrapOption = Some

distPair :: (Pair a, Pair b) -> Pair (a, b)
distPair (P x1 y1, P x2 y2) = P (x1, x2) (y1, y2)

wrapPair :: a -> Pair a
wrapPair x = P x x

distQuad :: (Quad a, Quad b) -> Quad (a, b)
distQuad (Q x1 y1 z1 t1, Q x2 y2 z2 t2) = Q (x1, x2) (y1, y2) (z1, z2) (t1, t2)

wrapQuad :: a -> Quad a
wrapQuad x = Q x x x x

distAnnotated :: Semigroup e => (Annotated e a, Annotated e b) -> Annotated e (a, b)
distAnnotated (x1 :# e1, x2 :# e2) = (x1, x2) :# e1 <> e2

wrapAnnotated :: Monoid e => a -> Annotated e a
wrapAnnotated x = x :# mempty

distExcept :: (Except e a, Except e b) -> Except e (a, b)
distExcept (Success x1, Success x2) = Success (x1, x2)
distExcept (Error x, _) = Error x
distExcept (_, Error x) = Error x

wrapExcept :: a -> Except e a
wrapExcept = Success

distPrioritised :: (Prioritised a, Prioritised b) -> Prioritised (a, b)
distPrioritised (High x1, priori2) = High (x1, extractPriori priori2)
distPrioritised (priori1, High x2) = High (extractPriori priori1, x2)
distPrioritised (Medium x1, priori2) = Medium (x1, extractPriori priori2)
distPrioritised (priori1, Medium x2) = Medium (extractPriori priori1, x2)
distPrioritised (priori1, priori2) = Low (extractPriori priori1, extractPriori priori2)

extractPriori :: Prioritised a -> a
extractPriori (Low x) = x
extractPriori (Medium x) = x
extractPriori (High x) = x

wrapPrioritised :: a -> Prioritised a
wrapPrioritised = Low

distStream :: (Stream a, Stream b) -> Stream (a, b)
distStream (x1 :> xs1, x2 :> xs2) = (x1, x2) :> distStream (xs1, xs2)

wrapStream :: a -> Stream a
wrapStream x = x :> wrapStream x

distList :: (List a, List b) -> List (a, b)
distList (l1@(x1 :. Nil), x2 :. xs2) = (x1, x2) :. distList (l1, xs2)
distList (x1 :. xs1, l2@(_ :. _)) = extend (distList (x1 :. Nil, l2)) $ distList (xs1, l2)
distList _ = Nil

wrapList :: a -> List a
wrapList = (:. Nil)

distFun :: (Fun i a, Fun i b) -> Fun i (a, b)
distFun (F f, F g) = F $ \x -> (f x, g x)

wrapFun :: a -> Fun i a
wrapFun x = F $ \_ -> x
