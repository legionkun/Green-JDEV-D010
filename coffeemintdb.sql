CREATE SCHEMA `coffeemintdb-jdevd010` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `coffeemintdb-jdevd010`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`));

INSERT INTO `coffeemintdb-jdevd010`.`users` (`username`, `password`, `enabled`) VALUES ('admin', '$2a$10$QF/YQ9BzZoPmi5of5kkNeOP0bre/XmlsxyMX61aEeKwBsSxsfv1/u', '1');
INSERT INTO `coffeemintdb-jdevd010`.`users` (`username`, `password`, `enabled`) VALUES ('green', '$2a$10$QF/YQ9BzZoPmi5of5kkNeOP0bre/XmlsxyMX61aEeKwBsSxsfv1/u', '1');

CREATE TABLE `coffeemintdb-jdevd010`.`users_roles` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `role_fk_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_fk_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `coffeemintdb-jdevd010`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `role_fk_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `coffeemintdb-jdevd010`.`roles` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `coffeemintdb-jdevd010`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(12) NULL,
  `address` VARCHAR(128) NULL,
  `enabled` TINYINT NULL DEFAULT 1,
  `auth_provider` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
  
  
  create table persistent_logins (
	username varchar(64) not null, 
    series varchar(64) primary key,
	token varchar(64) not null, 
    last_used timestamp not null
)