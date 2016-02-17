# --- !Ups

CREATE TABLE IF NOT EXISTS `archive_numeric` (
  `archive_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `app_id` INT UNSIGNED NOT NULL,
  `date1` DATE,
  `date2` DATE,
  `period` TINYINT(3) UNSIGNED,
  `ts_archived` DATETIME,
  `value` DOUBLE,
  PRIMARY KEY (`archive_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

# --- !Downs

DROP TABLE IF EXISTS `archive_numeric` ;
