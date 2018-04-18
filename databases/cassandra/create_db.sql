DROP KEYSPACE concierge;

CREATE KEYSPACE concierge WITH REPLICATION = {
	'class' : 'SimpleStrategy',
	'replication_factor' : 3
};

use concierge;

CREATE TABLE IF NOT EXISTS attempts_to_enter_by_day
(   
    day date,
    user_id int,
    device_type text,
    event_time timestamp,
    PRIMARY KEY((day, user_id), event_time)
) WITH CLUSTERING ORDER BY (event_time DESC); -- reverse sorting for the data being written


CREATE TABLE IF NOT EXISTS user_activity
(   
    user_id int,
    event_type text,
    device_type text,
    event_time timestamp,
    PRIMARY KEY(user_id, event_time)
) WITH CLUSTERING ORDER BY (event_time DESC);

CREATE TABLE IF NOT EXISTS register
(   
    day date,
    user_id int,
    device_type text,
    event_time timestamp,
    PRIMARY KEY(user_id)
);

------------------------------------##
INSERT INTO attempts_to_enter_by_day(day, user_id, device_type, event_time) VALUES (toDate(now()), 1, 'mobile', dateOf(now()));
INSERT INTO attempts_to_enter_by_day(day, user_id, device_type, event_time) VALUES (toDate(now()), 2, 'desktop', dateOf(now()));
INSERT INTO attempts_to_enter_by_day(day, user_id, device_type, event_time) VALUES (toDate(now()), 3, 'desktop', dateOf(now()));
INSERT INTO attempts_to_enter_by_day(day, user_id, device_type, event_time) VALUES (toDate(now()), 4, 'mobile', dateOf(now()));
INSERT INTO attempts_to_enter_by_day(day, user_id, device_type, event_time) VALUES (toDate(now()), 2, 'mobile', dateOf(now())) USING TTL 15552000;
INSERT INTO attempts_to_enter_by_day(day, user_id, device_type, event_time) VALUES (toDate(now()), 2, 'mobile', dateOf(now())) USING TTL 15552000;
INSERT INTO attempts_to_enter_by_day(day, user_id, device_type, event_time) VALUES (toDate(now()), 3, 'desktop', dateOf(now())) USING TTL 15552000;
------------------------------------##                                                                           
INSERT INTO user_activity(user_id, event_type, device_type, event_time) VALUES (1, 'register', 'mobile', dateOf(now()));
INSERT INTO user_activity(user_id, event_type, device_type, event_time) VALUES (2, 'register', 'desktop', dateOf(now()));
INSERT INTO user_activity(user_id, event_type, device_type, event_time) VALUES (3, 'register', 'desktop', dateOf(now()));
INSERT INTO user_activity(user_id, event_type, device_type, event_time) VALUES (4, 'register', 'mobile', dateOf(now()));
INSERT INTO user_activity(user_id, event_type, device_type, event_time) VALUES (2, 'log out', 'mobile', dateOf(now())) USING TTL 15552000;
INSERT INTO user_activity(user_id, event_type, device_type, event_time) VALUES (2, 'log in', 'mobile', dateOf(now())) USING TTL 15552000;
INSERT INTO user_activity(user_id, event_type, device_type, event_time) VALUES (3, 'log out', 'desktop', dateOf(now())) USING TTL 15552000;
------------------------------------##
INSERT INTO register(day, user_id, device_type, event_time) VALUES (toDate(now()), 1, 'mobile', dateOf(now()));
INSERT INTO register(day, user_id, device_type, event_time) VALUES (toDate(now()), 2, 'desktop', dateOf(now()));
INSERT INTO register(day, user_id, device_type, event_time) VALUES (toDate(now()), 3, 'desktop', dateOf(now()));
INSERT INTO register(day, user_id, device_type, event_time) VALUES (toDate(now()), 4, 'mobile', dateOf(now()));
INSERT INTO register(day, user_id, device_type, event_time) VALUES (toDate(now()), 2, 'mobile', dateOf(now()));
INSERT INTO register(day, user_id, device_type, event_time) VALUES (toDate(now()), 2, 'mobile', dateOf(now()));
INSERT INTO register(day, user_id, device_type, event_time) VALUES (toDate(now()), 3, 'desktop', dateOf(now()));
------------------------------------##

SELECT * FROM attempts_to_enter_by_day;
SELECT * FROM user_activity;
SELECT * FROM register;
