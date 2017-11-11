declare
   result number;
BEGIN
    result := UTIL.CREATE_REQUISITE('palka', 100);
    result := UTIL.CREATE_REQUISITE('chairs', 600);
END;
select * from requisite;


BEGIN
    UTIL.delete_requisite_by_id(1);
END;
select * from requisite;


declare
   result number;
BEGIN
    result := UTIL.CREATE_PERFORMANCE('Athello', 3000, 'just an example');
END;
select * from PERFORMANCE;

BEGIN
    UTIL.delete_performance_by_id(1);
END;

select * from PERFORMANCE;