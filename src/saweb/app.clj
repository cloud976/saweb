(ns saweb.app
  (:require [ring.middleware.params :as param-tools])
  (:require [saweb.common :as common])
  (:require [saweb.route :as route]))

(defn get-params
  "取得当前URL参数"
  [req]
  (let [gets (:params (param-tools/assoc-query-params req (:character-encoding req)))]
    (when (map? gets)
      (dosync (alter common/GET conj gets)))))

(defn post-params
  "取得当前URL参数"
  [req]
  (let [posts (:params (param-tools/assoc-form-params req (:character-encoding req)))]
    (when (map? posts)
      (dosync (alter common/POST conj posts)))))

(defn app-init
  "初始化环境变量GET/POST/COOKIE/SESSION等"
  [req]
  (get-params req))

(defn run
  "web程序入口"
  [req]
  (app-init req)
  (route/run-action req))
