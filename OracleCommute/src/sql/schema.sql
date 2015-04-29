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
    is_driver VARCHAR2(1 CHAR)
);


create sequence emp_id_seq start with 1 increment by 1 nomaxvalue;
alter table employee add constraint employee_pk primary key (emp_id);


CREATE TABLE emp_group
(
    g_id NUMBER NOT NULL,
    emp_id NUMBER
);

create sequence emp_grp_id_seq start with 1 increment by 1 nomaxvalue;

alter table emp_group add constraint emp_group_pk primary key (g_id);
alter table emp_group add constraint emp_group_fk foreign key (emp_id) references employee(emp_id) ON DELETE CASCADE;

CREATE TABLE group_attr
(
    g_id NUMBER,
    path VARCHAR2(256), 
    start_time DATE,
    driver_id NUMBER
);

alter table group_attr add constraint group_attr_fk foreign key (g_id) references emp_group(g_id) ON DELETE CASCADE;

commit;
/

