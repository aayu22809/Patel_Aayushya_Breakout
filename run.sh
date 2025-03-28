#!/bin/bash

# Compile project
mvn compile

# Run project
mvn exec:java -Dexec.mainClass="com.apcs.ljaag.LJAAG" -f "./ljaag/pom.xml"