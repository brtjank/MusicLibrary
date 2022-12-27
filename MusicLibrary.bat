@echo off
start "" java -jar songs.jar
start "" java -jar artist.jar
start "" java -jar gateway.jar
cd lab4 
npm run dev
exit