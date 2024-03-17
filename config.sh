#!/bin/bash

set -e
set -x

clojure -T:build compile

# not currently needed
java -agentlib:native-image-agent=config-output-dir=config -cp "$(clojure -Spath):target/classes" com.phronemophobic.membrane.hello_world

clojure -T:build fix-config
