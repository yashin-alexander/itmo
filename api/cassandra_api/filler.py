import faker
import random
import multiprocessing
from cassandra.cluster import Cluster

import constants


class CassandraFillerFakes(faker.providers.BaseProvider):
    def __init__(self, *args, **kwargs):
        super(CassandraFillerFakes, self)
        self.fake = faker.Faker()

    def register(self):
        return {
                'day': "'{}'".format(self.fake.date()),
                'user_id': 'uuid()',
                'device_type': "'{}'".format(random.choice(constants.DEVICE_TYPES)),
                'event_time': "'{} {}.123'".format(self.fake.date(), self.fake.time())
        }

    def user_activity(self):
        return {
                'user_id': 'uuid()',
                'event_type': "'{}'".format(random.choice(constants.EVENT_TYPES)),
                'device_type': "'{}'".format(random.choice(constants.DEVICE_TYPES)),
                'event_time': "dateOf(now())"
        }

    def enter_attempts(self):
        return {
                'day': "toDate(now())",
                'user_id': 'uuid()',
                'device_type': "'{}'".format(random.choice(constants.DEVICE_TYPES)),
                'event_time': "dateOf(now())"
        }


class CassandraFiller:
    def __init__(self):
        self.cluster = Cluster()
        self.session = self.cluster.connect(constants.KEYSPACE_NAME)
        self.faker = faker.Faker()
        self.faker.add_provider(CassandraFillerFakes)

    def _insert_record(self, database, record, ttl=''):
        query = "INSERT INTO {}{} VALUES ({}) {};" \
            .format(database, constants.DATABASES_COLUMNS[database], record, ttl)
        self.session.execute(query)

    def insert_register_record(self, data=None):
        if not data:
            data = self.faker.register()

        values = ''

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
        for key in data.keys():
            values += str(data[key])
            values += ', '
        self._insert_record(constants.DB_ENTER_ATTEMPTS, values[:-2])

    def insert_enter_attempts(self, records_number):
        for _ in range(records_number):
            data = self.faker.enter_attempts()
            self.insert_enter_attempts_record(data)


def fill():
    filler = CassandraFiller()
    # activity = filler.get_register()
    # filler.insert_registers(200000)
    # filler.insert_user_activities(200000)
    filler.insert_enter_attempts(200)


def multiprocessing_fill(processes_number):
    workers = []
    for i in range(processes_number):
        workers.append(multiprocessing.Process(target=fill))

    for worker in workers:
        worker.start()

    for worker in workers:
        worker.join()


if __name__ == '__main__':
    filler = CassandraFiller()
    # activity = filler.get_register()
    # filler.insert_registers(20)
    # print(list(activity))
    # filler.insert_register({'day': "'2018-01-03'", #'toconstants.Date(now())',
    #                         'user_id': i,
    #                         'device_type': "'mobile'",
    #                         'event_time':  'dateOf(now())'})
    # multiprocessing_fill()
    filler.insert_user_activities(20)
    filler.insert_registers(20)
    filler.insert_enter_attempts(20)
    # filler.remove_registers_by_uuids('bb1ed1ec-28b6-4b93-9ffa-e0e0d40d73da')
    # filler.update_register_by_uuid("device_type = 'mobile'", '834cd519-b58b-490d-bf3e-953da8cdc8de')
