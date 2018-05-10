import flask
from werkzeug.routing import BaseConverter

from mongo_api.api import MongoAPI
from cassandra_api.api import CassandraAPI


class RegexConverter(BaseConverter):
    def __init__(self, url_map, *items):
        super(RegexConverter, self).__init__(url_map)
        self.regex = items[0]


app = flask.Flask(__name__)
app.url_map.converters['regex'] = RegexConverter

mongo_api = MongoAPI()
cassandra_api = CassandraAPI()


@app.route('/')
def index():
    return flask.render_template('404.html', address='/')


@app.route('/<regex(".*"):address>/')
def bad_request(address):
    return flask.render_template('404.html', address=address)


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
