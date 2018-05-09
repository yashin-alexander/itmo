import flask
from mongo.api import MongoAPI


app = flask.Flask(__name__)
mongo_api = MongoAPI()

# MONGO API CALLS


@app.route('/mongo/create_user', methods=['POST'])
def mongo_create_user():
    mongo_api.create_user()


@app.route('/mongo/create_place', methods=['POST'])
def mongo_create_place():
    mongo_api.create_place()


@app.route('/mongo/collections', methods=['GET'])
def mongo_collections():
    return mongo_api.read_collections()


@app.route('/mongo/places', methods=['GET'])
def mongo_places():
    return mongo_api.read_places()


@app.route('/mongo/users', methods=['GET'])
def mongo_users():
    return mongo_api.read_users()


@app.route('/mongo/update_places', methods=['POST'])
def mongo_update_places():
    return mongo_api.update_places()


@app.route('/mongo/update_users', methods=['POST'])
def mongo_update_users():
    mongo_api.update_users()


@app.route('/mongo/delete_places', methods=['GET'])
def mongo_delete_places_by():
    mongo_api.delete_places()


@app.route('/mongo/delete_users', methods=['GET'])
def mongo_delete_users_by():
    mongo_api.delete_users()

# CASSANDRA API CALLS

# NEO4J API CALLS


if __name__ == '__main__':
    app.debug = True
    app.run()
