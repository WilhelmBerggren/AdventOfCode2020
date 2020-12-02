(map
 (fn [n] 
   (condp #(zero? (mod %2 %1)) n
     15 "fizzbuzz"
     3 "fizz"
     5 "buzz"
     n))
 (range 1 101))