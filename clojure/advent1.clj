(let [vals (map 
             #(Integer/parseInt %) 
             (str/split (slurp "../inputs/input1.txt") #"\n"))]
  (first 
    (for [x vals
          y vals
          z vals
          :when (= (+ x y z) 2020)] 
      (* x y z)))) 
