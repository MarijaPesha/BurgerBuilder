(ns app.app
  (:require [keechma.next.controllers.router]
            [keechma.next.controllers.dataloader]
            [keechma.next.controllers.subscription]
            [app.controllers.burgerBuilder]
             [app.controllers.login]
            ["react-dom" :as rdom]))


(def app
  {:keechma.subscriptions/batcher rdom/unstable_batchedUpdates,
   :keechma/controllers
   {:router {:keechma.controller/params true,
             :keechma.controller/type :keechma/router,
             :keechma/routes [["" {:page "home"}] ":page" 
                              ":page/:subpage"]},
    :dataloader {:keechma.controller/params true,
                 :keechma.controller/type :keechma/dataloader}
    :burgerBuilder    #:keechma.controller {:params true} 
    :login    #:keechma.controller {:params true} 
    }})