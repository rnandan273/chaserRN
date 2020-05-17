(ns env.index
  (:require [env.dev :as dev]))

;; undo main.js goog preamble hack
(set! js/window.goog js/undefined)

(-> (js/require "../../../js/figwheel-bridge")
    (.withModules #js {"./assets/images/chaser.png" (js/require "../../../assets/images/chaser.png"), "./assets/images/bevco.png" (js/require "../../../assets/images/bevco.png"), "./assets/icons/loading.png" (js/require "../../../assets/icons/loading.png"), "react-navigation-tabs" (js/require "react-navigation-tabs"), "./assets/images/chaser.jpeg" (js/require "../../../assets/images/chaser.jpeg"), "expo" (js/require "expo"), "./assets/images/cljs.png" (js/require "../../../assets/images/cljs.png"), "./assets/icons/app.png" (js/require "../../../assets/icons/app.png"), "./assets/images/msil_loading.png" (js/require "../../../assets/images/msil_loading.png"), "native-base" (js/require "native-base"), "react-navigation-stack" (js/require "react-navigation-stack"), "./assets/images/MSILLogo.png" (js/require "../../../assets/images/MSILLogo.png"), "./assets/images/bevco1.png" (js/require "../../../assets/images/bevco1.png"), "react-native" (js/require "react-native"), "react-navigation" (js/require "react-navigation"), "./assets/images/msil_loading.jpeg" (js/require "../../../assets/images/msil_loading.jpeg"), "react" (js/require "react"), "./assets/images/banner.png" (js/require "../../../assets/images/banner.png"), "create-react-class" (js/require "create-react-class"), "@expo/vector-icons" (js/require "@expo/vector-icons")}
)
    (.start "main" "expo" "192.168.1.4"))
