
# Example of using membrane's java2d backend with membrane.

## Instructions

## Setup


### Prep deps
Git deps must be prepped

```sh
clojure -X:deps prep 
```

### Install native image

These instructions have only been tested with liberica, but should theoretically work with graalvm on linux (but not mac osx).

Follow the instructions for installing native image:
- Liberica https://docs.bell-sw.com/liberica-nik/22.3.5b1-11.0.22b12/general/install-guide/
- graalvm https://github.com/graalvm/graalvm-ce-builds/releases

## Compile

```sh
./compile.sh
```

## Run

```sh
./hello-world
```


## Resources

https://www.praj.in/posts/2021/compiling-swing-apps-ahead-of-time/
https://github.com/praj-foss/swing-graalvm-demo

Automatically Discovering Reflection Config
https://github.com/clj-easy/graal-docs?tab=readme-ov-file#automatically-discovering-reflection-config

