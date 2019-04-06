(ns conquer.meh
  (:require 
    [clojure.set :as cljset]
    )
  )

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
           (- (count land) 1)
           )
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

(defn look [position land]
  (let [[x y] position]
    ( (land x) y)))

(defn mine [mycolor thiscolor]
  (= mycolor thiscolor))

(defn new? [position memory]
  (= #{}
     (cljset/intersection (set position) (set memory))  
     )
  )

(defn combine [s1 s2 s3 s4]

  (apply cljset/union
         (filter #(not (nil? %))
                 [s1 s2 s3 s4]
                              )))

(defn conquer
  [position land mycolor memory]

  ; Went off grid
  (if (nil? position)
    memory

    (if (and (= mycolor (look position land))
             (new? position memory))

      ;
      (let [capture-vec 
            (map #(conquer (% position land)
                           land
                           mycolor

                           ;accumulate memory 
                           (conj memory position)
                           )
                 [up down right left])

            ]
        (println (str "DEBUG ... " ) capture-vec )

        (combine capture-vec)
        )
      
      ;
      memory)

    ))



(defn print-land [land]
  (println (cons '. (range (count land))))
  (map println
       (range (count land))
       land
       )
  )
