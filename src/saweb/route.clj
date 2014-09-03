(ns saweb.route
  (:use ring.util.codec))

(defn get-params
  "取得当前URL参数"
  [req]
  (when (string? (req :query-string)) (let [query (form-decode (req :query-string))]
    (when (map? query) query))))

(defn get-query
  "取得当前query对应键值"
  [params query-name]
  (when (contains? params query-name)(params query-name)))

(defn get-action
  "取得当前控制器名称"
  [req]
  (let [action (get-query (get-params req) "action")]
    (if (nil? action) "index" action)))
