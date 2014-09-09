(ns saweb.common)


(def GET (ref {}))
(def POST {})
(def SERVER {})
(def SESSION {})
(def COOKIE {})

(defn get-query
  "取得当前query对应键值"
  [query-name]
  (when (contains? @GET query-name)(@GET query-name)))
