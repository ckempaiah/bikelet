DROP TABLE IF EXISTS user_subscription_policy;

DROP TABLE IF EXISTS  user_role;

DROP TABLE IF EXISTS  tenant_license_policy;

DROP TABLE IF EXISTS  subscription_rate;

DROP TABLE IF EXISTS  role_permission;

DROP TABLE IF EXISTS  payment_transaction;

DROP TABLE IF EXISTS  bill_transaction;

DROP TABLE IF EXISTS  bike_location;

DROP TABLE IF EXISTS  address_association;

DROP TABLE IF EXISTS  station;

DROP TABLE IF EXISTS  subscription_policy;

DROP TABLE IF EXISTS  program;

DROP TABLE IF EXISTS  payment_info;

DROP TABLE IF EXISTS  organization;

DROP TABLE IF EXISTS  bill;

DROP TABLE IF EXISTS  address;

DROP TABLE IF EXISTS  permission;

DROP TABLE IF EXISTS  license_policy;

DROP TABLE IF EXISTS  rent_transaction;

DROP TABLE IF EXISTS  bike;

DROP TABLE IF EXISTS  bike_let_user;

DROP TABLE IF EXISTS  bike_let_role;

DROP TABLE IF EXISTS  tenant;

CREATE TABLE address
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   address_line1 varchar(100) NOT NULL,
   address_line2 varchar(100) NOT NULL,
   address_state varchar(255) NOT NULL,
   address_type varchar(15) NOT NULL,
   cellphone varchar(15) NOT NULL,
   city varchar(255) NOT NULL,
   country varchar(255) NOT NULL,
   version int,
   workphone varchar(15) NOT NULL,
   zip_code varchar(5) NOT NULL,
   entity_id int,
   entity_type int,
   user_id bigint
)
;
CREATE TABLE address_association
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   address_id bigint,
   entity_id int,
   entity_type int,
   version int
)
;
CREATE TABLE bike
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   bike_height int NOT NULL,
   bike_color varchar(20) NOT NULL,
   bike_type varchar(20) NOT NULL,
   last_service_date date,
   wheel_size varchar(50) NOT NULL,
   bike_status varchar(60) NOT NULL,
   tenant_id bigint,
   version int
)
;
CREATE TABLE bike_let_role
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   role_name varchar(255) NOT NULL,
   version int
)
;
CREATE TABLE bike_let_user
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   email varchar(40) NOT NULL,
   first_name varchar(30) NOT NULL,
   last_name varchar(30) NOT NULL,
   password varchar(15) NOT NULL,
   program_id bigint,
   tenant_id bigint NOT NULL,
   version int
)
;
CREATE TABLE bike_location
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   bike_status varchar(10),
   version int,
   station_id bigint,
   bike_id bigint
)
;
CREATE TABLE bill
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   bill_end_date timestamp,
   bill_start_date timestamp,
   created_date timestamp,
   description varchar(100),
   total_charges int,
   user_id bigint,
   version int
)
;
CREATE TABLE bill_transaction
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   description varchar(100),
   end_date timestamp,
   start_date timestamp,
   total_cost double,
   transaction_type varchar(100),
   bill_id bigint,
   version int
)
;
CREATE TABLE license_policy
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   free_trial_period_in_days int,
   license_base_cost double,
   license_cost_peruser double,
   license_name varchar(100),
   license_type int,
   version int
)
;
CREATE TABLE organization
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   org_name varchar(30),
   tenant_id bigint,
   contact_id bigint,
   version int
)
;
CREATE TABLE payment_info
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   card_number varchar(19) NOT NULL,
   card_user_name varchar(255) NOT NULL,
   user_id bigint null,
   payment_id bigint,
   version int
)
;
CREATE TABLE payment_transaction
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   date_of_transaction timestamp,
   description varchar(100),
   permission_name varchar(30),
   status int,
   user_id bigint,
   payment_id bigint,
   bill_id bigint,
   version int
)
;
CREATE TABLE permission
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   permission_name varchar(60),
   description varchar(255),
   version int
)
;
CREATE TABLE program
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   description varchar(255),
   program_name varchar(60),
   tenant_id bigint,
   org_id bigint,
   version int
)
;
CREATE TABLE rent_transaction
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   comments text NOT NULL,
   from_station_id int,
   rate_id int,
   user_id bigint NOT NULL,
   bike_id bigint,
   rent_end_date timestamp,
   rent_start_date timestamp,
   status varchar(10) NOT NULL,
   tenant_id bigint,
   to_station_id bigint,
   version int
)
;
CREATE TABLE role_permission
(
   id int PRIMARY KEY NOT NULL,
   permission_id bigint NOT NULL,
   role_id bigint NOT NULL
)
;
CREATE TABLE station
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   location varchar(255) NOT NULL,
   tenant_id bigint,
   program_id bigint,
   version int
)
;
CREATE TABLE subscription_policy
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   policy_description varchar(250),
   policy_name varchar(255) NOT NULL,
   program_id bigint,
   version int
)
;
CREATE TABLE subscription_rate
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   excess_charge_per_min double,
   free_mins_per_day int,
   membership_per_month double,
   organization_share double,
   policy_end_date timestamp,
   policy_start_date timestamp,
   user_share double,
   policy_id bigint,
   version int
)
;
CREATE TABLE tenant
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   tenant_name varchar(30),
   contact_id int,
   version int
)
;
CREATE TABLE tenant_license_policy
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   license_end_date timestamp,
   license_start_date timestamp,
   tenant_id bigint,
   license_id bigint,
   is_trial bit,
   version int
)
;
CREATE TABLE user_role
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   user_id bigint,
   role_id bigint,
   version int
)
;
CREATE TABLE user_subscription_policy
(
   id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
   user_id bigint,
   policy_id bigint,
   version int
)
;

