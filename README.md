
#### Conquer
* Given a _grid_ with colors, and a starting position, accumulate (_aka conquer_) some land.
* Simple case at commit `81ae122`

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

