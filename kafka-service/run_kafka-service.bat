call ..\config-service\run_config-service.bat

cd ..\kafka-service
docker-compose up --build -d

