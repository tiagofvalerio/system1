(ns system1.server
  (:gen-class) ; for -main method in uberjar
  (:require [io.pedestal.http :as server]
            [io.pedestal.http.route :as route]
            [system1.service :as service]
            [system1.integration.system2-integration :as system2-integration]))
            ;[everest.integration.finanfor :as finanfor-integration]))

(defonce runnable-service (server/create-server service/service))

(defn run-dev
  "The entry-point for 'lein run-dev'"
  [& args]
  (println "\nCreating your [DEV] server...")
  ;(finanfor-integration/start-listening-accept-signup-queue)
  (-> service/service
      (merge {:env :dev
              ::server/join? false
              ::server/allowed-origins {:creds true :allowed-origins (constantly true)}})
      server/default-interceptors
      server/dev-interceptors
      server/create-server
      server/start))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (println "\nCreating your server...")
  (server/start runnable-service)
  (system2-integration/start-integration))
