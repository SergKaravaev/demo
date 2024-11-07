docker network inspect demo-network >nul 2>&1 || docker network create demo-network
cd ..\config-service
docker-compose up --build -d

