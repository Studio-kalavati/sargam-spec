(ns sargam.spec
(:require 
          #?(:clj  [clojure.spec.alpha :as s :refer [valid?]] 
             :cljs [cljs.spec.alpha :as s :include-macros true :refer [valid?]])))

(def ^{:doc "vector of swaras in an octave that an be displayed: 12 notes plus vishram and avagraha"}
  i-note-seq
  [:s :-r :r :-g :g :m :m+ :p :-d :d :-n :n :- :a])

(def
  ^{:doc "the set of all swaras in an an octave"}
  i-note
  (set i-note-seq))
;;5 octaves
(def
  ^{:doc "the set of five octaves supported:

          ati-mandra, mandra, madhyam, taar and ati-taar "}
  saptak #{:ati-mandra :mandra :madhyam :taar :ati-taar})


;;a note consists of a saptak and swara
(s/def ::note 
  (s/and (s/coll-of #(or (i-note %)
                         (saptak %))
                    :kind vector?
                    :count 2)
         #(and (i-note (second %))
              (saptak (first %))))
  )
(valid? ::note [:ati-mandra :s])
(valid? ::note [:s :ati-mandra ]);;false


;;a note can have decorations such as
;; kan swara
(s/def ::kan ::note)
(valid? ::kan [:mandra :g]);;true

;;
;; khatka ()
(s/def ::khatka boolean?)
(valid? ::khatka true)
(valid? ::khatka 0) ;;false

;;meends/glides could start or end on a swara
(s/def ::meend-start boolean?)
(s/def ::meend-end boolean?)

;;list of possible decorators for a swara
(s/def ::decorator #{::meend-start ::meend-end ::khatka ::kan})

;;this is a bit incorrect, a note can have either kan swara, or khatka, or meend-start/end, but not all together
;;appear that the (or keys) form works only in :req and not in :opt
(s/def ::s-note (s/keys :req-un [::note]
                        :opt-un [::kan ::khatka ::meend-start ::meend-end]))

(s/valid? ::s-note {:note [:madhyam :s] 
                  :kan [:madhyam :r] 
                  :khatka true
                  :meend-start true})
(s/def ::sahitya string?)

;;collection of notes in one matra
(s/def ::m-note (s/coll-of ::s-note :kind vector? :max-count 10 :min-count 1))
(valid? ::m-note [{:note [:madhyam :s]
                     :kan [:madhyam :r]}
                    {:note [:madhyam :g]}])

;;notes with sahitya
(s/def ::m-s-note (s/keys :req-un [::m-note ::sahitya]))
(valid? ::m-s-note {:m-note [{:note [:madhyam :s]}] :sahitya "Hi"})

;;a sequence of notes
(s/def ::m-noteseq (s/coll-of ::m-note :kind vector? :min-count 1))
(valid? ::m-noteseq [[{:note [:madhyam :s]}]])

;;a sequence of notes + sahitya
(s/def ::ms-noteseq (s/coll-of ::m-s-note :kind vector? :min-count 1))
(valid? ::ms-noteseq [{:m-note [{:note [:madhyam :s]}]
                      ::sahitya "sarega"}])

;;taal definitions
;;assuming less than 24 beats
(s/def ::num-beats (s/and int? #(and (> % 0 ) (<= % 24))))
(s/def ::taal-name string?)
;;display name, incase multi language support needed
(s/def ::taal-label string?)
(s/def ::bhaags (s/coll-of int? :kind vector? :min-count 2))
(s/def ::sam-khaali (s/and map? #(every? int? (keys %))))
(valid? ::sam-khaali {1 2})
(valid? ::sam-khaali {1 2 "a" "b"}) ;;false

(s/def ::taal (s/and (s/keys :req-un [::num-beats ;::taal-name
                                      ::taal-label
                                    ::bhaags ::sam-khaali])
                     ;;all the bhaags divisions should be less than total number of beats
                     #(= (:num-beats %) (apply + (:bhaags %)))
                     ;;each sam, taali or khaali should be less than total beats
                     #(every? (fn[i] (>= (:num-beats %) i)) (keys (:sam-khaali %)))
                     ))

(valid? ::taal {:num-beats 10 :taal-name "jhaptaal"
                  :taal-label "झपताल"
                  :sam-khaali {1 :sam 3 "2" 8 "4" 6 :khaali}
                  :bhaags [2 3 2 3]})

;;sam-khaali greater than 10
(valid? ::taal {:num-beats 10 :taal-name "jhaptaal"
                  :taal-label "झपताल"
                  :sam-khaali {1 :sam 3 "2" 11 "4" 6 :khaali}
                  :bhaags [2 3 2 3]});;false

;;bhaags don't add up to 10
(valid? ::taal {:num-beats 10 :taal-name "jhaptaal"
                  :taal-label "झपताल"
                  :sam-khaali {1 :sam 3 "2" 8 "4" 12 :khaali}
                  :bhaags [2 5 7 10]});false

;;composition part e.g. sthahi/antara/taan
(s/def ::part-num int?)
(s/def ::part-label string?)
(s/def ::part-id string?)
;;taal is optional, incase the composition specifies the same taal for all parts
(s/def ::comp-part (s/keys :req-un [::m-noteseq]
                           :opt-un [::part-id ::taal ::part-num ::part-label]))
(valid? ::comp-part {:m-noteseq [[{:note [:madhyam :s]}]]
                     :taal {:num-beats 10 :taal-name :jhaptaal
                            :taal-label "झपताल"
                            :sam-khaali {1 :sam 3 "2" 8 "4" 6 :khaali}
                            :bhaags [2 3 2 3]}
                     :part-id "0xfafacaca"})
;;composition consisting of many parts
(s/def ::parts (s/coll-of ::comp-part :kind vector? :min-count 1))
(s/def ::comp-label string?)
(s/def ::comp-id string?)

(s/def ::composition (s/keys :req-un [::parts  ::taal]
                             :opt-un [::comp-label ::comp-id]))

(s/valid? ::composition {:parts  [{:m-noteseq [[{:note [:madhyam :s]}]]
                                   :part-id "0xfafacaca"}
                                  {:m-noteseq [[{:note [:madhyam :r]}]]
                                   :part-id "0xfafacacd"}]
                         :taal {:num-beats 10 :taal-name :jhaptaal
                                 :taal-label "झपताल"
                                 :sam-khaali {1 :sam 3 "2" 8 "4" 6 :khaali}
                                 :bhaags [2 3 2 3]}
                         :comp-id "cacaddad"})


