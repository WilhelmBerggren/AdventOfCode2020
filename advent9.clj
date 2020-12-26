(def data (vec (map edn/read-string (str/split (slurp "input9.txt") #"\n"))))

(def offset 25)

(def part1
  (first
   (for [i (range offset (dec (count data)))
         :let [num (nth data i)
               prev (subvec data (- i offset) i)
               sums (for [n1 prev
                          n2 (drop-last prev)]
                      (+ n1 n2))]
         :when (not-any? #(= num %) sums)]
     num)))

(def part2
  (first
   (for [i1 (range (dec (count data)))
         i2 (range i1 (count data))
         :let [ar (subvec data i1 i2)]
         :when (= part1 (apply + ar))]

     (+ (apply max ar)
        (apply min ar)))))

[part1 part2]