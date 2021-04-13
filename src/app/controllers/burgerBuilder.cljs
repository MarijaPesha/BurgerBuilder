(ns app.controllers.burgerBuilder
  (:require [keechma.next.controller :as ctrl]))

(derive :burgerBuilder :keechma/controller)

(def initial-burger-ingredients
  {:cheese {:type "Cheese"
            :price 2.8}
   :meat {:type "Meat"
          :price 4.3}
   :salad {:type "Salad"
           :price 1.5}
   :bacon {:type "Bacon"
           :price 3.1}})

(defmethod ctrl/start :burgerBuilder [ctrl params prev]
  {:current-burger initial-burger-ingredients
   :burger-update [] })