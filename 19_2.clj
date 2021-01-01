(def nums (vec (map read-string (str/split (slurp "input19_2.txt") #","))))

(def sample [1,9,10,3,2,3,11,0,99,30,40,50])

(defn update-nums [nums opindex]
  (let [target (nth nums (+ opindex 3))
        op (if (= 1 (nth nums opindex)) + *)
        arg1 (nth nums (nth nums (+ opindex 1)))
        arg2 (nth nums (nth nums (+ opindex 2)))]
    (assoc nums target (op arg1 arg2))))

(defn run [nums]
  (loop [nums nums
         opindex 0]
    (if (= (nth nums opindex) 99)
      nums
      (recur (update-nums nums opindex) (+ opindex 4)))))

(def part1 (first (run (assoc (assoc nums 1 12) 2 2))))
;; 6087827

(def part2
  (first
   (for [noun (range 100)
         verb (range 100)
         :let [result (first (run (assoc (assoc nums 1 noun) 2 verb)))]
         :when (= result 19690720)]
     (+ (* 100 noun) verb))))
;; 5379

[part1 part2]