
select table_name from user_tables;
--book表
--列名  数据类型  可否为空  说明
--book_id int not null  书籍编号，自增长
--book_name varchar not null  书籍名称
--book_count  int not null  借出次数
--status  int not null  书籍状态（0，已借出，1，可借）

create table libary_tab_book(
    book_id number(6) primary key,--书籍编号 主键 自增长
    book_name varchar2(40) not null,--书名 非空
    book_count number(3) not null,--借出次数 非空
    book_status number(1) not null check(book_status in (0,1))
    --书籍状态 非空 并且只能为0借出 或者 1可借两种状态 
);
create sequence seq_libary_book_id;--这个序列为book_id赋值 默认从1开始



--user表
--列名  数据类型  可否为空  说明
--user_id int not null  用户编号，自增长
--user_name varchar not null  用户名，唯一
--user_password varchar not null  用户密码
--type  int not null  用户类型，1，普通用户，2，管理员

create table libary_tab_user(
   user_id number(6) primary key,--主键
   user_name varchar2(40) not null unique,--用户名唯一
   user_password varchar2(40) not null,
   user_type number(1) not null check(user_type in (1,2))
);
create sequence seq_libary_user_id;


--Record 表
--列名  数据类型  可否为空  说明
--record_id int not null  记录编号，自增长
--user_id int not null  借书人的编号，外键
--book_id int not null  书籍编号，外键
--lend_time date  not null  借出时间
--return_time date    归还时间
create table libary_tab_record(
   record_id number(6) primary key,
   user_id number(6) not null,
   book_id number(6) not null,
   lend_time date not null,
   return_time date 
);

create sequence seq_libary_record_id;





alter table libary_tab_record
  add constraint FK_USER_ID foreign key (user_id)
  references libary_tab_user(user_id);
   
alter table libary_tab_record
add constraint FK_BOOK_ID foreign key (book_id)
references libary_tab_book(book_id);


select * from libary_tab_book;
select * from libary_tab_user;
select * from libary_tab_record;
select book_id,book_name,book_count,book_status from libary_tab_book where book_status=0

insert into libary_tab_user(user_id,user_name,user_password,user_type)
 values((select max(user_id) from libary_tab_user)+1,'157','123456',1);


select seq_libary_record_id.nextval from dual;



