(ns sketch.dynamic
  (:require [quil.core :as q]
            [incanter.stats :as stats]))

(defn setup []
  (q/rect-mode :center)
  (q/no-loop)
  (q/no-stroke)
  (q/color-mode :hsb 360 100 100 1.0)
  (q/background 0 0 100))


(def shapes 6)

(defn draw []
  (q/with-translation
    [(/ (q/width) 2) (/ (q/width) 2)]
    (dotimes [l 6]
      (q/push-matrix)
      (dotimes [_ 8]
        (q/rotate (/ 360 8))
        (q/with-fill
          [25 25 25 (rand 0.5)]
          (q/ellipse (* l 50) 0 10 10)))
      (q/pop-matrix))))