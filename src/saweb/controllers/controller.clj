(ns saweb.controllers.controller
  (:require [saweb.common :as common]))

(defn actionIndex
  "默认入口函数"
  [req]
  (Thread/sleep 5000)
  (println (.get common/GET))
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "调用默认入口"})

(defn actionLogin
  "默认登录入口"
  [req]
  (println (.get common/GET))
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "默认登录入口"})
