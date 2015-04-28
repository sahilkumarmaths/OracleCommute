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



end;
/
commit;
show errors; 
