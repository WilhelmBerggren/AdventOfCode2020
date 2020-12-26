;; (def data (str/split (slurp "input8.txt") #"\n"))
(def data '("nop +0"
            "acc +1"
            "jmp +4"
            "acc +3"
            "jmp -3"
            "acc -99"
            "acc +1"
            "jmp -4"
            "acc +6"))

(def items
  (reduce
   (fn [acc i] (assoc acc (first i) (second i)))
   {}
   (map #(str/split % #" ") data)))

items