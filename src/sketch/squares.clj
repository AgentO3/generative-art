(ns sketch.squares
  (:require [quil.core :as q]))

(defn setup []
  (q/no-loop)
  (q/no-stroke)
  (q/color-mode :hsb 360 100 100 1.0)
  (q/background 0 0 100))


(def pallet [[290 54 36] [289 17 65] [288 7 84] [270 2 96]])

(defn draw []
  (doseq [l (reverse (range 1 150 20))]
    (let [l2 (* l 2)
          c1 (rand-nth (concat (range 1 15)
                               (range 30 35)
                               (range 300 360)))
          c2 (rand-nth (range 10 50))
          c3 (rand-nth (range 75 100))
          o (rand 0.7)
          hw (rand-nth (range 19 20))
          s1 20
          s2 20
          x (+ 0 l2)
          y (- 900 l2)]

      (doseq [_ (range 1 l2)]
        (q/with-fill
          (conj (rand-nth pallet) o)
          (q/rect
           (rand-nth (range x y s1))
           (rand-nth (range x y s2))
           hw
           hw))))))

(defn refresh []
  (q/defsketch s
    :title "Squares"
    :setup setup
    :draw draw
    :size [900 900]))

(refresh)
