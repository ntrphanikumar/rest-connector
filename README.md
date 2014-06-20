rest-connector
==============

Navigate to rest-connector folder in command prompt

pre-requisite:
jdk 1.6 or higher and maven 2 or higher installed

do "mvn clean install" to build the application

start server by "mvn -Pserver"

access the url in browser "http://localhost:8080/translate"
sample rest url is "http://localhost:8080/rest/random-user"

provide 2nd url in input field of form provided in tranlate url.
if new user is created you will be redirected to user display page.
on error/wrong url you will be taken to error page