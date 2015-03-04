create table Companies (
    id serial primary key,
    _name TEXT,
    _desc TEXT,
    _type TEXT
);

create table Users (
    id serial primary key,
    _username TEXT,
    _password TEXT
);

INSERT INTO Users (_username, _password) VALUES ('sadmin', 'jdJHsa7/1D2lapAWh0Wz2/DSXuden+5x');
INSERT INTO Companies (_name, _type, _desc) VALUES ('company one', 'orpm', '');
INSERT INTO Companies (_name, _type, _desc) VALUES ('company two', 'orpm', '');
INSERT INTO Companies (_name, _type, _desc) VALUES ('company three', 'orpm', '');
INSERT INTO Companies (_name, _type, _desc) VALUES ('company four', 'orpm', '');