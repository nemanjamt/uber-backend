--------------ROLES--------------
insert into roles (name) values ('ROLE_CLIENT');
insert into roles (name) values ('ROLE_DRIVER');
insert into roles (name) values ('ROLE_ADMIN');
--------------ADDRESS--------------
insert into coordinates (id, latitude, longitude) values (nextval('SeqV2'),'45.253649102956196','19.84253007429691');
insert into coordinates (id, latitude, longitude) values (nextval('SeqV2'),'45.26011466220762','19.832236354964667');
insert into coordinates (id, latitude, longitude) values (nextval('SeqV2'),'45.255159813549426','19.8119920402779');
--------------PHOTOS--------------
insert into photo (path) values ('default,jpg');
insert into photo (path) values ('image1.jpg');
insert into photo (path) values ('image2.jpg');
insert into photo (path) values ('image3.jpg');
insert into photo (path) values ('image4.jpg');
insert into photo (path) values ('image5.jpg');
insert into photo (path) values ('image6.jpg');
insert into photo (path) values ('image7.jpg');

--------------COORDINATES FOR VEHICLE POSITION--------------
insert into coordinates (id,latitude,longitude) values (nextval('SeqV2'),'45.24772758901914','19.816793738510604');
insert into coordinates (id,latitude,longitude) values (nextval('SeqV2'),'45.24824','19.81674');
insert into coordinates (id,latitude,longitude) values (nextval('SeqV2'),'45.265157468883295','19.835347303315036');
insert into coordinates (id,latitude,longitude) values (nextval('SeqV2'),'45.24340318918538','19.841866658892144');

--------------COORDINATES FOR VEHICLE POSITION--------------
-- STANDARD,
--     LUXURIOUS,
--     VAN
insert into vehicle_type (name,kind) values ( 'STANDARD' , 0);
insert into vehicle_type (name,kind) values ('LUXURIOUS',1);
insert into vehicle_type (name,kind) values ('VAN',2);


--------------VEHICLES--------------
insert into vehicle (licence_plate,capacity,baby_transport,pet_transport,occupied,location_id,vehicle_type_id) values ('54-6677-90',4,true,true,true,4,1);
insert into vehicle (licence_plate,capacity,baby_transport,pet_transport,occupied,location_id,vehicle_type_id) values ('78-1111-88',4,true,false,true,5,2);
insert into vehicle (licence_plate,capacity,baby_transport,pet_transport,occupied,location_id,vehicle_type_id) values ('23-4331-55',4,false,true,false,6,3);
insert into vehicle (licence_plate,capacity,baby_transport,pet_transport,occupied,location_id,vehicle_type_id) values ('33-9090-11',4,true,true,true,7,3);


--------------USERS--------------
insert into clients (id, name, last_name, username, email, password, activated, blocked, phone, photo_id, address) values (nextval('SeqV1'),'Marko','Markovic', 'marko', 'marko@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', true,false,'+38121763225',2,'1');

insert into admins (id, name, last_name, username, email, password, blocked,phone, photo_id, address) values (nextval('SeqV1'),'Admin','Adminovic','admin', 'admin@gmail.com','$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS',false,'+38121763221',1,'2');
insert into drivers (id, name, last_name, username, email, password, active, blocked, phone, photo_id, address, available,vehicle_id) values (nextval('SeqV1'),'Petar','Petrovic','petar', 'petar@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', false, false,'+38121763226',4,'',false,1);
insert into drivers (id, name, last_name, username, email, password, active, blocked, phone, photo_id, address, available,vehicle_id) values (nextval('SeqV1'),'Marko','Markovic','ma', 'marko@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', false, false,'+38121898926',5,'',false,2);
insert into drivers (id, name, last_name, username, email, password, active, blocked, phone, photo_id, address, available,vehicle_id) values (nextval('SeqV1'),'Zarko','Zarkovic','za', 'zarko@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', false, false,'+38121999226',6,'',false,3);
insert into drivers (id, name, last_name, username, email, password, active, blocked, phone, photo_id, address, available,vehicle_id) values (nextval('SeqV1'),'Jovan','Jovanovic','jo', 'jovan@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', false, false,'+38112363226',7,'',false,4);
insert into clients (id, name, last_name, username, email, password, activated, blocked, phone, photo_id, address) values (nextval('SeqV1'),'Mirko','Mirkovic', 'mirko', 'mirko@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', true,false,'+38121763225',8,'1');

--------------USERS-ROLES--------------
insert into user_role (user_id, role_id) values (1,1);
insert into user_role (user_id, role_id) values (2,3);
insert into user_role (user_id, role_id) values (3,2);
insert into user_role (user_id, role_id) values (4,2);
insert into user_role (user_id, role_id) values (5,2);
insert into user_role (user_id, role_id) values (6,2);
insert into user_role (user_id, role_id) values (7,1);

--------------ROUTE MARKER COORDINATES--------------
-- insert into route_coordinates (latitude,longitude, index) values ('45.24824','19.81674',0);
-- insert into route_coordinates (latitude,longitude, index) values ('45.24908','19.82474',1);
-- insert into route_coordinates (latitude,longitude, index) values ('45.25408','19.82453',0);
-- insert into route_coordinates (latitude,longitude, index) values ('45.25961','19.80893',0);
-- insert into route_coordinates (latitude,longitude, index) values ('45.25858','19.82329',0);

-- insert into route_part (start_id ,end_id,index ) values (8,9,1);
-- insert into route_part (start_id ,end_id,index ) values (9,10,0);
-- insert into route_part (start_id ,end_id,index ) values (11,12,0);





