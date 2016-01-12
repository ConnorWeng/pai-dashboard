# --- !Ups

CREATE TABLE IF NOT EXISTS `base` (
  `base_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `base_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`base_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `department` (
  `department_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `department_name` VARCHAR(45) NOT NULL,
  `base_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`department_id`),
  INDEX `base_id_idx` (`base_id` ASC),
  CONSTRAINT `department_base_fk`
    FOREIGN KEY (`base_id`)
    REFERENCES `base` (`base_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `app` (
  `app_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_name` VARCHAR(45) NOT NULL,
  `app_url` VARCHAR(255) NOT NULL,
  `department_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`app_id`),
  INDEX `department_id_idx` (`department_id` ASC),
  CONSTRAINT `app_department_fk`
    FOREIGN KEY (`department_id`)
    REFERENCES `department` (`department_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `week` (
  `week_id` INT(6) UNSIGNED NOT NULL,
  `week_num_in_year` TINYINT(2) UNSIGNED NOT NULL,
  `year` SMALLINT(4) UNSIGNED NOT NULL,
  PRIMARY KEY (`week_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `month` (
  `month_id` INT(6) UNSIGNED NOT NULL,
  `month_name` VARCHAR(45) NOT NULL,
  `month_num_in_year` TINYINT(2) NOT NULL,
  `year` SMALLINT(4) UNSIGNED NOT NULL,
  PRIMARY KEY (`month_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `day` (
  `day_id` INT(8) UNSIGNED NOT NULL,
  `day_name` VARCHAR(10) NOT NULL,
  `day_num_in_week` TINYINT(1) NOT NULL,
  `weekday` TINYINT(1) NOT NULL,
  `holiday` TINYINT(1) NOT NULL,
  `week_id` INT(6) UNSIGNED NOT NULL,
  `month_id` INT(6) UNSIGNED NOT NULL,
  PRIMARY KEY (`day_id`),
  INDEX `month_id_idx` (`month_id` ASC),
  INDEX `week_id_idx` (`week_id` ASC),
  CONSTRAINT `day_week_fk`
    FOREIGN KEY (`week_id`)
    REFERENCES `week` (`week_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `day_month_fk`
    FOREIGN KEY (`month_id`)
    REFERENCES `month` (`month_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `machine` (
  `machine_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `machine_code` VARCHAR(45) NOT NULL,
  `machine_name` VARCHAR(45) NULL DEFAULT NULL,
  `browser` VARCHAR(45) NULL DEFAULT NULL,
  `resolution` VARCHAR(45) NULL DEFAULT NULL,
  `first_day_id` INT(8) UNSIGNED NOT NULL,
  `last_day_id` INT(8) UNSIGNED NOT NULL,
  PRIMARY KEY (`machine_id`),
  INDEX `first_day_id_idx` (`first_day_id` ASC),
  INDEX `last_day_id_idx` (`last_day_id` ASC),
  CONSTRAINT `machine_first_day_fk`
    FOREIGN KEY (`first_day_id`)
    REFERENCES `day` (`day_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `machine_last_day_fk`
    FOREIGN KEY (`last_day_id`)
    REFERENCES `day` (`day_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `module` (
  `module_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `module_name` VARCHAR(45) NOT NULL,
  `module_url` VARCHAR(2038) NOT NULL,
  `app_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`module_id`),
  INDEX `app_id_idx` (`app_id` ASC),
  CONSTRAINT `module_app_fk`
    FOREIGN KEY (`app_id`)
    REFERENCES `app` (`app_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `page` (
  `page_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `page_url` VARCHAR(2038) NOT NULL,
  `app_id` INT UNSIGNED NOT NULL,
  `module_id` INT UNSIGNED NULL,
  PRIMARY KEY (`page_id`),
  INDEX `page_app_idx` (`app_id` ASC),
  INDEX `page_module_idx` (`module_id` ASC),
  CONSTRAINT `page_app_fk`
    FOREIGN KEY (`app_id`)
    REFERENCES `app` (`app_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `page_module_fk`
    FOREIGN KEY (`module_id`)
    REFERENCES `module` (`module_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `page_event` (
  `page_event_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `machine_id` INT UNSIGNED NOT NULL,
  `session_code` VARCHAR(45) NOT NULL,
  `timestamp` BIGINT(13) UNSIGNED NOT NULL,
  `page_id` INT UNSIGNED NOT NULL,
  `event_type` VARCHAR(45) NOT NULL,
  `event_data` VARCHAR(5000) NOT NULL,
  `day_id` INT(8) UNSIGNED NOT NULL,
  PRIMARY KEY (`page_event_id`),
  INDEX `machine_id_idx` (`machine_id` ASC),
  INDEX `day_id_idx` (`day_id` ASC),
  INDEX `event_page_idx` (`page_id` ASC),
  CONSTRAINT `event_machine_fk`
    FOREIGN KEY (`machine_id`)
    REFERENCES `machine` (`machine_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `event_day_fk`
    FOREIGN KEY (`day_id`)
    REFERENCES `day` (`day_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `event_page_fk`
    FOREIGN KEY (`page_id`)
    REFERENCES `page` (`page_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

# --- !Downs

DROP TABLE IF EXISTS `base` ;

DROP TABLE IF EXISTS `department` ;

DROP TABLE IF EXISTS `app` ;

DROP TABLE IF EXISTS `week` ;

DROP TABLE IF EXISTS `month` ;

DROP TABLE IF EXISTS `day` ;

DROP TABLE IF EXISTS `machine` ;

DROP TABLE IF EXISTS `module` ;

DROP TABLE IF EXISTS `page` ;

DROP TABLE IF EXISTS `page_event` ;
