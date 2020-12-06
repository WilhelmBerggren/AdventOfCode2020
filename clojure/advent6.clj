(def data (str/split (slurp "../inputs/input6.txt") #"\R\R"))

(def groups (map #(map set (str/split-lines %)) data))

(def part1 (apply + (map #(count (apply set/union %)) groups)))

(def part2 (apply + (map #(count (apply set/intersection %)) groups)))

[part1 part2]