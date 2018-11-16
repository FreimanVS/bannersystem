INSERT INTO bannersystem.language (id, name) VALUES (1, 'russian'), (2, 'english');

INSERT INTO bannersystem.users (username, password, enabled) VALUES
	('admin', '{bcrypt}$2a$10$hB.sEjKFmoIixVehbywkYe8v0j5YoDrFpceuVq3sXe7o5n4XoJZhi', true),
    ('user', '{bcrypt}$2a$10$CU2GYhGhhE3dehjsDw5/PeyMzjpyEAxwZqMmxZ2hQzJLWu1jEHyrS', true),
    ('admin2', '{bcrypt}$2a$10$oPWNDolVe/TSODBh6TkkN.IGOSOzYHznreH8cSEdyEDeRZq/OhV52', true);

INSERT INTO bannersystem.authorities (username, authority) VALUES
	('admin', 'ROLE_ADMIN'),
	('admin2', 'ROLE_ADMIN'),
    ('user', 'ROLE_USER');

INSERT INTO bannersystem.banner (id, imgSrc, width, height, targetUrl, langId) VALUES
    (1, 'banners/banner-ru.png', 700, 250, 'https://www.google.ru', 1),
    (2, 'banners/banner-enUS.png', 700, 250, 'https://www.google.com', 2);