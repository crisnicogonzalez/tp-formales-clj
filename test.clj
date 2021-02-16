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

(run-tests)