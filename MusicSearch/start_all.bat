@echo off
TITLE Music Search Full Stack Runner

:: 1) Start Elasticsearch
echo Starting Elasticsearch...
cd "G:\elasticsearch-9.1.0-windows-x86_64\elasticsearch-9.1.0\bin"
start cmd /k ".\elasticsearch.bat"

:: 2) Start SpringBoot Backend
echo Starting Spring Boot...
cd "G:\MusicSearch"
start cmd /k "mvn spring-boot:run"

:: 3) Start React Frontend
echo Starting React...
cd "G:\music_search_frontend_app"
start cmd /k "npm start"

echo ---------------------------------------------
echo All services are launching... please wait.
echo ---------------------------------------------
pause
