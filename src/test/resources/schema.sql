drop table if exists `role`;

create table role
(
    id          int auto_increment comment '主键'
        primary key,
    name        varchar(20)  default '-1' not null comment '角色名',
    create_time datetime                  null comment '角色建立时间',
    menus       varchar(255) default ''   not null comment '有权限的页面',
    __v         int          default 0    not null comment '版本',
    auth_time   datetime                  null comment '授权时间',
    auth_name   varchar(20)  default ' '  not null comment '授权人'
)
    comment '角色表';

-- category
drop table if exists `category`;

create table category
(
    id            int auto_increment
        primary key,
    parent_id     varchar(30) null,
    name          varchar(20) null,
    category_name varchar(20) null,
    parent_name   varchar(20) null
);

-- products
drop table if exists `products`;

create table products
(
    id            int auto_increment
        primary key,
    images        text        null,
    status        int         null,
    id_str        varchar(40) null,
    name          varchar(40) null,
    `desc`        tinytext    null,
    price         varchar(20) null,
    p_Category_id varchar(30) null,
    category_Id   varchar(30) null,
    detail        text        null,
    __v           int         null
);

-- user
drop table if exists `user`;

create table user
(
    id          int auto_increment
        primary key,
    password    varchar(20) null,
    name        varchar(20) null,
    phone       varchar(20) null,
    email       varchar(30) null,
    role_id     varchar(30) null,
    create_time datetime     null,
    __v         int         null,
    constraint user_name_uindex
        unique (name)
);
