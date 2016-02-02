SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `gestorcobranza` DEFAULT CHARACTER SET utf8 ;
USE `gestorcobranza` ;

-- -----------------------------------------------------
-- Table `gestorcobranza`.`usuario_unidad_negocio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestorcobranza`.`usuario_unidad_negocio` (
  `id_Usuario_unidad_negocio` INT(11) NOT NULL AUTO_INCREMENT ,
  `id_usuario` INT(11) NOT NULL ,
  `id_unidad_negocio` INT(11) NULL DEFAULT NULL ,
  `activo` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id_Usuario_unidad_negocio`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestorcobranza`.`cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestorcobranza`.`cliente` (
  `id_Cliente` INT(11) NOT NULL AUTO_INCREMENT ,
  `cedula_ruc` VARCHAR(13) NOT NULL ,
  `cod_cliente` VARCHAR(8) NOT NULL ,
  `telefono1` VARCHAR(10) NOT NULL ,
  `telefono2` VARCHAR(10) NOT NULL ,
  `direccion` VARCHAR(45) NOT NULL ,
  `registro_activo` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id_Cliente`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestorcobranza`.`mora`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestorcobranza`.`mora` (
  `id_Mora` INT(11) NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(150) NOT NULL ,
  `nro_factura` INT(11) NOT NULL ,
  `fecha_facturacion` DATE NOT NULL ,
  `plazo` VARCHAR(45) NOT NULL ,
  `dias_en_mora` INT(11) NOT NULL ,
  `saldo_total_de_factura` DOUBLE NOT NULL ,
  `Usuario_unidad_negocio_id_Usuario_unidad_negocio` INT(11) NOT NULL ,
  `Cliente_id_Cliente` INT(11) NOT NULL ,
  PRIMARY KEY (`id_Mora`, `Usuario_unidad_negocio_id_Usuario_unidad_negocio`, `Cliente_id_Cliente`) ,
  INDEX `fk_Mora_Usuario_unidad_negocio1` (`Usuario_unidad_negocio_id_Usuario_unidad_negocio` ASC) ,
  INDEX `fk_Mora_Cliente1` (`Cliente_id_Cliente` ASC) ,
  CONSTRAINT `fk_Mora_Usuario_unidad_negocio1`
    FOREIGN KEY (`Usuario_unidad_negocio_id_Usuario_unidad_negocio` )
    REFERENCES `gestorcobranza`.`usuario_unidad_negocio` (`id_Usuario_unidad_negocio` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mora_Cliente1`
    FOREIGN KEY (`Cliente_id_Cliente` )
    REFERENCES `gestorcobranza`.`cliente` (`id_Cliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestorcobranza`.`forma_pago`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestorcobranza`.`forma_pago` (
  `id_Forma_Pago` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `registro_activo` TINYINT(1) NOT NULL ,
  `Forma_Pagocol1` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_Forma_Pago`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestorcobranza`.`abono_deuda`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestorcobranza`.`abono_deuda` (
  `id_Abono_Deuda` INT(11) NOT NULL AUTO_INCREMENT ,
  `fecha_abono` DATE NOT NULL ,
  `abono` DOUBLE NOT NULL ,
  `Mora_id_Mora` INT(11) NOT NULL ,
  `Mora_Usuario_unidad_negocio_id_Usuario_unidad_negocio` INT(11) NOT NULL ,
  `Mora_Cliente_id_Cliente` INT(11) NOT NULL ,
  `Forma_Pago_id_Forma_Pago` INT(11) NOT NULL ,
  PRIMARY KEY (`id_Abono_Deuda`, `Mora_id_Mora`, `Mora_Usuario_unidad_negocio_id_Usuario_unidad_negocio`, `Mora_Cliente_id_Cliente`, `Forma_Pago_id_Forma_Pago`) ,
  INDEX `fk_Abono_Deuda_Mora1` (`Mora_id_Mora` ASC, `Mora_Usuario_unidad_negocio_id_Usuario_unidad_negocio` ASC, `Mora_Cliente_id_Cliente` ASC) ,
  INDEX `fk_Abono_Deuda_Forma_Pago1` (`Forma_Pago_id_Forma_Pago` ASC) ,
  CONSTRAINT `fk_Abono_Deuda_Mora1`
    FOREIGN KEY (`Mora_id_Mora` , `Mora_Usuario_unidad_negocio_id_Usuario_unidad_negocio` , `Mora_Cliente_id_Cliente` )
    REFERENCES `gestorcobranza`.`mora` (`id_Mora` , `Usuario_unidad_negocio_id_Usuario_unidad_negocio` , `Cliente_id_Cliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Abono_Deuda_Forma_Pago1`
    FOREIGN KEY (`Forma_Pago_id_Forma_Pago` )
    REFERENCES `gestorcobranza`.`forma_pago` (`id_Forma_Pago` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestorcobranza`.`tipo_gestion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestorcobranza`.`tipo_gestion` (
  `id_Tipo_Gestion` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `registro_activo` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id_Tipo_Gestion`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestorcobranza`.`resultado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestorcobranza`.`resultado` (
  `id_Resultado` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `registro_activo` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id_Resultado`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestorcobranza`.`gestion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestorcobranza`.`gestion` (
  `id_Gestion` INT(11) NOT NULL AUTO_INCREMENT ,
  `fecha_gestion` DATE NOT NULL ,
  `observaciones` VARCHAR(80) NOT NULL ,
  `Tipo_Gestion_id_Tipo_Gestion` INT(11) NOT NULL ,
  `Mora_id_Mora` INT(11) NOT NULL ,
  `Resultado_id_Resultado` INT(11) NOT NULL ,
  PRIMARY KEY (`id_Gestion`, `Mora_id_Mora`, `Resultado_id_Resultado`) ,
  INDEX `fk_Gestion_Tipo_Gestion1` (`Tipo_Gestion_id_Tipo_Gestion` ASC) ,
  INDEX `fk_Gestion_Mora1` (`Mora_id_Mora` ASC) ,
  INDEX `fk_Gestion_Resultado1` (`Resultado_id_Resultado` ASC) ,
  CONSTRAINT `fk_Gestion_Tipo_Gestion1`
    FOREIGN KEY (`Tipo_Gestion_id_Tipo_Gestion` )
    REFERENCES `gestorcobranza`.`tipo_gestion` (`id_Tipo_Gestion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Gestion_Mora1`
    FOREIGN KEY (`Mora_id_Mora` )
    REFERENCES `gestorcobranza`.`mora` (`id_Mora` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Gestion_Resultado1`
    FOREIGN KEY (`Resultado_id_Resultado` )
    REFERENCES `gestorcobranza`.`resultado` (`id_Resultado` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestorcobranza`.`respuesta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `gestorcobranza`.`respuesta` (
  `id_Respuesta` INT(11) NOT NULL AUTO_INCREMENT ,
  `respuesta_cliente` VARCHAR(45) NULL DEFAULT NULL ,
  `fecha_compromiso_pago` DATE NULL DEFAULT NULL ,
  `fecha_respuesta` DATE NULL DEFAULT NULL ,
  `Gestion_id_Gestion` INT(11) NOT NULL ,
  PRIMARY KEY (`id_Respuesta`, `Gestion_id_Gestion`) ,
  INDEX `fk_Respuesta_Gestion_idx` (`Gestion_id_Gestion` ASC) ,
  CONSTRAINT `fk_Respuesta_Gestion`
    FOREIGN KEY (`Gestion_id_Gestion` )
    REFERENCES `gestorcobranza`.`gestion` (`id_Gestion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
