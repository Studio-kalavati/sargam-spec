(ns sargam.ragas)

;;svaras that are vArjit / not present in each Raga
(def varjit-svaras
  {
   ;;thaats
   :bilawal #{:-r :-g :m+ :-d :-n}
   :kalyan #{:-r :-g :-d :-n}
   :khamaj #{:-r :-g :m+ :-d :n}
   :bhairav #{:r :-g :m+ :d :-n}
   :kafi #{:-r :g :m+ :-d :n}
   :poorvi #{:r :-g :m :d :-n}
   :marwa #{:r :-g :m :-d :-n}
   :asavari #{:-r :g :m+ :d :n}
   :bhairavi #{:r :g :m+ :d :n}
   :todi #{:r :g :m :d :-n}

   :bhup #{:-r :-g :m :m+ :-d :-n :n}
   :hansadhwani #{:-r :-g :m  :m+ :-d :d :-n }
   :tilakkamod #{:-r :-g :m+ :-d}

   :jogkauns #{:-r :r :m+ :d}
   :darbari #{:-r :g :m+ :d :n}
   :yaman #{:-r :-g :m :-d :-n}

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

;;thaats
(def list-of-thats
  [:bilawal :kalyan :khamaj :bhairav
   :kafi :poorvi :marwa :asavari :bhairavi :todi])

(def english-raga-labels
  (zipmap (keys varjit-svaras)
          (map (comp clojure.string/capitalize name) (keys varjit-svaras))))
