(def data (str/split (slurp "input11.txt") #"\n"))
(def test-data (str/split
"L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL" #"\n"))

(def first-seats (mapv #(apply vector (seq %)) test-data))
(def row-length (count (first first-seats)))
(def row-count (count first-seats))

(defn get-pos [seats x y] (nth (nth seats x) y))

(defn get-adjacent [seats posx posy]
  (for [x (range (- posx 1) (+ posx 2))
        y (range (- posy 1) (+ posy 2))]
    (when (and
           (< -1 x row-count)
           (< -1 y row-length))
      (get-pos seats x y))))

(defn all-adjacent [seats]
  (for [x (range (count (first seats)))
        y (range (count seats))]
    (get-adjacent seats x y)))

(defn char-and-count [seats]
  (map
   #(list
     (nth % 4)
     (count (filter (partial = \#) %)))
   (all-adjacent seats)))

(defn char-and-switched [seats]
  (map (fn [[c adj]]
         (let [new-c (case c
                       \# (if (> adj 4) \L \#)
                       \L (if (= adj 0) \# \L)
                       \.)]
           (list new-c (not (= c new-c)))))
       (char-and-count seats)))

(defn update-and-count-switched [seats]
  (let [res (char-and-switched seats)
        switched (count (filter true? (map second res)))
        new-seats (map first res)]
    (list switched (mapv (partial apply vector)
                         (partition row-length new-seats)))))

(loop [[n seats] [1 first-seats]]
  ;; (println (str/join "\n" (map #(apply str %) seats)))
  (println n)
  (if (> n 0)
    (recur (update-and-count-switched seats))
    (count (filter (partial = \#) (flatten seats)))))