#!/bin/bash   
set -v

rm -rf jmods jlink
mkdir jmods 

#Compile
javac -d ./mods/ --module-source-path src $(find src -name "*.java")

#Jmod
jmod create jmods/info.modernjava.customruntimeimage.jmod --class-path mods/info.modernjava.customruntimeimage

#Jlink
jlink --module-path $JAVA_HOME/jmods:jmods --add-modules info.modernjava.customruntimeimage --output jlink --launcher run=info.modernjava.customruntimeimage/info.modernjava.customruntimeimage.CustomRuntimeImageApp