ALTER TABLE payment_info ADD INDEX FKpayment_in938275 (user_id), ADD CONSTRAINT FKpayment_in938275 FOREIGN KEY (user_id) REFERENCES bike_let_user (id);
ALTER TABLE bike_let_user ADD INDEX FKuser330759 (tenant_id), ADD CONSTRAINT FKuser330759 FOREIGN KEY (tenant_id) REFERENCES tenant (id);
ALTER TABLE program ADD INDEX FKprogram318023 (tenant_id), ADD CONSTRAINT FKprogram318023 FOREIGN KEY (tenant_id) REFERENCES tenant (id);
ALTER TABLE bike ADD INDEX FKbike906225 (tenant_id), ADD CONSTRAINT FKbike906225 FOREIGN KEY (tenant_id) REFERENCES tenant (id);
ALTER TABLE station ADD INDEX FKstation903803 (tenant_id), ADD CONSTRAINT FKstation903803 FOREIGN KEY (tenant_id) REFERENCES tenant (id);
ALTER TABLE organization ADD INDEX FKorganizati993399 (tenant_id), ADD CONSTRAINT FKorganizati993399 FOREIGN KEY (tenant_id) REFERENCES tenant (id);
ALTER TABLE bike_location ADD INDEX FKbike_locat442826 (bike_id), ADD CONSTRAINT FKbike_locat442826 FOREIGN KEY (bike_id) REFERENCES bike (id);
ALTER TABLE bike_location ADD INDEX FKbike_locat282148 (station_id), ADD CONSTRAINT FKbike_locat282148 FOREIGN KEY (station_id) REFERENCES station (id);
ALTER TABLE user_role ADD INDEX FKuser_role807043 (user_id), ADD CONSTRAINT FKuser_role807043 FOREIGN KEY (user_id) REFERENCES bike_let_user (id);
ALTER TABLE rent_transaction ADD INDEX FKrent_trans366470 (user_id), ADD CONSTRAINT FKrent_trans366470 FOREIGN KEY (user_id) REFERENCES bike_let_user (id);
ALTER TABLE rent_transaction ADD INDEX FKrent_trans618708 (bike_id), ADD CONSTRAINT FKrent_trans618708 FOREIGN KEY (bike_id) REFERENCES bike (id);
ALTER TABLE user_role ADD INDEX FKuser_role220486 (role_id), ADD CONSTRAINT FKuser_role220486 FOREIGN KEY (role_id) REFERENCES bike_let_role (id);
ALTER TABLE subscription_policy ADD INDEX FKsubscripti157516 (program_id), ADD CONSTRAINT FKsubscripti157516 FOREIGN KEY (program_id) REFERENCES program (id);
ALTER TABLE role_permission ADD INDEX FKrole_permi451547 (role_id), ADD CONSTRAINT FKrole_permi451547 FOREIGN KEY (role_id) REFERENCES bike_let_role (id);
ALTER TABLE role_permission ADD INDEX FKrole_permi183089 (permission_id), ADD CONSTRAINT FKrole_permi183089 FOREIGN KEY (permission_id) REFERENCES permission (id);
ALTER TABLE tenant_license_policy ADD INDEX FKtenant_lic996616 (license_id), ADD CONSTRAINT FKtenant_lic996616 FOREIGN KEY (license_id) REFERENCES license_policy (id);
ALTER TABLE tenant_license_policy ADD INDEX FKtenant_lic646131 (tenant_id), ADD CONSTRAINT FKtenant_lic646131 FOREIGN KEY (tenant_id) REFERENCES tenant (id);
ALTER TABLE subscription_rate ADD INDEX FKsubscripti703864 (policy_id), ADD CONSTRAINT FKsubscripti703864 FOREIGN KEY (policy_id) REFERENCES subscription_policy (id);
ALTER TABLE bill_transaction ADD INDEX FKbill_trans447579 (bill_id), ADD CONSTRAINT FKbill_trans447579 FOREIGN KEY (bill_id) REFERENCES bill (id);
ALTER TABLE bill ADD INDEX FKbill642927 (user_id), ADD CONSTRAINT FKbill642927 FOREIGN KEY (user_id) REFERENCES bike_let_user (id);
ALTER TABLE address_association ADD INDEX FKaddress_as240280 (address_id), ADD CONSTRAINT FKaddress_as240280 FOREIGN KEY (address_id) REFERENCES address (id);
ALTER TABLE address ADD INDEX FKaddress360001 (user_id), ADD CONSTRAINT FKaddress360001 FOREIGN KEY (user_id) REFERENCES bike_let_user (id);
ALTER TABLE user_subscription_policy ADD INDEX FKuser_subsc833909 (user_id), ADD CONSTRAINT FKuser_subsc833909 FOREIGN KEY (user_id) REFERENCES bike_let_user (id);
ALTER TABLE user_subscription_policy ADD INDEX FKuser_subsc847371 (policy_id), ADD CONSTRAINT FKuser_subsc847371 FOREIGN KEY (policy_id) REFERENCES subscription_policy (id);
ALTER TABLE station ADD INDEX FKstation306681 (program_id), ADD CONSTRAINT FKstation306681 FOREIGN KEY (program_id) REFERENCES program (id);
ALTER TABLE payment_transaction ADD INDEX FKpayment_tr290075 (user_id), ADD CONSTRAINT FKpayment_tr290075 FOREIGN KEY (user_id) REFERENCES bike_let_user (id);
ALTER TABLE payment_transaction ADD INDEX FKpayment_tr494181 (payment_id), ADD CONSTRAINT FKpayment_tr494181 FOREIGN KEY (id) REFERENCES payment_info (id);
ALTER TABLE payment_transaction ADD INDEX FKpayment_tr669597 (bill_id), ADD CONSTRAINT FKpayment_tr669597 FOREIGN KEY (bill_id) REFERENCES bill (id);
ALTER TABLE program ADD INDEX FKprogram840536 (org_id), ADD CONSTRAINT FKprogram840536 FOREIGN KEY (org_id) REFERENCES organization (id);


