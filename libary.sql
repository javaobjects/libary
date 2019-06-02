prompt PL/SQL Developer Export Tables for user SCOTT@ORCL
prompt Created by yanbo on 2019年5月30日
set feedback off
set define off

prompt Creating TAB_BOOK...
create table TAB_BOOK
(
  book_id     NUMBER(6) not null,
  book_name   VARCHAR2(40) not null,
  book_count  NUMBER(3) not null,
  book_status NUMBER(1) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TAB_BOOK
  add primary key (BOOK_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TAB_BOOK
  add check (book_status in (0,1));

prompt Creating TAB_USER...
create table TAB_USER
(
  user_id       NUMBER(6) not null,
  user_name     VARCHAR2(40) not null,
  user_password VARCHAR2(40) not null,
  user_type     NUMBER(1) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TAB_USER
  add primary key (USER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TAB_USER
  add unique (USER_NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TAB_USER
  add check (user_type in (1,2));

prompt Creating TAB_RECORD...
create table TAB_RECORD
(
  record_id   NUMBER(6) not null,
  user_id     NUMBER(6) not null,
  book_id     NUMBER(6) not null,
  lend_time   DATE not null,
  return_time DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TAB_RECORD
  add primary key (RECORD_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table TAB_RECORD
  add constraint FK_BOOK_ID foreign key (BOOK_ID)
  references TAB_BOOK (BOOK_ID);
alter table TAB_RECORD
  add constraint FK_USER_ID foreign key (USER_ID)
  references TAB_USER (USER_ID);

prompt Disabling triggers for TAB_BOOK...
alter table TAB_BOOK disable all triggers;
prompt Disabling triggers for TAB_USER...
alter table TAB_USER disable all triggers;
prompt Disabling triggers for TAB_RECORD...
alter table TAB_RECORD disable all triggers;
prompt Disabling foreign key constraints for TAB_RECORD...
alter table TAB_RECORD disable constraint FK_BOOK_ID;
alter table TAB_RECORD disable constraint FK_USER_ID;
prompt Deleting TAB_RECORD...
delete from TAB_RECORD;
commit;
prompt Deleting TAB_USER...
delete from TAB_USER;
commit;
prompt Deleting TAB_BOOK...
delete from TAB_BOOK;
commit;
prompt Loading TAB_BOOK...
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (2, 'JS高级', 2, 1);
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (3, 'PHP入门', 5, 1);
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (4, 'Oracle基础', 1, 0);
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (5, 'vue入门', 3, 1);
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (6, '计算机基础', 5, 0);
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (7, '网络 基础', 6, 1);
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (8, 'xshell', 2, 1);
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (9, 'git入门', 6, 1);
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (10, 'webpack配置', 9, 1);
insert into TAB_BOOK (book_id, book_name, book_count, book_status)
values (1, 'Java基础', 3, 0);
commit;
prompt 10 records loaded
prompt Loading TAB_USER...
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (11, '157', 'y123456', 2);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (1, '1234', '123456', 1);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (2, '333', '123456', 2);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (3, '444', '123456', 1);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (4, '555', '123456', 1);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (5, '666', '123456', 2);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (6, '777', '123456', 1);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (7, '888', '123456', 2);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (8, '999', '123456', 1);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (9, '222', '123456', 2);
insert into TAB_USER (user_id, user_name, user_password, user_type)
values (10, '111', '123456', 1);
commit;
prompt 11 records loaded
prompt Loading TAB_RECORD...
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (1, 1, 1, to_date('01-05-2019', 'dd-mm-yyyy'), null);
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (2, 2, 2, to_date('01-05-2019', 'dd-mm-yyyy'), to_date('28-05-2019 15:19:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (3, 3, 3, to_date('06-05-2019', 'dd-mm-yyyy'), to_date('24-05-2019', 'dd-mm-yyyy'));
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (4, 4, 4, to_date('05-05-2019', 'dd-mm-yyyy'), null);
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (5, 5, 5, to_date('01-05-2019', 'dd-mm-yyyy'), to_date('24-05-2019', 'dd-mm-yyyy'));
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (6, 6, 6, to_date('01-05-2019', 'dd-mm-yyyy'), null);
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (7, 7, 7, to_date('01-05-2019', 'dd-mm-yyyy'), to_date('24-05-2019', 'dd-mm-yyyy'));
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (8, 8, 8, to_date('01-05-2019', 'dd-mm-yyyy'), null);
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (9, 9, 9, to_date('01-05-2019', 'dd-mm-yyyy'), to_date('24-05-2019', 'dd-mm-yyyy'));
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (10, 10, 10, to_date('01-05-2019', 'dd-mm-yyyy'), null);
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (12, 2, 7, to_date('27-05-2019 17:20:22', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (13, 2, 7, to_date('27-05-2019 23:08:27', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-05-2019 15:54:54', 'dd-mm-yyyy hh24:mi:ss'));
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (16, 2, 9, to_date('28-05-2019 15:18:45', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-05-2019 15:54:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (17, 2, 10, to_date('28-05-2019 15:42:01', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-05-2019 15:54:34', 'dd-mm-yyyy hh24:mi:ss'));
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (18, 2, 8, to_date('28-05-2019 15:42:04', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-05-2019 15:54:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into TAB_RECORD (record_id, user_id, book_id, lend_time, return_time)
values (11, 2, 9, to_date('27-05-2019 14:52:36', 'dd-mm-yyyy hh24:mi:ss'), to_date('28-05-2019 15:04:19', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 16 records loaded
prompt Enabling foreign key constraints for TAB_RECORD...
alter table TAB_RECORD enable constraint FK_BOOK_ID;
alter table TAB_RECORD enable constraint FK_USER_ID;
prompt Enabling triggers for TAB_BOOK...
alter table TAB_BOOK enable all triggers;
prompt Enabling triggers for TAB_USER...
alter table TAB_USER enable all triggers;
prompt Enabling triggers for TAB_RECORD...
alter table TAB_RECORD enable all triggers;

set feedback on
set define on
prompt Done
