#!/bin/bash

SITE=""
if [ -z "$1" ]
  then
    echo "No argument supplied. ARG0=y to perform mvn site or n only to pull and mvn install"
	exit
  else
    SITE=$1
fi

echo "-------------------------------------"
echo " UPDATING FORM REPOSITORY"
git pull

echo "-------------------------------------"
echo "-------------------------------------"

echo "-------------------------------------"
echo "COMPILING COMMONS"
cd Commons 
mvn install
if [ $SITE == "y" ]
    then
	echo "-------------------------------------"
	echo "SITE COMMONS"
	  mvn site -Ddependency.locations.enabled=false
fi

echo "-------------------------------------"
read -p "Press any key to continue... " -n1 -s

echo "-------------------------------------"
echo "-------------------------------------"
echo "COMPILING ProcessService"
cd ../ProcessService 
mvn install
if [ $SITE == "y" ]
    then
	echo "-------------------------------------"
	echo "SITE ProcessService"
	  mvn site -Ddependency.locations.enabled=false
fi

echo "-------------------------------------"
read -p "Press any key to continue... " -n1 -s

echo "-------------------------------------"
echo "-------------------------------------"
echo "COMPILING AccountService"
cd ../AccountService 
mvn install
if [ $SITE == "y" ]
    then
	echo "-------------------------------------"
	echo "SITE AccountService"
	  mvn site -Ddependency.locations.enabled=false
fi

echo "-------------------------------------"
read -p "Press any key to continue... " -n1 -s

echo "-------------------------------------"
echo "-------------------------------------"
echo "COMPILING ServiceAggregator"
cd ../ServiceAggregator 
mvn install
if [ $SITE == "y" ]
    then
	echo "-------------------------------------"
	echo "SITE ServiceAggregator"
	  mvn site -Ddependency.locations.enabled=false
fi

echo "-------------------------------------"
echo "-------------------------------------"
echo "END"
