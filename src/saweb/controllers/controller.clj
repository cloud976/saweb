(ns saweb.controllers.controller
  (:require [saweb.common :as common])
  (:require [saweb.tmpl :as tmpl]))

(defn actionIndex
  "默认入口函数"
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (tmpl/render "src/saweb/tmpl/index.tmpl" {:name (common/_POST "user")})})

(defn actionLogin
  "默认登录入口"
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (tmpl/render "src/saweb/tmpl/login.tmpl" {})})

(defn actionTest
  "默认登录入口"
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "abca"})
