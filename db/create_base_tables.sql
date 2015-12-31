create database if not exists pai;
use pai;

drop table if exists page_event;
create table page_event (
  page_event_id varchar(45) not null,
  app_id varchar(45) not null,
  machine_id varchar(45) not null,
  session_id varchar(45) not null,
  timestamp bigint not null,
  page varchar(255) not null,
  event_type varchar(45) not null,
  event_data varchar(5000) not null,
  module_name varchar(255) default '',
  day_id int not null
) engine=MyISAM default charset=utf8;

drop table if exists machine;
create table machine (
  machine_id varchar(45) not null,
  machine_name varchar(45),
  browser varchar(45),
  resolution varchar(45)
) engine=MyISAM default charset=utf8;

drop table if exists app;
create table app (
  app_id varchar(45) not null,
  app_url varchar(255) not null,
  department_id varchar(45) not null
) engine=MyISAM default charset=utf8;

drop table if exists department;
create table department (
  department_id varchar(45) not null,
  department_name varchar(255) not null,
  base_id varchar(45) not null
) engine=MyISAM default charset=utf8;

drop table if exists base;
create table base (
  base_id varchar(45) not null,
  base_name varchar(255) not null
) engine=MyISAM default charset=utf8;

drop table if exists day;
create table day (
  day_id int not null,
  day_name varchar(45) not null,
  day_num_in_week tinyint not null,
  weekday tinyint not null,
  holiday tinyint not null,
  week_id int not null,
  month_id int not null
) engine=MyISAM default charset=utf8;

drop table if exists week;
create table week (
  week_id int not null,
  week_num_in_year int not null,
  year int not null
) engine=MyISAM default charset=utf8;

drop table if exists month;
create table month (
  month_id int not null,
  month_name varchar(45) not null,
  month_num_in_year tinyint not null,
  year int not null
) engine=MyISAM default charset=utf8;
