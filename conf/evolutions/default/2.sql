# --- !Ups

CREATE TABLE IF NOT EXISTS `module_daily` (
  `module_daily_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_id` INT UNSIGNED NOT NULL,
  `app_name` VARCHAR(45) NOT NULL,
  `module_id` INT UNSIGNED NOT NULL,
  `module_name` VARCHAR(45) NOT NULL,
  `module_view` INT UNSIGNED NOT NULL,
  `duration` BIGINT(13) UNSIGNED NOT NULL,
  `day_id` INT(8) UNSIGNED NOT NULL,
  PRIMARY KEY (`module_daily_id`),
  INDEX `module_daily_day_idx` (`day_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `module_machine_daily` (
  `module_daily_id` INT UNSIGNED NOT NULL,
  `machine_id` INT UNSIGNED NOT NULL,
  `machine_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`module_daily_id`, `machine_id`),
  CONSTRAINT `module_machine_daily_module_daily_fk`
    FOREIGN KEY (`module_daily_id`)
    REFERENCES `module_daily` (`module_daily_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

# --- !Downs

DROP TABLE IF EXISTS `module_daily` ;

DROP TABLE IF EXISTS `module_machine_daily` ;
