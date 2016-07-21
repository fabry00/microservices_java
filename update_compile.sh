#!/bin/sh

SITE=""
if [ -z "$1" ]
  then
    echo "No argument supplied. ARG0=y to site or n only to pull and compile"
	exit
  else
    SITE=$1
fi

echo "-------------------------------------"
echo " UPDATEING FORM REPOSITORY"
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
	  mvn site
fi


echo "-------------------------------------"
echo "-------------------------------------"
echo "COMPILING ProcessService"
cd ../ProcessService 
mvn install
if [ $SITE == "y" ]
    then
	echo "-------------------------------------"
	echo "SITE ProcessService"
	  mvn site
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
	  mvn site
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
	  mvn site
fi

echo "-------------------------------------"
echo "-------------------------------------"
echo "END"
