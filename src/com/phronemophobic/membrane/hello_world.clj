(ns com.phronemophobic.membrane.hello-world
  (:require [membrane.ui :as ui]
            [membrane.example.todo :as td]
            membrane.component 
            [membrane.java2d :as java2d])
  (:gen-class))

(defn -main [& _args]

  (println
   (str
    (ui/bounds
     ((membrane.component/make-app
       #'td/todo-app
       td/todo-state)))))
  #_(java2d/run
      (membrane.component/make-app
       #'td/todo-app
       td/todo-state)))
