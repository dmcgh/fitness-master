CREATE TABLE `fitness`.`devices` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NOT NULL DEFAULT 0,
  `serial_number` VARCHAR(20) NOT NULL,
  `product` VARCHAR(255) NOT NULL,
  `category` ENUM('RUN', 'SWIM') NOT NULL,
  `user_id` INT NOT NULL DEFAULT 0,
  `created` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modified` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`));
