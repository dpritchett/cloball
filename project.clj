(defproject cloball "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "Eclipse Public License"
                      :url "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.5.1"]
                           [enlive "1.1.2"]
                           [ring/ring-core "1.2.0"]
                           [org.clojure/tools.namespace "0.2.4"]
                           [ring/ring-jetty-adapter "1.2.0"]
                           [com.novemberain/monger "1.5.0"] ]
            :profiles {:dev
                       {:dependencies
                        [[org.clojure/tools.namespace "0.2.4"]]}} )
