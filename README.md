
#### Conquer
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
user=> (conquer [0 0] land "W" [])
[]
user=> (conquer [0 0] land "G" [])
[[0 0] [0 1]]
user=> 


```

