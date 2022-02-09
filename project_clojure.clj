(ns project-clojure.core)

(declare simplify)

(def p1 '(and x(or x(and y(not z)))))
;; {x false,z true}  =>false
;; {z false} => (and x(or x y)
(def p2 '(and (and z false)(or x true))) ;false
(def p3 '(or true a)) ;true
(def p4 '(not (and y (not x))))
;; {y true} => x
(def p5 '(not (and x y (not z))))
;; {x true} => (or (not y) z)

(defn simplify-and [l]
  "and function simplifies simple cases"
  (let [l (distinct l)]
    (cond
      (some false? l) false
      (= (count (rest (remove true? l))) 1)(first (rest (remove true? l)))
      (some symbol? (rest l))(remove true? l)
      :else true
      )))

(defn simplify-or [l]
  "or function simplifies simple cases"
  (let [l (distinct l)]
    (cond
      (some true? l) true
      (=(count (rest (remove false? l)))1)(first (rest (remove false? l)))
      (some symbol? (rest l))(remove false? l )
      :else false
      )))

(defn simplify-not [l]
  "not function changes true to false and false to true, and handles 'not' nested expression"
  (let [l (distinct l)]
    (cond
      (some true? l) false
      (some false? l) true
      (and (seq? (last l))(= 'not (first (last l))))(last(last l))
      (and (seq? (last l))(= 'and (first (last l))))(simplify (cons 'or (map #(list 'not %)(rest (last l)))))
      (and (seq? (last l))(= 'or (first (last l))))(simplify (cons 'and (map #(list 'not %)(rest (last l)))))
      :else l
      )))

(defn simplify [l]
  "simplify function for nested expressions"
  (cond
    (seq? l)(let[l (map simplify l)]
    (cond
      (= (first l) 'and)(simplify-and l)
      (= (first l) 'or)(simplify-or l)
      :else (simplify-not l)))
   :else l
    ))

(defn deepsub [m l]
  "function used from Clojure class example deepsub and lookup
    modified deepsub to get lookup in same function"
  (map (fn [i]
         (if (seq? i)
           (deepsub m i)
           (get m i i)))
       l))

(defn evalexp [exp bindings]
  "given function used the project description "
  (simplify(deepsub bindings exp)))