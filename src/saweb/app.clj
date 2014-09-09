(ns saweb.app
  (:require [ring.util.codec :as urltools])
  (:require [saweb.common :as common])
  (:require [saweb.route :as route]))

(defn get-params
  "取得当前URL参数"
  [req]
   (when (string? (req :query-string))
     (dosync (alter common/GET conj (urltools/form-decode (req :query-string))))))

(defn app-init
  "初始化环境变量GET/POST/COOKIE/SESSION等"
  [req]
  (get-params req))

(defn run
  "web程序入口"
  [req]
  (app-init req)
  (route/run-action req))
