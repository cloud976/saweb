(ns saweb.error)


(defn error404
  "默认404页面"
  [req]
  {:status  404
   :headers {"Content-Type" "text/html"}
   :body    "该页面没找到"})
