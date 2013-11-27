# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contact (
  id                        bigint auto_increment not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  digits                    varchar(255),
  telephone_type            varchar(255),
  user_info_email           varchar(255),
  constraint pk_contact primary key (id))
;

create table user_info (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  is_admin                  tinyint(1) default 0,
  constraint pk_user_info primary key (email))
;

alter table contact add constraint fk_contact_userInfo_1 foreign key (user_info_email) references user_info (email) on delete restrict on update restrict;
create index ix_contact_userInfo_1 on contact (user_info_email);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table contact;

drop table user_info;

SET FOREIGN_KEY_CHECKS=1;

