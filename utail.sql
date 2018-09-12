-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.3.8-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 utail 的数据库结构
CREATE DATABASE IF NOT EXISTS `utail` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `utail`;

-- 导出  表 utail.category 结构
CREATE TABLE IF NOT EXISTS `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 正在导出表  utail.category 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`cid`, `name`) VALUES
	(1, '笔记'),
	(2, '感悟'),
	(3, 'JavaScript'),
	(4, 'Java'),
	(5, '人工智能'),
	(6, '大数据'),
	(8, '测试分类');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 导出  表 utail.post 结构
CREATE TABLE IF NOT EXISTS `post` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `content_path` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_cid` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `FKsaqw2kmgx1brb1lcdlm2ybxjb` (`category_cid`),
  CONSTRAINT `FKsaqw2kmgx1brb1lcdlm2ybxjb` FOREIGN KEY (`category_cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8;

-- 正在导出表  utail.post 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` (`pid`, `author`, `content_path`, `created_time`, `title`, `category_cid`, `description`, `image_path`) VALUES
	(228, '测试员1', '测试文章2018-09-10.md', '2018-09-10 10:24:01', '测试文章', 4, '这是描述文字!!\r\n字数最好控制在50字左右!!\r\n这是描述文字!!\r\n字数最好控制在50字左右!!', '5.jpg'),
	(229, 'chen', 'TensorFlow笔记2018-09-10.md', '2018-09-10 10:30:54', 'TensorFlow笔记', 6, '如果你还不够了解 TensorFlow.js，可以右转：here。下面是关于一些关于 TensorFlow.js 核心概念。由于现在 Tenso...', '6.jpg');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;

-- 导出  表 utail.posts_tags 结构
CREATE TABLE IF NOT EXISTS `posts_tags` (
  `pid` int(11) NOT NULL,
  `tid` int(11) NOT NULL,
  PRIMARY KEY (`tid`,`pid`),
  KEY `FKi3fio2nq3auk213sluctdu3s0` (`pid`),
  CONSTRAINT `FK6i6wq6bm5q8mawbjdbwkt5rj6` FOREIGN KEY (`tid`) REFERENCES `tag` (`tid`),
  CONSTRAINT `FKi3fio2nq3auk213sluctdu3s0` FOREIGN KEY (`pid`) REFERENCES `post` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  utail.posts_tags 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `posts_tags` DISABLE KEYS */;
INSERT INTO `posts_tags` (`pid`, `tid`) VALUES
	(228, 15),
	(229, 15),
	(228, 16),
	(229, 17),
	(229, 18);
/*!40000 ALTER TABLE `posts_tags` ENABLE KEYS */;

-- 导出  表 utail.tag 结构
CREATE TABLE IF NOT EXISTS `tag` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  UNIQUE KEY `UK1wdpsed5kna2y38hnbgrnhi5b` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- 正在导出表  utail.tag 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` (`tid`, `name`) VALUES
	(12, ''),
	(14, '111'),
	(9, '123'),
	(8, 'abc'),
	(18, 'JavaScript'),
	(17, 'TensorFlow'),
	(11, '又一个'),
	(15, '测试'),
	(13, '测试的'),
	(10, '测试的!'),
	(16, '这是一个测试');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;

-- 导出  表 utail.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKn4swgcf30j6bmtb4l4cjryuym` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  utail.user 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `created_time`, `email`, `nickname`, `password`, `username`) VALUES
	(1, '2018-09-09 21:22:13', 'demo@demo.com', 'chen', 'chen', 'chen');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
