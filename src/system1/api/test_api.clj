(ns system1.api.test-api
  (:require [clojure.walk :as walk]
            [clojure.tools.logging :as log]))

(defn get-all-tests
  [{:keys [path-params json-params] :as request}]
  {:status 200})
