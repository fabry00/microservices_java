#!/bin/sh

PIDs=""

####AccountService
echo "******************** STARTING AccountService *************************"
sleep 1s
java -jar AccountService/target/AccountService-1.0-SNAPSHOT-launcher.jar server AccountService/account-service.yml &
PID=$!
echo "PID PrOCESS: $PID"
PIDs="$PIDs $PID "
####################################ààà
sleep 5s

echo "*************************************************************"
echo "******************** ProcessService *************************"
sleep 1s
java -jar ProcessService/target/ProcessService-1.0-SNAPSHOT-launcher.jar server ProcessService/processservice-service.yml &
PID=$!
echo "PID PrOCESS: $PID"
PIDs="$PIDs $PID "
####################################ààà
sleep 5s

echo "*************************************************************"
echo "******************** Aggregator *************************"
sleep 1s
java -jar ServiceAggregator/target/ServiceAggregator-1.0-SNAPSHOT-launcher.jar server ServiceAggregator/aggregator-service.yml &
PID=$!
echo "PID PrOCESS: $PID"
PIDs="$PIDs $PID "

echo "*************************************************************"
echo "*************************************************************"
echo "*************************************************************"

echo "All pids processes: $PIDs"
echo "" > "PIDs"
echo "$PIDs" >> "PIDs"
