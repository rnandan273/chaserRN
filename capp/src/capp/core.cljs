(ns capp.core
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :refer [subscribe dispatch dispatch-sync]]
              [oops.core :refer [ocall]]
              [capp.handlers]
              [capp.subs]))

(def ReactNative (js/require "react-native"))
(def expo (js/require "expo"))
(def AtExpo (js/require "@expo/vector-icons"))
(def ionicons (.-Ionicons AtExpo))
(def ic (r/adapt-react-class ionicons))

(def text1 (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def Alert (.-Alert ReactNative))

(def NativeBase (js/require "native-base"))
(def text (r/adapt-react-class (.-Text NativeBase)))
(def button (r/adapt-react-class (.-Button NativeBase)))

(def container (r/adapt-react-class (.-Container NativeBase)))
(def header (r/adapt-react-class (.-Header NativeBase)))
(def body (r/adapt-react-class (.-Body NativeBase)))
(def leftn (r/adapt-react-class (.-Left NativeBase)))
(def rightn (r/adapt-react-class (.-Right NativeBase)))
(def title (r/adapt-react-class (.-Title NativeBase)))
(def icon (r/adapt-react-class (.-Icon NativeBase)))
(def content (r/adapt-react-class (.-Content NativeBase)))
(def lst (r/adapt-react-class (.-List NativeBase)))
(def lstitem (r/adapt-react-class (.-ListItem NativeBase)))
(def card (r/adapt-react-class (.-Card NativeBase)))
(def carditem (r/adapt-react-class (.-CardItem NativeBase)))

(def react-navigation (js/require "react-navigation"))
(def react-navigation-tabs (js/require "react-navigation-tabs"))
(def react-navigation-stack (js/require "react-navigation-stack"))

;(def tab-navigator (.-TabNavigator react-navigation))
(def tab-screen (.-TabScreen react-navigation))
(def stack-navigator (.-StackNavigator react-navigation))
(def create-tab-navigator (.-createBottomTabNavigator react-navigation-tabs))
(def create-stack-navigator (.-createStackNavigator react-navigation-stack))
(def create-app-container (.-createAppContainer react-navigation))


(def form (r/adapt-react-class (.-Form NativeBase)))
(def item (r/adapt-react-class (.-Item NativeBase)))
(def label (r/adapt-react-class (.-Label NativeBase)))
(def input (r/adapt-react-class (.-Input NativeBase)))

(def picker (r/adapt-react-class (.-Picker NativeBase)))
(def pickeritem (r/adapt-react-class (.-Picker.Item NativeBase)))



(def button-color "#2952a3")
(def item-color "#CC0000")
(def white-color "#FFFFFF")
(def fab-color "#D4E157")
(def active-color "tomato")
(def card-color "#FEF9E7")

(def stack-color white-color);"#EAF2F8")

(def input-color "#B7E2F0")
(def tag-line "Safe, Secure, Authentic")
(def text-limit 15)

(defn alert [title]
  (.alert Alert title))

(defn getRow [btext navigation price]
 (fn []
  [lstitem {:icon true};[text body]]
     ;[leftn  [button {:transparent true} btnTextL]]
     [body [text {:style {:font-size 16 :font-weight "300"}} btext]]
     [rightn  
         [text (str "Rs " price)] 
            [button {:transparent true :style {:margin-left "2%"}
                     :on-press #(do (dispatch [:set-selected {:name btext}])
                                    (.navigate navigation "Details"))
                     } [icon {:name "eye"}]]
            [button {:transparent true :style {:margin-left "2%"}
                     :on-press #(dispatch [:add-selected {:name btext}])
                     } [icon {:name "add"}]]
     ]]))


(defn catalog-view []
  (let [items (subscribe [:get-items])]
    (fn []
      [container;style {:flex-direction "column" :margin 40 :align-items "center"}}
        [header
           [leftn  [button {:transparent true} [icon {:name "arrow-back"}]]]
           [body [title {:style {:font-size 20 :font-weight "500"}} "Catalog"]]
           [rightn  [button {:transparent true
                             :on-press #(dispatch [:set-status false])
                             } [icon {:name "md-exit"}]]]]
        (comment[content
          [lst
            (for [item @items]
            ^{:key (str "at3 -" (rand 10000000))}
              [(getRow (:name item) (:price item))])]]
            )
       ])))



