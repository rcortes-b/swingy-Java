#!/bin/sh
DATA_FILE=db_data.sql
SCHEMA_FILE=schema.sql
DB_PATH=/home/$USER/Projects/swingy-Java/swingy/db
ROOT_PATH=/home/$USER/Projects/swingy-Java/swingy

#PostgreSQL installation
sudo apt install postgresql -y

#PostgreSQL Setup

sudo cp $DB_PATH/$DATA_FILE /tmp/$DATA_FILE
sudo cp $DB_PATH/$SCHEMA_FILE /tmp/$SCHEMA_FILE
sudo cp $DB_PATH/.env /tmp/.env

cd /tmp

export $(grep -v '^#' .env | xargs)
sudo -u postgres psql \
  -v db_name="$DB_NAME" \
  -v db_user="$DB_USER" \
  -f $DATA_FILE
sudo -u postgres psql -d swingy_db -f $SCHEMA_FILE


cd $ROOT_PATH

#sudo -i -u postgres

#psql

#CREATE DATABASE $DB_NAME;

#CREATE USER $DB_USER WITH ENCRYPTED PASSWORD \'$DB_PASSWORD\';

#GRANT ALL PRIVILEGES ON DATABASE swingy_db TO swingy_user;
