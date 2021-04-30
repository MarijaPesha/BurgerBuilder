(ns app.controllers.login
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [keechma.next.controllers.form :as form]
            [app.validators :as v]
            [clojure.string :as string]
           [keechma.next.toolbox.logging :as l]
            [keechma.next.toolbox.ajax :refer [GET POST DELETE PUT]]
            [app.settings :refer [api-endpoint]]
          ; [promesa.core :as p]            [oops.core :refer [ocall oget]]
            [keechma.next.controllers.router :as router]))

(derive :login ::pipelines/controller)

(defn user-create
  [{:keys [username password email]}]
  (POST (str api-endpoint "/users.json")
    {:response-format :json, :keywords? true, :format :json
     :params
     {:username username
      :password password
      :email email}}))

(def pipelines
  {:keechma.form/submit-data
   (pipeline! [value {:keys [meta-state*] :as ctrl}]
              #_(l/pp "value" value)
              (let [user (:name value)
                    email (:email value)
                    pass (:password value)]
                (user-create {:username user
                              :password pass
                              :email email})
                (ctrl/broadcast ctrl :login-data {:user user
                                                  :email email
                                                  :pass pass}))
              (router/redirect! ctrl :router {:page "order"}))})

(defmethod ctrl/prep :login
  [ctrl]
  (pipelines/register ctrl
                      (form/wrap pipelines
                                 (v/to-validator {:name [:not-empty]
                                                  :email [:email :not-empty]
                                                  :password [:not-empty
                                                             :ok-password]}))))