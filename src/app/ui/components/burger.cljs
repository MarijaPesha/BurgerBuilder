(ns app.ui.components.burger
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]))

 (defclassified BreadTop :div "h-1/4 w-3/5 bg-gradient-to-r from-yellow-500 via-yellow-600 to-yellow-700 rounded-t-full shadow-sm relative my-0.5 mx-auto")
 (defclassified Cheese :div "w-2/5 h-3  bg-gradient-to-r from-yellow-200 via-yellow-300 to-yellow-400 rounded shadow-sm relative mx-auto my-0.5")
 (defclassified Meat :div "w-2/6 h-7 bg-gradient-to-r from-yellow-700 via-yellow-800 to-yellow-900 mx-auto my-0.5 rounded")
 (defclassified Salad :div "w-2/5 h-5 bg-gradient-to-r from-green-400 via-green-600 to-green-700 rounded shadow-sm relative mx-auto my-0.5")
 (defclassified Bacon :div "w-2/6 h-2 bg-gradient-to-r from-red-700 via-red-800 to-red-900 rounded shadow-sm relative mx-auto my-0.5")
 (defclassified BreadBottom :div "w-2/6 h-12 w-3/5 bg-gradient-to-r from-yellow-500 via-yellow-600 to-yellow-700 rounded-b-full shadow-sm relative mx-auto my-0.5")

(defnc BurgerRenderer [_]
(d/div {:className "max-h-96 max-w-screen-sm m-auto overflow-scroll text-center font-bold text-xl"}
       (d/div {:className "h-full w-full mt-10"}
        ($ BreadTop
           (d/div {:className "Seeds1"})
           (d/div {:className "Seeds2"}))
        ($ Cheese)
        ($ Meat)
        ($ Salad)
        ($ Bacon)
        ($ BreadBottom))))

(def Burger (with-keechma BurgerRenderer))