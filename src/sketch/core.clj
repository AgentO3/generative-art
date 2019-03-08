(ns sketch.core
  (:require [quil.core :as q]]
  (:gen-class))

(defn refresh 
  [title setup draw size]
  (q/defsketch s
    :title title
    :setup setup
    :draw draw
    :size size))

