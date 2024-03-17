#!/bin/bash

set -e
set -x

which java
java -version

clojure -T:build:native-image compile


    # -H:ConfigurationFileDirectories=config \

native-image \
    -cp "$(clojure -Spath):target/classes" \
    -H:Name=hello-world \
    -Djava.awt.headless=false \
    -H:+ReportExceptionStackTraces \
    -J-Dclojure.spec.skip-macros=true \
    -J-Dclojure.compiler.direct-linking=true \
    -J-Dtech.v3.datatype.graal-native=true \
    --features=clj_easy.graal_build_time.InitClojureClasses \
    --verbose \
    --no-fallback \
    com.phronemophobic.membrane.hello_world
