#!/bin/bash

mkdir Apps
cp pom.xml pom.stable
cp src/main/resources/META-INF/switchyard.xml switchyard.stable

# make a for loop here
# change the application name, queue used

for IDX in {1..10}
do
    cp pom.stable pom.xml
    cp switchyard.stable src/main/resources/META-INF/switchyard.xml
    ARTIFACT="switchyard-quickstart-camel-jms-binding"$IDX
    QUEUE="GreetingServiceQueue"$IDX
    sed -i -e 's/switchyard-quickstart-camel-jms-binding/'"$ARTIFACT"'/g' pom.xml
    sed -i -e 's/GreetingServiceQueue/'"$QUEUE"'/g' src/main/resources/META-INF/switchyard.xml
    mvn clean package -DskipTests
    mv target/switchyard-quickstart-camel-jms-binding?.jar Apps
done

mv pom.stable pom.xml
mv switchyard.stable src/main/resources/META-INF/switchyard.xml






