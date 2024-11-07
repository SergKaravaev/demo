call ..\config-service\run_config-service.bat

cd ..\specialization-service
docker-compose up --build -d

