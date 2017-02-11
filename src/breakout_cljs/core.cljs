(ns breakout-cljs.core
  (:require
    [phzr.game :as pg]
    [phzr.image :as pi]
    [phzr.loader :as pl]
    [phzr.physics :as physics]
    ;; The following namespace would not load.
    ; [phzr.physics.arcade :as arcade]
    [phzr.game-object-factory :as pgof]
    [phzr.scale-manager :as sm]
    [phzr.core :as p :refer [pset!]]))

(enable-console-print!)


(defn preload [game]
  (let [scale (:scale game)]
     (pset! scale :page-align-horizontally true)
     (pset! scale :page-align-vertically true)
    (pset! scale :scale-mode (sm/const :show-all)))
  (pset! (:stage game) :background-color "#eee")
  (pl/image (:load game) "ball" "img/ball.png")
  (pl/image (:load game) "paddle" "img/paddle.png"))


(defn build-create-fn [ball paddle]
  (fn [game]
    (let [center (* 0.5 (get-in game [:world :width]))
          ball (reset! ball (pgof/sprite (:add game) center (- (get-in game [:world :width]) 301)  "ball"))
          paddle (reset! paddle
                    (pgof/sprite (:add game) center (- (get-in game [:world :width]) 301) "paddle"))
          arcade (physics/const :arcade)]
      (physics/start-system (:physics game) arcade)
      (physics/enable (:physics game) ball arcade)
      (physics/enable (:physics game) paddle arcade)
      (pset! (get-in ball [:body :velocity])  :y 150)
      (pset! (get-in ball [:body :velocity])  :x 150)
      (pset! (:body ball) :collide-world-bounds true)
      (pset! (get-in ball [:body :bounce])  :x 1)
      (pset! (get-in ball [:body :bounce])  :y 1)
      (pset! (:anchor paddle) :x 0.5)
      (pset! (:anchor paddle) :y 1)
      (pset! (:body paddle) :immovable true)
      (.log js/console game))))



(defn build-update-fn [ball paddle]
  (fn [game]
    ; Since the arcade namespace wouldn't load, I used the second line to call collide
    ; (arcade/collide (get-in game [:physics :arcade ]) @ball @paddle)))
    (.collide (get-in game [:physics :arcade]) @ball @paddle)
    (pset! @paddle :x (or (get-in game [:input :x]) (* 5 (get-in game [:world :width]))))))

(defn build-states []
  (let [ball (atom nil)
        paddle (atom nil)
        create (build-create-fn ball paddle)
        update (build-update-fn ball paddle)]
     {"preload" preload
      "create"  create
      "update" update}))

(defn start-game []
  (pg/->Game 800 500 (p/phaser-constants :auto) "app" (build-states)))

(start-game)

(defn on-js-reload [])
