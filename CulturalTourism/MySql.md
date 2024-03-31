
```mysql
create table cart
(
user_id      int null,
goods_id     int null,
goods_number int null,
is_check     int null
);

create table collection
(
user_id     int null,
heritage_id int null
);

create table goods
(
goods_id       int auto_increment
primary key,
goods_category varchar(10)  null,
goods_store    varchar(50)  null,
goods_name     varchar(10)  null,
goods_img      varchar(100) null,
goods_detail   text         null,
goods_price    float        null,
goods_location varchar(100) null
);

create table heritage
(
heritage_id       int auto_increment
primary key,
heritage_category varchar(30)  null,
heritage_name     varchar(30)  null,
heritage_city     varchar(30)  null,
heritage_address  varchar(30)  null,
heritage_detail   text         null,
heritage_img      varchar(100) null,
application_time  date         null
);

create table hotel
(
hotel_id       int auto_increment
primary key,
hotel_category varchar(30)  null,
hotel_name     varchar(30)  null,
hotel_city     varchar(30)  null,
hotel_address  varchar(30)  null,
hotel_detail   text         null,
hotel_price    float        null,
hotel_img      varchar(150) null,
hotel_count    float        null,
hotel_evaluate varchar(30)  null
);

create table museum
(
museum_id       int auto_increment
primary key,
museum_category varchar(30)  null,
museum_name     varchar(30)  null,
museum_city     varchar(30)  null,
museum_address  varchar(30)  null,
museum_detail   text         null,
museum_img      varchar(100) null,
museum_time     varchar(50)  null,
museum_evaluate varchar(30)  null
);

create table orders
(
orders_id     varchar(50) not null
primary key,
user_id       int         null,
address_id    int         null,
orders_money  float       null,
orders_date   datetime    null,
orders_statue varchar(10) null
);

create table orders_items
(
orders_id    bigint null,
goods_id     int    null,
goods_number int    null,
goods_total  float  null
);

create table scenery
(
scenery_id       int auto_increment
primary key,
scenery_category varchar(30)  null,
scenery_name     varchar(30)  null,
scenery_city     varchar(30)  null,
scenery_address  varchar(30)  null,
scenery_detail   text         null,
scenery_price    float        null,
scenery_img      varchar(150) null,
scenery_evaluate varchar(30)  null,
scenery_count    float        null
);

create table theater
(
theater_id       int auto_increment
primary key,
theater_category varchar(30)  null,
theater_name     varchar(30)  null,
theater_city     varchar(30)  null,
theater_address  varchar(30)  null,
theater_detail   text         null,
theater_price    float        null,
theater_img      varchar(100) null,
theater_time     varchar(50)  null,
theater_evaluate varchar(30)  null
);

create table user
(
user_id       int auto_increment
primary key,
user_account  varchar(12)  null,
user_password varchar(200) null,
user_phone    varchar(30)  null,
user_avatar   varchar(50)  null,
user_name     varchar(6)   null,
user_sex      varchar(2)   null,
user_age      int          null,
user_detail   varchar(50)  null,
user_role     varchar(5)   null
);

create table user_address
(
address_id     int auto_increment
primary key,
user_id        int         null,
name           varchar(20) null,
phone          varchar(20) null,
province       varchar(20) null,
city           varchar(20) null,
detail_address varchar(60) null,
weight         int         null
);
```


