(ns capp.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :get-greeting
 (fn [db _]
   (:greeting db)))

(reg-sub
 :get-status
 (fn [db _]
   (:login db)))

(reg-sub
 :get-items
 (fn [db _]
   (:items db)))

(reg-sub
 :get-selected
 (fn [db _]
   (:selected db)))

(reg-sub
 :get-list
 (fn [db _]
   (:selected-list db)))


(reg-sub
 :master1
 (fn [db _]
   (:master db)))

(reg-sub
 :master
 (fn [db _]
 	(map (fn [x] {:category (:category x) :items (map (fn [y] {:pid (nth y 0) :order (nth y 1) :details (nth y 2)}) (:items x))}) 
          (:kerala db))))



