(ns breakout-cljs.core
  (:require
    [phzr.game :as pg]
    [phzr.scale-manager :as sm]
    [phzr.core :as p :refer [pset!]]))

(enable-console-print!)

(defn preload [game]
  (let [scale (:scale game)]
    (pset! scale :page-align-horizontally true)
    (pset! scale :page-align-vertically true)
    (pset! scale :scale-mode (sm/const :show-all)))
  (pset! (:stage game) :background-color "#eee"))

(defn create [game])

(defn start-game []
  (pg/->Game 800 600 (p/phaser-constants :auto) "app"
                 {"preload" preload
                  "create"  create}))

(start-game)
(defn on-js-reload [])
