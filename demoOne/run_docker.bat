@echo off
SET IMAGE_NAME=demo_one
SET CONTAINER_NAME=demo_one

FOR /F "tokens=*" %%i IN ('docker ps -q -f "name=%CONTAINER_NAME%"') DO (
    docker stop %%i
    docker rm %%i
)

FOR /F "tokens=*" %%j IN ('docker images -q %IMAGE_NAME%') DO (
    docker rmi -f %%j
)

docker build -t %IMAGE_NAME% .

docker run --name %CONTAINER_NAME% -p 8080:8080 -d %IMAGE_NAME%

exit
