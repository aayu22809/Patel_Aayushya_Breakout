#!/bin/bash
# author of this script had no experience with bash before
mvn clean install compile
for i in {1..4} ; do
  mvn exec:java -Dexec.mainClass="com.apcs.ljaag.LJAAG\$Client" -f "./ljaag/pom.xml" &
done
mvn exec:java -Dexec.mainClass="com.apcs.ljaag.LJAAG" -f "./ljaag/pom.xml"
