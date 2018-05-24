# Helper-Concierge
Databases course project / Backend part

Developed using Python, Mongodb, Apache Cassandra, Neo4j.

## Requirements:
- python3
- mongodb
- cassandra
- neo4j

#### pip packages:
- faker
- flask
- pymongo
- neo4jrestclient
- cassandra
- ccm

#### intallation:

    sudo pip3 install <package name>

## Quickstart
To use cassandra db in application you will need to open `cqlsh`
in terminal and paste content from `databases/cassandra/create_db.sql` there.


## Run database fillers:
- For each database specify it's url in `api/db_name/constants.py`
- Carefully check out the `fill` and possibly `multiprocessing_fill` methods.
- Specify number of instances that you want to fill for each table in database.

#### Then simply:

    cd api/db_name
    python filler.py


## Run REST API:
For each database specify it's url in `api/db_name/constants.py`

#### Then simply:

    cd api
    python global_api.py

Go to http://127.0.0.1:5000/mongo/places

