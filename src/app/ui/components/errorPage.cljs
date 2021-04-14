(ns app.ui.components.errorPage
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.controllers.router :as router]))

(defclassified MainWrapper :div "h-screen w-screen bg-gray-100 flex items-center")
(defclassified Container :div "container flex flex-col md:flex-row items-center justify-center px-5 text-gray-700")
(defclassified ContentWrapper :div "max-w-md")

(defnc ErrorRenderer [props] 
  ($ MainWrapper 
     ($ Container 
        ($ ContentWrapper 
           (d/div
            {:className "text-5xl font-dark font-bold"}
            "404")
           (d/p
            {:className "text-2xl md:text-3xl font-light leading-normal"}
            "Sorry we couldn't find this page.")
           (d/p
            {:className "mb-8"}
            "But dont worry, you can find plenty of other things on our homepage.")
           (d/button
            {:className "cursor-pointer bg-white hover:bg-gray-100 text-gray-600 font-semibold py-2 px-4 border border-gray-400 rounded shadow m-auto"
             :onClick #(router/redirect! props :router {:page "home"})}
            "Back To Homepage")))))

(def Error (with-keechma ErrorRenderer))