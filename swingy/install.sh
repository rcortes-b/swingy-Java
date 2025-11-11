#!/bin/sh
# Load env variables
export $(grep -v '^#' .env | xargs)

docker build \
  --build-arg SWINGY_DB_USER=$SWINGY_DB_USER \
  --build-arg SWINGY_DB_PASSWORD=$SWINGY_DB_PASSWORD \
  --build-arg SWINGY_DB_NAME=$SWINGY_DB_NAME \
  -t swingy_db_image -f db/Dockerfile .

docker run -d \
  --name $SWINGY_DB_NAME \
  -p 5432:5432 \
  -v ./db/data:/var/lib/postgresql/data swingy_db_image

mvn clean package

echo "#!/bin/bash" > console_exe.sh
echo "export $(grep -v '^#' .env | xargs)" >> console_exe.sh
echo "docker rm -f $SWINGY_DB_NAME" >> console_exe.sh
echo "docker run -d \
  --name $SWINGY_DB_NAME \
  -p 5432:5432 \
  -v ./db/data:/var/lib/postgresql/data swingy_db_image" >> console_exe.sh
echo "java -jar target/swingy-1.0.jar console" >> console_exe.sh

echo "#!/bin/bash" > gui_exe.sh
echo "export $(grep -v '^#' .env | xargs)" >> gui_exe.sh
echo "docker rm -f $SWINGY_DB_NAME" >> gui_exe.sh
echo "docker run -d \
  --name $SWINGY_DB_NAME \
  -p 5432:5432 \
  -v ./db/data:/var/lib/postgresql/data swingy_db_image" >> gui_exe.sh
echo "java -jar target/swingy-1.0.jar gui" >> gui_exe.sh

chmod +x console_exe.sh
chmod +x gui_exe.sh
