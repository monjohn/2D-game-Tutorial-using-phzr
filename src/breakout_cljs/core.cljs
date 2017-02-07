(ns breakout-cljs.core
  (:require
    [phzr.game :as pg]
    [phzr.core :as p :refer [pset!]]))

(enable-console-print!)

(defn create [])
(defn preload [])

(defn start-game []
  (pg/->Game 800 600 (p/phaser-constants :auto) "app"
                 {"preload" preload
                  "create"  create}))

(defn on-js-reload [])
