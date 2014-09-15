(ns saweb.common)


(def GET (ThreadLocal.))
(def POST (ThreadLocal.))
(def SERVER (ThreadLocal.))
(def SESSION (ThreadLocal.))
(def COOKIE (ThreadLocal.))

(defn _GET
  "取得当前query对应键值"
  [query-name]
  (when (contains? (.get GET) query-name)((.get GET) query-name)))

(defn _POST
  "取得当前form对应键值"
  [query-name]
  (when (contains? @POST query-name)(@POST query-name)))
