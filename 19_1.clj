(def nums (map read-string (str/split (slurp "input19_1.txt") #"\n")))

(defn fuel [n] (- (int (Math/floor (/ n 3))) 2))

(def part1 (apply + (map fuel nums)))
;; 3443395

(def part2
  (loop [nums nums
         sum 0]
    (let [nums' (filter (partial < 0) (map fuel nums))
          sum' (apply + nums')]
      (if (= sum' 0)
        sum
        (recur nums' (+ sum sum'))))))
;; 5162216

[part1 part2]