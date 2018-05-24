import json
import pymongo
from flask import request, Response
from functools import wraps
from bson.objectid import ObjectId

from . import constants


def catcher(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        try:
            return f(*args, **kwargs)
        except Exception as err:
            print(err)
            return Response(status=500, response='{"status": "Failure"}')
    return decorated


class MongoAPI:
    def __init__(self):
        self.client = pymongo.MongoClient(constants.REPLICA_IPS, replicaset=constants.REPLICASET)
        self.db = self.client[constants.DB_NAME]
        self.users = self.db[constants.USERS_COLLECTION_NAME]
        self.places = self.db[constants.PLACES_COLLECTION_NAME]

    @staticmethod
    def to_json(data):
        return json.dumps(data) + "\n"

    @property
    def request_parameters(self):
        conditions = request.args.to_dict()
        if 'age' in conditions:
            conditions['age'] = int(conditions['age'])
        if 'rating' in conditions:
            conditions['rating'] = int(conditions['rating'])
        if '_id' in conditions:
            conditions['_id'] = ObjectId(conditions['_id'])

        return conditions

    @property
    def post_params(self):
        if 'condition' in request.json:
            if '_id' in request.json['condition']:
                request.json['condition']['_id'] = ObjectId(request.json['condition']['_id'])
        return request.json

    def response(self, status_code, data):
        json_data = json.dumps(data, indent=4, sort_keys=True, default=str)
        return Response(
            status=status_code,
            mimetype="application/json",
            response=json_data
        )

# CREATE methods
    @catcher
    def create_place(self):
        print(request.json)
        id = self.places.insert_one(dict(request.json))
        return self.response(200, {'id': id})

    @catcher
    def create_user(self):
        id = self.users.insert(dict(request.json))
        return self.response(200, {'id': id})

# READ methods
    @catcher
    def read_collections(self):
        return self.response(200, {'collections': self.db.collection_names()})

    @catcher
    def read_users(self):
        print(self.request_parameters)
        data = list(self.users.find(self.request_parameters).limit(20))
        return self.response(200, data)

    @catcher
    def read_places(self):
        data = list(self.places.find(self.request_parameters).limit(20))
        return self.response(200, data)

    @catcher
    def read_places_with_rating_more_than(self):
        rating = request.args.pop()
        data = list(self.places.find({'rating': {'$gt': rating}}).limit(20))
        return self.response(200, data)

# UPDATE methods
    @catcher
    def update_users(self):
        new_parameters = self.post_params['new_parameters']
        search_condition = self.post_params['condition']
        self.users.update(search_condition, {"$set": new_parameters})
        return self.response(200, {"status": "Success"})

    @catcher
    def update_places(self):
        new_parameters = self.post_params['new_parameters']
        search_condition = self.post_params['condition']
        self.places.update(search_condition, {"$set": new_parameters})
        return self.response(200, {"status": "Success"})

# DELETE methods
    @catcher
    def delete_places(self):
        self.places.remove(self.request_parameters)
        return self.response(200, {"status": "Success"})

    @catcher
    def delete_users(self):
        self.users.remove(self.request_parameters)
        return self.response(200, {"status": "Success"})
