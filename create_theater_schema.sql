/*POSITIONS (должности)*/

CREATE TABLE Position
(
  	positionId INT NOT NULL PRIMARY KEY,
  	positionName VARCHAR2(30),
  	salary NUMBER
);

CREATE SEQUENCE position_id_seq
	START WITH 1
	INCREMENT BY 1
	NOCACHE;

CREATE OR REPLACE TRIGGER autoincrement_tr_position
	BEFORE INSERT OR UPDATE
	ON Position
	REFERENCING NEW AS NEW
	FOR EACH ROW
	BEGIN
		SELECT position_id_seq.NEXTVAL
		INTO :NEW.positionId
		FROM DUAL;
	END;

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
