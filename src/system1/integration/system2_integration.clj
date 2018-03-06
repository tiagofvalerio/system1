(ns system1.integration.system2-integration
  (:require [langohr.core      :as rmq]
            [langohr.channel   :as lch]
            [langohr.queue     :as lq]
            [langohr.consumers :as lc]
            [langohr.basic     :as lb]
            [system1.test :as t]))

(def ^{:const true}
  default-exchange-name "")

(defn message-handler
  [ch {:keys [content-type delivery-tag type] :as meta} ^bytes payload]
  (println (format "[consumer] Received a message: %s, delivery tag: %d, content type: %s, type: %s"
                   (String. payload "UTF-8") delivery-tag content-type type))
  (t/create-test (String. payload "UTF-8")))

(defn start-integration
  []
  (prn "******************************")
  (prn "[BEGIN] start-integration")
  (let [conn  (rmq/connect {:host "amqp://localhost" :username "guest" :password "guest"})
        ch    (lch/open conn)
        qname "system2queue"]
    (println (format "[main] Connected. Channel id: %d" (.getChannelNumber ch)))
    (lq/declare ch qname {:exclusive false :auto-delete true})
    (lc/subscribe ch qname message-handler {:auto-ack true})))
