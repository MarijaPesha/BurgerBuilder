(ns app.ui.main
  (:require [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [helix.core :as hx :refer [$]]
            [helix.dom :as d]
            [app.ui.pages.home :refer [Home]]
            [app.ui.pages.login :refer [Login]]
            [app.ui.pages.order :refer [Order]]
            [app.ui.pages.end :refer [End]]
            [app.ui.components.errorPage :refer [Error]]))

(defnc MainRenderer [props]
  (let [{:keys [page]} (use-sub props :router)]
    (case page
      "home" ($ Home)
      "login" ($ Login)
      "order" ($ Order)
      "end" ($ End)
      (d/div ($ Error)))))

(def Main (with-keechma MainRenderer))