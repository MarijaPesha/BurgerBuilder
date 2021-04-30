(ns app.controllers.order
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [keechma.next.controllers.form :as form]
            [app.validators :as v]
            [clojure.string :as string]
            [keechma.next.toolbox.logging :as l]
            [keechma.next.toolbox.ajax :refer [GET POST DELETE PUT]]
            [app.settings :refer [api-endpoint]]
            [keechma.next.controllers.router :as router]))


(derive :order ::pipelines/controller)

(defn order-create
  [{:keys[name street zipcode delivery]}]
  (POST (str api-endpoint "/users/order.json")
    {:response-format :json, :keywords? true, :format :json
     :params 
     {:name name
      :street street
      :zipcode zipcode
      :delivery delivery}}))

(def pipelines
  {:keechma.form/submit-data
   (pipeline! [value {:keys [meta-state*] :as ctrl}]
              (let [street (:street value)
                    zipcode (:zipcode value)
                    delivery (:delivery value)]
                (order-create {:street street
                               :zipcode zipcode
                               :delivery delivery}))
              (router/redirect! ctrl :router {:page "end"}))})

(defmethod ctrl/prep :order 
  [ctrl]
  (pipelines/register ctrl
                      (form/wrap pipelines
                                 (v/to-validator {:street [:not-empty]
                                                  :zipcode [:not-empty]}))))