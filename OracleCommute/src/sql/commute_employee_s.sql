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
			 i_is_driver 	  varchar2,
                         is_grp_assigned  varchar2);
                         
procedure getEmployee(o_employee OUT NOCOPY SYS_REFCURSOR, i_username NUMBER);
                         
procedure getGroup(o_group OUT NOCOPY SYS_REFCURSOR, i_grp_id NUMBER);

procedure updateEmployee(i_emp_id     number,
                         i_username varchar2,
                         i_password varchar2,
                         i_coordx   varchar2,
                         i_coordy   varchar2,
                         i_name   varchar2,
                         i_phone  number,
                         i_addr   varchar2,
                         i_email  varchar2,
                         i_home_departure TIMESTAMP,
			 i_office_departure TIMESTAMP,
                         i_is_driver      varchar2,
                         i_is_grp_assigned  varchar2);


procedure retrieveEmployees(o_employees OUT NOCOPY SYS_REFCURSOR);

procedure getEmployeeLocation(i_id varchar2, o_coordx OUT varchar2, o_coordy OUT varchar2);

PROCEDURE get_grp_empl_locations(
	i_group_id NUMBER,
	o_locations OUT NOCOPY SYS_REFCURSOR);

procedure getGroupPaths(o_paths OUT NOCOPY SYS_REFCURSOR);
		 
procedure assignGroup(i_gid  IN NUMBER, i_id IN NUMBER);

	
PROCEDURE write_path(i_group_id NUMBER, i_path VARCHAR2);


PROCEDURE getAllEmpNotAssigned(o_emp_cur OUT NOCOPY SYS_REFCURSOR);

PROCEDURE getVacantGroups(o_vacant_grp OUT NOCOPY SYS_REFCURSOR);
		    

PROCEDURE insertGroup(gr_id IN NUMBER, em_id IN NUMBER);

PROCEDURE insertGroupAttr( o_g_id OUT NOCOPY NUMBER, i_start_time TIMESTAMP, i_driver_id NUMBER, i_size NUMBER, i_path varchar2 );

PROCEDURE get_driver(o_driver OUT NOCOPY SYS_REFCURSOR, i_group_id NUMBER);

procedure get_group_id(o_group OUT NOCOPY SYS_REFCURSOR, i_emp_id NUMBER);

procedure updateGroupAttr(i_group_id NUMBER, i_path varchar2, i_start_time TIMESTAMP, i_driver_id NUMBER, i_size NUMBER);
		       
end;
/

commit;

show errors;
