# Deploy MySQL and SpringBoot Containers for GiCancers App

## Create Docker Bridged Network for Backend and Frontend

~~~bash
docker network create -d bridge rasa-backend
docker network create -d bridge colon-cancer-mayoclinic
~~~

## Deploy Docker Container's MySQL Server in Background

1\. Create MySQL Docker image with prebuilt DB:

~~~bash
cd ${PWD}/GI-Cancers-2D-Gallery/gi-cancers-mysql-db

docker pull mysql:8.0.27

docker build -t gi-cancers-mysql-db:dev .
~~~

2\. Launch a Docker container with MySQL server running:

~~~bash
docker run -d \
    --name rasa-gi-cancers-db \
    --restart always \
    -p 3307:3306 \
    --network=rasa-backend \
    gi-cancers-mysql-db:dev
~~~

3\. Let's execute bash command to enter into the mytrial-db Docker container, then login to the container's MySQL server directly:

~~~bash
docker exec -it rasa-gi-cancers-db bash

mysql -u rasa_docker -p # enter password rasa_docker

# or login as passwordless root
mysql -u root
~~~


4\. Lets verify we can see some data in tables within our mytrial database that was created when we built the docker image earlier. Let's look at org and trial tables:

~~~sql
-- if not used, make sure use mytrial db
SHOW databases;

USE gicancers;

SELECT * FROM pcc_symptoms LIMIT 5;

quit
~~~

## Deploy SpringBoot Alpine Docker Container with JDBC MySQL Connection

Recommended: Use Ubuntu WSL, VM, or server for this part.

NOTE: If you use Ubuntu WSL, it should be integrated with Windows Docker Desktop. So when you build SpringBoot docker container, it will be part of Windows Docker Desktop docker containers.

NOTE: If you use Ubuntu VM, make sure the MySQL and rest of Rasa app are built using that Ubuntu VM's Docker Desktop.

NOTE: If you use Native Ubuntu Server, just make sure MySQL the rest of Rasa app docker containers are built using Ubuntu Server Docker daemon.

1\. Open WSL Ubuntu terminal, Install OpenJDK and Maven:

~~~bash
sudo apt -y update
sudo apt -y install openjdk-17-jre-headless
sudo apt -y install maven
~~~

2\. Let's change to the gi-cancers-api directory:

~~~bash
cd ${PWD}/GI-Cancers-2D-Gallery/gi-cancers-api
~~~

3\. Create .jar file use below command

~~~bash
./mvnw install
~~~

4\. Build and tag the Docker image for our SpringBoot gi-cancers-api app using the Dockerfile:

~~~bash
docker build -t gi-cancers-api:dev .
~~~

5\. Launch the Docker container named **rasa-sb-server** for our SpringBoot app with the code based from gi-cancers-api folder:

~~~bash
docker run -d \
    --name rasa-sb-server \
    -p 8080:8080 \
    -e SPRING_DATASOURCE_URL=jdbc:mysql://rasa-gi-cancers-db:3306/gicancers \
    -e SPRING_DATASOURCE_USERNAME=rasa_docker \
    -e SPRING_DATASOURCE_PASSWORD=rasa_docker \
    --network=rasa-backend \
    gi-cancers-api:dev

# Rasa Sprinboot Server can connect with Rasa Chatbot Server
docker network connect colon-cancer-mayoclinic rasa-sb-server
~~~

Access **gi-cancers-api** app at `localhost:8080/test`

If you need to see the log of your SpringBoot server, you can run `tail -f` in your docker container from your host machine with the following docker command:

~~~bash
docker exec -it rasa-sb-server tail -f myapplication.log
~~~

Since our Spring Boot server is now configured to writing all the logs to **`myapplication.log`**, we can tail it.

