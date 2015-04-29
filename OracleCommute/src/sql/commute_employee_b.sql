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

PROCEDURE get_grp_empl_locations(
	i_group_id NUMBER,
	o_locations OUT NOCOPY SYS_REFCURSOR)
IS
BEGIN
	OPEN o_locations FOR
		SELECT coordx, coordy FROM
		employee emp,
		emp_grp grp
		WHERE g_id = i_group_id;
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


end;
/
commit;
show errors; 
