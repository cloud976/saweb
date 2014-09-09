(ns saweb.core
  (:use saweb.app)
  (:use org.httpkit.server))

(defn app
  "默认程序入口"
  [req]
  (run req))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn -main [& args]
  (reset! server (run-server #'app {:port 8000}))
  (println "server start"))
