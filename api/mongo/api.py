import flask
import json
import pymongo
from bson import json_util
import filler

app = flask.Flask(__name__)
filler = filler.MongoFiller()


def db_conn():
    client = pymongo.MongoClient()

    return client['concierge']


def to_json(data):
    return json.dumps(data) + "\n"


def resp(code, data):
    return flask.Response(
        status=code,
        mimetype="application/json",
        response=to_json(data)
    )


@app.route('/')
def root():
    return flask.redirect('/api/')


@app.route('/api/collections', methods=['GET'])
def collections():
    db = db_conn()
    return resp(200, {'collections': db.collection_names()})


@app.route('/api/places', methods=['GET'])
def places():
    data = filler.get_places_data()
    return resp(200, json.dumps(data, default=json_util.default))


@app.route('/api/users', methods=['GET'])
def users():
    data = filler.get_users_data()
    return resp(200, json.dumps(data, default=json_util.default))


@app.route('/api/users_by_condition', methods=['GET'])
def users_by_condition():
    field = flask.request.args.get('field')
    value = flask.request.args.get('value')
    if field == 'age':
        value = int(value)

    parameter = {field: value}
    data = list(filler.get_users_by(parameter))

    return resp(200, json.dumps(data, default=json_util.default))


if __name__ == '__main__':
    app.debug = True  # enables auto reload during development
    app.run()
