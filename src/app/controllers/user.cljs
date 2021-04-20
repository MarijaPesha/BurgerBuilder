(ns app.controllers.user
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.router :as router]
            [keechma.next.toolbox.logging :as l]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]))

(derive :user ::pipelines/controller)

(def user-data-load
  (pipeline!  [value {:keys [deps-state* state*] :as ctrl}]
              (pp/swap! state* assoc :data value)
              (l/pp "value user controller" value)))

(def pipelines
  {:login-data user-data-load})

  (defmethod ctrl/prep :user
    [ctrl]
    (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state :user
  [_ state deps-state]
  (:data state))