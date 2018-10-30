use demo;


insert into user_status
values (1, 'client'),
       (2, 'manager'),
       (3, 'admin');


insert into order_status
values (1, 'in basket'),
       (2, 'booked'),
       (3, 'payed'),
       (4, 'delivered'),
       (5, 'finished');


insert into camp_type
values (1, 'stationary'),
       (2, 'tent'),
       (3, 'day stay');

#пользователь с логино first и паролем qwerty123

insert into user
values (1, 'Main', 'first', '$2a$10$/1j4vr//s8o4ovCN64rMXesDIKhTs80jZs0KtufvmRzABT5BLTBZm', 0, 3);

#тестовые данные

insert into place
values (1, 'Place1', 'far far away', 'something', 0),
       (2, 'Place2', 'far', 'something', 1),
       (3, 'Not first Place', 'very near', 'something', 0),
       (4, 'Place not 1st', 'this', 'something', 0);

insert into camp (id, name, age_min, age_max, date_start, date_finish, children_count, removed,  type_id, place_id)
values (1, 'Camp1', 8, 12, '2017-10-10', '2017-10-19', 60, 0, 1, 1);

commit;