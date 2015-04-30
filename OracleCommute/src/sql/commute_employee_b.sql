ALTER SESSION SET CURRENT_SCHEMA=COMMUTE;
create or replace package body commute_employee
AS

procedure createEmployee(i_username varchar2,
                         i_password varchar2,
                         i_coordx   varchar2,
                         i_coordy   varchar2,
                         i_name   varchar2,
                         i_phone  number,
                         i_addr   varchar2,
                         i_email  varchar2,
                         i_home_departure TIMESTAMP,
			 i_office_departure TIMESTAMP,
                         i_is_driver      varchar2)
IS
BEGIN
	INSERT INTO employee
	(
		EMP_ID,
		USERNAME,
		PASSWD,
		COORDX,
		COORDY,
		NAME,
		PHNO,
		ADDRESS,
		EMAIL,
		HOME_DEPARTURE,
		OFFICE_DEPARTURE,
		IS_DRIVER
		)
		VALUES
		(
		emp_id_seq.NEXTVAL,
		i_username,
		i_password,
		i_coordx,
		i_coordy,
		i_name,
		i_phone,
		i_addr,
		i_email,
		i_home_departure,
		i_office_departure,
		i_is_driver
	);		
END;


procedure retrieveEmployees(o_employees OUT NOCOPY SYS_REFCURSOR)
IS
BEGIN
	open o_employees FOR
		select * from employee;

END;

procedure getEmployee(o_employee OUT NOCOPY SYS_REFCURSOR, i_emp_id NUMBER)
IS
BEGIN
	open o_employee FOR
		select * from employee where emp_id = i_emp_id ;

END;

procedure getGroup(o_group OUT NOCOPY SYS_REFCURSOR, i_grp_id NUMBER)
IS
BEGIN
	open o_group FOR
		select * from emp_group where g_id = i_grp_id ;

END;

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
                         is_grp_assigned  varchar2)
IS
BEGIN
	UPDATE employee
        SET USERNAME := i_username,
        SET PASSWD   := i_password,
	SET COORDX   := i_coordx,
        SET COORDY   := i_coordy,
	SET NAME     := i_name,
        SET PHNO     := i_phone,
	SET ADDRESS  := i_addr,
	SET EMAIL    := i_email,
	SET HOME_DEPARTURE := i_home_departure,
	SET OFFICE_DEPARTURE := i_office_departure,
	SET IS_DRIVER        := i_is_driver,
        SET is_grp_assigned  := is_grp_assigned
        WHERE
        emp_id = emp_id;
        commit;
        
END;


procedure getEmployeeLocation(i_id varchar2, o_coordx OUT varchar2, o_coordy OUT varchar2)
IS
BEGIN
    SELECT coordx , coordy INTO o_coordx, o_coordy FROM employee WHERE  emp_id = i_id;
END;

procedure getGroupPaths(o_paths OUT NOCOPY SYS_REFCURSOR)
IS 
BEGIN
    open o_paths FOR SELECT * FROM group_attr ORDER BY g_id ;
END;
 
PROCEDURE get_grp_empl_locations(
	i_group_id NUMBER,
	o_locations OUT NOCOPY SYS_REFCURSOR)
IS
BEGIN
	OPEN o_locations FOR
		SELECT coordx, coordy FROM
		employee emp,
		emp_group grp
		WHERE g_id = i_group_id;
END;

procedure assignGroup(i_gid  IN NUMBER, i_id IN NUMBER)
IS 
BEGIN
    INSERT INTO emp_group(g_id,emp_id) VALUES(i_gid, i_id);
    COMMIT;
END;


PROCEDURE write_path(
i_group_id NUMBER,
i_path VARCHAR2)
IS
BEGIN
	UPDATE group_attr
	SET path = i_path
	WHERE g_id = i_group_id;
END;	

PROCEDURE getAllEmpNotAssigned(o_emp_cur OUT NOCOPY SYS_REFCURSOR)
IS
BEGIN
    OPEN o_emp_cur FOR SELECT * from employee WHERE is_grp_assigned = 'N';
END;


PROCEDURE getVacantGroups(o_vacant_grp OUT NOCOPY SYS_REFCURSOR)
IS
BEGIN
    OPEN o_vacant_grp FOR SELECT * FROM group_attr WHERE size < '5';
END;

PROCEDURE insertGroup(gr_id IN NUMBER, em_id IN NUMBER)
IS
BEGIN
    INSERT INTO employee (g_id, emp_id) values(gr_id, em_id);
    commit;
END;

PROCEDURE insertGroupAttr( o_g_id OUT NOCOPY NUMBER, i_start_time TIMESTAMP, i_driver_id NUMBER, i_size NUMBER)
IS
BEGIN
    o_g_id = emp_grp_id_seq.NEXTVAL;
    INSERT INTO group_attr ( g_id, path, start_time, driver_id, size) INTO (o_g_id, null,i_start_time,i_driver_id, i_size );
    commit;
END;

PROCEDURE updateGroupAttr(i_group_id NUMBER, i_path varchar2, i_start_time TIMESTAMP, i_driver_id NUMBER, i_size NUMBER)
IS 
BEGIN
    
    update group_attr
    SET path :=  i_path,
    SET start_time := i_start_time,
    SET driver_id := i_driver_id,
    SET size := i_size
    WHERE g_id = i_group_id;

END;


PROCEDURE get_grp_empl_locations(
	i_group_id NUMBER,
	o_locations OUT NOCOPY SYS_REFCURSOR)
IS
BEGIN
	OPEN o_locations FOR
		SELECT coordx, coordy FROM
		employee emp JOIN emp_group grp ON emp.emp_id = grp.EMP_ID
		WHERE g_id = i_group_id;
END;

PROCEDURE get_driver(
o_driver OUT NOCOPY SYS_REFCURSOR,
i_group_id NUMBER)
IS
BEGIN
	OPEN o_driver FOR
		SELECT coordx, coordy FROM group_attr grp JOIN EMPLOYEE emp ON grp.DRIVER_ID = emp.EMP_ID where g_id = i_group_id;
END;






end;
/
commit;
show errors; 
