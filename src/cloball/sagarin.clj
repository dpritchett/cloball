(ns cloball.sagarin
  (:require [net.cgrand.enlive-html :as html]))

(def ^:dynamic *sagarin-url*
  "http://usatoday30.usatoday.com/sports/sagarin/fbt12.htm")
(def rating-pattern        #"^\s+[\d]{1,3}.*$")

(def curl
  (memoize
    (fn [url]
      (slurp (java.net.URL. url)))))

(def sagarin-text (curl *sagarin-url*))

(def raw-ratings 
  (filter #(re-find rating-pattern %)
          (clojure.string/split-lines sagarin-text)))

(defn team-rating [rating-str]
  (let [text (subs rating-str 53 60)
        trimmed (clojure.string/trim text)]
    (Double. trimmed)))

(defn team-name [rating-str]
  (let [text (subs rating-str 6 27)
        trimmed (clojure.string/trim text)]
    trimmed))

(defn parse-rating [r]
  (let [ks [:team :rating]]
    (zipmap ks [(team-name r) (team-rating r)])))

(def ratings (map parse-rating raw-ratings))
