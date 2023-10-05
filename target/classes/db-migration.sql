create table public.user(
    userId serial primary key not null,
    userName  varchar(100) not null);

create table public.phone(
    phoneId serial primary key not null,
    user_id int,
    foreign key (user_id) references public.user(UserId) on delete cascade,
    phoneNumber varchar(100) not null);

create table public.role(
    roleId serial primary key not null,
    roleName varchar(100) not null);

create table public.userRole(
    user_id int,
    role_id int,
    foreign key (user_id) references public.user(userId) on delete cascade,
    foreign key (role_id) references public.role(roleId) on delete cascade);

insert into public.role (roleName) VALUES ('Админ');
insert into public.role (roleName) VALUES ('Клиент');

insert into public.user(UserName) values ('Коля');
insert into public.user(UserName) values ('Катя');
insert into public.user(UserName) values ('Виталик');

insert into public.phone (user_id, phonenumber) values (1, '7894234');
insert into public.phone (user_id, phonenumber) values (1, '5779757');
insert into public.phone (user_id, phonenumber) values (2, '124576');
insert into public.phone (user_id, phonenumber) values (3, '273874');

insert into public.userrole (user_id, role_id) VALUES (1, 1);
insert into public.userrole (user_id, role_id) VALUES (1, 2);
insert into public.userrole (user_id, role_id) VALUES (2, 1);
insert into public.userrole (user_id, role_id) VALUES (3, 2);