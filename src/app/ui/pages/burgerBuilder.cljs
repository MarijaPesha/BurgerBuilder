(ns app.ui.pages.burgerBuilder 
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.classified :refer [defclassified]]
            [helix.hooks :as hooks]
            [keechma.next.controllers.router :as router]
            [keechma.next.toolbox.logging :as l]))

;; style for burger 
(defclassified BreadTop :div "w-2/6 h-1/4 w-3/5 bg-gradient-to-r from-yellow-500 via-yellow-600 to-yellow-700 rounded-t-full shadow-sm relative my-0.5 mx-auto")
(defclassified Cheese :div "Cheese")
(defclassified Meat :div "Meat")
(defclassified Salad :div "Salad")
(defclassified Bacon :div "Bacon")
(defclassified BreadBottom :div "w-2/6 h-12 w-3/5 bg-gradient-to-r from-yellow-500 via-yellow-600 to-yellow-700 rounded-b-full shadow-sm relative mx-auto my-0.5")

;; style for count&price
(defclassified IngredientsWrapper :div "flex justify-between items-center my-3 mx-32")
(defclassified ButtonWrapper :button "disabled:opacity-50 cursor-pointer bg-white hover:bg-gray-100 text-gray-800 font-semibold py-0 px-4 border border-gray-400 rounded shadow")
(defclassified IngredientsTextWrapper :div " text-gray-600 font-semibold border-b")
(defclassified SingUpWrapper :div "flex w-full items-center text-sm mt-6")
(defclassified SingUpButton :button "cursor-pointer bg-white hover:bg-gray-100 text-gray-600 font-semibold py-2 px-4 border border-gray-400 rounded shadow m-auto")

(defnc BurgerBuilderRenderer [props]
  (let [ingredients (use-sub props :burgerBuilder)
        total-price (:total-price (get ingredients 4))]
    (l/pp "state" ingredients)
    (d/div {:className ""}
           (d/div {:className "Burger"}
                  ($ BreadTop
                     (d/div {:className "Seeds1"}))
                  (if (= 0 total-price)
                    "Please start adding ingredients!"
                    nil)
                  (map #($ Cheese {:key %}) (range 0 (:count (get ingredients 0))))
                  (map #($ Meat {:key %}) (range 0 (:count (get ingredients 1))))
                  (map #($ Salad {:key %}) (range 0 (:count (get ingredients 2))))
                  (map #($ Bacon {:key %}) (range 0 (:count (get ingredients 3))))
                  ($ BreadBottom))
           (d/div {:className "text-lg text-gray-600 py-1 font-semibold border-b"} "Total Price: " (.toFixed total-price 2))
            (map-indexed
             (fn [idx i]
               ($ IngredientsWrapper {:key (:type i)}
                  ($ ButtonWrapper {:disabled (= 0 (:count i))
                                    :onClick #(dispatch props :burgerBuilder :dec-count idx)} "-")
                  ($ IngredientsTextWrapper (:type i) " Count: " (:count i))
                  ($ ButtonWrapper {:disabled (<= 3 (:count i))
                                    :onClick #(dispatch props :burgerBuilder :add-count idx)} "+"))) (take 4 ingredients))
           ($ SingUpWrapper
              ($ SingUpButton {:onClick #(dispatch props :burgerBuilder :restart)} "Reset Order")))))

(def BurgerBuilder (with-keechma BurgerBuilderRenderer))