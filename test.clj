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
(run-tests)