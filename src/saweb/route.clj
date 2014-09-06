(ns saweb.route
  (:use ring.util.codec)
  (:use saweb.controllers)
  (:use saweb.error)
  (:require [clojure.string :as strobj]))

(def action-route {"index" actionIndex
                   "404" error404})
(defn get-params
  "取得当前URL参数"
  [req]
  (when (string? (req :query-string)) (let [query (form-decode (req :query-string))]
    (when (map? query) query))))

(defn get-query
  "取得当前query对应键值"
  [params query-name]
  (when (contains? params query-name)(params query-name) ))

(defn get-action
  "取得当前控制器名"
  [req]
  (let [action (get-query (get-params req) "action")]
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
        (apply f [req]) (run-error req "404")))

(defn run-action
  "调用当前路由中控制器"
  [req action]
  (if (contains? action-route action) ((action-route action) req) (run-route req action)))
