(ns cloball.core
  (:require [net.cgrand.enlive-html :as html]))

(def ^:dynamic *sagarin-url* "http://usatoday30.usatoday.com/sports/sagarin/fbt12.htm")
(def ^:dynamic rating        #"^\s+[\d]{1,3}.*$")
(def ^:dynamic noise         #"[\|\(\)=]")
(def sagarin-keys [:ranking :name :division :elo :wins :losses
                   :schedule :schedule_rank
                   :wins_top10 :losses_top10 :wins_top30 :losses_top30
                   :elo_rating :elo_rank :predictor_rating :predictor_rank])

(defn- curl-no-memo [url]
  (slurp (java.net.URL. url)))

(def curl (memoize curl-no-memo))

(def sagarin-text (curl *sagarin-url*))

(def raw-ratings 
  (filter #(re-find rating %)
          (clojure.string/split-lines sagarin-text)))

(def bama (first raw-ratings))

(defn extract-content [coll]
  (let [els (map #(or (:content %) %) coll)]
    (apply str (map #(apply str %) els))))

(defn remove-noise [text]
  (clojure.string/replace text noise ""))

(defn split-on-whitespace [text]
  (clojure.string/split text #"\s+"))

(defn mappify [rating-seq]
  (zipmap sagarin-keys rating-seq))

(defn parse-rating [r]
  (->> r
    html/html-snippet
    extract-content
    remove-noise
    clojure.string/trim
    split-on-whitespace
    mappify))

(defn reload [] (load-file "src/cloball/core.clj"))

(parse-rating bama)
