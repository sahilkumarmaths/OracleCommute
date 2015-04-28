SET ECHO ON
SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 80
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 1000

create or replace package commute_employee
is

procedure createEmployee(i_username varchar2,
			 i_password varchar2,
			 i_coordx   varchar2,
 			 i_coordy   varchar2,
			 i_name	  varchar2,
			 i_phone  number,
  			 i_addr   varchar2,
			 i_email  varchar2,
			 i_time_departure date,
			 i_is_driver 	  varchar2);
		       
end;
/
commit;
show errors;

