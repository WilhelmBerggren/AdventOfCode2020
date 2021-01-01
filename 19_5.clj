(def nums (mapv read-string (str/split (slurp "input19_5.txt") #",")))

(def instr-lengths {1 4, 2 4, 3 2, 4 2, 99 1})

(defn next-instruction [nums]
  (let [s (first nums)
        s' (format "%05d" s)
        instr (zipmap [:p3 :p2 :p1 :op] 
                (map (fn [[a b]] (read-string (subs s' a b))) 
                     '((0 1) (1 2) (2 3) (3 5))))
        length (instr-lengths (:op instr))]
    [instr (subvec nums 1 length)]))

(defn get-by-mode [mode arg nums]
  (if (= mode 1) arg (nth nums arg)))

(defn exec-instr [instr args nums]
  (case (:op instr)
    1 (assoc nums (nth args 2)
             (+ (get-by-mode (:p1 instr) (first args) nums)
                (get-by-mode (:p2 instr) (second args) nums)))
    2 (assoc nums (nth args 2)
             (* (get-by-mode (:p1 instr) (first args) nums)
                (get-by-mode (:p2 instr) (second args) nums)))
    3 (assoc nums (first args) 1)
    4 (do (println (get-by-mode (:p1 instr) (first args) nums)) nums)))

(loop [index 0
       nums nums]
  (if (< (count nums) index) 
    nums
    (let [[instr args] (next-instruction (subvec nums index))
          index' (+ index (instr-lengths (:op instr)))]
      (if (= (:op instr) 99) nums
          (recur index' (exec-instr instr args nums))))))
