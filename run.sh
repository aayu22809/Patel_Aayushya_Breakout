#!/bin/bash
(cd processor ; mvn clean install compile)
mvn clean install compile
mvn exec:java -Dexec.mainClass="com.apcs.ljaag.LJAAG" -f "./ljaag/pom.xml"