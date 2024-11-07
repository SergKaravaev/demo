call ..\config-service\run_config-service.bat

cd ..\user-service
docker-compose up --build -d

