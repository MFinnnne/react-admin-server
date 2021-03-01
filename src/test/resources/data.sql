-- role
begin;
insert into `role`(`name`, create_time, menus, __v, auth_time, auth_name)
values ('mfine1', '2021-01-10 14:25:57', '', 0, '2021-01-10 14:25:57', '');
insert into `role`(`name`, create_time, menus, __v, auth_time, auth_name)
values ('mfine2', '2021-01-10 14:25:57', '', 0, '2021-01-10 14:25:57', '');
commit;

-- product
begin;
insert into products (images, `status`, id_str, `name`, `desc`, price, p_Category_id, category_Id, detail, __v)
values ('', 1, 'asdasdsa', 'mfine', 'dasdasdsa', '2888', '0', '22', '还行', 0);
insert into products (images, `status`, id_str, `name`, `desc`, price, p_Category_id, category_Id, detail, __v)
values ('', 1, 'asdasdasdsa', 'mfine1', 'dasdasddasdsa', '3888', '0', '22', '还行22', 0);
insert into products (images, `status`, id_str, `name`, `desc`, price, p_Category_id, category_Id, detail, __v)
values ('', 1, 'asdasdasdsa', 'mfine1', '一部手机而已', '3888', '0', '22', '一部手机而已', 0);
commit;

-- user
begin;
insert into `user` (`password`, `name`, phone, email, role_id, create_time, __v)
values ('21232f297a57a5a743894a0e4a801fc3', 'admin', '13584574374', 'lxemyf@gmail.com', '1', null, 0);
commit;