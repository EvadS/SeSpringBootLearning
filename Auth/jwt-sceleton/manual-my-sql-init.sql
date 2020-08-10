CREATE TABLE `secureDb`.`role_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

  CREATE TABLE `secureDb`.`user_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) BINARY NULL,
  `password` VARCHAR(45) NULL,
  `role_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

  ALTER TABLE `secureDb`.`user_table`
ADD INDEX `fk_user_table_1_idx` (`role_id` ASC);
ALTER TABLE `secureDb`.`user_table`
ADD CONSTRAINT `fk_user_table_1`
  FOREIGN KEY (`role_id`)
  REFERENCES `secureDb`.`role_table` (`name`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  insert into role_table(name) values ('ROLE_ADMIN');
insert into role_table(name) values ('ROLE_USER');


