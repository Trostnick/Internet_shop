use demo;


insert into user_status
values (1, 'ROLE_client'),
       (2, 'ROLE_manager'),
       (3, 'ROLE_admin');


insert into order_status
values (1, 'in cart'),
       (2, 'booked'),
       (3, 'payed'),
       (4, 'delivered'),
       (5, 'finished');


insert into camp_type
values (1, 'stationary'),
       (2, 'tent'),
       (3, 'day stay');

#пользователь(администратор) с логином first и паролем qwerty123
#пользователь(продавец) с логином manager и паролем qwerty123
#пользователь(покупатель) с логином user и паролем qwerty123

insert into user
values (1, 'Main', 'first', '$2a$10$/1j4vr//s8o4ovCN64rMXesDIKhTs80jZs0KtufvmRzABT5BLTBZm', 0, 3),
       (2, 'Manager', 'manager', '$2a$10$/1j4vr//s8o4ovCN64rMXesDIKhTs80jZs0KtufvmRzABT5BLTBZm', 0, 2),
       (3, 'User', 'user', '$2a$10$/1j4vr//s8o4ovCN64rMXesDIKhTs80jZs0KtufvmRzABT5BLTBZm', 0, 1);

#тестовые данные

insert into place
values (1, 'Place1', 'far far away', 'something', 0),
       (2, 'Place2', 'far', 'something', 1),
       (3, 'Not first Place', 'very near', 'something', 0),
       (4, 'Place not 1st', 'this', 'something', 0);


commit;