(ns sketch.joy-division
  (:require [quil.core :as q])
  (:gen-class))
        
(defn draw 
  []
  (q/stroke 0 0 0 0)
  (q/stroke-weight 0)
  (let [center (/ (q/width) 2)
        padding 200
        pallet [[222 75 46] [214 75 75] [207 67 85] [205 58 95] [200 40 96]]]
    (doseq [l (range 0 (q/height))]
      (q/with-fill
        (conj (nth pallet (rand (count pallet))) (rand 0.7))
        (q/begin-shape)
        (q/vertex 0 l)
        (doseq [x (range (- center padding) (+ center padding) 40)]
          (let [d (q/abs (- x center))
                v (/ (max (- (- center padding) d) 0) 2)]
            (q/curve-vertex x (rand-nth (range (- l v) l)))))
        (q/vertex (q/width) l)
        (q/end-shape)))))

(defn refresh []
  (q/defsketch s
    :title "Joy Division"
    :setup (fn []
             (q/no-loop)
             (q/no-stroke)
             ;(q/no-fill)
             (q/color-mode :hsb 360 100 100 1.0)
             (q/background 0 0 100))
    :draw draw
    :size [900 900]))

(refresh)