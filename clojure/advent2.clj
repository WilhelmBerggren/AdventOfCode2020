(def data (str/split (slurp "../inputs/input2.txt") #"\n"))

(def ans 
  (for [val data
      :let [[a b c] (str/split val #" ")
            [min max] (map 
                        #(Integer/parseInt %) 
                        (str/split a #"-"))
            letter (first b)
            amount (count (filter #{letter} c))]
      :when (and (>= amount min)
                 (<= amount max))]
  val))

(count ans)
