(ns sargam.ragas)

(def bhup {:varjit-swaras #{:-r :-g :m :m+ :-d :-n :n}
           :id :bhup})

(def hansadhwani {:varjit-swaras #{:-r :-g :m  :m+ :-d :d :-n }
                  :id :hansadhwani})

(def bilawal {:varjit-swaras #{:-r :-g :m+ :-d :-n}
              :id :bilawal})

(def todi {:varjit-swaras #{:r :g :m :d :-n}
           :id :todi})

(def tilakkamod {:varjit-swaras #{:-r :-g :m+ :-d }
                 :id :tilakkamod})

(def jogkauns {:varjit-swaras #{:-r :r :m+ :d}
               :id :jogkauns})
;;short list of ragas
(def simple-ragas [bhup hansadhwani bilawal todi tilakkamod jogkauns])

;;all the ragas defined here
(def all-ragas [bhup hansadhwani bilawal todi tilakkamod jogkauns])

(def english-raga-labels
  (mapv #(assoc (select-keys %1 [:id])
                :label %2)
        all-ragas
        ["Bhup" "Hansadhwani" "Bilawal" "Todi" "Tilak Kamod" "Jogkauns"]))
(def varjit-swaras (apply merge (mapv (fn[{:keys [id varjit-swaras]}]
                                        {id varjit-swaras})
                                      all-ragas)))
