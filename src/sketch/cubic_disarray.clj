(ns sketch.cubic_disarray
  (:require [quil.core :as q])
  (:gen-class))


(def pallet [[290 54 36] [289 17 65] [288 7 84] [270 2 96]])

(defn draw
  []
  (let [rect-size 50
        displacement 20
        multipler 30
        size (q/width)]
    (doseq [x (range 0 (+ (q/width) rect-size) rect-size)]
      (doseq [y (range 0 (q/height) rect-size)]
        (apply q/stroke (rand-nth pallet))
        (q/stroke-weight (rand 4))
        (q/with-fill
          (conj (rand-nth pallet) (rand 0.9))
          (let [plusorminus (rand-nth [-1 1])
                angle (/ (* (/ y size) (Math/PI)) 180)
                rotate (* (* (rand angle) plusorminus) multipler)
                trans (* (/ y size) plusorminus)]
            (q/with-translation
              [(+ x (* (rand trans) displacement)) y]
              (q/with-rotation
                [rotate]
                (q/rect 0 0 rect-size rect-size)))))))))

(defn refresh []
  (q/defsketch s
    :title "Cubic Disarray"
    :setup (fn []
             (q/rect-mode :center)
             (q/no-loop)
             (q/no-stroke)
             (q/no-fill)
             (q/color-mode :hsb 360 100 100 1.0)
             (q/background 0 0 100))
    :draw draw
    :size [900 900]))

(refresh)