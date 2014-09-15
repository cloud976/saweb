(ns saweb.app
  (:require [ring.middleware.params :as param-tools])
  (:require [saweb.common :as common])
  (:require [saweb.route :as route]))

(defn get-params
  "取得当前URL参数"
  [req]
  (let [gets (:params (param-tools/assoc-query-params req (:character-encoding req)))]
    (when (map? gets)
      (.set common/GET gets)) req))

(defn post-params
  "取得当前URL参数"
  [req]
  (let [posts (:params (param-tools/assoc-form-params req (:character-encoding req)))]
    (when (map? posts)
      (.set common/POST posts)) req))

(defn app-init
  "初始化环境变量GET/POST/COOKIE/SESSION等"
  [req]
  (-> req get-params post-params))

(defn run
  "web程序入口"
  [req]
  (app-init req)
  (route/run-action req))
