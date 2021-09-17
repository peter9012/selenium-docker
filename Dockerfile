#FROM openjdk:12.0.1-jdk-oraclelinux7
FROM openjdk:8u212-jre-alpine3.9

# Workspace
WORKDIR /usr/share/udemy

RUN apk add curl jq
# ADD .jar under target from host
# into this image
ADD target/selenium-docker.jar 			  selenium-docker.jar
ADD target/selenium-docker-tests.jar 	selenium-docker-tests.jar
ADD target/libs							          libs

# ADD suite files
ADD book-flight-module.xml				    book-flight-module.xml
ADD search-module.xml					        search-module.xml

ADD healthcheck.sh                    healthcheck.sh

ENTRYPOINT sh healthcheck.sh
#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE
