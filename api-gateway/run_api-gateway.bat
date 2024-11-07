call ..\config-service\run_config-service.bat

cd ..\user-service
docker-compose up --build -d

cd ..\specialization-service
docker-compose up --build -d

cd ..\employee-service
docker-compose up --build -d

cd ..\api-gateway
docker-compose up --build -d
