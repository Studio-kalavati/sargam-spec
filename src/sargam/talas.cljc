(ns sargam.talas)

(def teentaal {:id :teentaal
               :bhaags [4 8 12 16]
               :sam-khaali {1 :sam 5 "2" 
                            13 "4" 9 :khaali}

               :split-points #{5 10 15}
               :num-beats 16})

(def jhaptaal {:id :jhaptaal 
               :bhaags [2 5 7 10]
               :sam-khaali {1 :sam 3 "2" 
                            8 "4" 6 :khaali}
               :split-points #{3 7 10}
               :num-beats 10})

(def all-talas [teentaal jhaptaal])
