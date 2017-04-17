#!/usr/bin/env bash
mvn package
scp target/scrum-poker-planner-server-1.0-SNAPSHOT.jar root@85.143.212.66:/home/projects/java/poker-planner-server
