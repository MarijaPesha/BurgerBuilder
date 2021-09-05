(ns app.ui.pages.checkout 
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.controllers.router :as router]
            [keechma.next.toolbox.logging :as l]))


(defclassified Wrapper :div "flex items-center justify-center h-screen")
(defclassified Container :div "flex-row")

(defnc CheckoutRenderer
  [_]
  ($ Wrapper
  "CHECKOUT"))

(def Checkout (with-keechma CheckoutRenderer))
