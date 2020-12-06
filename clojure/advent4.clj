(def data (str/split (slurp "../input4.txt") #"\n\n"))

(def passports
  (for [passport data
        :let [rows (str/split passport #"\s")
              row-keys (map #(str/split % #":") rows)]]
    (apply hash-map (flatten row-keys))))

(defn valid-keys? [passport]
  (let [mapped (reduce #(assoc %1 (first %2) (second %2)) {} passport)]
    (every? true? (map
                   #(contains? mapped %)
                   ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"]))))

(defn in-range? [[min max] val]
  (<= min (Integer/parseInt val) max))

(defn valid-field? [[field val]]
  (case field
    "byr" (in-range? [1920 2002] val)
    "iyr" (in-range? [2010 2020] val)
    "eyr" (in-range? [2020 2030] val)
    "hgt" (if-let [[_ n u] (re-find #"(\d+)(cm|in)\b" val)]
             (case u
               "cm" (in-range? [150 193] n)
               "in" (in-range? [59 76] n)))
    "hcl" (some? (re-matches #"#[0-9a-f]{6}" val))
    "ecl" (some? (re-matches #"(amb|blu|brn|gry|grn|hzl|oth)" val))
    "pid" (some? (re-matches #"[0-9]{9}" val))
    "cid" true))

(defn valid-passport? [passport] 
  (every? true? (map valid-field? passport)))

(def part1 (filter valid-keys? passports))
(def part2 (filter valid-passport? part1))

[(count part1) (count part2)]