(ns app.ui.pages.burgerBuilder 
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.classified :refer [defclassified]]
            [helix.hooks :as hooks]
        ;   [keechma.next.toolbox.logging :as l]
        ;   [app.ui.components.burger :refer [Burger]]
            ))

;; style for burger 
(defclassified BreadTop :div "w-2/6 h-1/4 w-3/5 bg-gradient-to-r from-yellow-500 via-yellow-600 to-yellow-700 rounded-t-full shadow-sm relative my-0.5 mx-auto")
(defclassified Cheese :div "w-2/5 h-3  bg-gradient-to-r from-yellow-200 via-yellow-300 to-yellow-400 rounded shadow-sm relative mx-auto my-0.5")
(defclassified Meat :div "w-2/6 h-7 bg-gradient-to-r from-yellow-700 via-yellow-800 to-yellow-900 mx-auto my-0.5 rounded")
(defclassified Salad :div "w-2/5 h-5 bg-gradient-to-r from-green-400 via-green-600 to-green-700 rounded shadow-sm relative mx-auto my-0.5")
(defclassified Bacon :div "w-2/6 h-2 bg-gradient-to-r from-red-700 via-red-800 to-red-900 rounded shadow-sm relative mx-auto my-0.5")
(defclassified BreadBottom :div "w-2/6 h-12 w-3/5 bg-gradient-to-r from-yellow-500 via-yellow-600 to-yellow-700 rounded-b-full shadow-sm relative mx-auto my-0.5")

;; style for count&price
(defclassified IngredientsWrapper :div "flex justify-between items-center my-3 mx-32")
(defclassified ButtonWrapper :button "disabled:opacity-50 cursor-pointer bg-white hover:bg-gray-100 text-gray-800 font-semibold py-0 px-4 border border-gray-400 rounded shadow")
(defclassified CheeseWrapper :div "text-yellow-400 font-semibold border-b")
(defclassified MeatWrapper :div "text-yellow-800 font-semibold border-b")
(defclassified SaladWrapper :div "text-green-600 font-semibold border-b")
(defclassified BaconWrapper :div "text-red-700 font-semibold border-b") 
(defclassified SingUpWrapper :div "flex w-full items-center text-sm mt-6")
(defclassified SingUpButton :button "cursor-pointer bg-white hover:bg-gray-100 text-gray-600 font-semibold py-2 px-4 border border-gray-400 rounded shadow m-auto")

(defn get-full-price [ingredients burger]
  (+ (* (:cheese burger) (get-in ingredients [:cheese :price]))
     (* (:meat burger) (get-in ingredients [:meat :price]))
     (* (:salad burger) (get-in ingredients [:salad :price]))
     (* (:bacon burger) (get-in ingredients [:bacon :price]))))

(defnc BurgerBuilderRenderer [props]
  (let [burger-state (use-sub props :burgerBuilder)
        ingredients (get-in burger-state [:current-burger])
        [burger set-burger] (hooks/use-state {:cheese 0 :meat 0 :salad 0 :bacon 0})
        full-price (get-full-price ingredients burger)]
    (d/div {:className ""}
     (d/div {:className "h-72 w-full m-auto overflow-scroll text-center font-bold text-xl"}
       (d/div {:className "h-full w-full mt-10"}
              ($ BreadTop
                 (d/div {:className "Seeds1"})
                 (d/div {:className "Seeds2"}))  
              (case (:cheese burger)
                0 "Please start adding ingredients!"
                1  ($ Cheese)
                2  (d/div ($ Cheese) ($ Cheese))
                3  (d/div ($ Cheese) ($ Cheese) ($ Cheese))
                4  (d/div ($ Cheese) ($ Cheese) ($ Cheese) ($ Cheese))
                5  (d/div ($ Cheese) ($ Cheese) ($ Cheese) ($ Cheese) ($ Cheese)))
              (case (:meat burger)
                0 nil
                1  ($ Meat)
                2  (d/div ($ Meat) ($ Meat)))
              (case (:salad burger)
                0 nil
                1  ($ Salad)
                2  (d/div ($ Salad) ($ Salad))
                3  (d/div ($ Salad) ($ Salad) ($ Salad))
                4  (d/div ($ Salad) ($ Salad) ($ Salad) ($ Salad))
                5  (d/div ($ Salad) ($ Salad) ($ Salad) ($ Salad) ($ Salad)))
              (case (:bacon burger)
                0 nil
                1  ($ Bacon)
                2  (d/div ($ Bacon) ($ Bacon))
                3  (d/div ($ Bacon) ($ Bacon) ($ Bacon)))
              ($ BreadBottom)))
     (d/div {:className "text-xl text-gray-600 py-2 font-semibold border-b"} "Total Price: " (.toFixed full-price 2))
       ($ IngredientsWrapper
          ($ ButtonWrapper {:disabled (= 0 (:cheese burger))
                            :on-click (partial set-burger #(update % :cheese dec))} "-")
          ($ CheeseWrapper (get-in ingredients [:cheese :type]) " Count: " (.toFixed (:cheese burger) 2))
          ($ ButtonWrapper {:disabled (<= 5 (:cheese burger))
                            :on-click (partial set-burger #(update % :cheese inc))} "+"))
       ($ IngredientsWrapper
          ($ ButtonWrapper {:disabled (= 0 (:meat burger))
                            :on-click (partial set-burger #(update % :meat dec))} "-")
          ($ MeatWrapper (get-in ingredients [:meat :type]) " Count:  " (.toFixed (:meat burger) 2))
          ($ ButtonWrapper {:disabled (<= 2 (:meat burger))
                            :on-click (partial set-burger #(update % :meat inc))} "+"))
     ($ IngredientsWrapper
          ($ ButtonWrapper {:disabled (= 0 (:salad burger))
                            :on-click (partial set-burger #(update % :salad dec))} "-")
          ($ SaladWrapper (get-in ingredients [:salad :type]) " Count:  " (.toFixed (:salad burger) 2))
          ($ ButtonWrapper {:disabled (<= 5 (:salad burger))
                            :on-click (partial set-burger #(update % :salad inc))} "+"))
     ($ IngredientsWrapper
          ($ ButtonWrapper {:disabled (= 0 (:bacon burger))
                            :on-click (partial set-burger #(update % :bacon dec))} "-")
          ($ BaconWrapper (get-in ingredients [:bacon :type]) " Count:  " (.toFixed (:bacon burger) 2))
          ($ ButtonWrapper {:disabled (<= 3 (:bacon burger))
                            :on-click (partial set-burger #(update % :bacon inc))} "+"))
     ($ SingUpWrapper
        ($ SingUpButton {:on-click #(set-burger {:cheese 0 :meat 0 :salad 0 :bacon 0})} "Reset Order")))))

(def BurgerBuilder (with-keechma BurgerBuilderRenderer))



