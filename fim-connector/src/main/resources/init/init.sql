

drop database if exists fim;
create database fim;
use fim;

drop table if exists t_user;

create table t_user
(
  id                   varchar(32) not null comment '用户编号',
  username             varchar(60) not null comment '用户名称',
  password             varchar(32) not null comment '密码',
  sex                  int(11) not null default 1 comment '性别，字典项（0-女， 1-男）',
  age                  int(11) not null comment '备注',
  role                 int(11) not null comment '备注',
  UNIQUE KEY (username),
  primary key (id)
);