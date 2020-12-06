(def data (str/split (slurp "../inputs/input6.txt") #"\n\n"))

(def groups (map #(set (str/split % #"\s")) data))

(def part1 (reduce + (map #(count (apply set/union (map set %))) groups)))

(def part2 (reduce + (map #(count (apply set/intersection (map set %))) groups)))

[part1 part2]