-- MySQL Script generated by MySQL Workbench
-- Tue Aug 29 16:13:05 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema course_management
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `course_management` ;

-- -----------------------------------------------------
-- Schema course_management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `course_management` DEFAULT CHARACTER SET utf8 ;
USE `course_management` ;

-- -----------------------------------------------------
-- Table `course_management`.`Teacher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `course_management`.`Teacher` ;

CREATE TABLE IF NOT EXISTS `course_management`.`Teacher` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `designation` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `course_management`.`Course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `course_management`.`Course` ;

CREATE TABLE IF NOT EXISTS `course_management`.`Course` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `credits` INT NOT NULL,
  `Teacher_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Course_Teacher_idx` (`Teacher_id` ASC),
  CONSTRAINT `fk_Course_Teacher`
    FOREIGN KEY (`Teacher_id`)
    REFERENCES `course_management`.`Teacher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `course_management`.`Student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `course_management`.`Student` ;

CREATE TABLE IF NOT EXISTS `course_management`.`Student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `enrolled_since` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `course_management`.`Course_Student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `course_management`.`Course_Student` ;

CREATE TABLE IF NOT EXISTS `course_management`.`Course_Student` (
  `Course_id` INT NOT NULL,
  `Student_id` INT NOT NULL,
  PRIMARY KEY (`Course_id`, `Student_id`),
  INDEX `fk_Course_has_Student_Student1_idx` (`Student_id` ASC),
  INDEX `fk_Course_has_Student_Course1_idx` (`Course_id` ASC),
  CONSTRAINT `fk_Course_has_Student_Course1`
    FOREIGN KEY (`Course_id`)
    REFERENCES `course_management`.`Course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Course_has_Student_Student1`
    FOREIGN KEY (`Student_id`)
    REFERENCES `course_management`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
