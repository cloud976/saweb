(ns saweb.route
  (:require [saweb.common :as common])
  (:use saweb.controllers)
  (:use saweb.error)
  (:require [clojure.string :as strobj]))

(def action-table {"index" actionIndex
                   "404" error404})

(defn get-action
  "取得当前控制器名"
  []
  (let [action (common/get-query "action")]
    (if (nil? action) "index" action)))

(defn run-error
  "调用默认错误控制器"
  [req err]
  (when-let [f (resolve (symbol (str "saweb.error/error" err)))]
        (apply f [req])))

(defn run-route
  "调用当前名称控制器"
  [req action]
  (if-let [f (resolve (symbol (str "saweb.controllers/action" (strobj/capitalize action))))]
    (apply f [req])
    (run-error req "404")))

(defn run-action
  "调用当前路由中控制器"
  [req]
  (let [action (get-action)]
  (if (contains? action-table action)
    ((action-table action) req)
    (run-route req action))))
