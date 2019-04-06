
(defn sum
   ([vals] (sum vals 0)) 
  ([vals accumulating-total]
     (if (empty? vals)  
       accumulating-total
       (sum (rest vals) (+ (first vals) accumulating-total)))))

(def land
  [["G" "G" "W" "R"]
   ["G" "G" "W" "R"]
   ["G" "W" "R" "W"]
   ["R" "W" "W" "W"]
   ])

(defn up [position land]
  (let [[x y] position]
    ; magic
    (if (> x 0) [(- x 1) y])
    ))

(defn down [position land]
  (let [[x y] position]
    ; magic
    (if (< x
           (- (count land))
           1)
      [(+ x 1) y])
    )
  )

(defn left [position land]
  (let [[x y] position]
    ; magic
    (if (> y 0) [x (- y 1)])
    )
  )

(defn right [position land]
  (let [[x y] position]
    ; magic
    (if (< y
           (- 
             (count (land 0))
             1)
           )
      [x (+ y 1)])
    )
  )

(defn mine [mycolor thiscolor]
  (= mycolor thiscolor))

(defn conquer
  [position land mycolor memory]
  (if (nil? position)

    memory

    (if (= mycolor (look position land))

      ;
      (conquer (right position land)
               land
               mycolor

               ;accumulate memory 
               (conj memory position)
               )
      ;
      memory)

    ))

(defn look [position land]
  (let [[x y] position]
    ( (land x) y)))


(defn print-land [land]
  (println (cons '. (range (count land))))
  (map println
       (range (count land))
       land
       )
  )
