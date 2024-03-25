#!/bin/bash

## kill all spring application using 8080, 8081, 8082 ports
kill -9 $(lsof -t -i:8080) $(lsof -t -i:8081) $(lsof -t -i:8082) 2> /dev/null
