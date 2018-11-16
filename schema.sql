DROP SCHEMA IF EXISTS bannersystem;
CREATE SCHEMA IF NOT EXISTS bannersystem DEFAULT CHARACTER SET utf8 ;
USE bannersystem;

DROP TABLE IF EXISTS bannersystem.language;
CREATE TABLE IF NOT EXISTS bannersystem.language (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS bannersystem.banner;
CREATE TABLE IF NOT EXISTS bannersystem.banner (
  id BIGINT NOT NULL AUTO_INCREMENT,
  imgSrc VARCHAR(500) NULL,
  width INT NULL,
  height INT NULL,
  targetUrl VARCHAR(500) NULL,
  langId INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT banner_language FOREIGN KEY (langId) REFERENCES bannersystem.language (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS bannersystem.users;
CREATE TABLE `users` (
  username varchar(256) NOT NULL UNIQUE,
  password varchar(256) NOT NULL,
  enabled tinyint(1) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS bannersystem.authorities;
CREATE TABLE IF NOT EXISTS bannersystem.authorities (
  username VARCHAR(256) NOT NULL,
  authority VARCHAR(256) NOT NULL,
  UNIQUE KEY `authorities` (username, authority),
  CONSTRAINT `authorities_username` FOREIGN KEY (`username`) REFERENCES bannersystem.users (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS bannersystem.audit;
CREATE TABLE IF NOT EXISTS bannersystem.audit (
  banner_id BIGINT NOT NULL,
  action varchar(6) NOT NULL,
  time DATETIME NOT NULL,
  username VARCHAR(256) NOT NULL,
  CONSTRAINT audit_username FOREIGN KEY (username) REFERENCES bannersystem.users (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;