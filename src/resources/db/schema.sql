CREATE TABLE IF NOT EXISTS `user` (
  `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100)          DEFAULT NULL,
  `last_name`  VARCHAR(100)          DEFAULT NULL,
  `email`      VARCHAR(100)          DEFAULT NULL,
  `login`      VARCHAR(100)          DEFAULT NULL,
  `password`   VARCHAR(100)          DEFAULT NULL,
  `date`       DATE                  DEFAULT NULL,
  `role`       ENUM('ADMIN', 'USER'),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE (login)
)
  ENGINE =INNODB
  DEFAULT CHARSET =UTF8
  COLLATE = UTF8_SWEDISH_CI;


CREATE TABLE IF NOT EXISTS `hall` (
  `id`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(100)          DEFAULT NULL,
  `row_count`    INT                   DEFAULT NULL,
  `column_count` INT                   DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE(name)
)
  ENGINE =INNODB
  DEFAULT CHARSET =UTF8
  COLLATE = UTF8_SWEDISH_CI;

CREATE TABLE IF NOT EXISTS `movie` (
  `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`       VARCHAR(100) NOT NULL,
  `description` VARCHAR(1000)         DEFAULT NULL,
  `duration`    BIGINT                DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE (title)
);


CREATE TABLE IF NOT EXISTS `session` (
  `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date`     TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  `hall_id`  INT UNSIGNED          DEFAULT NULL,
  `movie_id` INT UNSIGNED          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE INDEX `session_INDEX` (`date`, `hall_id`, `movie_id` ASC),
  CONSTRAINT `fk_session_1` FOREIGN KEY (`hall_id`)
  REFERENCES `hall` (`id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
  CONSTRAINT `fk_session_2` FOREIGN KEY (`movie_id`)
  REFERENCES `movie` (`id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS `ticket` (
  `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `row`        INT                   DEFAULT NULL,
  `place`      INT                   DEFAULT NULL,
  `session_id` INT UNSIGNED          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  CONSTRAINT `fk_ticket_1` FOREIGN KEY (`session_id`)
  REFERENCES `session` (`id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

INSERT IGNORE INTO user (first_name, last_name, email, login, password, date, role)
VALUES ('Ivan', 'Ivanov', 'ivan@ivan.com', 'admin', 'root', '2000-05-01', 'ADMIN');
INSERT IGNORE INTO user (first_name, last_name, email, login, password, date, role)
VALUES ('Petr', 'Petrov', 'petr@petr.com', 'petr', 'petr', '1999-04-04', 'USER');
INSERT IGNORE INTO movie (title, description, duration) VALUES ('Suicide Squad',
                                                                'A team of the world most dangerous, incarcerated super villains, are armed by the government and sent on a mission to defeat an enigmatic enemy. But they discover they werent picked to succeed but chosen for their culpability when the mission inevitably fails. Suicide Squad takes place several months after the events in Batman v Superman, and The Joker has already had many battles with Batman over the previous years.',
                                                                146);
INSERT IGNORE INTO movie (title, description, duration) VALUES ('The Jungle Book',
                                                                'Directed by Jon Favreau (Iron Man), based on Rudyard Kiplings timeless stories and inspired by Disneys classic animated film, The Jungle Book is an all-new live-action epic adventure about Mowgli (newcomer Neel Sethi), a man-cub who’s been raised by a family of wolves. But Mowgli finds he is no longer welcome in the jungle when fearsome tiger Shere Khan (voice of Idris Elba), who bears the scars of Man, promises to eliminate what he sees as a threat. Urged to abandon the only home hes ever known, Mowgli embarks on a captivating journey of self-discovery, guided by panther-turned-stern mentor Bagheera (voice of Ben Kingsley), and the free-spirited bear Baloo (voice of Bill Murray).',
                                                                120);
INSERT IGNORE INTO hall (name, row_count, column_count) VALUES ('Red', 10, 10);
INSERT IGNORE INTO hall (name, row_count, column_count) VALUES ('Green', 8, 8);
INSERT IGNORE INTO `session` (date, movie_id, hall_id) VALUES ('2016-05-01 14:10', 1, 1);
INSERT IGNORE INTO `session` (date, movie_id, hall_id) VALUES ('2016-05-02 15:00', 1, 2);
INSERT IGNORE INTO `session` (date, movie_id, hall_id) VALUES ('2016-05-01 10:30', 2, 2)

