(ns system1.api.test-api
  (:require [clojure.walk :as walk]
            [clojure.tools.logging :as log]
            [system1.test :as t]))

(defn get-all-tests
  [request]
  {:status 200 :body (t/find-all)})
