#!/bin/bash

set -e
set -x

sudo apt install g++ make zlib1g-dev

# liberica
# wget -O liberica.tar.gz https://download.bell-sw.com/vm/23.1.2/bellsoft-liberica-vm-core-openjdk21.0.2+14-23.1.2+1-linux-amd64.tar.gz
# tar -xzf liberica.tar.gz

# rm liberica.tar.gz
# mv bellsoft* $HOME/graalvm/

# graalvm
wget -O graalvm.tar.gz https://github.com/graalvm/graalvm-ce-builds/releases/download/jdk-21.0.2/graalvm-community-jdk-21.0.2_linux-x64_bin.tar.gz
tar -xzf graalvm.tar.gz

rm graalvm.tar.gz
ls
mv graalvm* $HOME/graalvm/

