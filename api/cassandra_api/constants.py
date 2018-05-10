KEYSPACE_NAME = 'concierge'
DB_REGISTER = 'register'
DB_USER_ACTIVITY = 'user_activity'
DB_ENTER_ATTEMPTS = 'attempts_to_enter_by_day'

DEVICE_TYPES = ("mobile", "desktop")
EVENT_TYPES = ("log in", "log out")
NON_REGISTER_EVENT_TYPES = ("'log in'", "'log out'")
DEFAULT_TTL = 'USING TTL 15552000'

DATABASES_FIELDS = {
    DB_REGISTER: ('day', 'user_id', 'device_type', 'event_time'),
    DB_USER_ACTIVITY: ('user_id', 'event_type', 'device_type', 'event_time'),
    DB_ENTER_ATTEMPTS: ('day', 'user_id', 'device_type', 'event_time')
}

DATABASES_COLUMNS = {
    DB_REGISTER:       '({}, {}, {}, {})'.format('day', 'user_id', 'device_type', 'event_time'),
    DB_USER_ACTIVITY:  '({}, {}, {}, {})'.format('user_id', 'event_type', 'device_type', 'event_time'),
    DB_ENTER_ATTEMPTS: '({}, {}, {}, {})'.format('day', 'user_id', 'device_type', 'event_time')
}

