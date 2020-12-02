(def data (str/split (slurp "input2.txt") #"\n"))
 
(def ans
  (for [val data
        :let [[a b c] (str/split val #" ")
              [pos1 pos2] (map 
                            #(Integer/parseInt %) 
                            (str/split a #"-"))
              letter (first b)
              [ans1 ans2] (map 
                            #(= (nth c (dec %)) letter)
                            [pos1 pos2])]
        :when (or (and ans1 (not ans2))
                  (and ans2 (not ans1)))]
    [a b c]))

(count ans)
