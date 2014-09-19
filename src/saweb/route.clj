(ns saweb.route
  (:require [saweb.common :as common])
  (:require [bultitude.core :only [namespaces-in-dir]])
  (:require [clojure.string :as strobj])
  )
(apply require (bultitude.core/namespaces-in-dir "src/saweb/controllers"))  ;初始化控制器列表

(def action-table {{"default" "main"} saweb.controllers.controller/actionLogin})

(defn get-controller
  "取得当前控制器名"
  []
  (let [controller (common/_GET "controller")]
    (if (nil? controller) "default" controller)))

(defn get-action
  "取得当前方法"
  []
  (let [action (common/_GET "action")]
    (if (nil? action) "main" action)))

(defn run-error
  "调用默认错误控制器"
  [req err]
  (when-let [f (resolve (symbol (str "saweb.controllers.error/error" err)))]
        (apply f [req])))

(defn run-route
  "调用当前名称控制器"
  [req action controller]
  (if-let [f (resolve (symbol (str "saweb.controllers." controller "/action" (strobj/capitalize action))))]
    (apply f [req])
    (run-error req "404")))

(defn run-action
  "调用当前路由中控制器"
  [req]
  (let [action (get-action) controller (get-controller)]
  (if (contains? action-table {controller action})
    ((action-table {controller action}) req)
    (run-route req action controller))))
