(ns sketch.un-deux-trois
  (:require [quil.core :as q])
  (:gen-class))

(defn draw
  []
  (let [step 20
        h (q/height)
        w (q/width)]
    (doall
     (for [x (range 0 w step)
           y (range 0 h step)]
       (do
         (q/stroke 0 0 0 (rand 0.5))
         (q/stroke-weight (rand 3))
         (q/with-translation
           [x y]
           (q/line 0 0 (rand (/ h 2)) (rand (/ w 2)))))))
    ))

(defn refresh []
  (q/defsketch s
    :title "Un Deux Trois"
    :setup (fn []
             (q/no-loop)
             ;(q/no-stroke)
             ;(q/no-fill)
             (q/color-mode :hsb 360 100 100 1.0)
             (q/background 0 0 100))
    :draw draw
    :size [900 900]))

(refresh)