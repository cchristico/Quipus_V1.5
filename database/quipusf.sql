SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `quipus` DEFAULT CHARACTER SET utf8 ;
USE `quipus` ;

-- -----------------------------------------------------
-- Table `quipus`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `quipus`.`usuario` (
  `ID_USU` INT(11) NOT NULL ,
  `NOMBRESAPELLIDOS` VARCHAR(25) NOT NULL ,
  `CEDULA` VARCHAR(25) NOT NULL ,
  PRIMARY KEY (`ID_USU`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `quipus`.`factura`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `quipus`.`factura` (
  `IDFACTURA` INT(11) NOT NULL AUTO_INCREMENT ,
  `ID_USU` INT(11) NULL DEFAULT NULL ,
  `FECHAFACTURA` DATE NULL DEFAULT NULL ,
  `VALORFACTURA` CHAR(10) NULL DEFAULT NULL ,
  PRIMARY KEY (`IDFACTURA`) ,
  INDEX `FK_RELATIONSHIP_2` (`ID_USU` ASC) ,
  CONSTRAINT `FK_RELATIONSHIP_2`
    FOREIGN KEY (`ID_USU` )
    REFERENCES `quipus`.`usuario` (`ID_USU` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `quipus`.`rubro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `quipus`.`rubro` (
  `IDRUBROALCANZADO` INT(11) NOT NULL ,
  `NOMBRERUBRO` CHAR(25) NULL DEFAULT NULL ,
  `VALORACTUAL` CHAR(25) NULL DEFAULT NULL ,
  `CODIGORUBRO` CHAR(25) NULL DEFAULT NULL ,
  `VALORREFERENCIA` INT NOT NULL ,
  PRIMARY KEY (`IDRUBROALCANZADO`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `quipus`.`facturaegreso`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `quipus`.`facturaegreso` (
  `IDFACTURA` INT(11) NOT NULL ,
  `IDRUBROALCANZADO` INT(11) NULL DEFAULT NULL ,
  `ID_USU` INT(11) NULL DEFAULT NULL ,
  `FECHAFACTURA` DATE NULL DEFAULT NULL ,
  `VALORFACTURA` CHAR(10) NULL DEFAULT NULL ,
  PRIMARY KEY (`IDFACTURA`) ,
  INDEX `FK_PERTENCE` (`IDRUBROALCANZADO` ASC) ,
  CONSTRAINT `FK_INHERITANCE_1`
    FOREIGN KEY (`IDFACTURA` )
    REFERENCES `quipus`.`factura` (`IDFACTURA` ),
  CONSTRAINT `FK_PERTENCE`
    FOREIGN KEY (`IDRUBROALCANZADO` )
    REFERENCES `quipus`.`rubro` (`IDRUBROALCANZADO` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `quipus`.`facturaingreso`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `quipus`.`facturaingreso` (
  `IDFACTURA` INT(11) NOT NULL AUTO_INCREMENT ,
  `ID_USU` INT(11) NULL DEFAULT NULL ,
  `FECHAFACTURA` DATE NULL DEFAULT NULL ,
  `VALORFACTURA` CHAR(10) NULL DEFAULT NULL ,
  PRIMARY KEY (`IDFACTURA`) ,
  CONSTRAINT `FK_INHERITANCE_2`
    FOREIGN KEY (`IDFACTURA` )
    REFERENCES `quipus`.`factura` (`IDFACTURA` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
