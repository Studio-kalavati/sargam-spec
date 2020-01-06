(ns sargam.talas)

(def teentaal {:id :teentaal
               ;;note, it is not the actual location of the bhaag,
               ;;but the notes in each bhaag.
               ;;this reads: 4 notes in the first one, 4 in the second and so on.
               ;;for jhaptaal, it is, [ 2 3 2 3], which means 2 in the first, 3 in the second and  so on.
               :bhaags [4 4 4 4]
               :sam-khaali {1 "x" 5 "2"
                            13 "4" 9 "o"}
               :split-points #{5 10 15}
               :num-beats 16})

(def jhaptaal {:id :jhaptaal
               :bhaags [2 3 2 3]
               :sam-khaali {1 "x" 3 "2"
                            8 "4" 6 "0"}
               :split-points #{3 7 10}
               :num-beats 10})

(def ektaal {:id :ektaal
              :bhaags [2 2 2 2 2 2]
              :sam-khaali {1 "x" 3 "o"
                           5 "2" 7 "o"
                           9 "3" 11 "4"}
              :split-points #{4 7 10}
             :num-beats 12})

(def rupak {:id :rupak
             :bhaags [3 2 2]
             :sam-khaali {1 "o" 4 "1"
                          6 "2"}
             :split-points #{1 4 6}
             :num-beats 7})

(def all-talas [teentaal jhaptaal ektaal rupak])

;;this is not used by the web app. Instead, define it in languages.cljc
(def english-taal-labels
  (mapv #(assoc (select-keys %1 [:id])
                :label %2)
        all-talas ["Teentaal" "Jhaptaal" "Ektaal"]))

(def bhaags (apply merge (mapv (fn[{:keys [id bhaags]}]
                                 {id bhaags}) all-talas)))

(def sam-khali (apply merge (mapv (fn[{:keys [id sam-khaali]}]
                                    {id sam-khaali}) all-talas)))

(def num-beats (apply merge (mapv (fn[{:keys [id num-beats]}]
                                    {id num-beats}) all-talas)))
