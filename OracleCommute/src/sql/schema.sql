CREATE TABLE employee
(
    emp_id NUMBER,
    passwd VARCHAR2(64),
    coordx NUMBER,
    coordy NUMBER,
    name VARCHAR2(256),
    phNo NUMBER(10),
    address VARCHAR2(256),
    email VARCHAR2(64),
    time_departure DATE,
    is_driver BOOLEAN
);

CREATE TABLE GROUP
(
    gid NUMBER,
    empid NUMBER,
    , constraints emp_fkey ... 
    , 
),


CREATE TABLE GROUP_ATTR
(
    gid NUMBER,
    path VARCHAR2(256), 
    start_time DATE,
    driver_id NUMBER,
    constraint fkey driverid
);