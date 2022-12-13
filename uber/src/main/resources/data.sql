--------------ROLES--------------
insert into roles (name) values ('ROLE_CLIENT');
insert into roles (name) values ('ROLE_DRIVER');
insert into roles (name) values ('ROLE_ADMIN');
--------------ADDRESS--------------
insert into coordinates (latitude, longitude) values ('dsa','dsa');
insert into coordinates (latitude, longitude) values ('dsa2','dsa2');
insert into coordinates (latitude, longitude) values ('dsa3','dsa3');
--------------PHOTOS--------------
insert into photo (path) values ('image1.jpg');
insert into photo (path) values ('default,jpg');
insert into photo (path) values ('image3.jpg');
--------------USERS--------------
insert into clients (id, name, last_name, username, email, password, activated, blocked, phone, photo_id, address) values (nextval('SeqV1'),'Marko','Markovic', 'marko', 'marko@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', true,false,'+38121763225',1,'Neka adresa');
insert into drivers (id, name, last_name, username, email, password, active, blocked, phone, photo_id, address, available) values (nextval('SeqV1'),'Petar','Petrovic','petar', 'petar@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', false, false,'+38121763226',3,'neka adresa',false);
insert into admins (id, name, last_name, username, email, password, blocked,phone, photo_id, address) values (nextval('SeqV1'),'Admin','Adminovic','admin', 'admin@gmail.com','$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS',false,'+38121763221',2,'neka adresa');

--------------USERS-ROLES--------------
insert into user_role (user_id, role_id) values (1,1);
insert into user_role (user_id, role_id) values (2,2);
insert into user_role (user_id, role_id) values (3,3);