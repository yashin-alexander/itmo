# Helper-Concierge
Databases course project / Backend part

Developed using Python, Mongodb, Apache Cassandra, Neo4j.

## Requirements
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


## Database fillers
- For each database specify it's url in `api/db_name/constants.py`
- Carefully check out the `fill` and possibly `multiprocessing_fill` methods.
- Specify number of instances that you want to fill for each table in database.

#### Then run:

    cd api/db_name
    python filler.py


## REST API
For each database specify it's url in `api/db_name/constants.py`

#### Then run:

    cd api
    python global_api.py

Go to http://127.0.0.1:5000/mongo/places

## Clustering hints

To get more info about each database clustering check out the documentation.

#### Mongo
It was decided to use two arbiters (4 & 5 mongodb instances).
Since in case when there is only one secondary node left, it is impossible to write to it.
Two extra arbiters fixes that.
In case you do not want to use this approach, you need to use automation systems for such actions.
Or, as a last resort, in mongoshell for the last secondary replica:

    use admin
    cfg = rs.conf()
    cfg.members = [cfg.members[_last_replica_index_]]
    rs.reconfig(cfg, {force : true})

#### Cassandra
ccm is a perfect script/lib to create, launch and remove an Apache Cassandra cluster on localhost.
Simply:

    ccm create test -v 2.2.12 -n 3 -s

#### Neo4j

