(def data (str/split (slurp "input12.txt") #"\n"))

(def state1 {:d \E, \W 0, \N 0, \E 0, \S 0})

(defn parse [item]
  (let [d (first item)
        n (read-string (rest item))]
    [d n]))

(defn dir->int [d] 
  (get {\W 0 \N 1 \E 2 \S 3} d))

(defn int->dir [n] 
  (get {0 \W 1 \N 2 \E 3 \S} n))

(defn forward [old n] 
  (merge old {(:d old) (+ (get old (:d old)) n)}))

(defn turn [old d n] ; d: \L or \R
  (merge old {:d (int->dir (mod (+ 4 (* (/ n 90) (case d \L -1 \R 1)) (dir->int (:d old))) 4))}))

(defn move [old d n]
  (merge old {d (+ (get old d) n)}))

(defn dispatch [old s]
  (let [[d n] (parse s)]
    (case d
      \N (move old d n)
      \W (move old d n)
      \S (move old d n)
      \E (move old d n)
      \L (turn old d n)
      \R (turn old d n)
      \F (forward old n))))

(def part1
  (let [res (reduce dispatch state1 data)
        x (- (get res \W) (get res \E))
        y (- (get res \N) (get res \S))]
    (apply + (map #(max % (- %)) [x y]))))

(def state2 {:d \E, \W 0, \N 1, \E 10, \S 0})