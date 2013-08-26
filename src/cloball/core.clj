(ns cloball.core
  (:require [cloball.sagarin :as sagarin])
  (:require [net.cgrand.enlive-html :as html]))

(defn team-to-tr [team-map keys]
  (html/html [:tr
                 (map
                   #(html/html [:td (% team-map)])) ]))

(defn all-to-table [ratings]
  (html/html [:table 
              (map #(team-to-tr % [:name :ranking :wins :losses])
                            ratings)]))

(all-to-table sagarin/ratings)

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (all-to-table sagarin/ratings)})

