(ns sargam.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s :refer [valid?]]
            [clojure.data.json :as json]
            [sargam.spec :as ss]))

(def t1 {:num-beats 10 :taal-name :jhaptaal
         :taal-label "झपताल"
         :sam-khaali {1 :sam 3 "2" 8 "4" 6 :khaali}
         :bhaags [2 3 2 3]})

(def part-1 {:m-noteseq [[{:note [:madhyam :s]}]]
                  :taala {:num-beats 10 :taal-name :jhaptaal
                          :taal-label "झपताल"
                          :sam-khaali {1 :sam 3 "2" 8 "4" 6 :khaali}
                          :bhaags [2 3 2 3]}
                  :part-id "0xfafacaca"})

(defn round-trip
  [i]
  (-> i
       json/write-str
       (json/read-str :value-fn (fn[k v]
                                  ;(println " k " k " v " v)
                                  (cond (= :note k)
                                        (mapv keyword v)
                                        :default v))
                      :key-fn (fn [i] (try (let [k  (Integer/parseInt i)]
                                             k)
                                           (catch Exception e
                                             (keyword i)))))))

(def  comp1 {:parts  [{:m-noteseq [[{:note [:madhyam :s]}]]
                       :part-id "0xfafacaca"}
                      {:m-noteseq [[{:note [:madhyam :r]}]]
                       :part-id "0xfafacacd"}]
             :taal {:num-beats 10 :taal-name :jhaptaal
                    :taal-label "झपताल"
                    :sam-khaali {1 :sam 3 "2" 8 "4" 6 :khaali}
                    :bhaags [2 3 2 3]}
             :comp-id "cacaddad"})

(deftest testspec 
  (testing "taal"
    (is (not (s/valid? ::ss/taal (dissoc t1 :num-beats))))
    (is (s/valid? ::ss/taal (round-trip t1)))
    (is (s/valid? ::ss/taal t1)))
  (testing "comp-part"
    (is (not (s/valid? ::ss/comp-part (dissoc part-1 :m-noteseq))))
    (is (s/valid? ::ss/comp-part (round-trip part-1)))
    (is (s/valid? ::ss/comp-part part-1)))

  (testing "comp "
    (is (not (s/valid? ::ss/composition (dissoc comp1 :parts))))
    (is (not (s/valid? ::ss/composition (dissoc comp1 :taal))))
    (is (s/valid? ::ss/composition (round-trip comp1)))
    (is (s/valid? ::ss/composition comp1))
    )
  )

