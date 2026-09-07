(ns sargam.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s :refer [valid?]]
            [clojure.data.json :as json]
            [sargam.spec :as ss]
            [sargam.talas :as talas]
            [sargam.languages :as lang]))

(def t1 {:num-beats 10 :taal-name :jhaptaal
         :taal-label "झपताल"
         :sam-khaali {1 :sam 3 "2" 8 "3" 6 :khaali}
         :bhaags [2 3 2 3]})

(def part-1 {:m-noteseq [[{:note [:madhyam :s]}]]
                  :taala {:num-beats 10 :taal-name :jhaptaal
                          :taal-label "झपताल"
                          :sam-khaali {1 :sam 3 "2" 8 "3" 6 :khaali}
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
                    :sam-khaali {1 :sam 3 "2" 8 "3" 6 :khaali}
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


(defn- marks-in-order
  "sam/khaali labels in the order they are recited across one avartan."
  [taal]
  (let [{:keys [bhaags sam-khaali]} (get talas/taal-def taal)
        bhaag-start-beats (->> bhaags (reductions + 0) butlast (map inc))]
    (mapv #(get sam-khaali %) bhaag-start-beats)))

(deftest tali-numbering
  (testing "khaali is not counted, so the bhaag after it is 3 and not 4"
    (is (= ["x" "2" "o" "3"] (marks-in-order :teentaal)))
    (is (= ["x" "2" "0" "3"] (marks-in-order :jhaptaal))))

  (testing "taals that already skipped khaali are unaffected"
    (is (= ["x" "o" "2" "o" "3" "4"] (marks-in-order :ektaal)))
    (is (= ["o" "1" "2"] (marks-in-order :rupak)))
    (is (= ["x" "o"] (marks-in-order :dadra)))
    (is (= ["x" "o"] (marks-in-order :kehrwa)))
    (is (= ["x" "2" "o" "3" "o" "4" "o"] (marks-in-order :adachautaal))))

  (testing "every taal labels each bhaag and its bhaags add up"
    (doseq [[taal {:keys [bhaags num-beats]}] talas/taal-def]
      (is (= num-beats (apply + bhaags)) (str taal))
      (is (= (count bhaags) (count (marks-in-order taal))) (str taal))
      (is (every? some? (marks-in-order taal)) (str taal)))))

(deftest dhamaar
  (let [{:keys [bhaags num-beats sam-khaali] :as taal} (:dhamaar talas/taal-def)]
    (testing "14 matras in vibhags of 5 2 3 4"
      (is (= [5 2 3 4] bhaags))
      (is (= 14 num-beats))
      (is (= num-beats (apply + bhaags))))

    (testing "sam on 1, tali on 6 and 11, khaali on 8"
      (is (= {1 "x" 6 "2" 8 "o" 11 "3"} sam-khaali))
      (is (= ["x" "2" "o" "3"] (marks-in-order :dhamaar))))

    (testing "valid per the taal spec"
      (is (s/valid? ::ss/taal (assoc taal :taal-label "Dhamaar"))))))

(deftest taal-labels-cover-every-taal
  (testing "each language labels every taal"
    (doseq [[lang-key {:keys [tala-labels]}] lang/lang-labels]
      (is (= (set (keys talas/taal-def)) (set (keys tala-labels)))
          (str lang-key " is missing or has extra taal labels"))))

  ;;the label maps are built by zipmap over (keys taal-def), which preserves order
  ;;only while taal-def has 8 entries or fewer. a 9th silently mispairs them all,
  ;;so assert pairings and not merely presence.
  (testing "labels are paired with the right taal, not merely present"
    (is (= "Teentaal" (:teentaal talas/english-taal-labels)))
    (is (= "Ada Chautaal" (:adachautaal talas/english-taal-labels)))
    (is (= "Dhamaar" (:dhamaar talas/english-taal-labels)))))
