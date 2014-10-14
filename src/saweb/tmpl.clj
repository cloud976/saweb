(ns saweb.tmpl
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as cstr]))

(def ^:dynamic param {})
(def out-put (ThreadLocal.))

(defn echo
	[in-put]
	(.set out-put (conj (.get out-put) in-put)))

(defn parse-line
  [line]
  (cstr/replace
   (cstr/replace
    (cstr/replace (str "%>" line "\\r\\n<%") "%><%" "") #"%>(.+?)<%" "(echo \"$1\" )") #"(%>|<%)" ""))

(defn parse
  [reader]
  (apply str (map parse-line (line-seq reader))))

(defn render
  [filename arg]
  (.set out-put [])
  (with-open [reader (io/reader filename)]
    (binding [param arg]
    (-> (str "(do " (parse reader) ")") read-string eval)))
  (cstr/trim (apply str (.get out-put))))
