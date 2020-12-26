(def data (str/split (slurp "input6.txt") #"\n\n"))
(def d (str/split "abc

a
b
c

ab
ac

a
a
a
a

b" #"\n\n"))

(def item (first data))

(defn q-count [item]
  (map #(flatten (distinct %)) (str/split item #"\s")))

(map q-count d)