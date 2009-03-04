
(ns hoeck.marchingcube
  (:use clojure.contrib.fcase
        clojure.contrib.duck-streams
        hoeck.library)
  (:import (java.io StreamTokenizer)))


(defn float-tokenizer
  "Create a lazy Seq from java.io.StreamTokenizer constructed with (reader input)."
  [input]
  (take-while identity 
              (repeatedly (let [tk (StreamTokenizer. (reader input))]                            
                            #(loop []
                               (let [t (.nextToken tk)]
                                 (case t
                                   StreamTokenizer/TT_NUMBER (.nval tk)
                                   StreamTokenizer/TT_EOF nil
                                   (recur))))))))


;; vector-math

(defn crossproduct [[v1 v2 v3], [w1 w2 w3]]
  [(- (* v1 w3) (* v3 w2))
   (- (* v3 w1) (* v1 w3))
   (- (* v1 w2) (* v2 w1))])

(defn magnitude [[v1 v2 v3]]
  (Math/sqrt (+ (* v1 v1) (* v2 v2) (* v3 v3))))

(defn product [[v1 v2 v3] s]
  [(* s v1) (* s v2) (* s v3)])

(defn normalize [v]
  (product v (/ (magnitude v))))

(defn dot [[v1 v2 v3] [w1 w2 w3]]
  (+ (* v1 w1) (* v2 w2) (* v3 w3)))

(defn subtract [[v1 v2 v3] [w1 w2 w3]]
  [(- v1 w1)
   (- v2 w2)
   (- v3 w3)])

(defn project 
  "Project a vector onto the x-y plane using a factor of d."
  [[v1 v2 v3] d]
  (let [dz (if (= v3 0) 1 (/ d v3))]
    [(* dz v1)
     (* dz v2)
     0]))

(defn z
  "Returns the z koordinate of a vector."
  [vec]
  (vec 2))

  
;; matrix math

(defn make-trans-matrix [view-v]
  (let [oben [0.0 -1.0 0.0]
        camx (crossproduct view-v oben)
        camy (crossproduct view-v camx)]
    [(normalize camx)
     (normalize camy)
     (normalize view-v)]))

(defn matrix-product
  "Calculates the resulting vector of a matrix-vector multiplication in R^3"
  [[m1 m2 m3] v]
  [(dot m1 v)
   (dot m2 v)
   (dot m3 v)])


;; triangles

(defn normal
  "Returns the normal vector of a triangle."
  [[a b c]]
  (crossproduct (subtract b a) (subtract c a)))

(defn get-triangles
  "Format a sequence of floats to vectors of 3 vectors representing
  points of a triangle."
  [float-seq]
  (map vec (map #(map vec (partition 3 %)) (partition 9 float-seq))))

(defn project-triangle [t]
  (map #(project % 2) t))

(defn min-z
  "Return the smalles z-coordinate of a triangle."
  [triangle] (reduce min (map z triangle)))

(defn triangle-compare-depth
  "comparator for depth-sorting of triangles."
  [tri-1 tri-2]
  (if (< (min-z tri-1) (min-z tri-2)) -1 1))

(defn backface?
  "returns true if triangle is behind the x-y plane."
  [min-distance triangle]
  (< min-distance (min-z triangle)))

(defn visible?
  "A triangle is visible if the z part of its normal is positive."
  [triangle]
  (< 0 (z (normal triangle))))

(defn transform [rot-matrix, translation-vec, triangle]
  (vec (map #(matrix-product rot-matrix (subtract % translation-vec)) triangle)))

(def *default-cam-pos*  [0 0 -10])
(def *default-cam-view* [0 0 -1])

(defn run
  "perform a set of operations to a seq of floats assuming they are triangles."
  ([float-seq] (run *default-cam-view* *default-cam-pos* float-seq))
  ([rot-matrix, translation-vec, float-seq]
     (pipe (get-triangles float-seq)
           (map (let [m (make-trans-matrix rot-matrix)] #(transform m translation-vec %)))
           (filter (partial backface? -0.1))
           (filter visible?)
           (sort triangle-compare-depth)
           (map #(conj % (normal %)))
           (map project-triangle))))

(def test-triangle '([0 0 0]  [1 1 0] [1 0 0]))

(defn test-run []
  (run [0 0 -1] [0 0 -10] (mapcat identity test-triangle)))

(defn convert-file
  "convert date from in-filename to out-filename."
  [in-filename out-filename]
  (spit out-filename (run (float-tokenizer (reader in-filename)))))




