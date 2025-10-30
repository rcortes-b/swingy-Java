#!/bin/bash

docker kill $(docker ps -q) 2>/dev/null
docker rm $(docker ps -a -q) 2>/dev/null
docker volume rm $(docker volume ls -q) 2>/dev/null
docker rmi $(docker images -q) 2>/dev/null

sudo rm -rf db/data