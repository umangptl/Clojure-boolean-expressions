# Clojure-boolean-expressions
This Clojure code provides a set of functions to perform symbolic simplification and evaluation of boolean expressions using and, or, and not. The expressions are represented as unevaluated lists, and the code includes sample expressions to demonstrate the functionality.

The code provides the following functions:

- **simplify-and:** Simplifies "and" expressions by handling simple cases.
- **simplify-or:** Simplifies "or" expressions by handling simple cases.
- **simplify-not:** Simplifies "not" expressions by changing "true" to "false", "false" to "true", and handling nested expressions.
- **simplify:** Simplifies nested expressions by applying the appropriate simplification function based on the operator.
- **deepsub:** Substitutes variable values from the bindings map into the expression.
- **evalexp:** Evaluates the expression by simplifying it and substituting variable values.

The code also includes sample expressions (p1, p2, p3, p4, p5) and demonstrates their evaluation using the evalexp function and a bindings map.

Please note that the code assumes the presence of a bindings map containing the values for the variables used in the expressions. The code also handles nested expressions and applies the simplification rules 

```
;; Length 1 Pattern Examples

(and false x) => false
(and x false) => false
(and true x) => x
(and x true) => x

(or false x) => x
(or x false) => x
(or true x) => true
(or x true) => true

(not (not x)) => x

;; Length 2 Pattern Examples

(and x x) => x
(or x x) => x

;; Length 3 Pattern Examples

(or x true y) => true
(and x false y) => false
```
