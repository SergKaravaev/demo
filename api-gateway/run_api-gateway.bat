call ..\config-service\run_config-service.bat

cd ..\eureka-service
docker-compose up --build -d

cd ..\user-service
docker-compose up --build -d

cd ..\employee-service
docker-compose up --build -d

cd ..\api-gateway
docker-compose up --build -d
