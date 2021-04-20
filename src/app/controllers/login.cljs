(ns app.controllers.login
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [keechma.next.controllers.form :as form]
            [app.validators :as v]
            [clojure.string :as string]
            [keechma.next.toolbox.logging :as l]
            [keechma.next.controllers.router :as router]))

(derive :login ::pipelines/controller)

(def pipelines
  {:keechma.form/submit-data 
   (pipeline! [value {:keys [meta-state*] :as ctrl}]
              (l/pp "value" value)
              (let [user (first (string/split (:email value) #"@"))
                    email (:email value)
                    pass (:password value)]
                (l/pp "value" user)
                (ctrl/broadcast ctrl :login {:user user
                                             :email email
                                             :password pass}))
              (router/redirect! ctrl :router {:page "home"}))})

(defmethod ctrl/prep :login
  [ctrl]
  (pipelines/register ctrl
                      (form/wrap pipelines
                                 (v/to-validator {:email [:email :not-empty],
                                                  :password [:not-empty
                                                             :ok-password]}))))