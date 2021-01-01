(def nums (mapv read-string (str/split (slurp "input19_5.txt") #",")))

(def instr-lengths {1 4, 2 4, 3 2, 4 2, 99 1})

(defn parse-instr [num]
  (let [s (format "%05d" num)]
    (zipmap [:p3 :p2 :p1 :op] 
            (map (fn [[a b]] (read-string (subs s a b))) 
                 '((0 1) (1 2) (2 3) (3 5))))))

(defn get-by-mode [mode arg nums]
  (if (= mode 1) arg (nth nums arg)))

(defn tick [[index nums]]
  (let [instr (parse-instr (nth nums index))
        index' (+ index (instr-lengths (:op instr)))
        args (subvec nums (inc index) index')]
    (case (:op instr)
      1 [index' (assoc nums (nth args 2)
                       (+ (get-by-mode (:p1 instr) (first args) nums)
                          (get-by-mode (:p2 instr) (second args) nums)))]
      2 [index' (assoc nums (nth args 2)
                       (* (get-by-mode (:p1 instr) (first args) nums)
                          (get-by-mode (:p2 instr) (second args) nums)))]
      3 [index' (assoc nums (first args) 1)]
      4 [index' (do (println (get-by-mode (:p1 instr) (first args) nums)) nums)]
      99 [(count nums) nums])))

(loop [state [0 nums]]
  (when (< (first state) (count nums)) 
    (recur (tick state))))