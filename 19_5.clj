(def nums (mapv read-string (str/split (slurp "input19_5.txt") #",")))

(def instr-lengths {1 4, 2 4, 3 2, 4 2, 99 1, 5 3, 6 3, 7 4, 8 4})

(defn parse-instr [num]
  (let [s (format "%05d" num)]
    (zipmap [:p3 :p2 :p1 :op] 
            (map (fn [[a b]] (Integer/parseInt (subs s a b))) 
                 '((0 1) (1 2) (2 3) (3 5))))))

(defn parse-args [instr args nums]
  (mapv (fn [[p n]] (if (= p 1) n (nth nums n)))
       (map vector
            (vals (select-keys instr [:p1 :p2 :p3]))
            args)))

(defn tick [[index nums]]
  (let [instr (parse-instr (nth nums index))
        index' (+ index (instr-lengths (:op instr)))
        argvec (subvec nums (inc index) index')
        args (parse-args instr argvec nums)]
    [(case (:op instr)
       5 (if (not= (first args) 0) (second args) index')
       6 (if (= (first args) 0) (second args) index')
       99 Integer/MAX_VALUE
       index')
     (case (:op instr)
       1 (assoc nums (nth argvec 2) (+ (first args) (second args)))
       2 (assoc nums (nth argvec 2) (* (first args) (second args)))
       3 (assoc nums (first argvec) 5)
       4 (do (println (first args)) nums)
       7 (assoc nums (nth argvec 2) (if (< (first args) (second args)) 1 0))
       8 (assoc nums (nth argvec 2) (if (= (first args) (second args)) 1 0))
       nums)]))

(loop [state [0 nums]]
  (when (< (first state) (count nums)) 
    (recur (tick state))))
