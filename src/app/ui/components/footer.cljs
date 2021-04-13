(ns app.ui.components.footer
  (:require [helix.core :as hx :refer [$]]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.lib :refer [defnc]]))

(defclassified FooterOverlay :div " bg-gray-100 text-gray-800 font-semibold text-center text-xs p-3 fixed bottom-0 w-full")

(defnc FooterRenderer [_] 
  ($ FooterOverlay "@Burger Bar"))

(def Footer (with-keechma FooterRenderer))