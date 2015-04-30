CREATE TABLE employee
(
    emp_id NUMBER NOT NULL,
    username VARCHAR2(64),
    passwd VARCHAR2(64),
    coordx NUMBER,
    coordy NUMBER,
    name VARCHAR2(256),
    phNo NUMBER(10),
    address VARCHAR2(256),
    email VARCHAR2(64),
    home_departure TIMESTAMP(6),
    office_departure TIMESTAMP(6),
    is_driver VARCHAR2(1 CHAR),
    is_grp_assigned VARCHAR2(1 CHAR)
);


create sequence emp_id_seq start with 1 increment by 1 nomaxvalue;



CREATE TABLE emp_group
(
    g_id NUMBER NOT NULL,
    emp_id NUMBER
);

create sequence emp_grp_id_seq start with 1 increment by 1 nomaxvalue;



CREATE TABLE group_attr
(
    g_id NUMBER,
    path VARCHAR2(1024), 
    start_time TIMESTAMP,
    driver_id NUMBER,
    grp_size NUMBER
);



commit;
/

