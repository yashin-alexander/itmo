DECLARE
  l_blob BLOB;
  v_src_loc  BFILE := BFILENAME('IMG_DIR', 'exp20000.png');
  v_amount   INTEGER;
  sonya DESCRIPTION;
BEGIN
  DBMS_LOB.OPEN(v_src_loc, DBMS_LOB.LOB_READONLY);
  v_amount := DBMS_LOB.GETLENGTH(v_src_loc);
  dbms_lob.createtemporary(l_blob, true);
  DBMS_LOB.LOADFROMFILE(l_blob, v_src_loc, v_amount);
  sonya := description('{"a": "kek"}',l_blob, TO_DATE('12.10.04 12:12:30','dd.mm.yy.  hh24:mi:ss'));
  INSERT INTO STAGING (STAGINGID, STAGINGNAME, STAGINGPRICE, DESCRIPTION, DURATIONTIME) VALUES
  (2, 'test', 2003, sonya, 5);
  DBMS_LOB.CLOSE(v_src_loc);
  COMMIT;
END;

DECLARE
  t_blob BLOB;
  t_len NUMBER;
  t_file_name VARCHAR2(100);
  t_output UTL_FILE.file_type;
  t_TotalSize number;
  t_position number := 1;
  t_chucklen NUMBER := 4096;
  t_chuck raw(4096);
  t_remain number;
    sonya DESCRIPTION;
BEGIN
  SELECT DESCRIPTION INto sonya FROM STAGING WHERE STAGINGID=5;
  t_blob := sonya.getPicture;
  t_TotalSize := DBMS_LOB.getlength (t_blob);
  t_file_name := 'kek.png';
  t_remain := t_TotalSize;
  t_output := UTL_FILE.fopen ('IMG_DIR', t_file_name, 'wb', 32760);
  WHILE t_position < t_TotalSize
  LOOP
    DBMS_LOB.READ (t_blob, t_chucklen, t_position, t_chuck);
    UTL_FILE.put_raw (t_output, t_chuck);
    UTL_FILE.fflush (t_output);
    t_position := t_position + t_chucklen;
    t_remain := t_remain - t_chucklen;
    IF t_remain < 4096
    THEN
      t_chucklen := t_remain;
    END IF;
  END LOOP;
END;
