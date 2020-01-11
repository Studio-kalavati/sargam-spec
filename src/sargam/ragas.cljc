(ns sargam.ragas)

(def varjit-map
  {
   :bhup #{:-r :-g :m :m+ :-d :-n :n}
   :hansadhwani #{:-r :-g :m  :m+ :-d :d :-n }
   :bilawal #{:-r :-g :m+ :-d :-n}
   :todi #{:r :g :m :d :-n}
   :tilakkamod #{:-r :-g :m+ :-d}

   :jogkauns #{:-r :r :m+ :d}
   :darbari #{:-r :g :m+ :d :n}
   :yaman #{:-r :-g :m :-d :-n}
   :kalyan #{:-r :-g :-d :-n} 

   :shankara #{:-r :-g :m :m+ :-d :-n}
   :sohini #{:r :-g :m :p :-d :-n}
   :bhimpalasi #{:-r :g :m+ :-d :n}
   :sarang #{:-r :-g :g :m+ :d :-d}

   :kamod #{:-r :-g :-d :n :-n}
   :nand #{:-r :-g  :-d :-n}
   :desh #{:-r :-g :m+ :-d }
   :puriyadhanashri #{:r :-g :m :d :-n}
   :all #{}

   })
(comment
  (def bhup {:varjit-swaras #{:-r :-g :m :m+ :-d :-n :n} :id :bhup})

  (def hansadhwani {:varjit-swaras #{:-r :-g :m  :m+ :-d :d :-n } :id :hansadhwani})

  (def bilawal {:varjit-swaras #{:-r :-g :m+ :-d :-n} :id :bilawal})

  (def todi {:varjit-swaras #{:r :g :m :d :-n} :id :todi})

  (def tilakkamod {:varjit-swaras #{:-r :-g :m+ :-d } :id :tilakkamod})

  (def jogkauns {:varjit-swaras #{:-r :r :m+ :d} :id :jogkauns})

  (def darbari {:varjit-swaras #{:-r :g :m+ :d :n} :id :darbari})

  (def yaman {:varjit-swaras #{:-r :-g :m :-d :-n} :id :yaman})

  (def kalyan {:varjit-swaras #{:-r :-g :-d :-n} :id :kalyan})

  (def shankara {:varjit-swaras #{:-r :-g :m :m+ :-d :-n} :id :shankara})

  (def sohini {:varjit-swaras #{:r :-g :m :p :-d :-n} :id :sohini})

  (def bhimpalasi {:varjit-swaras #{:-r :g :m+ :-d :n} :id :bhimpalasi})

  (def sarang {:varjit-swaras #{:-r :-g :g :m+ :d :-d} :id :sarang})

  (def kamod {:varjit-swaras #{:-r :-g :-d :n :-n} :id :sarang})

  (def nand {:varjit-swaras #{:-r :-g  :-d :-n} :id :nand})

  (def desh {:varjit-swaras #{:-r :-g :m+ :-d } :id :desh})

  (def puriyadhanashri {:varjit-swaras #{:r :-g :m :d :-n } :id :puriyadhanashri })

  (def all-swaras {:varjit-swaras #{} :id :all})

  )
;;short list of ragas
#_(def simple-ragas [bhup hansadhwani bilawal todi tilakkamod jogkauns
                   darbari
                   yaman
                   all-swaras])

;;all the ragas defined here
#_(def all-ragas simple-ragas)


;;deprecated
(comment (def english-raga-labels
           (mapv #(assoc (select-keys %1 [:id])
                         :label %2)
                 all-ragas
                 ["Bhup" "Hansadhwani" "Bilawal" "Todi" "Tilak Kamod" "Jogkauns"])))

;;(def varjit-swaras varjit-swara-map)
