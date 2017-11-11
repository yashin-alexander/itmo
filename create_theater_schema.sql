/*usertype object FIO (3 fields and 1 method that returns Familia I. O.)*/

CREATE OR REPLACE TYPE FIO AS OBJECT
(
	firstName  VARCHAR2(20),
	secondName VARCHAR2(20),
	surname    VARCHAR2(20),
	MEMBER FUNCTION abbreviation
	RETURN VARCHAR2
);

CREATE OR REPLACE TYPE BODY FIO AS
  	MEMBER FUNCTION abbreviation RETURN VARCHAR2 IS
  	BEGIN
  	RETURN surname+substr(firstName, 1, 1)+'. '+substr(secondName, 1, 1)+'.';
  	END;
END;

/*SERVICES*/

CREATE TABLE Service
(
  	serviceId NUMBER NOT NULL PRIMARY KEY,
  	serviceName VARCHAR2(30)
);

CREATE SEQUENCE service_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_service
	BEFORE INSERT
	ON Service
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
		SELECT service_id_seq.NEXTVAL
		INTO :NEW.serviceId
		FROM DUAL;
	END;


/*POSTs (должности)*/

CREATE TABLE Post
(
  	postId NUMBER NOT NULL PRIMARY KEY,
  	postName VARCHAR2(30),
  	salary NUMBER,
	serviceId NUMBER,
	CONSTRAINT fk_post FOREIGN KEY (serviceId)
	REFERENCES SERVICE (serviceId)
);

CREATE SEQUENCE post_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_post
	BEFORE INSERT
	ON Post
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
		SELECT post_id_seq.NEXTVAL
		INTO :NEW.postId
		FROM DUAL;
	END;

/*STAFF*/

CREATE TABLE Staff
(
  	personId NUMBER NOT NULL PRIMARY KEY,
	name FIO,
  	postId NUMBER,
	CONSTRAINT fk_staff FOREIGN KEY(postId)
	REFERENCES POST(postId)
);

CREATE SEQUENCE staff_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_staff
	BEFORE INSERT
	ON Staff
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
		SELECT staff_id_seq.NEXTVAL
		INTO :NEW.personId
		FROM DUAL;
	END;

/*PERFORMANCE*/

CREATE TABLE Performance
(
  	performanceId NUMBER NOT NULL PRIMARY KEY,
  	performanseName VARCHAR2(30),
	performancePrice NUMBER CHECK(performancePrice>=500 AND performancePrice<=5000),
	description VARCHAR2(50)
);

CREATE SEQUENCE performance_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_performance
	BEFORE INSERT
	ON Performance
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
		SELECT performance_id_seq.NEXTVAL
		INTO :NEW.performanceId
		FROM DUAL;
	END;

/*CAST*/

CREATE TABLE Cast
(
  	castId NUMBER NOT NULL PRIMARY KEY,
  	performanceId NUMBER,
	personId NUMBER,
	CONSTRAINT fk_cast_1 FOREIGN KEY(performanceId)
	REFERENCES PERFORMANCE(performanceId),
	CONSTRAINT fk_cast_2 FOREIGN KEY(personId)
	REFERENCES STAFF(personId)
);

CREATE SEQUENCE cast_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_cast
	BEFORE INSERT
	ON Cast
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
		SELECT cast_id_seq.NEXTVAL
		INTO :NEW.castId
		FROM DUAL;
	END;

/*REQUISITE*/

CREATE TABLE Requisite
(
  	requisiteId NUMBER NOT NULL PRIMARY KEY,
  	requisiteName VARCHAR2(20),
	requisitePrice NUMBER CHECK(requisitePrice>=50)
);

CREATE SEQUENCE requisite_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_requisite
	BEFORE INSERT
	ON Requisite
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
		SELECT requisite_id_seq.NEXTVAL
		INTO :NEW.requisiteId
		FROM DUAL;
	END;

/*DECORATION*/

CREATE TABLE Decoration
(
	decorationId NUMBER NOT NULL PRIMARY KEY,
  	performanceId NUMBER,
	requisiteId NUMBER,
	CONSTRAINT fk_decoration_1 FOREIGN KEY(performanceId)
	REFERENCES PERFORMANCE(performanceId),
	CONSTRAINT fk_decoration_2 FOREIGN KEY(requisiteId)
	REFERENCES REQUISITE(requisiteId)
);

CREATE SEQUENCE decoration_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_decoration
	BEFORE INSERT
	ON Decoration
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
		SELECT decoration_id_seq.NEXTVAL
		INTO :NEW.decorationId
		FROM DUAL;
	END;

/*PLACE*/

CREATE TABLE Place
(
	placeId NUMBER NOT NULL PRIMARY KEY,
  	placeType VARCHAR2(20),
	placePrice NUMBER
);

CREATE SEQUENCE place_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_place
	BEFORE INSERT
	ON Place
	REFERENCING NEW AS NEW
	FOR EACH ROW
	DECLARE
		error_place_type EXCEPTION;
	BEGIN
		IF :NEW.placeType <> 'stalls' THEN
			IF :NEW.placeType <> 'amphitheater' THEN
				IF :NEW.placeType <> 'balcony' THEN
					RAISE error_place_type;
				END IF;
			END IF;
		END IF;

		IF INSERTING THEN
			SELECT place_id_seq.NEXTVAL
			INTO :NEW.placeId
			FROM DUAL;
		END IF;
	EXCEPTION
		WHEN error_place_type THEN RAISE_APPLICATION_ERROR(-20999,
		'Places can be only of types balcony, amphitheater or stalls.');
	END autoincrement_tr_place;

/*TIMETABLE*/

CREATE TABLE Timetable
(
	eventId NUMBER NOT NULL PRIMARY KEY,
  	eventType VARCHAR2(20),
	eventDate DATE,
	performanceId NUMBER,
	CONSTRAINT fk_timetable FOREIGN KEY(performanceId)
	REFERENCES PERFORMANCE(performanceId)
);

CREATE SEQUENCE timetable_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_timetable
	BEFORE INSERT
	ON Timetable
	REFERENCING NEW AS NEW
	FOR EACH ROW
	DECLARE
		error_event_type EXCEPTION;
	BEGIN
		IF :NEW.eventType <> 'repetition' THEN
			IF :NEW.eventType<> 'dress rehearsal' THEN
				IF :NEW.eventType <> 'performance' THEN
					RAISE error_event_type;
				END IF;
			END IF;
		END IF;

		IF INSERTING THEN
			SELECT timetable_id_seq.NEXTVAL
			INTO :NEW.eventId
			FROM DUAL;
		END IF;
	EXCEPTION
		WHEN error_event_type THEN RAISE_APPLICATION_ERROR(-20999,
		'Events can be only of types repetition, dress rehearsal or performance.');
	END autoincrement_tr_timetable;

/*BOOKING*/

CREATE TABLE Booking
(
	bookingId NUMBER NOT NULL PRIMARY KEY,
 	eventId NUMBER,
	placeId NUMBER,
	placeNo NUMBER,
	CONSTRAINT fk_booking_1 FOREIGN KEY(eventId)
	REFERENCES TIMETABLE(eventId),
	CONSTRAINT fk_booking_2 FOREIGN KEY(placeId)
	REFERENCES PLACE(placeId)
);

CREATE SEQUENCE booking_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_booking
	BEFORE INSERT
	ON Booking
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
		SELECT booking_id_seq.NEXTVAL
		INTO :NEW.bookingId
		FROM DUAL;
	END;