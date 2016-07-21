#!/bin/sh

SITE=""
if [ -z "$1" ]
  then
    echo "No argument supplied. ARG0=y to site or n only to pull and compile"
	exit
  else
    SITE="&& mvn site"
fi

echo "-------------------------------------"
echo " UPDATEING FORM REPOSITORY"
git pull

echo "-------------------------------------"
echo "-------------------------------------"

echo "-------------------------------------"
echo "COMPILING COMMONS"
cd Commons 
mvn package && mvn install $SITE

echo "-------------------------------------"
echo "-------------------------------------"
echo "COMPILING ProcessService"
cd ProcessService 
mvn package && mvn install $SITE

echo "-------------------------------------"
echo "-------------------------------------"
echo "COMPILING AccountService"
cd AccountService 
mvn package && mvn install $SITE

echo "-------------------------------------"
echo "-------------------------------------"
echo "COMPILING ServiceAggregator"
cd ServiceAggregator 
mvn package && mvn install $SITE
