(ns capp.handlers
  (:require
    [re-frame.core :refer [reg-event-db ->interceptor]]
    [clojure.spec.alpha :as s]
    [capp.db :as db :refer [app-db]]))

;; -- Interceptors ----------------------------------------------------------
;;
;; See https://github.com/Day8/re-frame/blob/develop/docs/Interceptors.md
;;
(defn check-and-throw
  "Throw an exception if db doesn't have a valid spec."
  [spec db]
  (when-not (s/valid? spec db)
    (let [explain-data (s/explain-data spec db)]
      (throw (ex-info (str "Spec check failed: " explain-data) explain-data)))))

(def validate-spec
  (if goog.DEBUG
    (->interceptor
        :id :validate-spec
        :after (fn [context]
                 (let [db (-> context :effects :db)]
                   (check-and-throw ::db/app-db db)
                   context)))
    ->interceptor))

;; -- Handlers --------------------------------------------------------------

(reg-event-db
  :initialize-db
  [validate-spec]
  (fn [_ _]
    app-db))

(reg-event-db
  :set-greeting
  [validate-spec]
  (fn [db [_ value]]
    (assoc db :greeting value)))

(reg-event-db
  :set-status
  [validate-spec]
  (fn [db [_ value]]
    (assoc db :login value)))

(reg-event-db
  :set-selected
  [validate-spec]
  (fn [db [_ value]]
    (assoc db :selected value)))

(reg-event-db
  :add-selected
  [validate-spec]
  (fn [db [_ value]]
    (update-in db [:selected-list] (fn [x] (conj x value)))))

(reg-event-db
  :remove-selected
  [validate-spec]
  (fn [db [_ value]]
    (assoc db :selected-list (remove #(= (:order (:name %)) (:order (:name value))) (:selected-list db)))))


