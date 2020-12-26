(def data (map edn/read-string (str/split (slurp "input10.txt") #"\n")))

(count data)

(def all-jolts (sort (conj data 0 (+ 3 (apply max data)))))

(def part1
  (let [counts (for [n (partition-all 2 1 all-jolts)]
                 (apply - (reverse n)))]
   (apply * (map 
             (fn [n] 
               (count (filter #(= n %) counts))) 
             [1 3]))))

(for [i (range (- (count all-jolts) 3))
      :let [item (nth all-jolts i)
            next-three (map #(nth all-jolts (+ 1 i %)) (range 3))]
      next-item next-three
      :while (< (- next-item item) 4)]
  [item next-item])

part1