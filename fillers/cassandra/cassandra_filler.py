import faker
import random

import constants
from cassandra.cluster import Cluster


class CassandraFillerFakes(faker.providers.BaseProvider):
    def __init__(self, *args, **kwargs):
        super(CassandraFillerFakes, self)
        self.fake = faker.Faker()

    def register(self):
        return {
                'day': "'{}'".format(self.fake.date()),
                'user_id': 0,
                'device_type': "'{}'".format(random.choice(constants.DEVICE_TYPES)),
                'event_time': "'{} {}'".format(self.fake.date(), self.fake.time())
        }

    def user_activity(self):
        return {
                'user_id': 0,
                'event_type': "'{}'".format(random.choice(constants.EVENT_TYPES)),
                'device_type': "'{}'".format(random.choice(constants.DEVICE_TYPES)),
                'event_time': "dateOf(now())"
        }

    def enter_attempts(self):
        return {
                'day': "toDate(now())",
                'user_id': 0,
                'device_type': "'{}'".format(random.choice(constants.DEVICE_TYPES)),
                'event_time': "dateOf(now())"
        }


class CassandraFiller:
    def __init__(self):
        self.cluster = Cluster()
        self.session = self.cluster.connect(constants.KEYSPACE_NAME)
        self.faker = faker.Faker()
        self.faker.add_provider(CassandraFillerFakes)

    def get_max_registered_id(self):
        query = 'SELECT MAX(user_id) FROM {}.{} ;'\
            .format(constants.KEYSPACE_NAME, constants.DB_REGISTER)
        registered_id = self.session.execute(query)[0][0]
        if not registered_id:
            registered_id = 0
        return registered_id

    def _get_table_data(self, table):
        query = 'SELECT * FROM {}'.format(table)
        return self.session.execute(query)

    def get_user_activity(self):
        return self._get_table_data(constants.DB_USER_ACTIVITY)

    def get_register(self):
        return self._get_table_data(constants.DB_REGISTER)

    def get_enter_attempts_by_day(self):
        return self._get_table_data(constants.DB_ENTER_ATTEMPTS)

    def _insert_record(self, database, record, ttl=''):
        query = "INSERT INTO {}{} VALUES ({}) {};" \
            .format(database, constants.DATABASES_COLUMNS[database], record, ttl)
        print(query)
        self.session.execute(query)

    def insert_register_record(self, data=None):
        if not data:
            data = self.faker.register()

        values = ''
        max_existing_id = self.get_max_registered_id()
        data.update({'user_id': max_existing_id + 1})

        for key in data.keys():
            values += str(data[key])
            values += ', '
        self._insert_record(constants.DB_REGISTER, values[:-2])

    def insert_registers(self, records_number):
        for user_id in range(records_number):
            data = self.faker.register()
            self.insert_register_record(data)

    def insert_user_activity_record(self, data=None):
        if not data:
            data = self.faker.user_activity()

        ttl = ''
        values = ''
        max_existing_id = self.get_max_registered_id()
        data.update({'user_id': random.randint(1, max_existing_id)})

        if data['event_type'] == "'register'" and data['user_id'] <= max_existing_id:
            data.update({'event_type': random.choice(constants.NON_REGISTER_EVENT_TYPES)})
            ttl = constants.DEFAULT_TTL

        for key in data.keys():
            values += str(data[key])
            values += ', '
        self._insert_record(constants.DB_USER_ACTIVITY, values[:-2], ttl)

    def insert_user_activities(self, records_number):
        for _ in range(records_number):
            data = self.faker.user_activity()
            self.insert_user_activity_record(data)

    def insert_enter_attempts_record(self, data=None):
        if not data:
            data = self.faker.enter_attempts()

        values = ''
        max_existing_id = self.get_max_registered_id()
        data.update({'user_id': random.randint(1, max_existing_id)})
        for key in data.keys():
            values += str(data[key])
            values += ', '
        self._insert_record(constants.DB_ENTER_ATTEMPTS, values[:-2])

    def insert_enter_attempts(self, records_number):
        for _ in range(records_number):
            data = self.faker.enter_attempts()
            self.insert_enter_attempts_record(data)


if __name__ == '__main__':
    filler = CassandraFiller()
    activity = filler.get_register()
    print(list(activity))
    # filler.insert_register({'day': "'2018-01-03'", #'toconstants.Date(now())',
    #                         'user_id': i,
    #                         'device_type': "'mobile'",
    #                         'event_time':  'dateOf(now())'})
    filler.insert_registers(4)
    filler.insert_user_activities(4)
    filler.insert_enter_attempts(4)
