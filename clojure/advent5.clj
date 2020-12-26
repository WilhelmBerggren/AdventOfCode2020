(def data (str/split (slurp "../inputs/input5.txt") #"\n"))

(defn to-int [item]
  (Integer/parseInt 
   (str/join 
    (map #(case % \B 1 \R 1 0) item)) 
   2))

(def ids (sort (map to-int data)))

(def missing-ids
  (for [[n1 n2] (partition 2 1 ids)
        :when (not (= (inc n1) n2))]
    (inc n1)))

(def part1 (apply max ids))
(def part2 (first missing-ids))

[part1 part2]