(ns sargam.languages
  (:require
   [sargam.talas :as ta]
   [sargam.ragas :as ra]
   [sargam.spec :as us]))

(def lang-labels
  {:english
   {
    :label "English"
    :tala-labels (zipmap (mapv :id ta/all-talas)
                         ["Teentaal" "Jhaptaal" "Ektaal" "Rupak"])
    :raga-labels {:bhup "Bhoop"
                  :hansadhwani "Hansadhwani"
                  :bilaval "Bilaval"
                  :todi "Todi"
                  :tilakkamod "Tilak Kamod"
                  :jogkauns "Jogkauns"
                  :darbari "Darbari"
                  :yaman "Yaman"
                  :kalyan "Kalyan"
                  :shankara "Shankara"
                  :sohini "Sohini"
                  :bhimpalasi "Bhimpalasi"
                  :sarang "Sarang"
                  :kamod "Kamod"
                  :nand "Nand"
                  :desh "Desh"
                  :puriyadhanashri "Puriya Dhanashri"
                  :all "All"}
    :swara-labels (zipmap us/i-note-seq
                          ["S"  "r" "R"  "g" "G" "M" "m" "P" "d" "D" "n" "N" "-" "ऽ"])
    :raga "Raga"}
   :hindi
   {
    :label "हिन्दी"
    :tala-labels (zipmap (mapv :id ta/all-talas)
                         ["तीन्ताल" "झपताल" "एकताल" "रूपक"])
    :raga-labels {:bhup "भूप"
                  :hansadhwani "हम्सध्वनि"
                  :bilaval "बिलावल"
                  :todi "तोडि"
                  :tilakkamod "तिलक कामोद"
                  :jogkauns "जोगकौन्स"
                  :darbari "दरबारी"
                  :yaman "यमन"
                  :kalyan "कल्यण"
                  :shankara "शंकरा"
                  :sohini "सोहिनि"
                  :bhimpalasi "भिम्पलासि"
                  :sarang "सारंग"
                  :kamod "कामोद"
                  :nand "नंद"
                  :desh "देश"
                  :puriyadhanashri "पुरिया धनाश्रि"
                  :all "सारे स्वर"}
    :swara-labels (zipmap us/i-note-seq
                          ["सा" "रे॒" "रे" "ग॒" "ग" "म" "म॑" "प" "ध॒" "ध" "नि॒" "नि" "-" "ऽ"])
    :raga "राग"}})
