CREATE TABLE accident_type
(
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE accident
(
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    accident_type_id INT REFERENCES accident_type(id)
);

CREATE TABLE rule
(
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE accident_rules
(
    accident_id int REFERENCES accident (id) ON UPDATE CASCADE ON DELETE CASCADE,
    rule_id     int REFERENCES rule (id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO rule (name)
VALUES ('Статья 1 превышение скорости');
INSERT INTO rule (name)
VALUES ('Статья 2 превышение скорости');
INSERT INTO rule (name)
VALUES ('Статья 3 выезд на встречную полосу');

INSERT INTO accident_type (name)
VALUES ('Ужасная овария');
INSERT INTO accident_type (name)
VALUES ('Средней тяжести овария');
INSERT INTO accident_type (name)
VALUES ('Да и не овария совсем');

INSERT INTO accident (name, text, address, accident_type_id)
VALUES ('Трагедия номер 1', 'Описание трагедии 1', 'Адрес трагедии 1', 1);
INSERT INTO accident (name, text, address, accident_type_id)
VALUES ('Трагедия номер 2', 'Описание трагедии 2', 'Адрес трагедии 2', 2);
INSERT INTO accident (name, text, address, accident_type_id)
VALUES ('Трагедия номер 3', 'Описание трагедии 3', 'Адрес трагедии 3', 3);

INSERT INTO accident_rules (accident_id, rule_id)
VALUES (1, 1);
INSERT INTO accident_rules (accident_id, rule_id)
VALUES (1, 2);
INSERT INTO accident_rules (accident_id, rule_id)
VALUES (1, 3);
INSERT INTO accident_rules (accident_id, rule_id)
VALUES (2, 3);
INSERT INTO accident_rules (accident_id, rule_id)
VALUES (3, 3);
