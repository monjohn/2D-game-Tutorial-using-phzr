(ns breakout-cljs.core
  (:require
    [phzr.game :as pg]
    [phzr.image :as pi]
    [phzr.loader :as pl]
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
  (pl/image (:load game) "ball" "img/ball.png"))

(def ball-global (atom nil))

(defn create [game]
  (let [ball (pgof/sprite (:add game) 50 50 "ball")]
    (reset! ball-global ball)))

(defn update-fn []
  (pset! @ball-global :x (inc (get @ball-global :x))))


(defn start-game []
  (pg/->Game 800 600 (p/phaser-constants :auto) "app"
                 {"preload" preload
                  "create"  create
                  "update"  update-fn}))

(start-game)

(defn on-js-reload [])
