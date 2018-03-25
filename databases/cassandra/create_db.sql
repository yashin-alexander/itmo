DROP KEYSPACE concierge_history;

CREATE KEYSPACE concierge_history WITH REPLICATION = {
                                                     'class' : 'SimpleStrategy',
                                                     'replication_factor' : 3
                                                     };
use concierge_history;

CREATE TABLE IF NOT EXISTS posts
(
    userid uuid PRIMARY KEY,
    posted_date date,
    event_description text,
    event_duration int,
) WITH comment='Some posts in application concierge';


INSERT INTO posts(userid, posted_date, event_description, event_duration)
VALUES (uuid(), toDate(now()), 'new strange event', 5);



UPDATE posts
SET event_description = 'neweee'
WHERE userid in (027eaf7e-8f43-4ed7-b5ce-ebba4d50af7a, 027eaf7e-8f43-4ed7-b5ce-ebba4d50eeee);

SELECT * FROM posts
WHERE userid IN (SELECT userid FROM posts);


SELECT json * FROM posts;

SELECT * FROM posts;
