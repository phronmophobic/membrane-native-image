#!/bin/bash

set -e
set -x

sudo apt install g++ make zlib1g-dev

# wget -O liberica.deb https://download.bell-sw.com/vm/22.3.5/bellsoft-liberica-vm-core-openjdk11.0.22+12-22.3.5+1-linux-amd64.deb

# sudo apt install ./liberica.deb

wget -O liberica.tar.gz https://download.bell-sw.com/vm/23.1.2/bellsoft-liberica-vm-core-openjdk21.0.2+14-23.1.2+1-linux-amd64.tar.gz


tar -xzf liberica.tar.gz

rm liberica.tar.gz
mv bellsoft* $HOME/liberica/

