#!/bin/bash
# Build the processor module first
mvn clean install -f "./processor/pom.xml"

# Then build the rest of the project
mvn clean install compile

# Run the main game class
mvn exec:java -Dexec.mainClass="com.apcs.ljaag.LJAAG" -f "./ljaag/pom.xml"