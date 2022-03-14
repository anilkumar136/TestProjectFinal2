docker run -d --name mongo-on-docker -p 27017:27017 mongo
---This will start mongo image on docker --internal port of mongo inside docker is 27017 and will be exposed outside on 
local host on port 27017
--Connect to this mongo with compass, create collection and document
--Create Docker file in the project structure 
--build docker image of our app with below cmd:
docker build -t app . (Means build docker image as per Docker File present in the . current folder)

--now run our app
docker run --name app -p 8080:8080 app

Special Note::::
As Docker has its own host and internal ports ----In application.properties we have to use 
spring.data.mongodb.host=host.docker.internal  
To tell Spring to use ip address of internal Docker which is runnning on this host. 
As our app is now running in with in Docker contianer which has its own IP, so giving localhost will not work.
from IntelliJ it will work with localhost and above both but in decpker contianer it will only run with above host


