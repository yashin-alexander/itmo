----------------------------------------------TYPE CREATING----------------------------------------------

CREATE OR REPLACE TYPE description AS OBJECT
(
  summary CLOB,
  picture BLOB,
  presale DATE,
  MEMBER FUNCTION getAll RETURN description,
  MEMBER FUNCTION getAllInJson RETURN JSON,
  MEMBER FUNCTION getPicture RETURN BLOB,
  MEMBER FUNCTION getPresale RETURN DATE,
  MEMBER PROCEDURE setAll(summary IN JSON, picture IN BLOB, presale IN DATE)
);

CREATE OR REPLACE TYPE BODY description AS
  MEMBER FUNCTION getAll RETURN description IS
    BEGIN
      RETURN SELF;
    END getAll;
  MEMBER FUNCTION getAllInJson RETURN JSON IS
    result JSON;
    tmp JSON;
    pic CLOB;
    BEGIN
      pic := UTIL.CLOBFROMBLOB(SELF.picture);
      result := JSON();
      tmp := JSON(SELF.summary);
      result.put('summary', tmp);
      result.put('picture', json_value(pic));
      result.put('presale', SELF.presale);
      RETURN result;
    END getAllInJson;
  MEMBER FUNCTION getPicture RETURN BLOB IS
    BEGIN
      RETURN SELF.picture;
    END getPicture;
  MEMBER FUNCTION getPresale RETURN DATE IS
    BEGIN
      RETURN SELF.presale;
    END getPresale;
  MEMBER PROCEDURE setAll(summary IN JSON, picture IN BLOB, presale IN DATE) AS
    BEGIN
      SELF.summary := summary.to_char;
      SELF.picture := picture;
      SELF.presale := presale;
    END setAll;
  END;

----------------------------------------------TYPE TESTS-----------------------------------------------

DECLARE
    summary json;
    alldesc json;
    presale DATE;
    picture BLOB;
    sonya description;
    sonya1 description;
  BEGIN
    sonya := description('{"a": "kek"}', to_blob('123'), TO_DATE('12.10.04 12:12:30','dd.mm.yy.  hh24:mi:ss'));
    DBMS_OUTPUT.PUT_LINE(sonya.SUMMARY);
    alldesc := sonya.getAllInJson;
    DBMS_OUTPUT.PUT_LINE(alldesc.to_char);

    sonya.setAll(JSON('{"a": false }'), to_blob('333'), TO_DATE('22.12.24 12:12:30','dd.mm.yy.  hh24:mi:ss'));
    sonya1 := sonya.getAll;

    picture := sonya1.getPicture;
    DBMS_OUTPUT.PUT_LINE(UTL_RAW.CAST_TO_VARCHAR2(picture));

    presale := sonya1.getPresale;
    DBMS_OUTPUT.PUT_LINE(presale);

     alldesc := sonya1.getAllInJson;
    DBMS_OUTPUT.PUT_LINE(alldesc.to_char);

  END;
