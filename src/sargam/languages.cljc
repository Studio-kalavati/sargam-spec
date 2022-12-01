(ns sargam.languages
  (:require
   [sargam.talas :as ta]
   [sargam.ragas :as ra]
   [sargam.spec :as us]))

(def lang-labels
  {:english
   {
    :label "English"
    :tala-labels ta/english-taal-labels
    :raga-labels ra/english-raga-labels 
    :swara-labels (zipmap us/i-note-seq
                          ["S"  "r" "R"  "g" "G" "M" "m" "P" "d" "D" "n" "N" "-" "ऽ"])
    :raga "Raga"}
   :hindi
   {
    :label "हिन्दी"
    :tala-labels (zipmap (keys ta/taal-def) 
                         ["तीन्ताल" "झपताल" "एकताल" "रूपक" "दाद्रा" "केह्र्वा"])
    :raga-labels {
                  :bilawal "बिलावल"
                  :kalyan "कल्यण"
                  :khamaj "खमाज्"
                  :bhairav "भैरव"
                  :kafi "काफ़ि"
                  :poorvi "पुर्वि"
                  :marwa "मार्वा"
                  :asavari "असावरि"
                  :bhairavi "भैरवि"
                  :todi "तोडि"

                  :bhup "भूप"
                  :hansadhwani "हम्सध्वनि"
                  :tilakkamod "तिलक कामोद"
                  :jogkauns "जोगकौन्स"
                  :darbari "दरबारी"
                  :yaman "यमन"
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
