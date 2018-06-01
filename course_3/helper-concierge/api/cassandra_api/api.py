import json

from flask import request, Response
from functools import wraps
from cassandra.cluster import Cluster

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


class CassandraAPI:
    def __init__(self):
        self.cluster = Cluster(constants.NODE_IPS)
        self.session = self.cluster.connect(constants.KEYSPACE_NAME)

    @staticmethod
    def to_json(data):
        return json.dumps(data) + "\n"

    @property
    def request_parameters(self):
        return request.args.to_dict()

    def response(self, status_code, data):
        json_data = json.dumps(data, indent=8, sort_keys=True, default=str)
        return Response(
            status=status_code,
            mimetype="application/json",
            response=json_data
        )

    # CREATE methods

    def _create_record(self, database):
        data = request.json
        values = ''
        for key in constants.DATABASES_FIELDS[database]:
            values += ''
            values += str(data[key])
            values += ','
        query = "INSERT INTO {}{} VALUES ({});" \
            .format(database, constants.DATABASES_COLUMNS[database], values[:-1])
        print(query)
        self.session.execute(query)

    @catcher
    def create_register(self):
        self._create_record(constants.DB_REGISTER)
        return self.response(200, {})

    @catcher
    def create_user_activity(self):
        self._create_record(constants.DB_USER_ACTIVITY)
        return self.response(200, {})

    @catcher
    def create_enter_attempts(self):
        self._create_record(constants.DB_ENTER_ATTEMPTS)
        return self.response(200, {})

    # READ methods

    def _get_table_data(self, table):
        query = 'SELECT * FROM {}'.format(table)
        return self.session.execute(query)

    @catcher
    def user_activity(self):
        data = list(self._get_table_data(constants.DB_USER_ACTIVITY))
        return self.response(200, data)

    @catcher
    def register(self):
        data = list(self._get_table_data(constants.DB_REGISTER))
        return self.response(200, data)

    @catcher
    def enter_attempts_by_day(self):
        data = list(self._get_table_data(constants.DB_ENTER_ATTEMPTS))
        return self.response(200, data)

    # UPDATE methods

    @catcher
    def update_register_by_uuid(self):
        uuid = request.json['uuid']
        data = request.json['data']

        query = 'UPDATE register SET {} WHERE user_id IN ({});'.format(data, uuid)
        self.session.execute(query)
        return self.response(200, {})

    # DELETE methods

    @catcher
    def delete_registers(self):
        uuids = request.json['uuids']
        self.session.execute('DELETE FROM {} WHERE user_id IN ({});'
                             .format(constants.DB_REGISTER, uuids))
        return self.response(200, {})

    @catcher
    def delete_enter_attempts(self):
        uuids = request.json['uuids']
        self.session.execute('DELETE FROM {} WHERE user_id IN ({});'
                             .format(constants.DB_ENTER_ATTEMPTS, uuids))
        return self.response(200, {})

    @catcher
    def delete_user_activity(self):
        uuids = request.json['uuids']
        self.session.execute('DELETE FROM {} WHERE user_id IN ({});'
                             .format(constants.DB_USER_ACTIVITY, uuids))
        return self.response(200, {})

