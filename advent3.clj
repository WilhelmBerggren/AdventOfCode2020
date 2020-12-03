(def data (map seq (str/split (slurp "input3.txt") #"\n")))

(def height (count data))
(def width (count (first data)))

(defn getPos [x y] (nth (nth data y) (mod x width)))

(defn hits [dx, dy]
  (for [i (range 0 height)
        :let [x (* i dx)
              y (* i dy)
              p (if (< y height) (getPos x y) nil)]]
    (= \# p)))

(defn count-hits [[x y]] 
  (count (filter true? (hits x y))))

(reduce * (map count-hits [[1 1] [3 1] [5 1] [7 1] [1 2]]))