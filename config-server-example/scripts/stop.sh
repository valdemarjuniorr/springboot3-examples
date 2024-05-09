#!/bin/bash

## kill all spring application using 8888, 8080 ports
kill -9 $(lsof -t -i:8888) $(lsof -t -i:8080) 2> /dev/null