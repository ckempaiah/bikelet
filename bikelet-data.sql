--sample user data
insert into bike_let_role(id,role_name) values(1,'ROLE_ADMIN');
insert into bike_let_role(id,role_name) values(2,'ROLE_TENANT');
insert into bike_let_role(id,role_name) values(3,'ROLE_USER');
insert into tenant(id,tenant_name, contact_id) values(1,'tenant1', 1);
insert into bike_let_user(id,user_name, email, first_name, last_name,password, tenant_id) values (1,'user1', 'abc@somdomain.com', 'first', 'last', 'user1', 1);
insert into bike_let_user(id,user_name, email, first_name, last_name,password, tenant_id) values (2,'user2', 'abc@somdomain.com', 'first', 'last', 'user2', 1);
insert into bike_let_user(id,user_name, email, first_name, last_name,password, tenant_id) values (3,'user3', 'abc@somdomain.com', 'first', 'last', 'user3', 1);
insert into user_role(role_id, user_id) values(1, 1);
insert into user_role(role_id, user_id) values(2, 2);
insert into user_role(role_id, user_id) values(3, 3);