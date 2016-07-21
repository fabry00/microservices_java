#!/bin/sh

git pull
SITE=""
if [ -z "$1" ]
  then
    echo "No argument supplied. ARG0=y to site or n only to pull and compile"
  else
    SITE="&& mvn site"
fi

cd Commons 
mvn package && mvn install $SITE

cd ProcessService 
mvn package && mvn install $SITE

cd AccountService 
mvn package && mvn install $SITE

cd ServiceAggregator 
mvn package && mvn install $SITE
