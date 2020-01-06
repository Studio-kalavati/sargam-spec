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
    :raga-labels (zipmap (mapv :id ra/all-ragas)
                         ["Bhoop" "Hansadhwani" "Bilaval" "Todi" "Tilak Kamod"
                          "Jogkauns"
                          "Darbari"
                          "Yaman"
                          "All"])
    :swara-labels (zipmap us/i-note-seq
                          ["S"  "r" "R"  "g" "G" "M" "m" "P" "d" "D" "n" "N" "-" "ऽ"])
    :raga "Raga"}
   :hindi
   {
    :label "हिन्दी"
    :tala-labels (zipmap (mapv :id ta/all-talas)
                         ["तीन्ताल" "झपताल" "एकताल" "रूपक"])
    :raga-labels (zipmap (mapv :id ra/all-ragas)
                         ["भूप" "हम्सध्वनि" "बिलावल" "तोडि" "तिलक कामोद"
                          "जोगकौन्स"
                          "दरबारी"
                          "यमन"
                          "सारे स्वर"])
    :swara-labels (zipmap us/i-note-seq
                          ["सा" "रे॒" "रे" "ग॒" "ग" "म" "म॑" "प" "ध॒" "ध" "नि॒" "नि" "-" "ऽ"])
    :raga "राग"}})
