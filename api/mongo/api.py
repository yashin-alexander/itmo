import json
import pymongo
from bson import json_util
from flask import request, Response

from . import constants


class MongoAPI:
    def __init__(self):
        self.client = pymongo.MongoClient()
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

        return conditions

    def response(self, status_code, data):
        return Response(
            status=status_code,
            mimetype="application/json",
            response=self.to_json(data)
        )

# CREATE methods
    def create_place(self):
        self.places.insert_one(self.request_parameters)
        return self.response(200, {})

    def create_user(self):
        self.users.insert_one(self.request_parameters)
        return self.response(200, {})


# READ methods
    def read_collections(self):
        return self.response(200, {'collections': self.db.collection_names()})

    def read_users(self):
        data = list(self.users.find(self.request_parameters))
        return self.response(200, json.dumps(data, default=json_util.default))

    def read_places(self):
        data = list(self.places.find(self.request_parameters))
        return self.response(200, json.dumps(data, default=json_util.default))

    def read_places_with_rating_more_than(self):
        rating = request.args.pop()
        data = list(self.places.find({'rating': {'$gt': rating}}))
        return self.response(200, json.dumps(data, default=json_util.default))

# UPDATE methods
    def update_users(self):
        search_condition = self.request_parameters['condition']
        new_parameters = self.request_parameters['new_parameters']
        self.users.update(search_condition, {"$set": new_parameters})
        return self.response(200, {})

    def update_places(self):
        search_condition = request.json['condition']
        new_parameters = request.json['new_parameters']
        self.places.update(search_condition, {"$set": new_parameters})
        return self.response(200, {})


# DELETE methods
    def delete_places(self):
        self.places.remove(self.request_parameters)
        return self.response(200, {})

    def delete_users(self):
        self.users.remove(self.request_parameters)
        return self.response(200, {})
