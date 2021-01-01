;; (def data (str/split (slurp "input11.txt") #"\n"))
(def data (str/split (slurp "sample11.txt") #"\n"))

(def width (+ 2 (count (first data))))
(def height (+ 2 (count data)))
(defn pad [s]
  (str
   (.repeat "." width)
   "."
   (str/join ".." (partition width s))
   "."
   (.repeat "." width)))
(str/join ( width (str/join "" data)))

(defn adj [seats ox oy]
  (count (filter (partial = \#)
                 (for [x [-1 0 1]
                       y [-1 0 1]]
                   (get-in seats [(+ x ox) (+ y oy)])))))

(defn run [s]
  (mapv (fn [x]
          (mapv (fn [y] (get-in s [x y]))
                (range 1 (dec width))))
        (range 1 (inc (count data)))))

(into [] (map #(println (apply str %)) padded))
(into [] (map #(println (apply str %)) (run padded)))

;; (def r (dec (count (first data))))
;; (def first-s (apply str data))
;; (def l (count first-s))

;; (defn adj [s i]
;;   (map
;;    (fn [n]
;;      (let [row (+ (int (/ i r)) n)
;;            sub (subs s (* row r) (+ (* row r) r))
;;            valid #(< -1 (+ (mod i r) %) r)
;;            nth' #(nth sub (+ (mod i r) %))]
;;        (map #(when (valid %) (nth' %)) [-1 0 1])))
;;    (filter #(< -1 (+ (int (/ i r)) %) r) [-1 0 1])))

;; (defn count-filled [s i]
;;   (count (filter (partial = \#) (flatten (adj s i)))))

;; (defn tick [s]
;;   (apply str (map #(case (nth s %)
;;                      \# (if (> (count-filled s %) 4) \L \#)
;;                      \L (if (= (count-filled s %) 0) \# \L)
;;                      \.)
;;                   (range l))))

;; (defn print' [s]
;;   (into [] (map println (partition r s))))

;; (defn fixed [s]
;;   (loop [s s
;;          s' (tick s)]
;;     (print' s)
;;     (println)
;;     (if (= s s')
;;       s'
;;       (recur s' (tick s')))))

;; (print' (tick (tick first-s)))

;; (count (filter (partial = \#) (fixed first-s)))
      
;; (apply str (map count-filled (range l)))

;; (into
;;  []
;;  (map
;;   println
;;   (let [w 4
;;         s (str
;;            "abcd"
;;            "efgh"
;;            "ijkl")
;;         i 3
;;         row (int (/ i w))
;;         n #(nth s %)
;;         gr (fn [r p] ())]
;;     [[row]
;;      [(n (- i w 1)) (n (- i w)) (n (- i w -1))]
;;      [(n (+ i -1)) (n i) (n (+ i 1))]
;;      [(n (+ i w -1)) (n (+ i w)) (n (+ i w -2))]])))

;; (defn adjacent [seats row i]
;;   (let [row-1 (when (> row 0) (nth seats (dec row)))
;;         row-2 (nth seats row)
;;         row-3 (when (> row-count row) (nth seats (inc row)))]
;;     (map #(when (not= nil? %) (nth % (mod i row-length))) [row-1 row-2 row-3])))

;; (adjacent first-seats 8 1)

;; (nth first-seats 0)

;; (defn count-filled [seats] (count (filter (partial = \#) seats)))
;; (defn count-adjacent [seats i] (count-filled (adjacent seats i)))

;; (defn tick [seats]
;;   (map 
;;    (map #(case (nth seats %)
;;            \# (if (> (count-adjacent seats %) 3) \L \#)
;;            \L (if (= (count-adjacent seats %) 0) \# \L)
;;            \.) (range (count seats)))))

;; (defn fixed [seats]
;;   (loop [seats seats
;;          seats' (tick seats)]
;;     (if (= seats seats')
;;       seats'
;;       (recur seats' (tick seats')))))

;; (defn print-seats [seats]
;;   (str/join "\n" (partition row-length seats)))

;; (comment
;;   (print-seats (tick (tick first-seats)))
;;   (adjacent first-seats row-length)
;;   )

;; (println (str/join "\n" (map (partial apply str) (partition row-length (fixed first-seats)))))

;; (adj2 (flatten first-seats) 115)

;; (def first-seats (mapv #(apply vector (seq %)) data))
;; (def row-length (count (first data)))
;; (def row-count (count data))
;; 
;; (defn adjacent [seats posx posy]
;;   (for [x [-1 0 1]
;;         y [-1 0 1]
;;         :let [x' (+ posx x)
;;               y' (+ posy y)]
;;         :when (and
;;                (< -1 x' row-length)
;;                (< -1 y' row-count))]
;;     ;; (get-in seats) 
;;     [x' y']))

;; (adjacent first-seats 1 1)

;; (defn count-adjacent [seats posx posy]
;;   (count
;;    (filter (partial = \#)
;;            (adjacent seats posx posy))))

;; (defn tick [seats]
;;   (mapv (fn [x]
;;           (mapv (fn [y]
;;                   (case (get-in seats [x y])
;;                     \# (if (> (count-adjacent seats x y) 4) \L \#)
;;                     \L (if (= (count-adjacent seats x y) 0) \# \L)
;;                     \.))
;;                 (range row-length)))
;;         (range row-count)))

;; (defn count-filled [seats] (count (filter (partial = \#) (flatten seats))))

;; (defn fixed-seats [seats]
;;   (loop [seats seats
;;          seats' (tick seats)]
;;     (time (if (= seats' seats)
;;             seats
;;             (recur seats' (tick seats'))))))

;; (count-filled (fixed-seats first-seats))
;; (time (tick first-seats))
;; too high 2637
;; too low  2214