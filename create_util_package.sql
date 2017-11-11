create or replace package util as
	FUNCTION create_requisite(
		requisiteName VARCHAR2,
		requisitePrice NUMBER)
		return NUMBER;
	PROCEDURE delete_requisite_by_id(req_id NUMBER);
	PROCEDURE delete_requisite_by_name(req_name VARCHAR2);


	FUNCTION create_post(postName VARCHAR2, salary NUMBER, serviceId NUMBER) RETURN NUMBER;

	FUNCTION create_performance(
		performanseName VARCHAR2,
		performancePrice NUMBER,
		description VARCHAR2)
		RETURN NUMBER;

	FUNCTION calculate_theater_expenses RETURN NUMBER;
	PROCEDURE print_theater_expenses;

	PROCEDURE delete_performance_by_id(per_id NUMBER);


end util;

create or replace package body util as

	FUNCTION create_requisite(requisiteName VARCHAR2, requisitePrice NUMBER)
		RETURN NUMBER IS
		new_requisite_id NUMBER;
		BEGIN
			INSERT INTO REQUISITE(requisiteName, requisitePrice)
			VALUES(requisiteName, requisitePrice) RETURNING REQUISITE.REQUISITEID
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


	FUNCTION create_performance(
		performanseName VARCHAR2,
		performancePrice NUMBER,
		description VARCHAR2)
		RETURN NUMBER IS new_performance_id NUMBER;
		BEGIN
			INSERT INTO PERFORMANCE(performanseName, performancePrice, description)
			VALUES(performanseName, performancePrice, description) RETURNING PERFORMANCE.PERFORMANCEID
			INTO new_performance_id;
			RETURN(new_performance_id);
		END create_performance;


	PROCEDURE delete_performance_by_id(per_id NUMBER) AS
		BEGIN
			DELETE FROM PERFORMANCE WHERE performanceId = per_id;
		END;


	FUNCTION create_post(postName VARCHAR2, salary NUMBER, serviceId NUMBER)
		RETURN NUMBER IS
		new_post_id NUMBER;
	BEGIN
		INSERT INTO POST(postName, salary, serviceId)
		VALUES (postName, salary, serviceId) RETURNING POST.POSTID INTO new_post_id;
		RETURN (new_post_id);
	END create_post;


	FUNCTION calculate_theater_expenses RETURN NUMBER IS
		summary NUMBER;
		BEGIN
			SELECT SUM(REQUISITEPRICE + SALARY) INTO summary FROM REQUISITE, POST;
			RETURN(summary);
		END;

	PROCEDURE print_theater_expenses as
		BEGIN
			DBMS_OUTPUT.PUT_LINE('Theater Expenses:');
			DBMS_OUTPUT.PUT_LINE( UTIL.calculate_theater_expenses );
		END;

END util;