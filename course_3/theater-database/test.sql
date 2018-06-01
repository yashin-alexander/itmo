declare
   result number;
BEGIN
    result := UTIL.CREATE_REQUISITE('palka');
    result := UTIL.CREATE_REQUISITE('chairs');
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
    result := UTIL.CREATE_STAGING('Athello', 3000, 'just an example', 3);
    result := UTIL.CREATE_STAGING('Snegurochka', 3500, 'with ded moroz', 2.5);
    result := UTIL.CREATE_STAGING('Romario and Dakotta', 4000, 'suicide & redemption', 2);
    result := UTIL.CREATE_STAGING('Lady Makbett', 3000, 'just a lady', 3);
END;
select * from STAGING;

--

BEGIN
    UTIL.delete_STAGING_by_id(1);
END;
select * from STAGING;

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
    result := util.create_post('buffetter', 30000, 1);
    result := util.create_post('wardrobber', 25000, 2);
    result := util.create_post('wardrobber', 25000, 2);
    result := util.create_post('wardrobber', 25000, 2);
    result := util.create_post('toiletterino', 30000, 3);
END;
SELECT * FROM POST;

--

declare
   result number;
BEGIN
    result := UTIL.create_timetable('dress rehearsal', '05.15.2017', 1);
    result := UTIL.create_timetable('repetition', '06.15.2017', 2);
    result := UTIL.create_timetable('repetition', '09.15.2017', 1);
    result := UTIL.create_timetable('performance', '10.15.2017', 1);
END;
SELECT * FROM TIMETABLE;

--



--

BEGIN
    UTIL.UPDATE_PRICES_FOR_5_PERCENT;
END;
SELECT * FROM requisite;
SELECT * FROM POST;
SELECT * FROM PERFORMANCE;

--
