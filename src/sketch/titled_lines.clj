(ns sketch.titled-lines
  (:require [quil.core :as q])
  (:gen-class))

(defn setup []
  (q/no-loop)
  (q/no-stroke)
  (q/color-mode :hsb 360 100 100 1.0)
  (q/background 0 0 100))

(defn draw-line
  [x y d]
  (q/with-translation
    [x y]
    (q/stroke 0 0 0 1)
    (q/stroke-weight 2)
    (if (< d 0.5)
      (q/line 0 0 50 50)
      (q/line 0 50 50 0))))

(def steps 20)

(defn draw []
  (let [r (range 0 (q/width) steps)]
    (doseq [x r]
      (doseq [y r]
        (draw-line x y (rand 1))))))

(defn refresh []
  (q/defsketch s
    :title "Titled Lines"
    :setup setup
    :draw draw
    :size [900 900]))

(refresh)