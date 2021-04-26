(ns app.ui.components.navbar
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.core :refer [with-keechma  use-sub]]
            [keechma.next.toolbox.logging :as l]
            [clojure.string :as str]
            [keechma.next.helix.lib :refer [defnc]]))
 
(defclassified Header :header "bg-gray-100 border-b px-10 py-3 flex justify-between items-center")
 
 (defnc NavbarRenderer [props]
   ($ Header
      (d/img {:className "w-10"
              :onClick #(router/redirect! props :router {:page "home"})
              :src "/image/burger-logo.b8503d26.png"})
      (let [user-info (:login-data (use-sub props :user))
            user-name (:user user-info)]
        (d/nav {:className "cursor-pointer text-xl text-gray-600 font-semibold hover:text-green-700"
                :onClick #(router/redirect! props :router {:page "login"})}
               (if user-info
                 (str "Hi " (str/capitalize user-name ) " !")
                 "Log In And Order")))))
 
(def Navbar (with-keechma NavbarRenderer))