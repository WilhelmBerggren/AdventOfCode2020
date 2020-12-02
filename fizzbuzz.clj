(let [f #(zero? (mod %2 %1))
      fb #(condp f %
            15 "fizzbuzz"
            3 "fizz"
            5 "buzz"
            %)]
  (map 
   #(fb %) 
   (range 1 101)))