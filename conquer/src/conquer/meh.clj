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
     (cljset/intersection (set [position]) (set memory))  
     )
  )

(defn combine [colls]
  (prn "colls, " colls ".")

  (apply cljset/union
         (filter #(not (nil? %))
                 colls
                              )))

(defn what-next [position land memory]
  (println "\nDEBUG, memory, " memory)
  (prn "DEBUG, position, " position)
  (let [options (map #(% position land)
                     [up down right left])
        ; take out if in memory already though.
        _ (prn "DEBUG, options, " options)
        widdled (filter #(new? % memory) options)
        _ (prn "DEBUG, widdled, " widdled)

        ]
    widdled

    ))

(defn conquer
  [position land mycolor memory]

  ; Went off grid
  (println "\nconquer")
  (println "pos. " position )
  (println "memory. " memory )
  (if (nil? position)
    memory

    (if (and (= mycolor (look position land))
             (new? position memory))

      ;
      (let [next-positions (what-next position land memory)
            _ (prn "DEBUG, next:" next-positions)
            
            capture-vec 
            (map #(conquer %
                           land
                           mycolor

                           ;accumulate memory 
                           (conj memory position)
                           )
                 next-positions)
            _ (prn "DEBUG." (count capture-vec))
            _ (prn "DEBUG. capture-vec, " capture-vec)
            together (combine capture-vec)
            ]
        (prn (str "DEBUG ... " ) together)

        
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
