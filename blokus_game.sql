-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.59 - MySQL Community Server (GPL)
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- 正在导出表  blokus_game.t_game_account 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `t_game_account` DISABLE KEYS */;
INSERT INTO `t_game_account` (`id`, `account`, `password`, `personality_signature`, `stars`, `create_time`, `last_update_time`) VALUES
	(4, '222222', '222222', NULL, 0, '2018-03-08 00:18:36', '2018-03-08 00:18:36'),
	(5, '1111111', '1111111', NULL, 0, '2018-03-09 00:31:56', '2018-03-09 00:31:56'),
	(6, '123456', '123456', NULL, 0, '2018-03-11 12:58:18', '2018-03-11 12:58:18'),
	(7, '654321', '654321', NULL, 0, '2018-03-11 12:58:52', '2018-03-11 12:58:52'),
	(8, '1', '1', NULL, 0, '2018-07-28 15:37:10', '2018-07-28 15:37:12'),
	(9, '2', '2', NULL, 0, '2018-07-28 15:37:10', '2018-07-28 15:37:12'),
	(10, '3', '3', NULL, 0, '2018-07-28 15:37:10', '2018-07-28 15:37:12'),
	(11, '4', '4', NULL, 0, '2018-07-28 15:37:10', '2018-07-28 15:37:12');
/*!40000 ALTER TABLE `t_game_account` ENABLE KEYS */;


