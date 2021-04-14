(ns app.ui.components.navbar
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.core :refer [with-keechma]]
            [keechma.next.helix.lib :refer [defnc]]))
 
(defclassified Header :header "bg-gray-100 border-b px-10 py-3 flex justify-between items-center")
 
 (defnc NavbarRenderer [props]
   ($ Header
             (d/img {:className "w-10"
                     :onClick #(router/redirect! props :router {:page "home"})
                     :src "/image/burger-logo.b8503d26.png"})
             (d/nav {:className "text-gray-800 font-semibold hover:text-green-700 hover:border-b-2 hover:border-green-900"
                     :onClick #(router/redirect! props :router {:page "login"})} 
                    "Login")))
 
(def Navbar (with-keechma NavbarRenderer))