insert into route(length) values (4555.7);
insert into route(length) values (7597.9);
insert into route(length) values (4669.2);
insert into route(length) values (6740.0);
insert into route(length) values(101400.7);
insert into route(length) values(6180.2);

insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'),45.24399,  19.7875, 0, 1);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'),45.24217, 19.8284,1,1);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'),45.24498, 19.82887,2,1);

insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.28226,  19.83229,0,2);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'),45.2571, 19.84166,1,2);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.23271,  19.81296,2,2);

insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.23641,  19.80041,0,3);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.24145,  19.81114,1,3);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.25808,   19.82211,2,3);



insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.2607,  19.75934,0,4);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.24925,  19.79204,1,4);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.26542,   19.84827,2,4);




insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.24761,  19.7981,0,5);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 44.88841,   20.29203,1,5);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 44.79522,    20.47493,2,5);



insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.23997,  19.77275,0,6);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'),45.24106,   19.80712,1,6);
insert into route_coordinates(id, latitude, longitude, index, route_id) values (nextval('SeqV2'), 45.24327,    19.80621,2,6);


insert into ride (name,driver_id,baby_transport,comment,start,estimated_time,pet_transport,split_fare,ends,status,total,route_id) values ('A1, Аутопут за Загреб',4,true,'neki kom','2022-12-24T11:44:44.797','01:02:00.000',true,true,'2022-12-24T12:44:44.797',4,5400,5);
insert into ride (name,driver_id,baby_transport,comment,start,estimated_time,pet_transport,split_fare,ends,status,total,route_id) values ('Футошка, Цара Душана',3,true,'zavrsena voznja','2022-12-22T11:43:44.797','00:45:00.000',false,true,'2022-12-25T11:45:44.797',4,3000,2);
insert into ride (name,driver_id,baby_transport,comment,start,estimated_time,pet_transport,split_fare,ends,status,total,route_id) values ('Драгослава Срејовића, Булевар Јаше Томића',3,true,'neki kom','2022-12-24T11:14:44.797','00:20:00.000',true,true,'2022-12-24T11:24:44.797',4,5400,4);
insert into ride (name,driver_id,baby_transport,comment,start,estimated_time,pet_transport,split_fare,ends,status,total,route_id) values ('Булевар патријарха Павла, Мише Димитријевића',4,true,'neki kom','2022-12-24T11:50:44.797','00:20:00.000',true,true,'2022-12-24T11:54:44.797',4,2400,1);
insert into ride (name,driver_id,baby_transport,comment,start,estimated_time,pet_transport,split_fare,ends,status,total,route_id) values ('Футошка, Цара Душана',3,true,'neki kom','2022-12-24T11:50:44.797','00:20:00.000',true,true,'2022-12-24T11:54:44.797',4,2400,2);
insert into ride (name,driver_id,baby_transport,comment,start,estimated_time,pet_transport,split_fare,ends,status,total,route_id) values ('Новосадски пут, Булевар патријарха Павла',3,true,'neki kom','2022-12-28T11:50:44.797','00:20:00.000',true,true,'2022-12-28T11:54:44.797',4,2400,6);
insert into ride (name,driver_id,baby_transport,comment,start,estimated_time,pet_transport,split_fare,ends,status,total,route_id) values ('Вршачка, Булевар Европе',3,true,'neki kom','2022-12-29T11:50:44.797','00:20:00.000',true,true,'2022-12-29T11:54:44.797',4,2200,3);

-- insert into drivers_rides (driver_id,rides_id) values (4,1);
-- insert into drivers_rides (driver_id,rides_id) values (3,2);

insert into clients_rides(client_id, ride_id) values (1,1);
insert into clients_rides(client_id, ride_id) values (7,1);
insert into clients_rides(client_id, ride_id) values (1,2);
insert into clients_rides(client_id, ride_id) values (7,2);
insert into clients_rides(client_id, ride_id) values (1,3);
insert into clients_rides(client_id, ride_id) values (7,3);
insert into clients_rides(client_id, ride_id) values (1,4);
insert into clients_rides(client_id, ride_id) values (1,5);
insert into clients_rides(client_id, ride_id) values (7,5);
insert into clients_rides(client_id, ride_id) values (7,6);
insert into clients_rides(client_id, ride_id) values (7,7);



--------------USERS--------------
--insert into clients (id, name, last_name, username, email, password, activated, blocked, phone, photo_id, address) values (nextval('SeqV1'),'Marko','Markovic', 'marko', 'marko@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', true,false,'+38121763225',1,'Neka adresa');
--insert into drivers (id, name, last_name, username, email, password, active, blocked, phone, photo_id, address, available) values (nextval('SeqV1'),'Petar','Petrovic','petar', 'petar@gmail.com', '$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS', false, false,'+38121763226',3,'neka adresa',false);
--insert into admins (id, name, last_name, username, email, password, blocked,phone, photo_id, address) values (nextval('SeqV1'),'Admin','Adminovic','admin', 'admin@gmail.com','$2a$10$LFa7T.Gzp/OLv7fsZqpKZenC7tWoBMedGZNy3BC3UMz60XZnjIlYS',false,'+38121763221',2,'neka adresa');




------------REVIEWS-----------------
insert into review (comment,grade,client_id,ride_id) values ('ok voznja',4,1,1);
insert into review (comment,grade,client_id,ride_id) values ('not tu bed, samo da vozac zatvori prozor i upali klimu, jer zato klima i sluzi, a ne da je promaja :)',3,7,1);
insert into review (comment,grade,client_id,ride_id) values ('moze bolje, ali evo cisto da ostavim recenziju',2,7,2);
