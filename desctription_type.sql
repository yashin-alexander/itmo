CREATE OR REPLACE TYPE description AS OBJECT
(
  summary JSON,
  picture NUMBER,
  presale DATE,
  MEMBER FUNCTION getAll RETURN description,
  MEMBER FUNCTION getAllInJson RETURN JSON,
  MEMBER FUNCTION getPicture RETURN NUMBER,
  MEMBER FUNCTION getPresale RETURN DATE,
  MEMBER PROCEDURE setAll(summary IN JSON, picture IN NUMBER, presale IN DATE)
);

CREATE OR REPLACE TYPE BODY description AS
  MEMBER FUNCTION getAll RETURN description IS
    BEGIN
      RETURN SELF;
    END getAll;
  MEMBER FUNCTION getAllInJson RETURN JSON IS
    result JSON;
    BEGIN
      result := JSON();
      result.put('summary', SELF.summary);
      result.put('picture', SELF.picture);
      result.put('presale', SELF.presale);
      RETURN result;
    END getAllInJson;
  MEMBER FUNCTION getPicture RETURN NUMBER IS
    BEGIN
      RETURN SELF.picture;
    END getPicture;
  MEMBER FUNCTION getPresale RETURN DATE IS
    BEGIN
      RETURN SELF.presale;
    END getPresale;
  MEMBER PROCEDURE setAll(summary IN JSON, picture IN NUMBER, presale IN DATE) AS
    BEGIN
      SELF.summary := summary;
      SELF.picture := picture;
      SELF.presale := presale;
    END setAll;
  END;


  DECLARE
    summary json;
    alldesc json;
    presale DATE;
    picture NUMBER;
    sonya description;
    sonya1 description;
  BEGIN
    sonya := description(json('{"a": true }'), 123, TO_DATE('12.10.04 12:12:30','dd.mm.yy.  hh24:mi:ss'));

    alldesc := sonya.getAllInJson;
    DBMS_OUTPUT.PUT_LINE(alldesc.to_char);

    sonya.setAll(json('{"a": false }'), 333, TO_DATE('22.12.24 12:12:30','dd.mm.yy.  hh24:mi:ss'));
    sonya1 := sonya.getAll;

    picture := sonya1.getPicture;
    DBMS_OUTPUT.PUT_LINE(picture);

    presale := sonya1.getPresale;
    DBMS_OUTPUT.PUT_LINE(presale);

  END;
