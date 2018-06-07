package org.tottus.ventaonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {	
		SpringApplication.run(Application.class, args);
	}

}


/*
 * CREATE TABLE `bd_tottus`.`controlpromocion` (
  `idcontrolpromocion` INT NOT NULL AUTO_INCREMENT,
  `idequipomovil` VARCHAR(45) NULL,
  `codproducto` VARCHAR(45) NULL,
  `fecharegistro` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  PRIMARY KEY (`idcontrolpromocion`));
*/
 
 