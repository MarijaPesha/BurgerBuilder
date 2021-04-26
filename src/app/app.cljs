(ns app.app
  (:require [keechma.next.controllers.router]
            [keechma.next.controllers.dataloader]
            [keechma.next.controllers.subscription]
            [app.controllers.burgerBuilder]
            [app.controllers.login]
            [app.controllers.user]
            [app.controllers.order]
            ["react-dom" :as rdom]))

(defn page-eq? [page] (fn [{:keys [router]}] (= page (:page router))))

(def app
  {:keechma.subscriptions/batcher rdom/unstable_batchedUpdates,
   :keechma/controllers
   {:router {:keechma.controller/params true
             :keechma.controller/type :keechma/router
             :keechma/routes [["" {:page "home"}] ":page"
                              ":page/:subpage"]}
    :dataloader {:keechma.controller/params true
                 :keechma.controller/type :keechma/dataloader}
    :burgerBuilder    #:keechma.controller {:params true
                                            :deps [:router :user ]}
    :login    #:keechma.controller {:deps [:router]
                                    :params (page-eq? "login")}
    :user     #:keechma.controller {:params true}
    :order     #:keechma.controller {:deps [:router]
                                     :params (page-eq? "order")}}})