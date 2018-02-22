(ns system1.entities
    (:use korma.core system1.db))

(declare test)

(defentity test
    (pk :uuid)
    (table :test)
    (entity-fields :uuid :description))
