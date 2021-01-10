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


