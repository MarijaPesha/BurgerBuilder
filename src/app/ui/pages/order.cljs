(ns app.ui.pages.order
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.lib :refer [defnc]]
            [app.ui.components.inputs :refer [wrapped-input]]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.classified :refer [defclassified]]
            [helix.hooks :as hooks]
            [keechma.next.controllers.router :as router]
            [app.ui.components.navbar :refer [Navbar]]
            [keechma.next.toolbox.logging :as l]))

(defclassified OrderWrapper :div "h-screen w-screen flex items-center")
(defclassified OrderContainer :div "container flex flex-col md:flex-row items-center justify-center px-5 text-gray-700")
(defclassified FormWrapper :div "max-w-md")
(defclassified ButtonType :button "my-16 cursor-pointer bg-white text-sm hover:bg-gray-100 text-gray-600 font-semibold py-2 px-4 border border-gray-400 rounded shadow m-auto")

(defnc OrderRenderer
  [props]
  (d/div
   ($ Navbar)
   ($ OrderWrapper
      ($ OrderContainer
         ($ FormWrapper
            (d/form
             {:on-submit (fn [e]
                           (.preventDefault e)
                           (dispatch props :order :keechma.form/submit))}
             (d/p {:className "text-xs font-semibold"} "Please Leave Your Information Below:")
             (wrapped-input {:keechma.form/controller :order
                             :input/type :text
                             :input/attr [:street]
                             :placeholder "Street"})
             (wrapped-input {:keechma.form/controller :order
                             :input/type :text
                             :input/attr [:zipcode]
                             :placeholder "Zip Code"})
             (d/p {:className "text-xs font-semibold mt-6"} "Select Delivery Method:")
             (wrapped-input {:keechma.form/controller :order
                             :input/type :select
                             :input/attr :select
                             :options [{:value "default" :label "Fastest"}
                                       {:value "caucasian" :label "Cheapest"}]})
             ($ ButtonType "Confirm Order")))))))

(def Order (with-keechma OrderRenderer))