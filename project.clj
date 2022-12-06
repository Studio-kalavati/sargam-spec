(defproject studiokalavati/sargam-spec "0.1.12"
  :description "Clojure and json spec for Bhatkhande notation"
  :url "https://github.com/Studio-kalavati/sargam-spec"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories [["releases" {:url "https://repo.clojars.org"
                              :username :env
                              :password :env
                              }]]
  :deploy-repositories [["releases" :clojars]
                       ["snapshots" :clojars]]
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/data.json "0.2.6"] ]
  :repl-options {:init-ns sargam-spec.core})
