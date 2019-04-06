
#### Conquer
* Given a _grid_ with colors, and a starting position, accumulate (_aka conquer_) some land.

```clojure
(def land
  [["G" "G" "W" "R"]
   ["G" "G" "W" "R"]
   ["G" "W" "R" "W"]
   ["R" "W" "W" "W"]
   ])

user=> (print-land land)
(. 0 1 2 3)
0 [G G W R]
1 [G G W R]
2 [G W R W]
3 [R W W W]
(nil nil nil nil)
```
* Examples, 
```clojure
conquer.core=> (require '[conquer.meh :as meh])

conquer.core=> (meh/conquer [3 1] meh/land "W" #{}) 
#{[2 3] [3 3] [3 1] [2 1] [3 2]}
conquer.core=> (meh/conquer [1 1] meh/land "W" #{}) 
#{}
conquer.core=> (meh/conquer [1 1] meh/land "G" #{}) 
#{[0 0] [1 0] [1 1] [2 0] [0 1]}
conquer.core=> (meh/conquer [0 3] meh/land "R" #{}) 
#{[1 3] [0 3]}
conquer.core=> 


```

#### Earlier, Simple case at commit `81ae122`

* If we start with `"W"`, nothing is returned, because `conquer` only continues what was started.
```clojure
user=> (conquer [0 0] land "W" [])
[]
```
* But starting with `"G"`, there is opportunity to grow now.
```clojure
user=> (conquer [0 0] land "G" [])
[[0 0] [0 1]]
user=> 


```

