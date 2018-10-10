-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.7.17-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 blokus_game 的数据库结构
CREATE DATABASE IF NOT EXISTS `blokus_game` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `blokus_game`;


-- 导出  表 blokus_game.t_game_account 结构
CREATE TABLE IF NOT EXISTS `t_game_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  `personality_signature` varchar(1024) DEFAULT NULL,
  `stars` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_account` (`account`),
  KEY `index_account_password` (`account`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 blokus_game.t_game_log 结构
CREATE TABLE IF NOT EXISTS `t_game_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(16) NOT NULL,
  `game_type` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 blokus_game.t_gobang_optimize 结构
CREATE TABLE IF NOT EXISTS `t_gobang_optimize` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `all_chess` varchar(1024) NOT NULL,
  `x` tinyint(4) NOT NULL,
  `y` tinyint(4) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_all_chess` (`all_chess`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 blokus_game.t_player_game_log 结构
CREATE TABLE IF NOT EXISTS `t_player_game_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) NOT NULL,
  `game_log_id` int(11) NOT NULL,
  `game_result` tinyint(4) NOT NULL,
  `steps_count` tinyint(4) NOT NULL,
  `change_score` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_game_log_id` (`game_log_id`),
  KEY `index_account_create_time` (`account`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 blokus_game.t_player_game_record 结构
CREATE TABLE IF NOT EXISTS `t_player_game_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) NOT NULL,
  `game_type` tinyint(4) NOT NULL,
  `win_count` int(11) NOT NULL DEFAULT '0',
  `lose_count` int(11) NOT NULL DEFAULT '0',
  `escape_count` int(11) NOT NULL DEFAULT '0',
  `rank_score` int(11) NOT NULL DEFAULT '1200',
  `rank` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_account_game_type` (`account`,`game_type`),
  KEY `index_account` (`account`),
  KEY `index_game_type_rank_score` (`game_type`,`rank_score`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
