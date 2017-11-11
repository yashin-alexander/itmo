declare
   result number;
BEGIN
    result := UTIL.CREATE_REQUISITE('palka', 100);
    result := UTIL.CREATE_REQUISITE('chairs', 600);
END;
select * from requisite;

--

BEGIN
    UTIL.delete_requisite_by_id(1);
END;
select * from requisite;

--

declare
   result number;
BEGIN
    result := UTIL.CREATE_PERFORMANCE('Athello', 3000, 'just an example');
END;
select * from PERFORMANCE;

--

BEGIN
    UTIL.delete_performance_by_id(1);
END;
select * from PERFORMANCE;

--

BEGIN
    INSERT INTO SERVICE (SERVICENAME) VALUES ('buffet');
    INSERT INTO SERVICE (SERVICENAME) VALUES ('wardrobe');
    INSERT INTO SERVICE (SERVICENAME) VALUES ('toilet');
END;
select * from SERVICE;

--

DELETE FROM SERVICE;

--

declare
   result number;
BEGIN
    result := util.create_post('buffetter', 30000, 13);
    result := util.create_post('wardrobber', 25000, 14);
    result := util.create_post('wardrobber', 25000, 14);
    result := util.create_post('wardrobber', 25000, 14);
    result := util.create_post('toiletterino', 30000, 15);
END;
SELECT * FROM POST;

--

declare
   result number;
BEGIN
    result := util.calculate_theater_expenses();
    SELECT result;
END;

--

BEGIN
    util.print_theater_expenses;
END;