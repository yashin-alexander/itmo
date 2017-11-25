create or replace package util as
	FUNCTION create_requisite(
		requisiteName VARCHAR2)
		return NUMBER;
	PROCEDURE delete_requisite_by_id(req_id NUMBER);
	PROCEDURE delete_requisite_by_name(req_name VARCHAR2);

	FUNCTION create_post(postName VARCHAR2, salary NUMBER, serviceId NUMBER) RETURN NUMBER;
	FUNCTION create_staging(
		stagingName VARCHAR2,
		stagingPrice NUMBER,
		description VARCHAR2,
		durationTime NUMBER)
		RETURN NUMBER;

	PROCEDURE delete_staging_by_id(staging_id NUMBER);
	PROCEDURE delete_all_the_theater;

	FUNCTION create_timetable(eventType VARCHAR2, eventDate VARCHAR2, stagingId NUMBER) RETURN NUMBER;

	PROCEDURE read_timetable;

	PROCEDURE update_prices_for_5_percent;

	function clobfromblob(p_blob blob) return clob;


end util;

create or replace package body util as

	FUNCTION create_requisite(requisiteName VARCHAR2)
		RETURN NUMBER IS
		new_requisite_id NUMBER;
		BEGIN
			INSERT INTO REQUISITE(requisiteName)
			VALUES(requisiteName) RETURNING REQUISITE.REQUISITEID
			INTO new_requisite_id;
		RETURN(new_requisite_id);
	END create_requisite;

	PROCEDURE delete_requisite_by_id(req_id NUMBER) AS
		BEGIN
			DELETE FROM REQUISITE WHERE requisiteId = req_id;
		END;


	PROCEDURE delete_requisite_by_name(req_name VARCHAR2) AS
		BEGIN
			DELETE FROM REQUISITE WHERE REQUISITENAME = req_name;
		END;


	FUNCTION create_staging(
		stagingName VARCHAR2,
		stagingPrice NUMBER,
		description VARCHAR2,
		durationTime NUMBER)
		RETURN NUMBER IS new_staging_id NUMBER;
		BEGIN
			INSERT INTO STAGING(stagingName, stagingPrice, description, durationTime)
			VALUES(stagingName, stagingPrice, to_blob(description), durationTime) RETURNING STAGING.stagingId
			INTO new_staging_id;
			RETURN(new_staging_id);
		END create_staging;


	PROCEDURE delete_staging_by_id(staging_id NUMBER) AS
		BEGIN
			DELETE FROM STAGING WHERE stagingId = staging_id;
		END;


	FUNCTION create_post(postName VARCHAR2, salary NUMBER, serviceId NUMBER)
		RETURN NUMBER IS
		new_post_id NUMBER;
	BEGIN
		INSERT INTO POST(postName, salary, serviceId)
		VALUES (postName, salary, serviceId) RETURNING POST.POSTID INTO new_post_id;
		RETURN (new_post_id);
	END create_post;

	PROCEDURE delete_all_the_theater AS
		BEGIN
			DELETE FROM BOOKING;
			DELETE FROM CAST;
			DELETE FROM DECORATION;
			DELETE FROM STAGING;
			DELETE FROM role;
			DELETE FROM PLACE;
			DELETE FROM POST;
			DELETE FROM REQUISITE;
			DELETE FROM SERVICE;
			DELETE FROM STAFF;
			DELETE FROM TIMETABLE;
		END;

	FUNCTION create_timetable(
		eventType VARCHAR2,
		eventDate VARCHAR2,
		stagingId NUMBER) RETURN NUMBER IS
		timetable_id NUMBER;
		BEGIN
			INSERT INTO TIMETABLE(eventType, eventDate, stagingId)
				VALUES (eventType, TO_DATE(eventDate, 'DD/MM/YYYY'), stagingId) RETURNING TIMETABLE.EVENTID
			INTO timetable_id;
			RETURN (timetable_id);
		END;

	PROCEDURE read_timetable AS
		begin
			DBMS_OUTPUT.PUT_LINE('Timetable:');
			DBMS_OUTPUT.PUT_LINE('EventID | EventType | EventDate | StagingId');
			for time in (select * from TIMETABLE) loop
			DBMS_OUTPUT.PUT_LINE(time.EVENTID || ' | ' || time.EVENTTYPE || ' | ' || time.EVENTDATE || ' | ' || time.stagingID);
			end loop;
		end;

	PROCEDURE update_prices_for_5_percent AS
		BEGIN
			UPDATE POST
				SET SALARY = SALARY * 1.05;
			UPDATE STAGING
				SET stagingPRICE = stagingPRICE * 1.05;
		END;
	
	function clobfromblob(p_blob blob) return clob is
	l_clob clob;
	l_dest_offsset integer := 1;
	l_src_offsset integer := 1;
	l_lang_context integer := dbms_lob.default_lang_ctx;
	l_warning integer;

	begin

	if p_blob is null then
	return null;
	end if;

	dbms_lob.createTemporary(lob_loc => l_clob
	,cache => false);

	dbms_lob.converttoclob(dest_lob => l_clob
	,src_blob => p_blob
	,amount => dbms_lob.lobmaxsize
	,dest_offset => l_dest_offsset
	,src_offset => l_src_offsset
	,blob_csid => dbms_lob.default_csid
	,lang_context => l_lang_context
	,warning => l_warning);

	return l_clob;

	end clobfromblob;
END util;
