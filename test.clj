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


(deftest test-precedencia
         (is (= 1 (precedencia 'OR)))
         (is (= 2 (precedencia 'AND)))
         (is (= 6 (precedencia '*)))
         (is (= 7 (precedencia '-u)))
         (is (= 9 (precedencia 'MID$)))
         )


(deftest test-aridad
         (is (= 0 (aridad 'THEN)))
         (is (= 1 (aridad 'SIN)))
         (is (= 2 (aridad '*)))
         (is (= 2 (aridad 'MID$)))
         (is (= 3 (aridad 'MID3$)))
         )


(deftest test-cargar-linea
         (is (= ['((10 (PRINT X))) [:ejecucion-inmediata 0] [] [] [] 0 {}]  (cargar-linea '(10 (PRINT X)) [() [:ejecucion-inmediata 0] [] [] [] 0 {}]) ))
         )





(deftest test-no-next-no-comma
        (is (= false (no-next-no-comma 'NEXT)))
         (is (= false (no-next-no-comma (symbol ",") )))
         )

(deftest test-agregar-next-symbol
         (is (= '(NEXT A) (agregar-next-symbol 'A)))
         )


(deftest test-map-expandir-next
        (is (= '((NEXT A) (NEXT B)) (map-expandir-next (list 'NEXT 'A (symbol ",") 'B))))
         )



(deftest test-expandir-next
         (is (= '((PRINT 1) (NEXT A) (NEXT B)) (expandir-nexts (list '(PRINT 1) (list 'NEXT 'A (symbol ",") 'B)) ) ))
         )


(deftest test-contar-sentencias
         (is (= 2 (contar-sentencias 10 [(list '(10 (PRINT X) (PRINT Y)) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) [10 1] [] [] [] 0 {}])))
         (is (= 1 (contar-sentencias 15 [(list '(10 (PRINT X) (PRINT Y)) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) [10 1] [] [] [] 0 {}])))
         (is (= 1 (contar-sentencias 20 [(list '(10 (PRINT X) (PRINT Y)) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) [10 1] [] [] [] 0 {}])))
  )



(deftest test-map-representacion-intermedia
         (is (= '(10 (PRINT X) (PRINT Y))  (map-representacion-intermedia '(10 (PRINT X) (PRINT Y)) 10 2)))
         (is (= '(10 (PRINT X))  (map-representacion-intermedia '(10 (PRINT X) (PRINT Y)) 10 1)))
         (is (= '(10)  (map-representacion-intermedia '(10 (PRINT X) (PRINT Y)) 10 0)))
         )


(deftest test-buscar-lineas-restantes
         (is (= (buscar-lineas-restantes [(list '(10 (PRINT X) (PRINT Y)) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) [10 2] [] [] [] 0 {}]) (list '(10 (PRINT X) (PRINT Y)) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J)))) )
         (is (= (buscar-lineas-restantes [(list '(10 (PRINT X) (PRINT Y)) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) [10 1] [] [] [] 0 {}]) (list '(10 (PRINT X)) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J)))) )
         (is (= (buscar-lineas-restantes [(list '(10 (PRINT X) (PRINT Y)) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) [10 0] [] [] [] 0 {}]) (list '(10) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J)))) )
         (is (= (buscar-lineas-restantes [(list '(10 (PRINT X) (PRINT Y)) '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) [15 1] [] [] [] 0 {}]) (list '(15 (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) ))
         )


(deftest test-actualizar-puntero
         (is (= (actualizar-puntero [(list '(10 (PRINT X)) '(15 (GOSUB 100) (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) [20 3] [[15 2]] [] [] 0 {}]) [(list '(10 (PRINT X)) '(15 (GOSUB 100) (X = X + 1)) (list 20 (list 'NEXT 'I (symbol ",") 'J))) [15 2] [] [] [] 0 {}]))
         )
(run-tests)