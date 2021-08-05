INSERT INTO schoolgrade (id_key, name) VALUES(1, 'first');
INSERT INTO schoolgrade (id_key, name) VALUES(2, 'second');
INSERT INTO schoolgrade (id_key, name) VALUES(3, 'third');
INSERT INTO schoolgrade (id_key, name) VALUES(4, 'fourth');

insert into person (id_key, first_name, last_name, id_number)
values (1, 'Saeed', 'Safaeian', '0123456789');
insert into person (id_key, first_name, last_name, id_number)
values (2, 'Ali', 'Safaeian', '0123456780');


insert into person_schoolgrade (graduate_date, person_id_key, school_grade_id_key)
values ('2001-01-01',1,1);
insert into person_schoolgrade (graduate_date, person_id_key, school_grade_id_key)
values ('2002-01-01',2,2);
