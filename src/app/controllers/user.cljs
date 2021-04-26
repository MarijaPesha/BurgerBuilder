(ns app.controllers.user
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.router :as router]
            [keechma.next.toolbox.logging :as l]
            [clojure.string :as string]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]))

(derive :user ::pipelines/controller)

(def user-data-load
  (pipeline!  [value {:keys [deps-state* state*] :as ctrl}]
              (pp/swap! state* assoc :login-data value)))

(def pipelines
  {:login-data user-data-load
   :logout (pipeline! [value ctrl]
                      (.reload js/window.location true))})

  (defmethod ctrl/prep :user
    [ctrl]
    (pipelines/register ctrl pipelines))

