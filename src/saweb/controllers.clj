(ns saweb.controllers)

(defn actionIndex
  "默认入口函数"
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "调用默认入口"})

(defn actionLogin
  "默认登录入口"
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "默认登录入口"})
