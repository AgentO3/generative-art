(ns sketch.triangular_mesh
  (:require [quil.core :as q])
  (:gen-class))

(def pallet [[290 54 36] [289 17 65] [288 7 84] [270 2 96]])

(defn build-mesh
  [h w g]
  (into []
        (for [y (range 0 w g)
              :let [l (/ y 2)]]
          (into []
                (for [x (range 0 h g)]
                  (let [r 40
                        x1 (rand-nth (range (- x r)
                                            (+ x r)))
                        y1 (rand-nth (range (- y r)
                                            (+ y r)))]
                    (if (odd? l)
                      [(+ x1 (/ g 2)) 
                       y1]
                      [x1
                       y1])))))))

(defn draw
  []
  (q/stroke 0 0 0 0.5)
  (q/stroke-weight 0.5)
  (let [m (build-mesh (q/height) (q/width) 50)]
    (loop [l 0]
      (when (< l (count m))
        (println "line: " l)
        (let [l1 (get m l)
              l2 (get m (+ l 1))]
            (loop [p 0]
              (println "point: " p)
              (when (< p (- (count l1) 1))
                (q/with-fill
                  (rand-nth pallet)
                  (if (odd? l)
                    (let [p1 (get l1 p)
                          p2 (get l1 (+ p 1))
                          p3 (get l2 (+ p 1))
                          p4 (get l2 (+ p 2))]
                      (if (not (nil? p4))
                        (do
                          (apply q/triangle (flatten [p1 p2 p3]))
                          (apply q/triangle (flatten [p2 p3 p4])))))
                    (let [p1 (get l1 p)
                          p2 (get l1 (+ p 1))
                          p3 (get l2 p)
                          p4 (get l2 (+ p 1))]
                      (apply q/triangle (flatten [p1 p2 p3]))
                      (apply q/triangle (flatten [p2 p3 p4])))))
                (recur (inc p)))))
        (recur (inc l))))))

(defn refresh []
  (q/defsketch s
    :title "Triangular Mesh"
    :setup (fn []
             (q/no-loop)
             ;(q/no-stroke)
             ;(q/no-fill)
             (q/color-mode :hsb 360 100 100 1.0)
             (q/background 0 0 100))
    :draw draw
    :size [900 900]))

(refresh)