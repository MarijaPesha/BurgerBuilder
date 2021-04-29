(ns app.ui.pages.login
  (:require [keechma.next.helix.core :refer [with-keechma dispatch use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [helix.dom :as d]
            ["react" :as react]
            ["react-dom" :as rdom]
            [app.ui.components.inputs :refer [wrapped-input]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.classified :refer [defclassified]]
            [helix.core :as hx :refer [$]]))

(defclassified LoginWrapper :div "h-screen w-screen flex items-center")
(defclassified LoginContainer :div "container flex flex-col md:flex-row items-center justify-center px-5 text-gray-700")
(defclassified FormWrapper :div "max-w-md")
(defclassified ButtonWrapper :button "my-3 cursor-pointer bg-white hover:bg-gray-100 text-gray-600 font-semibold py-1 px-4 border border-gray-400 rounded shadow m-auto")
(defclassified TextWrapper :div "text-green-700 font-semibold py-3 animate-pulse text-2xl")

(defnc LoginRenderer [props]
  ($ LoginWrapper
    ($ LoginContainer
      ($ FormWrapper
         (let [price (:total-price (get (use-sub props :burgerBuilder) 4))]
           ($ TextWrapper
              "The price of your burger is " (.toFixed price 2) "$ !"))
         ($ TextWrapper
            "Login to add order.")
         (d/form
          {:on-submit (fn [e]
                        (.preventDefault e)
                        (dispatch props :login :keechma.form/submit))}
          (wrapped-input {:keechma.form/controller :login
                          :input/type :text
                          :input/attr [:name]
                          :placeholder "Name"})
          (wrapped-input {:keechma.form/controller :login
                          :input/type :text
                          :input/attr :email
                          :placeholder "Email"})
          (wrapped-input {:keechma.form/controller :login
                          :input/type :text
                          :input/attr [:password]
                          :type "password"
                          :placeholder "Password"})
          ($ ButtonWrapper
             "Log In"))))))

(def Login (with-keechma LoginRenderer))