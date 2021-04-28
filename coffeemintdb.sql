CREATE SCHEMA `coffeemintdb-jdevd010` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `coffeemintdb-jdevd010`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`));

INSERT INTO `coffeemintdb-jdevd010`.`users` (`username`, `password`, `enabled`) VALUES ('admin', '$2a$10$QF/YQ9BzZoPmi5of5kkNeOP0bre/XmlsxyMX61aEeKwBsSxsfv1/u', '1');
INSERT INTO `coffeemintdb-jdevd010`.`users` (`username`, `password`, `enabled`) VALUES ('green', '$2a$10$QF/YQ9BzZoPmi5of5kkNeOP0bre/XmlsxyMX61aEeKwBsSxsfv1/u', '1');