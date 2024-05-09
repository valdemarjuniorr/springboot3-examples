#!/bin/bash

# localhost url
LOCALHOST_URL=http://localhost:8080/

# set a parameter from command line and set in a variable
MAKE_COMMAND=$1
GREEN='\033[0;32m'

echo "starting applications:"
## start up spring boot authorization server, resource server and oath client in background mode
cd config-server && $(MAKE) "$MAKE_COMMAND" &
# wait for the application to start
while [ "$(curl -s -o /dev/null -L -w ''%{http_code}'' http://localhost:8888/actuator/default)" != "200" ]; do sleep 0.001; done
echo "- Config Server started"

cd config-client && $(MAKE) "$MAKE_COMMAND" &
while [ "$(curl -s -o /dev/null -L -w ''%{http_code}'' ${LOCALHOST_URL})" != "200" ]; do sleep 0.001; done
echo "- Config Client started"

printf "${GREEN}done!"

sleep 3
echo "Opening browser..."
# It will open the browser and redirect to the authorization server login page
open ${LOCALHOST_URL}