CREATE TABLE `fitness`.`positions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `version` INT NOT NULL DEFAULT 0,
  `latitude` FLOAT NOT NULL DEFAULT 0.0,
  `longitude` FLOAT NOT NULL DEFAULT 0.0,
  `altitude` INT NOT NULL DEFAULT 0,
  `currenttime` DATETIME NULL,
  `run_id` INT NOT NULL DEFAULT 0,
  `created` TIMESTAMP NOT NULL DEFAULT NOW(),
  `modified` TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`));
