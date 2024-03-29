CREATE TABLE TABLEDEF (
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY(ID)
);


CREATE TABLE TYPEDEF (
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY(ID)
);

CREATE TABLE COLUMNDEF (
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    TABLEID INT NOT NULL,
    TYPEID INT NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY (TABLEID) REFERENCES TABLEDEF(ID),
    FOREIGN KEY (TYPEID) REFERENCES TYPEDEF(ID)
);

CREATE TABLE SAMPLE (
ID INT NOT NULL AUTO_INCREMENT,
C1 INT,
C2 INT,
C3 INT,
C4 INT,
DELETED INT DEFAULT 0,
PRIMARY KEY(ID)
);