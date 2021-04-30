(ns app.ui.pages.end
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.controllers.router :as router]
            [keechma.next.toolbox.logging :as l]))

(defclassified EndWrapper :div "EndWrapper")
(defclassified Fade :div "Fade")
(defclassified Container :div "Container")
(defclassified Crawl :div "Crawl")

(defnc EndRenderer
  [_]
  ($ EndWrapper
     ($ Fade)
     ($ Container
        ($ Crawl
           "Your order has been completed!"))))


(def End (with-keechma EndRenderer))
