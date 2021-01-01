;; (def rows (str/split (slurp "input19_3.txt") #"\n"))
;; (def rows ["R75,D30,R83,U83,L12,D49,R71,U7,L72"
;;            "U62,R66,U55,R34,D71,R55,D58,R83"])
;; (def rows ["R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"
;;            "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"])
(def rows ["R8,U5,L5,D3" "U7,R6,D4,L4"])
(def line-1 (str/split (first rows) #","))
(def line-2 (str/split (second rows) #","))

(defn to-coords [[x y] dir]
  (let [d (first dir)
        n (read-string (rest dir))]
    [(case d \R (+ x n), \L (- x n), x)
     (case d \U (+ y n), \D (- y n) y)]))

(defn all-points [dirs]
  (loop [dirs dirs
         point [0 0]
         points [point]]
    (if (= 0 (count dirs))
      points
      (let [[dir & dirs'] dirs
            point' (to-coords point dir)
            points' (conj points point')]
        (recur dirs' point' points')))))

(defn connect-points [[x1 y1] [x2 y2]]
  (let [should-reverse (or (< x2 x1) (< y2 y1))
        x1' (min x1 x2)
        x2' (max x1 x2)
        y1' (min y1 y2)
        y2' (max y1 y2)
        coords (for [x (range x1' (inc x2'))
                     y (range y1' (inc y2'))]
                 [x y])]
    (if should-reverse (reverse coords) coords)))

(defn all-in-line [line]
  (let [p (all-points line)
        connected (map #(apply connect-points %) (partition 2 1 p))
        all (reduce (fn [acc i] (apply (partial conj acc) (rest i))) [[0 0]] connected)]
    all))

(let [al1 (all-in-line (all-points line-1))
      al2 (all-in-line (all-points line-2))]
  al1)

(defn abs [n] (max n (- n)))

;; (def crossings (let [coords-1 (set coords-1)
;;                      coords-2 (set coords-2)]
;;                  (seq (set/intersection coords-1 coords-2))))

(defn index-of-coord [coord, coords]
  (first (keep-indexed (fn [i x] (when (= x coord) i)) coords)))

(def part1 (second
            (sort (for [cross crossings
                        :let [d (+ (abs (first cross)) (abs (second cross)))]]
                    d))))
;; 1195

(def part2
  (second
   (sort (map
          #(+ (index-of-coord % coords-1) (index-of-coord % coords-2))
          crossings))))

;; crossings
;; coords-1
;; (first line-1)
;; (sort (fn [[a b][c d]] (- (+ c d) (+ a b))) (subvec coords-1 140 150))

[part1 part2]

#_(defn part2coords [dirs]
  (loop [dirs dirs
         point [0 0]
         points [[0 0]]]
    (if (= 0 (count dirs))
      points
      (let [point' (to-coords point (first dirs))
            points' (conj points point')]
        (recur (rest dirs) point' points')))))

;; (second (let [c (partition 2 1 (part2coords line-2))]
;;          (map (partial apply all-coords) [(first c) (second c)])))