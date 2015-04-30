-- Make any changes in this file
ALTER SESSION SET CURRENT_SCHEMA=COMMUTE;
set serveroutput on size 2500;

DECLARE
  result varchar2(256);
  data NUMBER;
  abc NUMBER;
  o_emp_cur SYS_REFCURSOR;
BEGIN
  --  commute_employee.getEmployeeLocation('1',data,abc);
    --commute_employee.getAllEmpNotAssigned(o_emp_cur);
    OPEN o_emp_cur FOR
        SELECT * from employee where IS_GRP_ASSIGNED = 'N';
    if o_emp_cur is NOT NULL then
        dbms_output.put_line('result : data');
    end if;
END;