(defn landing-view []
  (let [greeting (subscribe [:get-greeting])]
    (fn []
      [container {:style {:flex-direction "column" :margin 0}}
       [image {:source (js/require "./assets/images/bevco1.png");(js/require "./assets/images/chaser.png");(js/require "./assets/images/MSILLogo.png") 
               :style {:width "30%"
                       :height "14%"
                       :margin 20
                       :margin-top 50
                       :margin-left "35%"
                       }}]
      [image {:source (js/require "./assets/images/banner.png")
               :style {:width "100%"
                       :height "36%"
                       :margin-bottom "0%"}}]
       [text {:style {:font-size 20 :margin 15 :margin-left "20%" :font-weight "bold"}} 
          tag-line]

      [container {:style {
                          :background-color "#2E0C0B" 
                          ;:background-color "#FFFFFF" 
                          :flex-direction "column" 
                          :margin 0
                          :alignItems "center"}}
       [button {:transparent false 
                :primary true 
                :style {:font-size 20 :justifyContent "center" :margin-left "5%" :margin-right "5%" :margin-top "14%" :width 280}
                :on-press #(dispatch [:set-status true])}  
          [icon {:name "logo-facebook"}] [text {:style {:font-weight "600"}} (str "Login with Facebook")]]
       [button {:transparent false
                :primary true 
                :style{:font-size 20 :justifyContent "center" :margin-left "5%" :margin-right "5%" :margin-top "4%" :width 280}
                :on-press #(dispatch [:set-status true])}  
          [icon {:name "call"}] [text {:style {:font-weight "600"}} (str "Login with Phone Number")]]
       [button {:transparent false 
                :primary true 
                :style {:font-size 20 :justifyContent "center" :margin-left "5%" :margin-right "5%" :margin-top "4%" :width 280}
                :on-press #(dispatch [:set-status true])}  
          [icon {:name "logo-google"}] [text {:style {:font-weight "600"}} (str "Login with Google")]]]])))


(defn card-comp-old [item description footer]
  (fn []
    [card
      [carditem {:header false} [text (str (:order item))]]
      ;[carditem [body [text (str (:order item) " - Rs " (:details item))]]]
      [carditem {:footer false}
        [button {:transparent false 
                :primary true 
                :small true
                :on-press #(dispatch [:remove-selected {:name {:order (:order item) :details (:details item)}}])
                :style {:margin-top "0%"}}  
            [text (str "Cancel")]]]
      ]))

(defn card-comp [item description footer]
  (fn []
    [card [carditem {:header false :style {:backgroundColor card-color}}
      [leftn  
         [button {:small true 
                  :transparent true :style {:margin-left "0%"}
                  :on-press #(dispatch [:remove-selected {:name {:order (:order item) :details (:details item)}}])
                     } [icon {:name "close-circle"}]]]
      [text {:style {:font-size 14}} (str (subs (:order item) 0 (if (> (count (:order item)) 25) 25 (count (:order item)))) ": Rs " (:details item))]
      [rightn]
      ]]))

(defn payment-card-comp [item description footer]
  (fn []
    [card [carditem {:header false :style {:backgroundColor card-color}}
      [leftn]  
         
      [text (str (subs (:order item) 0 (if (> (count (:order item)) text-limit) text-limit (count (:order item)))) ": Rs " (:details item))]
      [rightn]
      ]]))


(defn pg-comp [item]
  (fn []
    [card
      [carditem {:header false} [text {:style {:width 150}}(str item)]
      [rightn  
         [button {:small true :transparent true :style {:margin-left "0%"}} [icon {:name "card"}]]]]]))

(defn wallet-comp [item]
  (fn []
    [card
      [carditem {:header false} [text {:style {:width 150}}(str item)]
      [rightn  
         [button {:small true :transparent true :style {:margin-left "0%"}} [icon {:name "wallet"}]]]]]))


(defn details-card-comp [item]
  (fn []
    [card
      [carditem {:header true} [text (str item)]]
      [carditem
        [body
          [text "This product is available at the following locations"]]]]))

(defn address-card-comp [item]
  (fn []
    [card 
      [carditem {:style {:backgroundColor card-color}}
        [body
          [text item]]]]))

(defn summary-card-comp [item]
  (fn []
    [card [carditem {:header false} 
      [text (str (:order (:name item)) ":" (:details (:name item)))]
      [rightn  
         [button {:small true :transparent true :style {:margin-left "0%"}
                     ;:on-press #(dispatch [:add-selected {:name btext}])
                     } [icon {:name "trash"}]]]]]))
(defn details-comp []
  (let [item (subscribe [:get-selected])]
  (fn [{:keys [navigation]}]
      [container {:style {:flex-direction "column" :margin 2}}
        [content
           [(details-card-comp (:name @item))]
           [button {:transparent false 
                :style {:margin 100}
                :primary true
                :onPress (fn [] (do (dispatch [:add-selected {:name (:name @item)}])
                                   (.navigate navigation "Catalog")))}  [text "Checkout"]]]])))

(defn catalog-comp []
  (let [items (subscribe [:get-items])]
    (fn [{:keys [navigation]}]
      [container {:style {:flex-direction "row" :margin 2 :background-color stack-color}}
        [content
          [lst
            (for [item @items]
            ^{:key (str "at3 -" (rand 10000000))}
              [(getRow (:name item) navigation (:price item))])]]
       ])))


(defn choice-comp-1 []
  (let [item (subscribe [:get-selected])]
  (fn [{:keys [navigation]}]
      [container
      [container {:style {:flex-direction "row" :margin 2}}
           ;[(details-card-comp (:name @item))]
        ;[content 
           [button {:transparent false 
                :style {:margin 10}
                :primary true
                :onPress (fn [] (do ;(dispatch [:add-selected (list {:name (:name @item)})])
                                   (.navigate navigation "Catalog")))}  [text "Beer"]]
            [button {:transparent false 
                :style {:margin 10}
                :primary true
                :onPress (fn [] (do ;(dispatch [:add-selected (list {:name (:name @item)})])
                                   (.navigate navigation "Catalog")))}  [text "Rum"]]
            [button {:transparent false 
                :style {:margin 10}
                :primary true
                :onPress (fn [] (do ;(dispatch [:add-selected (list {:name (:name @item)})])
                                   (.navigate navigation "Catalog")))}  [text "Whisky"]]
            [button {:transparent false 
                :style {:margin 10}
                :primary true
                :onPress (fn [] (do ;(dispatch [:add-selected (list {:name (:name @item)})])
                                   (.navigate navigation "Catalog")))}  [text "Wine"]]
            [button {:transparent false 
                :style {:margin 10}
                :primary true
                :onPress (fn [] (do ;(dispatch [:add-selected (list {:name (:name @item)})])
                                   (.navigate navigation "Catalog")))}  [text "Brandy"]]
            [button {:transparent false 
                :style {:margin 10}
                :primary true
                :onPress (fn [] (do ;(dispatch [:add-selected (list {:name (:name @item)})])
                                   (.navigate navigation "Catalog")))}  [text "Lab"]]
            [button {:transparent false 
                :style {:margin 10}
                :primary true
                :onPress (fn [] (do ;(dispatch [:add-selected (list {:name (:name @item)})])
                                   (.navigate navigation "Catalog")))}  [text "Vodka"]]]
            
           ])))

(defn choice-card-comp [header description footer]
  (fn []
    [card
      [carditem {:header false :style {:background-color card-color}} [text {:style {:font-weight "500"}} header]]]))

(defn list-item-comp [category items]
  (fn []
    [lstitem {:icon true}
      [body 
        [picker {:note false :itemStyle {:color button-color} :placeholderStyle {:color button-color :font-weight "bold"} :onValueChange (fn [value] (dispatch [:add-selected {:name (js->clj value :keywordize-keys true)}])) :mode "dialog" :placeholder (str category)}
          (for [item items]
          ^{:key (str "at3 -" (rand 10000000))}
            [pickeritem {:label (str (:order item) (if (not= "" (:details item)) (str "  Rs : " (:details item)))) :value {:order (:order item) :details (:details item)}}])]
            ]]))

(defn choice-comp []
  (let [master (subscribe [:master])
        selections (subscribe [:get-list])]
  (fn [{:keys [navigation]}]
    [container {:style {:flex-direction "row" :margin 0 :background-color white-color}}
      [content 
        [(choice-card-comp "SELECT PRODUCTS" "Select services" "footer")]
       [lst
          (for [item @master]
          ^{:key (str "at3 -" (rand 10000000))}
           [(list-item-comp (:category item) (:items item))])]

       [card {:style {:margin-top "5%"}} 
        [carditem {:header false :style {:background-color card-color}} [text {:style {:font-weight "500"}} (str "You have " (count @selections) " selections")]]
        [carditem {:header false :style {:background-color card-color}} [text {:style {:font-weight "500"}} (str "Check your order, Click on Orders Button below")]]]

        [button {:transparent false 
                :style {:background-color button-color :font-weight "bold" :justifyContent "center" :margin-top 10 :margin-left 100 :margin-right 100}
                :primary true
                :onPress (fn [] (.navigate navigation "Orders"))}  [text {:style {:font-size 14 :font-weight "700"}} "ORDERS"]]
        ;[text (str @(subscribe [:master]))]

        (comment [lst {:style {:margin "2%"}}
          (for [item @selections]
          ^{:key (str "at3 -" (rand 10000000))}
           [(summary-card-comp item)])])]])))


(defn catalog-stack-navigator []
  (create-stack-navigator 
     (clj->js  {:Catalog1 {:screen (r/reactify-component (catalog-comp))
                          
                          :navigationOptions {:title "Catalog1"
                                              :headerRight (r/as-element [button {:transparent true 
                                                                                  :on-press #(dispatch [:set-status false])} 
                                                             [text {:style {:fontWeight "bold"}} "Logout"]])}}
                :Catalog {:screen (r/reactify-component (choice-comp))
                          
                        :navigationOptions {:title "Catalog"
                                            :headerStyle {:backgroundColor stack-color}
                                            :headerBackTitleStyle {:color button-color :fontWeight "bold"}
                                            ;:headerTitleStyle {:color button-color :fontWeight "bold"}
                                            :headerRight (r/as-element [button {:transparent true 
                                                                                :on-press #(dispatch [:set-status false])} 
                                                             [text {:style {:fontWeight "bold"}} "Logout"]])}}
                :Details {:screen (r/reactify-component (details-comp))
                          :navigationOptions {:title "Details"}}
               })
    (clj->js {:initialRouteName "Catalog"
              :navigationOptions {:headerStyle {:backgroundColor button-color}
                                  :headerTintColor white-color
                                  :headerBackTitleStyle {:color button-color :fontWeight "bold"}
                                  :headerTitleStyle {:color button-color :fontWeight "bold"}
                                              }})))



(defn orders-comp []
  (let [items (subscribe [:get-list])]
    (fn [{:keys [navigation]}]
      (if (= 0 (count @items))
        [container {:style {:flex-direction "column" :margin 0 :background-color white-color}}
          [content 
            [view
              [header [text "No Orders currently, Please select from Catalog"]]
              
              [button {:transparent false 
                       :style {:background-color button-color :font-weight "bold" :justifyContent "center" :margin-top 10 :margin-left 100 :margin-right 100}
                       :primary true
                       :onPress (fn [] (.navigate navigation "Catalog"))}  [text {:style {:font-size 14 :font-weight "700"}} "CATALOG"]]]]]

      [container {:style {:flex-direction "column" :margin 0 :background-color white-color}}
        [content 
          [view
            (for [item @items]
              ^{:key (str "at3 -" (rand 10000000))}
            [(card-comp (:name item) "description" "footer")])

            [button {:transparent false 
                     :style {:background-color button-color :font-weight "bold" :justifyContent "center" :margin-top 10 :margin-left 100 :margin-right 100}
                     :primary true
                     :onPress (fn [] (.navigate navigation "Payment"))}  [text {:style {:font-size 14 :font-weight "700"}} "PROCEED"]]]]]))))


(defn payment-comp []
  (let [items (subscribe [:get-list])]
    (fn [{:keys [navigation]}]
      (if (= 0 (count @items))
        [container {:style {:flex-direction "column" :margin 0 :background-color white-color}}
          [content 
            [view
              [header  {:style {:backgroundColor card-color}} [text "No Orders currently, Please select from Catalog"]]
              
              [button {:transparent false 
                       :style {:background-color button-color :font-weight "bold" :justifyContent "center" :margin-top 10 :margin-left 100 :margin-right 100}
                       :primary true
                       :onPress (fn [] (.navigate navigation "Catalog"))}  [text {:style {:font-size 14 :font-weight "700"}} "CATALOG"]]]]]

      [container {:style {:flex-direction "column" :margin 0 :background-color white-color}}
        [content 
          [view
            (comment (for [item @items]
              ^{:key (str "at3 -" (rand 10000000))}
            [(payment-card-comp (:name item) "description" "footer")])
            )

            ;[header [text (str "Coupon for the above orders is " (rand-int 10000) " and valid until 5 days from today, product available at")]]
            ;[header [text (str "7024, Fortkochi,K.B.Jacob, Fort Kochi 682001")]]
            [(address-card-comp (str "Your Bill amount is INR " (rand-int 10000) ".\nYour Coupon number is " (rand-int 10000) 
              " and valid for 19 May 2020. \nPlease pick up your order from  \nBEVCO Outlet \n7024, Fortkochi,K.B.Jacob, Fort Kochi 682001"))]

            [(address-card-comp  "Payment Options")]

            [(wallet-comp "UPI")]
            [(pg-comp "Credit Card")]
            [(pg-comp "Debit Card")]
            [(wallet-comp "PayTM")]

            [button {:transparent false 
                     :style {:background-color button-color :font-weight "bold" :justifyContent "center" :margin-top 10 :margin-left 100 :margin-right 100}
                     :primary true
                     :onPress (fn [] (.navigate navigation "Catalog"))}  [text {:style {:font-size 14 :font-weight "700"}} "BACK"]]]]]))))


(defn orders-stack-navigator []
  (create-stack-navigator 
     (clj->js  {:Orders {:screen (r/reactify-component (orders-comp))
                          :navigationOptions {:title "Orders"
                                              :headerStyle {:backgroundColor stack-color}
                                              :headerBackTitleStyle {:color button-color :fontWeight "bold"}
                                              ;:headerTitleStyle {:color button-color :fontWeight "bold"}
                                              :headerRight (r/as-element [button {:transparent true 
                                                                                :on-press #(dispatch [:set-status false])} 
                                                             [text {:style {:fontWeight "bold"}} "Logout"]])}}
                :Payment {:screen (r/reactify-component (payment-comp))
                          :navigationOptions {:title "Payment"
                                              :headerStyle {:backgroundColor stack-color}
                                              :headerBackTitleStyle {:color button-color :fontWeight "bold"}
                                              ;:headerTitleStyle {:color button-color :fontWeight "bold"}
                                              :headerRight (r/as-element [button {:transparent true 
                                                                                :on-press #(dispatch [:set-status false])} 
                                                             [text {:style {:fontWeight "bold"}} "Logout"]])}}
               })
    (clj->js {:initialRouteName "Orders"
              :navigationOptions {:headerStyle {:backgroundColor button-color}
                                  :headerTintColor white-color
                                  :headerBackTitleStyle {:color button-color :fontWeight "bold"}
                                  :headerTitleStyle {:color button-color :fontWeight "bold"}
                                              }})))

(defn profile-comp []
 (let [pstate (r/atom {:flag false})]
  (fn  [{:keys [navigation]}]
    (if (:flag @pstate)
      [container {:style {:background-color white-color}}
        [content
          [card {:style {:width "100%" :margin-top 4 :backgroundColor card-color}}
            [carditem {:header true :style {:background-color card-color}} [text (str "Edit Profile")]]
            [form {:style {:margin-top "-4%" :backgroundColor card-color :width "80%"}}
                [item {:floatingLabel true :style {:backgroundColor card-color :margin-left "2%" :margin-right "5%"}}
                  [label {:style {:font-size 14 :margin-left "1%"}} "NAME"]
                  [input {:underlineColor "black" :placeholderTextColor "black" :underline true :style {:margin-bottom "7%" :height "90%"}
                  }]]
                [item {:floatingLabel true :style {:backgroundColor card-color :margin-left "2%" :margin-right "5%"}}
                  [label {:style {:font-size 14 :margin-left "1%"}} "ADDRESS"]
                  [input {:underlineColor "black" :placeholderTextColor "black" :underline true :style {:margin-bottom "7%" :height "90%"}
                  }]]

                [item {:floatingLabel true :style {:backgroundColor card-color :margin-left "2%" :margin-right "5%"}}
                  [label {:style {:font-size 14 :margin-left "1%" :height "90%"}} "ID PROOF"]
                  [input {:underlineColor "black" :placeholderTextColor "black" :underline true :style {:margin-bottom "7%" :height "90%"}
                  }]]]
            [carditem {:footer false :style {:background-color card-color}} 
              [button {:transparent false 
                      :primary true 
                      :small true
                      :onPress #(swap! pstate assoc :flag false)
                      :style {:background-color button-color :margin-top "0%"}}  
                  [text {:style {:font-size 14 :font-weight "700"}} (str "DONE")]]]]]]

          [container {:style {:background-color white-color}}
              [card {:style {:width "100%" :margin 2 :background-color card-color}}
              [carditem {:header true :style {:background-color card-color}} [text (str "Account Details")]]
              [carditem {:style {:background-color card-color}}
                [body
                  [text {:style {:font-size 14}} "NAME"] [text "Mr XYZ"]]]
              [carditem {:style {:background-color card-color}}
                [body
                  [text {:style {:font-size 14}} "ADDRESS"] [text "No 123/1 MG Road, Bangalore"]]]

              [carditem {:style {:background-color card-color}}
                [body
                  [text {:style {:font-size 14}} "ID PROOF"] [text "Aadhar number 123 434 5555"]]]

              [carditem {:style {:background-color card-color}}
                [body
                  [text {:style {:font-size 14}} "REGISTERED ON"] [text "Jan 23 2001"]]]
              [carditem {:footer false :style {:background-color card-color}}
                [button {:transparent false 
                        :primary true 
                        :small true
                        :onPress #(swap! pstate assoc :flag true)
                        :style {:background-color button-color :margin-top "0%"}}  
                    [text {:style {:font-size 14 :font-weight "700"}} (str "EDIT")]]]]]))))


(defn profile-stack-navigator []
  (create-stack-navigator 
     (clj->js  {:Profile {:screen (r/reactify-component (profile-comp))
                          :navigationOptions {:title "Profile"
                                              :headerStyle {:backgroundColor stack-color}
                                              :headerBackTitleStyle {:color button-color :fontWeight "bold"}
                                              ;:headerTitleStyle {:color button-color :fontWeight "bold"}
                                              :headerRight (r/as-element [button {:transparent true 
                                                                                :on-press #(dispatch [:set-status false])} 
                                                             [text {:style {:fontWeight "bold"}} "Logout"]])}}
               })
    (clj->js {:initialRouteName "Profile"
              :navigationOptions {:headerStyle {:backgroundColor button-color}
                                  :headerTitleContainerStyle {:color button-color}
                                  
                                  :headerBackTitleStyle {:color button-color :fontWeight "bold"}
                                  :headerTitleStyle {:color button-color :fontWeight "bold"}
                                              }})))
(def tab-navigator 
  (create-tab-navigator
   (clj->js {:Catalog {:screen (r/reactify-component (catalog-stack-navigator))}
             :Orders {:screen (r/reactify-component (orders-stack-navigator))}
             :Profile {:screen (r/reactify-component (profile-stack-navigator))}})

   (clj->js {:order ["Catalog" "Orders" "Profile"]
             :animationEnabled  true
             :tabBarOptions {:activeTintColor active-color 
                            :inactiveTintColor white-color 
                            :labelStyle {:fontSize 16 :fontWeight "bold"}
                            :tabStyle {:backgroundColor button-color :margin 15}
                            :style {:backgroundColor button-color}}})))


(defn app-root []
  (let [status (subscribe [:get-status])]
    (fn []
      (if @status
        [:> (create-app-container tab-navigator) {}];[(catalog-view)]
        [(landing-view)]))))

;[:> (create-app-container tab-navigator) {}];

(defn app-root-old []
  (let [greeting (subscribe [:get-status])]
    (fn []
      [view {:style {:flex-direction "column" :margin 40}}
       [image {:source (js/require "./assets/images/cljs.png")
               :style {:width 200
                       :height 200}}]
       [text {:style {:font-size 30 :font-weight "100" :margin-bottom 20 :text-align "center"}} @greeting]
       [ic {:name "ios-arrow-down" :size 60 :color "green"}]
       [touchable-highlight {:style {:background-color "#999" :padding 10 :border-radius 5}
                             :on-press #(alert "HELLO!")}
        [text {:style {:color "white" :text-align "center" :font-weight "bold"}} "press me"]]])))

(defn init []
  (dispatch-sync [:initialize-db])
  (ocall expo "registerRootComponent" (r/reactify-component app-root)))
