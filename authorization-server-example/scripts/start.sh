#!/bin/bash

# set a parameter from command line and set in a variable
MAKE_COMMAND=$1
GREEN='\033[0;32m'

echo "starting applications:"
## start up spring boot authorization server, resource server and oath client in background mode
cd authorization-server && $(MAKE) "$MAKE_COMMAND" &
# wait for the application to start
while [ "$(curl -s -o /dev/null -L -w ''%{http_code}'' http://localhost:8080/)" != "200" ]; do sleep 0.001; done
echo "- Authorization Server started"

cd resource-server && $(MAKE) "$MAKE_COMMAND" &
while [ "$(curl -s -o /dev/null -L -w ''%{http_code}'' http://localhost:8081/)" != "401" ]; do sleep 0.001; done
echo "- Resource Server started"

cd oauth-client && $(MAKE) "$MAKE_COMMAND" &
while [ "$(curl -s -o /dev/null -L -w ''%{http_code}'' http://127.0.0.1:8082/products)" != "401" ]; do sleep 0.001; done
echo "- OAuth-Client started"

printf "${GREEN}done!"

sleep 3
echo "Opening browser..."
# It will open the browser and redirect to the authorization server login page
open http://127.0.0.1:8082/products

