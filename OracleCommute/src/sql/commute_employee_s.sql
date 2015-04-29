ALTER SESSION SET CURRENT_SCHEMA=COMMUTE;
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
			 i_home_departure TIMESTAMP,
			 i_office_departure TIMESTAMP,
			 i_is_driver 	  varchar2);


procedure retrieveEmployees(o_employees OUT NOCOPY SYS_REFCURSOR);

procedure getEmployeeLocation(i_id varchar2, o_coordx OUT varchar2, o_coordy OUT varchar2);

procedure getGroupPaths(o_paths OUT NOCOPY SYS_REFCURSOR);
		 
procedure assignGroup(i_gid  IN NUMBER, i_id IN NUMBER);

PROCEDURE get_grp_empl_locations(
	i_group_id NUMBER,
	o_locations OUT NOCOPY SYS_REFCURSOR);
	
PROCEDURE write_path(
i_group_id NUMBER,
i_path VARCHAR2);
		       
end;
/
commit;
show errors;

