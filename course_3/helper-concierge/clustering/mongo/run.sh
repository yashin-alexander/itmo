#!/bin/bash

mongod --config ~/Documents/helper-concierge/clustering/mongo/mongodb$1.conf --replSet "rs0"
