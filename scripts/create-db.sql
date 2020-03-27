 DROP SCHEMA IF EXISTS `todo-project`;

CREATE SCHEMA `todo-project`;

use `todo-project`;

SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `todo_item`;

CREATE TABLE `todo_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(45) DEFAULT NULL,
  `modified_time` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `todoitem_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_TODOITEM_idx` (`todoitem_id`),
  
  CONSTRAINT `FK_TODOITEM` 
  FOREIGN KEY (`todoitem_id`) 
  REFERENCES `todo_item` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;
