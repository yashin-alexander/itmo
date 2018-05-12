import flask
from werkzeug.routing import BaseConverter
from requests.exceptions import ConnectionError
from functools import wraps

from mongo_api.api import MongoAPI
from neo4j_api.api import Neo4jAPI
from cassandra_api.api import CassandraAPI


class RegexConverter(BaseConverter):
    def __init__(self, url_map, *items):
        super(RegexConverter, self).__init__(url_map)
        self.regex = items[0]


app = flask.Flask(__name__)
app.url_map.converters['regex'] = RegexConverter

mongo_api = MongoAPI()
try:
    neo4j_api = Neo4jAPI()
except ConnectionError:
    print('No neo4j cluster available')
try:
    cassandra_api = CassandraAPI()
except Exception:
    print('No cassandra cluster available')


def catcher(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        try:
            return f(*args, **kwargs)
        except Exception:
            return flask.Response(status=500)
    return decorated


@app.route('/')
def index():
    return flask.render_template('404.html', address='/')


@app.route('/<regex(".*"):address>/')
def bad_request(address):
    return flask.render_template('404.html', address=address)


# MONGO API CALLS


@app.route('/mongo/create_user', methods=['POST'])
def mongo_create_user():
    return mongo_api.create_user()


@app.route('/mongo/create_place', methods=['POST'])
def mongo_create_place():
    return mongo_api.create_place()


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
    return mongo_api.update_users()


@app.route('/mongo/delete_places', methods=['GET'])
def mongo_delete_places_by():
    return mongo_api.delete_places()


@app.route('/mongo/delete_users', methods=['GET'])
def mongo_delete_users_by():
    return mongo_api.delete_users()


# CASSANDRA API CALLS

@app.route('/cassandra/create_user_activity', methods=['POST'])
def cassandra_create_user_activity():
    return cassandra_api.create_user_activity()


@app.route('/cassandra/update_register', methods=['POST'])
def cassandra_update_register():
    return cassandra_api.update_register_by_uuid()


@app.route('/cassandra/register', methods=['POST'])
def cassandra_create_register():
    return cassandra_api.create_register()


@app.route('/cassandra/enter_attempts', methods=['POST'])
def cassandra_create_enter_attempts():
    return cassandra_api.create_enter_attempts()


@app.route('/cassandra/user_activity', methods=['GET'])
def cassandra_user_activity():
    return cassandra_api.user_activity()


@app.route('/cassandra/register', methods=['GET'])
def cassandra_register():
    return cassandra_api.register()


@app.route('/cassandra/enter_attempts_by_day', methods=['GET'])
def cassandra_enter_attempts_by_day():
    return cassandra_api.enter_attempts_by_day()


@app.route('/cassandra/delete_registers', methods=['POST'])
def cassandra_delete_registers():
    return cassandra_api.delete_registers()


@app.route('/cassandra/delete_user_activity', methods=['POST'])
def cassandra_delete_user_activity():
    return cassandra_api.delete_user_activity()


@app.route('/cassandra/delete_enter_attempts', methods=['POST'])
def cassandra_delete_enter_attempts():
    return cassandra_api.delete_enter_attempts()

# NEO4J API CALLS


@app.route('/neo4j/create_history', methods=['POST'])
def neo4j_create_history():
    return neo4j_api.create_history()


@app.route('/neo4j/history', methods=['GET'])
def neo4j_history():
    return neo4j_api.history()


@app.route('/neo4j/update_history_name', methods=['GET'])
def neo4j_update_history_name():
    return neo4j_api.update_history_name()


@app.route('/neo4j/delete_history', methods=['GET'])
def neo4j_delete_history():
    return neo4j_api.delete_history()


if __name__ == '__main__':
    app.debug = True
    app.run()
