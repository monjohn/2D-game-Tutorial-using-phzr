(ns breakout-cljs.core
  (:require
    [phzr.game :as pg]
    [phzr.image :as pi]
    [phzr.loader :as pl]
    [phzr.physics :as physics]
    [phzr.game-object-factory :as pgof]
    [phzr.scale-manager :as sm]
    [phzr.core :as p :refer [pset!]]))

(enable-console-print!)


(defn preload [game]
  (let [scale (:scale game)]
    (pset! scale :page-align-horizontally true)
    (pset! scale :page-align-vertically true))
  ;  (pset! scale :scale-mode (sm/const :show-all)))
  (pset! (:stage game) :background-color "#eee")
  (pl/image (:load game) "ball" "img/ball.png"))


(defn create [game]
  (let [ball (pgof/sprite (:add game) 50 50 "ball")
        arcade (physics/const :arcade)]
    (physics/start-system (:physics game) arcade)
    (physics/enable (:physics game) ball arcade)
    (pset! (get-in ball [:body :velocity])  :y 150)
    (pset! (get-in ball [:body :velocity])  :x 150)
    (pset! (:body ball) :collide-world-bounds true)
    (pset! (get-in ball [:body :bounce])  :x 1)
    (pset! (get-in ball [:body :bounce])  :y 1)))


(defn start-game []
  (pg/->Game 800 500 (p/phaser-constants :auto) "app"
                 {"preload" preload
                  "create"  create}))

(start-game)

(defn on-js-reload [])
