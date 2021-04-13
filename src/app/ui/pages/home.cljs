(ns app.ui.pages.home
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]
            [app.ui.pages.burgerBuilder :refer [BurgerBuilder]]
            [app.ui.components.footer :refer [Footer]]
            [app.ui.components.navbar :refer [Navbar]]))

(defclassified HomepageWrapper :div "h-screen w-screen")

(defnc HomeRenderer [_]
  ($ HomepageWrapper
     ($ Navbar)
     (d/div {:className "mx-auto text-center"}
            (d/main  {:className "mt-4"}
                     ($ BurgerBuilder)
                     ($ Footer)))))

(def Home (with-keechma HomeRenderer))