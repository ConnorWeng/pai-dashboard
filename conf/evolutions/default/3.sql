# --- !Ups

CREATE TABLE IF NOT EXISTS `visitor_daily` (
  `visitor_daily_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_id` INT UNSIGNED NOT NULL,
  `app_name` VARCHAR(45) NOT NULL,
  `page_views` INT UNSIGNED NOT NULL,
  `sessions` INT UNSIGNED NOT NULL,
  `bounce_rate` INT UNSIGNED NOT NULL,
  `unique_visitors` INT UNSIGNED NOT NULL,
  `day_id` INT(8) UNSIGNED NOT NULL,
  PRIMARY KEY (`visitor_daily_id`),
  INDEX `visitor_daily_day_idx` (`day_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

# --- !Downs

DROP TABLE IF EXISTS `visitor_daily` ;