-- 导出  表 blokus_game.t_game_log 结构
CREATE TABLE IF NOT EXISTS `t_game_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(16) NOT NULL,
  `game_type` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

-- 正在导出表  blokus_game.t_game_log 的数据：~83 rows (大约)
/*!40000 ALTER TABLE `t_game_log` DISABLE KEYS */;
INSERT INTO `t_game_log` (`id`, `room_name`, `game_type`, `create_time`, `last_update_time`) VALUES
	(1, 'QQ', 2, '2018-03-10 23:56:32', '2018-03-10 23:56:32'),
	(2, '456', 2, '2018-03-11 02:02:57', '2018-03-11 02:02:57'),
	(3, '12', 2, '2018-03-11 02:07:48', '2018-03-11 02:07:48'),
	(4, 'www', 2, '2018-03-11 02:09:45', '2018-03-11 02:09:45'),
	(5, '12', 2, '2018-03-11 02:10:48', '2018-03-11 02:10:48'),
	(6, '12', 2, '2018-03-11 02:11:45', '2018-03-11 02:11:45'),
	(7, 'QWER', 2, '2018-03-11 13:00:28', '2018-03-11 13:00:28'),
	(8, 'QWE', 2, '2018-03-11 13:06:52', '2018-03-11 13:06:52'),
	(9, 'ww', 2, '2018-03-11 13:10:06', '2018-03-11 13:10:06'),
	(10, '444', 2, '2018-03-11 13:14:32', '2018-03-11 13:14:32'),
	(11, '444', 2, '2018-03-11 13:16:16', '2018-03-11 13:16:16'),
	(12, '444', 2, '2018-03-11 13:16:24', '2018-03-11 13:16:24'),
	(13, '444', 2, '2018-03-11 13:16:32', '2018-03-11 13:16:32'),
	(14, '444', 2, '2018-03-11 13:16:37', '2018-03-11 13:16:37'),
	(15, '444', 2, '2018-03-11 13:16:43', '2018-03-11 13:16:43'),
	(16, '444', 2, '2018-03-11 13:17:09', '2018-03-11 13:17:09'),
	(17, '444', 2, '2018-03-11 13:18:06', '2018-03-11 13:18:06'),
	(18, '444', 2, '2018-03-11 13:19:46', '2018-03-11 13:19:46'),
	(19, '444', 2, '2018-03-11 13:20:06', '2018-03-11 13:20:06'),
	(20, 'rr', 2, '2018-03-11 14:43:27', '2018-03-11 14:43:27'),
	(21, 'yy', 2, '2018-03-11 14:47:09', '2018-03-11 14:47:09'),
	(22, 'yy', 2, '2018-03-11 14:47:29', '2018-03-11 14:47:29'),
	(23, '34', 2, '2018-03-11 14:51:25', '2018-03-11 14:51:25'),
	(24, 'rrr', 2, '2018-03-11 15:47:00', '2018-03-11 15:47:00'),
	(25, '234', 2, '2018-03-11 15:49:51', '2018-03-11 15:49:51'),
	(26, '23', 2, '2018-03-11 16:18:31', '2018-03-11 16:18:31'),
	(27, 'fff', 2, '2018-03-11 16:55:13', '2018-03-11 16:55:13'),
	(28, 'ccc', 2, '2018-03-11 17:03:47', '2018-03-11 17:03:47'),
	(29, 'sss', 1, '2018-03-11 17:11:20', '2018-03-11 17:11:20'),
	(30, '123', 1, '2018-03-11 17:14:57', '2018-03-11 17:14:57'),
	(31, '12', 2, '2018-03-11 20:31:22', '2018-03-11 20:31:22'),
	(32, '23', 2, '2018-03-11 20:34:10', '2018-03-11 20:34:10'),
	(33, 'qqq', 2, '2018-03-11 21:48:38', '2018-03-11 21:48:38'),
	(34, 'qqq', 2, '2018-03-11 21:57:15', '2018-03-11 21:57:15'),
	(35, '123', 2, '2018-03-11 22:05:26', '2018-03-11 22:05:26'),
	(36, 'qwe', 1, '2018-03-11 23:36:03', '2018-03-11 23:36:03'),
	(37, 'qwe', 1, '2018-07-28 16:48:54', '2018-07-28 16:48:54'),
	(38, 'qwe', 1, '2018-07-28 17:10:48', '2018-07-28 17:10:48'),
	(39, 'qwe', 1, '2018-07-28 17:13:18', '2018-07-28 17:13:18'),
	(40, '23', 2, '2018-07-28 17:15:39', '2018-07-28 17:15:39'),
	(41, '23', 2, '2018-07-28 17:16:54', '2018-07-28 17:16:54'),
	(42, '23', 2, '2018-07-28 17:17:03', '2018-07-28 17:17:03'),
	(43, 'qw', 2, '2018-07-28 17:17:47', '2018-07-28 17:17:47'),
	(44, 'qwe', 2, '2018-07-28 17:18:38', '2018-07-28 17:18:38'),
	(45, 'qwe', 2, '2018-07-28 17:18:57', '2018-07-28 17:18:57'),
	(46, 's\'d', 2, '2018-07-28 17:19:43', '2018-07-28 17:19:43'),
	(47, 'q\'we', 2, '2018-07-28 17:20:23', '2018-07-28 17:20:23'),
	(48, 'q\'we', 2, '2018-07-28 17:22:38', '2018-07-28 17:22:38'),
	(49, 'q\'w', 2, '2018-07-28 17:24:28', '2018-07-28 17:24:28'),
	(50, 'a\'d', 2, '2018-07-28 17:27:04', '2018-07-28 17:27:04'),
	(51, 'q\'w', 1, '2018-07-28 17:32:11', '2018-07-28 17:32:11'),
	(52, 'q\'we', 1, '2018-07-28 17:41:39', '2018-07-28 17:41:39'),
	(53, 'q\'w', 1, '2018-07-28 17:43:38', '2018-07-28 17:43:38'),
	(54, 'as', 2, '2018-07-28 18:15:38', '2018-07-28 18:15:38'),
	(55, '123', 2, '2018-07-28 18:17:16', '2018-07-28 18:17:16'),
	(56, '123', 2, '2018-07-28 18:19:40', '2018-07-28 18:19:40'),
	(57, 'sdf', 2, '2018-07-28 18:23:34', '2018-07-28 18:23:34'),
	(58, '12', 2, '2018-07-28 18:24:23', '2018-07-28 18:24:23'),
	(59, 'qw', 2, '2018-07-28 18:30:15', '2018-07-28 18:30:15'),
	(60, 'qwe', 2, '2018-07-29 00:34:47', '2018-07-29 00:34:47'),
	(61, 'qw', 2, '2018-07-29 11:27:09', '2018-07-29 11:27:09'),
	(62, '123', 2, '2018-07-29 11:34:43', '2018-07-29 11:34:43'),
	(63, 'qwe', 2, '2018-07-29 11:37:27', '2018-07-29 11:37:27'),
	(64, 'qwe', 2, '2018-07-29 11:38:09', '2018-07-29 11:38:09'),
	(65, 'qwe', 2, '2018-07-29 11:42:31', '2018-07-29 11:42:31'),
	(66, 'qwe', 2, '2018-07-29 13:55:11', '2018-07-29 13:55:11'),
	(67, 'asd', 2, '2018-07-29 14:26:36', '2018-07-29 14:26:36'),
	(68, 'asd', 2, '2018-07-29 14:28:10', '2018-07-29 14:28:10'),
	(69, '2', 2, '2018-07-29 14:47:08', '2018-07-29 14:47:08'),
	(70, 'wqr', 2, '2018-07-29 15:26:02', '2018-07-29 15:26:02'),
	(71, 'qwe', 2, '2018-07-29 15:47:44', '2018-07-29 15:47:44'),
	(72, '23', 2, '2018-07-29 16:10:02', '2018-07-29 16:10:02'),
	(73, '12', 2, '2018-07-29 16:12:30', '2018-07-29 16:12:30'),
	(74, '12', 2, '2018-07-29 16:14:10', '2018-07-29 16:14:10'),
	(75, 'sdf', 2, '2018-07-29 16:54:13', '2018-07-29 16:54:13'),
	(76, 'sdf', 2, '2018-07-29 16:55:13', '2018-07-29 16:55:13'),
	(77, 'sdf', 2, '2018-07-29 16:57:28', '2018-07-29 16:57:28'),
	(78, 'sdf', 2, '2018-07-29 16:57:40', '2018-07-29 16:57:40'),
	(79, 'sdf', 2, '2018-07-29 16:58:13', '2018-07-29 16:58:13'),
	(80, 'sdf', 2, '2018-07-29 16:59:23', '2018-07-29 16:59:23'),
	(81, 'qwe', 2, '2018-07-29 16:59:52', '2018-07-29 16:59:52'),
	(82, '23', 2, '2018-07-29 17:01:47', '2018-07-29 17:01:47'),
	(83, '23', 2, '2018-07-29 17:03:29', '2018-07-29 17:03:29');
/*!40000 ALTER TABLE `t_game_log` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- 正在导出表  blokus_game.t_player_game_log 的数据：~146 rows (大约)
/*!40000 ALTER TABLE `t_player_game_log` DISABLE KEYS */;
INSERT INTO `t_player_game_log` (`id`, `account`, `game_log_id`, `game_result`, `steps_count`, `change_score`, `create_time`, `last_update_time`) VALUES
	(1, '1111111', 1, 2, 4, -25, '2018-03-10 23:57:20', '2018-03-10 23:57:20'),
	(2, '222222', 1, 1, 3, 25, '2018-03-10 23:57:20', '2018-03-10 23:57:20'),
	(3, '222222', 2, 1, 1, 25, '2018-03-11 02:03:56', '2018-03-11 02:03:56'),
	(4, '222222', 3, 1, 1, 25, '2018-03-11 02:08:01', '2018-03-11 02:08:01'),
	(5, '1111111', 4, 1, 0, 25, '2018-03-11 02:09:53', '2018-03-11 02:09:53'),
	(6, '1111111', 5, 1, 0, 25, '2018-03-11 02:11:06', '2018-03-11 02:11:06'),
	(7, '222222', 5, 2, 1, -25, '2018-03-11 02:11:06', '2018-03-11 02:11:06'),
	(8, '1111111', 6, 1, 0, 25, '2018-03-11 02:11:48', '2018-03-11 02:11:48'),
	(9, '654321', 7, 2, 1, -25, '2018-03-11 13:01:35', '2018-03-11 13:01:35'),
	(10, '123456', 7, 1, 0, 25, '2018-03-11 13:01:35', '2018-03-11 13:01:35'),
	(11, '654321', 8, 2, 1, -25, '2018-03-11 13:06:56', '2018-03-11 13:06:56'),
	(12, '123456', 8, 1, 0, 25, '2018-03-11 13:06:56', '2018-03-11 13:06:56'),
	(13, '654321', 9, 1, 1, 25, '2018-03-11 13:10:12', '2018-03-11 13:10:12'),
	(14, '123456', 9, 2, 1, -25, '2018-03-11 13:10:12', '2018-03-11 13:10:12'),
	(15, '123456', 10, 3, 0, -25, '2018-03-11 13:14:35', '2018-03-11 13:14:35'),
	(16, '654321', 10, 1, 0, 25, '2018-03-11 13:14:35', '2018-03-11 13:14:35'),
	(17, '123456', 11, 1, 0, 25, '2018-03-11 13:16:17', '2018-03-11 13:16:17'),
	(18, '123456', 12, 1, 0, 25, '2018-03-11 13:16:26', '2018-03-11 13:16:26'),
	(19, '123456', 13, 1, 0, 25, '2018-03-11 13:16:33', '2018-03-11 13:16:33'),
	(20, '123456', 14, 1, 0, 25, '2018-03-11 13:16:39', '2018-03-11 13:16:39'),
	(21, '123456', 15, 1, 0, 25, '2018-03-11 13:16:44', '2018-03-11 13:16:44'),
	(22, '654321', 16, 1, 0, 25, '2018-03-11 13:17:11', '2018-03-11 13:17:11'),
	(23, '123456', 17, 1, 0, 25, '2018-03-11 13:18:09', '2018-03-11 13:18:09'),
	(24, '123456', 18, 1, 0, 25, '2018-03-11 13:19:48', '2018-03-11 13:19:48'),
	(25, '654321', 19, 1, 1, 25, '2018-03-11 13:20:12', '2018-03-11 13:20:12'),
	(26, '123456', 19, 2, 1, -25, '2018-03-11 13:20:12', '2018-03-11 13:20:12'),
	(27, '654321', 20, 1, 0, 25, '2018-03-11 14:45:27', '2018-03-11 14:45:27'),
	(28, '123456', 20, 3, 0, -25, '2018-03-11 14:45:36', '2018-03-11 14:45:36'),
	(29, '654321', 21, 1, 0, 25, '2018-03-11 14:47:15', '2018-03-11 14:47:15'),
	(30, '123456', 22, 3, 0, -25, '2018-03-11 14:49:06', '2018-03-11 14:49:06'),
	(31, '654321', 22, 1, 0, 25, '2018-03-11 14:49:06', '2018-03-11 14:49:06'),
	(32, '654321', 23, 1, 0, 25, '2018-03-11 14:52:08', '2018-03-11 14:52:08'),
	(33, '222222', 24, 2, 1, -25, '2018-03-11 15:47:19', '2018-03-11 15:47:19'),
	(34, '1111111', 24, 1, 1, 25, '2018-03-11 15:47:19', '2018-03-11 15:47:19'),
	(35, '222222', 25, 3, 1, -25, '2018-03-11 15:50:41', '2018-03-11 15:50:41'),
	(36, '1111111', 25, 1, 1, 25, '2018-03-11 15:50:41', '2018-03-11 15:50:41'),
	(37, '222222', 26, 3, 0, -25, '2018-03-11 16:18:57', '2018-03-11 16:18:57'),
	(38, '1111111', 26, 1, 0, 25, '2018-03-11 16:18:57', '2018-03-11 16:18:57'),
	(39, '1111111', 27, 3, 2, -25, '2018-03-11 16:55:35', '2018-03-11 16:55:35'),
	(40, '222222', 27, 1, 2, 25, '2018-03-11 16:55:35', '2018-03-11 16:55:35'),
	(41, '1111111', 28, 3, 0, -25, '2018-03-11 17:04:07', '2018-03-11 17:04:07'),
	(42, '222222', 28, 1, 0, 25, '2018-03-11 17:04:07', '2018-03-11 17:04:07'),
	(43, '123456', 29, 3, 0, -25, '2018-03-11 17:11:37', '2018-03-11 17:11:37'),
	(44, '1111111', 29, 3, 0, -25, '2018-03-11 17:11:40', '2018-03-11 17:11:40'),
	(45, '222222', 29, 3, 0, -25, '2018-03-11 17:11:43', '2018-03-11 17:11:43'),
	(46, '654321', 29, 2, 0, -25, '2018-03-11 17:11:46', '2018-03-11 17:11:46'),
	(47, '1111111', 33, 3, 0, -25, '2018-03-11 21:49:02', '2018-03-11 21:49:02'),
	(48, '222222', 33, 1, 0, 25, '2018-03-11 21:49:02', '2018-03-11 21:49:02'),
	(49, '1111111', 34, 2, 2, -25, '2018-03-11 21:57:55', '2018-03-11 21:57:55'),
	(50, '222222', 34, 1, 2, 25, '2018-03-11 21:57:55', '2018-03-11 21:57:55'),
	(51, '222222', 35, 2, 2, -25, '2018-03-11 22:05:55', '2018-03-11 22:05:55'),
	(52, '1111111', 35, 1, 1, 25, '2018-03-11 22:05:55', '2018-03-11 22:05:55'),
	(53, '1111111', 36, 3, 0, -25, '2018-03-11 23:36:16', '2018-03-11 23:36:16'),
	(54, '123456', 36, 3, 0, -25, '2018-03-11 23:36:37', '2018-03-11 23:36:37'),
	(55, '654321', 36, 3, 0, -25, '2018-03-11 23:36:42', '2018-03-11 23:36:42'),
	(56, '1', 39, 3, 0, -29, '2018-07-28 17:15:15', '2018-07-28 17:15:15'),
	(57, '2', 39, 3, 0, -25, '2018-07-28 17:15:22', '2018-07-28 17:15:22'),
	(58, '1', 40, 3, 0, -25, '2018-07-28 17:16:35', '2018-07-28 17:16:35'),
	(59, '2', 40, 1, 0, 24, '2018-07-28 17:16:38', '2018-07-28 17:16:38'),
	(60, '3', 39, 3, 0, -28, '2018-07-28 17:16:57', '2018-07-28 17:16:57'),
	(61, '4', 39, 1, 0, 46, '2018-07-28 17:16:58', '2018-07-28 17:16:58'),
	(62, '2', 42, 3, 0, -27, '2018-07-28 17:17:07', '2018-07-28 17:17:07'),
	(63, '1', 42, 1, 0, 29, '2018-07-28 17:17:07', '2018-07-28 17:17:07'),
	(64, '2', 43, 3, 0, -30, '2018-07-28 17:18:27', '2018-07-28 17:18:27'),
	(65, '1', 43, 1, 0, 29, '2018-07-28 17:18:28', '2018-07-28 17:18:28'),
	(66, '2', 45, 3, 0, -25, '2018-07-28 17:19:07', '2018-07-28 17:19:07'),
	(67, '1', 45, 1, 0, 29, '2018-07-28 17:19:13', '2018-07-28 17:19:13'),
	(68, '2', 46, 3, 0, -29, '2018-07-28 17:20:08', '2018-07-28 17:20:08'),
	(69, '1', 46, 1, 0, 24, '2018-07-28 17:20:08', '2018-07-28 17:20:08'),
	(70, '1', 47, 3, 0, -31, '2018-07-28 17:22:06', '2018-07-28 17:22:06'),
	(71, '2', 47, 1, 0, 23, '2018-07-28 17:22:07', '2018-07-28 17:22:07'),
	(72, '2', 48, 3, 0, -26, '2018-07-28 17:24:14', '2018-07-28 17:24:14'),
	(73, '1', 48, 1, 0, 29, '2018-07-28 17:24:20', '2018-07-28 17:24:20'),
	(74, '2', 49, 3, 0, -28, '2018-07-28 17:26:55', '2018-07-28 17:26:55'),
	(75, '1', 49, 1, 0, 24, '2018-07-28 17:26:55', '2018-07-28 17:26:55'),
	(76, '2', 50, 3, 0, -30, '2018-07-28 17:27:39', '2018-07-28 17:27:39'),
	(77, '1', 50, 1, 0, 25, '2018-07-28 17:27:42', '2018-07-28 17:27:42'),
	(78, '4', 51, 3, 0, -31, '2018-07-28 17:41:10', '2018-07-28 17:41:10'),
	(79, '1', 51, 3, 0, -29, '2018-07-28 17:41:11', '2018-07-28 17:41:11'),
	(80, '2', 51, 3, 1, -27, '2018-07-28 17:41:13', '2018-07-28 17:41:13'),
	(81, '3', 51, 1, 0, 49, '2018-07-28 17:41:16', '2018-07-28 17:41:16'),
	(82, '2', 52, 3, 0, -31, '2018-07-28 17:42:39', '2018-07-28 17:42:39'),
	(83, '1', 52, 3, 1, -30, '2018-07-28 17:42:56', '2018-07-28 17:42:56'),
	(84, '3', 52, 3, 0, -31, '2018-07-28 17:42:57', '2018-07-28 17:42:57'),
	(85, '4', 52, 1, 0, 52, '2018-07-28 17:43:01', '2018-07-28 17:43:01'),
	(86, '1', 53, 3, 2, -30, '2018-07-28 17:44:54', '2018-07-28 17:44:54'),
	(87, '3', 53, 3, 2, -29, '2018-07-28 17:45:08', '2018-07-28 17:45:08'),
	(88, '2', 53, 3, 2, -31, '2018-07-28 17:45:39', '2018-07-28 17:45:39'),
	(89, '4', 53, 1, 2, 46, '2018-07-28 17:45:40', '2018-07-28 17:45:40'),
	(90, '1', 54, 2, 3, -21, '2018-07-28 18:16:42', '2018-07-28 18:16:42'),
	(91, '2', 54, 2, 4, -5, '2018-07-28 18:16:51', '2018-07-28 18:16:51'),
	(92, '2', 55, 3, 1, -31, '2018-07-28 18:19:24', '2018-07-28 18:19:24'),
	(93, '1', 55, 1, 0, 24, '2018-07-28 18:19:25', '2018-07-28 18:19:25'),
	(94, '2', 56, 3, 0, -31, '2018-07-28 18:23:19', '2018-07-28 18:23:19'),
	(95, '1', 56, 1, 0, 24, '2018-07-28 18:23:20', '2018-07-28 18:23:20'),
	(96, '2', 57, 3, 0, -27, '2018-07-28 18:24:13', '2018-07-28 18:24:13'),
	(97, '1', 57, 1, 0, 28, '2018-07-28 18:24:14', '2018-07-28 18:24:14'),
	(98, '2', 58, 3, 1, -29, '2018-07-28 18:30:06', '2018-07-28 18:30:06'),
	(99, '1', 58, 1, 1, 23, '2018-07-28 18:30:06', '2018-07-28 18:30:06'),
	(100, '2', 59, 2, 3, -24, '2018-07-28 18:31:27', '2018-07-28 18:31:27'),
	(101, '1', 59, 3, 2, -25, '2018-07-28 18:31:38', '2018-07-28 18:31:38'),
	(102, '1', 60, 3, 4, -28, '2018-07-29 00:35:11', '2018-07-29 00:35:11'),
	(103, '2', 60, 1, 3, 25, '2018-07-29 00:35:16', '2018-07-29 00:35:16'),
	(104, '2', 61, 2, 1, -22, '2018-07-29 11:33:09', '2018-07-29 11:33:09'),
	(105, '1', 61, 1, 2, 23, '2018-07-29 11:33:29', '2018-07-29 11:33:29'),
	(106, '2', 62, 2, 0, -23, '2018-07-29 11:34:46', '2018-07-29 11:34:46'),
	(107, '1', 62, 1, 0, 29, '2018-07-29 11:34:53', '2018-07-29 11:34:53'),
	(108, '1', 63, 2, 0, -24, '2018-07-29 11:37:34', '2018-07-29 11:37:34'),
	(109, '2', 63, 2, 4, -6, '2018-07-29 11:37:59', '2018-07-29 11:37:59'),
	(110, '2', 64, 3, 1, -29, '2018-07-29 11:42:09', '2018-07-29 11:42:09'),
	(111, '1', 64, 1, 0, 24, '2018-07-29 11:42:10', '2018-07-29 11:42:10'),
	(112, '2', 65, 2, 1, -22, '2018-07-29 11:42:45', '2018-07-29 11:42:45'),
	(113, '1', 65, 2, 3, -4, '2018-07-29 11:42:55', '2018-07-29 11:42:55'),
	(114, '1', 66, 3, 2, -29, '2018-07-29 13:55:46', '2018-07-29 13:55:46'),
	(115, '2', 66, 1, 3, 28, '2018-07-29 13:55:50', '2018-07-29 13:55:50'),
	(116, '2', 67, 2, 1, -21, '2018-07-29 14:27:44', '2018-07-29 14:27:44'),
	(117, '1', 67, 2, 4, -6, '2018-07-29 14:27:53', '2018-07-29 14:27:53'),
	(118, '1', 68, 3, 1, -26, '2018-07-29 14:28:50', '2018-07-29 14:28:50'),
	(119, '2', 68, 2, 0, 25, '2018-07-29 14:29:05', '2018-07-29 14:29:05'),
	(120, '1', 69, 2, 2, -24, '2018-07-29 14:47:36', '2018-07-29 14:47:36'),
	(121, '4', 69, 2, 3, -1, '2018-07-29 14:47:44', '2018-07-29 14:47:44'),
	(122, '2', 70, 3, 4, -27, '2018-07-29 15:28:36', '2018-07-29 15:28:36'),
	(123, '1', 70, 1, 4, 27, '2018-07-29 15:28:37', '2018-07-29 15:28:37'),
	(124, '2', 71, 2, 3, -25, '2018-07-29 15:47:57', '2018-07-29 15:47:57'),
	(125, '3', 71, 2, 3, -2, '2018-07-29 15:49:48', '2018-07-29 15:49:48'),
	(126, '1', 73, 2, 0, -27, '2018-07-29 16:12:37', '2018-07-29 16:12:37'),
	(127, '2', 73, 2, 1, -4, '2018-07-29 16:12:40', '2018-07-29 16:12:40'),
	(128, '1', 74, 3, 3, -30, '2018-07-29 16:14:58', '2018-07-29 16:14:58'),
	(129, '2', 74, 1, 4, 23, '2018-07-29 16:14:59', '2018-07-29 16:14:59'),
	(130, '2', 75, 3, 0, -28, '2018-07-29 16:55:06', '2018-07-29 16:55:06'),
	(131, '1', 75, 2, 0, -5, '2018-07-29 16:55:07', '2018-07-29 16:55:07'),
	(132, '1', 76, 3, 1, -27, '2018-07-29 16:57:17', '2018-07-29 16:57:17'),
	(133, '2', 76, 2, 0, 27, '2018-07-29 16:57:22', '2018-07-29 16:57:22'),
	(134, '2', 77, 2, 0, 27, '2018-07-29 16:57:36', '2018-07-29 16:57:36'),
	(135, '1', 78, 3, 0, -25, '2018-07-29 16:57:54', '2018-07-29 16:57:54'),
	(136, '2', 78, 2, 0, 28, '2018-07-29 16:58:08', '2018-07-29 16:58:08'),
	(137, '1', 79, 3, 0, -27, '2018-07-29 16:58:58', '2018-07-29 16:58:58'),
	(138, '2', 79, 2, 0, 26, '2018-07-29 16:59:09', '2018-07-29 16:59:09'),
	(139, '2', 80, 3, 0, -31, '2018-07-29 16:59:34', '2018-07-29 16:59:34'),
	(140, '1', 80, 1, 0, 24, '2018-07-29 16:59:38', '2018-07-29 16:59:38'),
	(141, '2', 81, 3, 0, -28, '2018-07-29 17:01:26', '2018-07-29 17:01:26'),
	(142, '1', 81, 1, 0, 23, '2018-07-29 17:01:30', '2018-07-29 17:01:30'),
	(143, '1', 82, 3, 1, -31, '2018-07-29 17:03:18', '2018-07-29 17:03:18'),
	(144, '2', 82, 2, 0, -6, '2018-07-29 17:03:25', '2018-07-29 17:03:25'),
	(145, '1', 83, 3, 0, -30, '2018-07-29 17:04:02', '2018-07-29 17:04:02'),
	(146, '2', 83, 1, 0, 25, '2018-07-29 17:04:03', '2018-07-29 17:04:03');
/*!40000 ALTER TABLE `t_player_game_log` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- 正在导出表  blokus_game.t_player_game_record 的数据：~16 rows (大约)
/*!40000 ALTER TABLE `t_player_game_record` DISABLE KEYS */;
INSERT INTO `t_player_game_record` (`id`, `account`, `game_type`, `win_count`, `lose_count`, `escape_count`, `rank_score`, `rank`, `create_time`, `last_update_time`) VALUES
	(5, '222222', 1, 0, 0, 1, 11975, 0, '2018-03-08 00:18:36', '2018-03-11 17:11:43'),
	(6, '222222', 2, 8, 2, 2, 12050, 0, '2018-03-08 00:18:36', '2018-03-11 22:05:55'),
	(7, '1111111', 1, 0, 0, 2, 11950, 0, '2018-03-09 00:31:56', '2018-03-11 23:36:16'),
	(8, '1111111', 2, 8, 1, 3, 12050, 0, '2018-03-09 00:31:56', '2018-03-11 22:05:55'),
	(9, '123456', 1, 0, 0, 2, 1150, 0, '2018-03-11 12:58:18', '2018-03-11 23:36:37'),
	(10, '123456', 2, 9, 2, 3, 1300, 0, '2018-03-11 12:58:18', '2018-03-11 14:49:06'),
	(11, '654321', 1, 0, 1, 1, 1150, 0, '2018-03-11 12:58:52', '2018-03-11 23:36:42'),
	(12, '654321', 2, 10, 0, 0, 1350, 0, '2018-03-11 12:58:52', '2018-03-11 14:52:08'),
	(13, '1', 2, 0, 0, 0, 1200, 0, '2018-03-11 12:58:18', '2018-07-29 17:08:30'),
	(14, '2', 2, 0, 0, 0, 1200, 0, '2018-03-11 12:58:18', '2018-07-29 17:08:30'),
	(15, '3', 2, 0, 0, 0, 1200, 0, '2018-03-11 12:58:18', '2018-07-29 17:08:30'),
	(17, '4', 2, 0, 0, 0, 1200, 0, '2018-03-11 12:58:18', '2018-07-29 17:08:30'),
	(18, '1', 1, 0, 0, 0, 1200, 0, '2018-03-11 12:58:18', '2018-07-29 17:08:30'),
	(19, '2', 1, 0, 0, 0, 1200, 0, '2018-03-11 12:58:18', '2018-07-29 17:08:30'),
	(20, '3', 1, 0, 0, 0, 1200, 0, '2018-03-11 12:58:18', '2018-07-29 17:08:30'),
	(21, '4', 1, 0, 0, 0, 1200, 0, '2018-03-11 12:58:18', '2018-07-29 17:08:30');
/*!40000 ALTER TABLE `t_player_game_record` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;