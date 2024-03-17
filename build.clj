(ns build
  (:require [clojure.tools.build.api :as b]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def lib 'com.phronemophobic.membrane/native-image)
(def version "0.1")

(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"
                            :aliases [:native-image]}))
(def jar-file (format "target/%s-%s.jar" (name lib) version))
(def src-pom "./pom-template.xml")

(defn clean [_]
  (b/delete {:path "target"}))

(defn compile [_]
  (b/compile-clj {:class-dir class-dir
                  :basis basis
                  :java-opts ["-Dtech.v3.datatype.graal-native=true"
                              "-Dclojure.compiler.direct-linking=true"
                              "-Dclojure.spec.skip-macros=true"]
                  :ns-compile '[com.phronemophobic.membrane.hello-world]})
  #_(b/javac {:src-dirs ["src-java"]
            :class-dir class-dir
            :basis basis
            :javac-opts ["-source" "8" "-target" "8"]}))

(defn fix-reflect-config [f]
  (let [config (with-open [rdr (io/reader f)]
                 (json/read rdr))
        new-config (->> config
                        (remove (fn [{:strs [name]}]
                                  (str/ends-with? name "__init"))))]
    (with-open [w (io/writer f)]
      (json/write new-config w))))

(defn fix-config [_]
  (fix-reflect-config (io/file "config" "reflect-config.json")))

#_(defn jar [opts]
    (compile opts)
    (b/write-pom {:class-dir class-dir
                  :src-pom src-pom
                  :lib lib
                  :version version
                  :basis basis
                  :src-dirs ["src"]})
    (b/copy-dir {:src-dirs ["src" "resources"]
                 :target-dir class-dir})
    (b/jar {:class-dir class-dir
            :jar-file jar-file}))

#_(defn deploy [opts]
  (jar opts)
  (try ((requiring-resolve 'deps-deploy.deps-deploy/deploy)
        (merge {:installer :remote
                :artifact jar-file
                :pom-file (b/pom-path {:lib lib :class-dir class-dir})}
               opts))
       (catch Exception e
         (if-not (str/includes? (ex-message e) "redeploying non-snapshots is not allowed")
           (throw e)
           (println "This release was already deployed."))))
  opts)

