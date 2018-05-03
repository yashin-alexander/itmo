from cassandra.cluster import Cluster

KEYSPACE_NAME = 'concierge'
DB_REGISTER = 'register'
DB_USER_ACTIVITY = 'user_activity'
DB_ENTER_ATTEMPTS = 'attempts_to_enter_by_day'

DATABASES_COLUMNS = {
    DB_REGISTER:       '({}, {}, {}, {})'.format('day', 'user_id', 'device_type', 'event_time'),
    DB_USER_ACTIVITY:  '({}, {}, {}, {})'.format('user_id', 'event_type', 'device_type', 'event_time'),
    DB_ENTER_ATTEMPTS: '({}, {}, {}, {})'.format('day', 'user_id', 'device_type', 'event_time')
}


class CassandraFiller:
    def __init__(self):
        self.cluster = Cluster()
        self.session = self.cluster.connect(KEYSPACE_NAME)

    def _get_table_data(self, table):
        query = 'SELECT * FROM {}'.format(table)
        return self.session.execute(query)

    def get_user_activity(self):
        return self._get_table_data(DB_USER_ACTIVITY)

    def get_register(self):
        return self._get_table_data(DB_REGISTER)

    def get_attempts_to_enter_by_day(self):
        return self._get_table_data(DB_ENTER_ATTEMPTS)

    def _insert_record(self, database, record):
        query = "INSERT INTO {}{} VALUES ({}) ;" \
            .format(database, DATABASES_COLUMNS[database], record)
        self.session.execute(query)

    def insert_register(self, data=None):
        values = ''
        for key in data.keys():
            values += str(data[key])
            values += ', '
        self._insert_record(DB_REGISTER, values[:-2])


if __name__ == '__main__':
    filler = CassandraFiller()
    # activity = filler.get_user_activity()
    # print(list(activity))
    for i in range(100):
        filler.insert_register({'day': 'toDate(now())',
                            'user_id': i,
                            'device_type': "'mobile'",
                            'event_time':  'dateOf(now())'})

