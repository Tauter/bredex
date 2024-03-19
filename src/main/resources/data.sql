INSERT INTO user_account(user_name, email, password)
VALUES ('user', 'email@gmail.com', 'password'),
       ('user2', 'email2@gmail.com', 'password2');


INSERT INTO ad(user_account_id, brand, type, description, price)
VALUES (1, 'Skoda', 'Superb', '2.0 TDI', 1),
       (1, 'Skoda', 'Fabia', '1.0 TDI', 2),
       (1, 'Audi', 'A4', '1.9 PD TDI', 1),
       (1, 'Audi', 'A6', '2.0 PD TDI', 3),
       (1, 'Volvo', 'S60', '1.6', 4),
       (1, 'Volvo', 'S80', '1.8', 5),
       (1, 'Opel', 'Corsa', '1.4', 5),
       (2, 'Opel', 'Corsa', '1.4', 5);
