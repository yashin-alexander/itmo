#!/bin/bash

mongod --config ~/Documents/helper-concierge/replications/mongo/mongodb$1.conf --replSet "rs0"
