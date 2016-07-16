#!/bin/sh
if [ "$1" == "" ]; then
   echo "create_new_project com.mycompany DWGettingStarted com.mycompany.dwstart"
   echo "after follow create_new_project_next_step"
   exit 0
fi

#example: com.mycompany
GROUPID=$1
#example: DWGettingStarted (project name)  
ARTIFACTID=$2
#example: DWGettingStarted (bho)
DNAME=$ARTIFACTID
#package example: com.mycompany.dwstart
PACKAGE=$3
/f/apache-maven-3.3.9/bin/mvn archetype:generate -DgroupId=$GROUPID -DartifactId=$ARTIFACTID -Dname=$DNAME -Dpackage=$PACKAGE -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DinteractiveMode=false -e
