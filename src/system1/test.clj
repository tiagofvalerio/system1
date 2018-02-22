(ns system1.test
  (:use korma.core)
  (:require
     [system1.entities :as e]))

(defn create-test
  [message]
  (let [sql (str "INSERT INTO test(uuid,description) VALUES(?,?)")]
    (korma.db/transaction
      (exec-raw [sql [(java.util.UUID/randomUUID) (str message)]]))))

(defn find-all
  []
  (select e/test))
