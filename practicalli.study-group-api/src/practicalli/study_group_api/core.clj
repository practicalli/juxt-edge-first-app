(ns practicalli.study-group-api.core
  (:require
    [yada.yada :as yada]
    [integrant.core :as ig]))

(defn string-resource
  [x]
  (yada/as-resource x))

(defmethod ig/init-key ::string
  [_ x]
  (string-resource x))


(def hello-language
  (yada/resource
    {:methods
     {:get
      {:produces
       {:media-type "text/plain"
        :language   #{"en" "zh-ch;q=0.9"}}
       :response
       #(case (yada/language %)
          "zh-ch" "你好世界\n"
          "en"    "Hello World!\n")}}}))


(defmethod ig/init-key
  ::hello-language
  [_ _]
  hello-language)
