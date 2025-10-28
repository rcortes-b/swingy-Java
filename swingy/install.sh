#!/bin/sh
# Load env variables
export $(grep -v '^#' .env | xargs)

docker build \
  --build-arg POSTGRES_USER=$POSTGRES_USER \
  --build-arg POSTGRES_PASSWORD=$POSTGRES_PASSWORD \
  --build-arg POSTGRES_DB=$POSTGRES_DB \
  -t swingy_db_image -f db/Dockerfile .

docker run -d \
  --name swingy_db \
  -p 5432:5432 \
  -v ./db/data:/var/lib/postgresql/data swingy_db_image
