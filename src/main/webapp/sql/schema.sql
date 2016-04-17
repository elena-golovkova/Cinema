create table if not exists`user` (
`id` int unsigned NOT NULL AUTO_INCREMENT,
`first_name` varchar(100) DEFAULT NULL,
`last_name` varchar(100) DEFAULT NULL,
`email` varchar(100) DEFAULT NULL,
`login` varchar(100) DEFAULT NULL,
`password` varchar(100) DEFAULT NULL,
`date` date DEFAULT NULL,
`role` enum ('ADMIN','USER'),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`));

create table if not exists`hall` (
`id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `row_count` int DEFAULT NULL,
  `column_count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`));

  create table if not exists `movie` (
`id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `duration` BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`));

  create table if not exists `session` (
`id` int unsigned NOT NULL AUTO_INCREMENT,
`date` Timestamp DEFAULT CURRENT_TIMESTAMP,
`hall_id` int unsigned  DEFAULT NULL,
`movie_id` int unsigned  default NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  CONSTRAINT `fk_session_1` FOREIGN KEY (`hall_id`) REFERENCES `hall` (`id`)
  ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT `fk_session_2` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
  ON UPDATE CASCADE ON DELETE SET NULL
);

  create table if not exists `ticket` (
`id` int unsigned NOT NULL AUTO_INCREMENT,
`row` INT DEFAULT NULL,
`place` INT DEFAULT NULL,
`session_id`  int unsigned DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `id` (`id`),
CONSTRAINT `fk_ticket_1` FOREIGN KEY (`session_id`) REFERENCES `session` (`id`)
ON UPDATE CASCADE
on delete set null)
