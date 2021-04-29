(ns app.controllers.burgerBuilder
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.toolbox.logging :as l])
  )

(derive :burgerBuilder :keechma/controller)
 
(def initial-burger-ingredients
  [{:type "Cheese"
    :price 2.8
    :count 0}
   {:type "Meat"
    :price 4.3
    :count 0}
   {:type "Salad"
    :price 1.5
    :count 0}
   {:type "Bacon"
    :price 3.1
    :count 0}])

(defmethod ctrl/start :burgerBuilder []
  initial-burger-ingredients)

(defmethod ctrl/handle :burgerBuilder [{:keys [state*] :as ctrl} ev payload]
  (case ev
    :add-count (swap! state* update-in [payload :count] inc)
    :dec-count (swap! state* update-in [payload :count] dec)
    :restart (reset! state* initial-burger-ingredients)
    nil))

(defn count-total
  [state]
  (apply +
   (mapv
    #(* (:price %) (:count %))
    state)))

(defmethod ctrl/derive-state :burgerBuilder 
  [_ state]
  (assoc-in state  [4 :total-price] (count-total (take 4 state))))



