(require '[clojure.test :refer [is deftest run-tests]])

(load-file "basic.clj")

(deftest test-palabra-reservada?
         (is (= true (palabra-reservada? 'REM)))
         (is (= false (palabra-reservada? 'SPACE)))
         (is (= true (palabra-reservada? 'DATA)))
         (is (= true (palabra-reservada? 'READ)))
         )

(deftest test-operador?
         (is (= true (operador? '+)))
         (is (= true (operador? (symbol "+"))))
         (is (= false (operador? (symbol "%"))))
         )

(deftest test-variable?
         (is (= true (variable? 'A)))
         (is (= true (variable? 'B)))
         (is (= true (variable? 'C)))
         (is (= true (variable? 'D)))
         (is (= true (variable? 'E)))
         (is (= true (variable? 'F)))
         (is (= true (variable? 'G)))
         (is (= true (variable? 'H)))
         (is (= true (variable? 'I)))
         )

(deftest test-es-valido?
         (is (= true (valido? '+)))
         (is (= true (valido? 'REM)))
         (is (= false (valido? 'SPACE)))
         (is (= true (valido? (symbol "+")) ))
         (is (= true (valido? (symbol "+")) ))
         )


(deftest test-anular-invalidos
         (is (= '(IF) (anular-invalidos '(IF))))
         (is (= '(nil) (anular-invalidos '(&))))
         )

(deftest test-convertir-a-string
          (is (= "A" (convertir_a_string 'A)))
          (is (= "1.5" (convertir_a_string 1.5)))
          (is (= "1.5" (convertir_a_string 1.50)))
          (is (= "1.0" (convertir_a_string 1.0)))
          )


(deftest test-separar-por-punto
         (is ( = '("0" "1") (separar_por_punto "0.1")))
         )


(deftest test-eliminar-cero-a-entero-part
         (is (= ".1" (eliminar-cero-a-entero-part "0.1")))
         (is (= "-.1" (eliminar-cero-a-entero-part "-0.1")))
         (is (= "-1.5" (eliminar-cero-a-entero-part "-1.5")))
         (is (= "-1" (eliminar-cero-a-entero-part "-1")))
         (is (= "1" (eliminar-cero-a-entero-part "1")))
         (is (= "0" (eliminar-cero-a-entero-part "0")))
  )



(deftest test-eliminar-cero-a-entero
         (is (= ".1" (eliminar-cero-entero "0.1")))
         (is (= "-.1" (eliminar-cero-entero "-0.1")))
         (is (= "-1.5" (eliminar-cero-entero "-1.5")))
         (is (= "-1" (eliminar-cero-entero "-1")))
         (is (= "1" (eliminar-cero-entero "1")))
         (is (= "0" (eliminar-cero-entero "0")))
         (is (= nil (eliminar-cero-entero nil)))
         (is (= "A" (eliminar-cero-entero 'A)))
         )


(deftest test-eliminar-cero-decimal
         (is (= 1 (eliminar-cero-decimal 1.0)))
         (is (= 1.5 (eliminar-cero-decimal 1.50)))
         (is (= 1.5 (eliminar-cero-decimal 1.5)))
         (is (= 1 (eliminar-cero-decimal 1)))
         (is (= "A" (eliminar-cero-decimal 'A)))
         )


(deftest test-variable-string
         (is (= true (variable-string? 'X$)))
         (is (= false (variable-string? 'X)))
         (is (= false (variable-string? 'X%)))
         )


(deftest test-variable-integer
         (is (= false (variable-integer? 'X$)))
         (is (= false (variable-integer? 'X)))
         (is (= true (variable-integer? 'X%)))
         )
(run-tests)