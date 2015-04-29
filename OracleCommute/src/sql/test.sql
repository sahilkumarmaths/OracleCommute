-- Make any changes in this file
ALTER SESSION SET CURRENT_SCHEMA=COMMUTE;
set serveroutput on size 2500;

DECLARE
  result varchar2(256);
  data NUMBER;
  abc NUMBER;
BEGIN
    commute_employee.getEmployeeLocation('1',data,abc);
    dbms_output.put_line('result : ' || data || '  ' || abc);
END;
