(ns sargam.talas)

(def teentaal {:id :teentaal
               :bhaags [4 8 12 16]
               :sam-khaali {1 "x" 5 "2" 
                            13 "4" 9 "o"}
               :split-points #{5 10 15}
               :num-beats 16})

(def jhaptaal {:id :jhaptaal 
               :bhaags [2 5 7 10]
               :sam-khaali {1 "x" 3 "2" 
                            8 "4" 6 "0"}
               :split-points #{3 7 10}
               :num-beats 10})

(def all-talas [teentaal jhaptaal])

(def english-taal-labels
  (mapv #(assoc (select-keys %1 [:id])
                :label %2)
        all-talas ["Teentaal" "Jhaptaal"]))

(def bhaags (apply merge (mapv (fn[{:keys [id bhaags]}]
                                 {id bhaags}) all-talas)))

(def sam-khali (apply merge (mapv (fn[{:keys [id sam-khaali]}]
                                    {id sam-khaali}) all-talas)))

(def num-beats (apply merge (mapv (fn[{:keys [id num-beats]}]
                                    {id num-beats}) all-talas)))
