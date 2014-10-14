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
  [form-name]
  (when (contains? (.get POST) form-name)((.get POST) form-name)))

(defn _SESSION
  "取得或者设置session对应键值"
  ([session-name]
    (when (contains? (.get SESSION) session-name)((.get SESSION) session-name)))
  ([session-name values]
    (.set SESSION (conj (.get SESSION) {session-name values}))))
