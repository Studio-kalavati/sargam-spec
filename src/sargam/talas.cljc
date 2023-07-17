(ns sargam.talas)

(def taal-def
  {:teentaal
   {
    ;;note, it is not the actual location of the bhaag,
    ;;but the notes in each bhaag.
    ;;this reads: 4 notes in the first one, 4 in the second and so on.
    ;;for jhaptaal, it is, [2 3 2 3], which means 2 in the first, 3 in the second and  so on.
    ;;for teentaal, it is, [4 4 4 4], which means 4 beats in the first bhaag, 4 in the second
    ;;and so on.
    :bhaags [4 4 4 4]
    :sam-khaali {1 "x" 5 "2" 13 "4" 9 "o"}
    :num-beats 16}
   :jhaptaal
   {:bhaags [2 3 2 3]
    :sam-khaali {1 "x" 3 "2" 8 "4" 6 "0"}
    :num-beats 10}
   :ektaal
   {
    :bhaags [2 2 2 2 2 2]
    :sam-khaali {1 "x" 3 "o"
                 5 "2" 7 "o"
                 9 "3" 11 "4"}
    :num-beats 12}
   :rupak
   {
    :bhaags [3 2 2]
    :sam-khaali {1 "o" 4 "1" 6 "2"}
    :num-beats 7}
   :dadra
   {
    :bhaags [3 3]
    :sam-khaali {1 "x" 4 "o"}
    :num-beats 6}
   :kehrwa
   {
    :bhaags [4 4]
    :sam-khaali {1 "x" 5 "o"}
    :num-beats 8}
   :adachautal
    {
     :bhaags [2 2 2 2 2 2 2]
     :sam-khaali {1 "x" 3 "2" 7 "3" 11 "4" 5 "o" 9 "o" 13 "o"}
     :num-beats 14}
   }
  )

(def english-taal-labels
  (zipmap (keys taal-def) ["Teentaal" "Jhaptaal" "Ektaal" "Rupak" "Dadra" "Kehrwa" "Ada Chautal"]))
