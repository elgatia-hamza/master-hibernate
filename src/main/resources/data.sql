/*CREATE TABLE COURSE(
   id INTEGER PRIMARY KEY,
   name VARCHAR(255) NOT NULL
);*/

/*insert into course (id, name) values (1001L, 'JPA in 50 Steps');
insert into course (id, name) values (1002L, 'Spring in 50 Steps');*/

insert into course (id, name, created_at, last_update_at, is_deleted) values (1001L, 'JPA in 50 Steps', CURRENT_DATE, CURRENT_DATE, false);
insert into course (id, name, created_at, last_update_at, is_deleted) values (1002L, 'Spring in 50 Steps', CURRENT_DATE, CURRENT_DATE, false);
insert into course (id, name, created_at, last_update_at, is_deleted) values (1003L, 'Spring boot in 100 Steps', CURRENT_DATE, CURRENT_DATE, false);

/*insert into course (id, name, created_at, last_update_at) values (1004L, 'Dummy1', CURRENT_DATE, CURRENT_DATE);
insert into course (id, name, created_at, last_update_at) values (1005L, 'Dummy2', CURRENT_DATE, CURRENT_DATE);
insert into course (id, name, created_at, last_update_at) values (1006L, 'Dummy3', CURRENT_DATE, CURRENT_DATE);
insert into course (id, name, created_at, last_update_at) values (1007L, 'Dummy4', CURRENT_DATE, CURRENT_DATE);
insert into course (id, name, created_at, last_update_at) values (1008L, 'Dummy5', CURRENT_DATE, CURRENT_DATE);
insert into course (id, name, created_at, last_update_at) values (1009L, 'Dummy6', CURRENT_DATE, CURRENT_DATE);*/


insert into passport (id, number) values (3001L, 'A123456789');
insert into passport (id, number) values (3002L, 'B123456789');
insert into passport (id, number) values (3003L, 'C123456789');

insert into student (id, name, passport_id) values (2001L, 'Zak', 3001L);
insert into student (id, name, passport_id) values (2002L, 'Nina', 3002L);
insert into student (id, name, passport_id) values (2003L, 'Fool', 3002L);

insert into review (id, rating, description, course_id) values (4001L,'FIVE', 'Great Course', 1001L);
insert into review (id, rating, description, course_id) values (4002L,'FOUR', 'Wonderful Course', 1001L);
insert into review (id, rating, description, course_id) values (4003L,'FIVE', 'Awesome Course', 1003L);

insert into student_course (student_id, course_id) values (2001,1001);
insert into student_course (student_id, course_id) values (2002,1001);
insert into student_course (student_id, course_id) values (2003,1001);
insert into student_course (student_id, course_id) values (2001,1003);