(defn nums [n]
  (map #(read-string (str %)) (str n)))

(defn part1 []
  (transduce 
   (comp (map nums)
         (filter (partial apply <=))
         (map (partial partition-by identity))
         (map #(< (count %) 6))
         (map (fn [_] 1)))
   +
   (range 273025 (inc 767253))))

(defn part2 []
  (transduce
   (comp (map nums)
         (filter (partial apply <=))
         (map (partial partition-by identity))
         (filter (fn [g] (some #(= (count %) 2) g)))
         (map (fn [_] 1)))
   +
   (range 273025 (inc 767253))))

[(time (part1)) (time (part2))]