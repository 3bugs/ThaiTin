-- phpMyAdmin SQL Dump
-- version 4.1.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 13, 2019 at 10:26 AM
-- Server version: 5.5.37
-- PHP Version: 5.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `5911011802022_mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `thaitin_sentence`
--

CREATE TABLE IF NOT EXISTS `thaitin_sentence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sentence` varchar(200) CHARACTER SET tis620 NOT NULL,
  `translation` varchar(200) CHARACTER SET tis620 NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `thaitin_sentence`
--

INSERT INTO `thaitin_sentence` (`id`, `sentence`, `translation`, `created_at`) VALUES
(1, 'ดีใจนำเด้อ', 'ขอแสดงความยินดี', '2019-08-12 05:35:13'),
(2, 'สำบายดีบ่', 'สบายดีไหม', '2019-08-12 05:35:13'),
(3, 'บ่ได๋พ่อกันโดนแล้ว', 'ไม่ได้เจอกันนานเลย', '2019-08-12 05:36:10'),
(4, 'ไว้พ่อกันอีกเด้อ', 'แล้วพบกันใหม่', '2019-08-12 05:36:10'),
(5, 'ไว้พ่อกันมื้ออื่นเด้อ', 'แล้วเจอกันพรุ่งนี้นะ', '2019-08-12 05:37:04'),
(6, 'ไป่ก่อนเด้อ', 'ไปแล้วนะ', '2019-08-12 05:37:04'),
(7, 'ขอบคุณหลาย', 'ขอบคุณมาก', '2019-08-12 05:37:47'),
(8, 'บ่เป็นอิหยัง', 'ไม่เป็นไร', '2019-08-12 05:37:47'),
(9, 'ขอโทษหลาย ๆ', 'ขอโทษจริง ๆ', '2019-08-12 05:38:58'),
(10, 'บ่ต้องห่วง', 'ไม่ต้องห่วง', '2019-08-12 05:38:58'),
(11, 'ซางมันเถาะ', 'ช่างมันเถอะ', '2019-08-12 05:39:55'),
(12, 'เข้าใจบ่', 'เข้าใจไหม', '2019-08-12 05:39:55'),
(13, 'ถ่าก่อนเด้อจักคราว', 'กรุณารอสักครู่', '2019-08-12 05:41:14'),
(14, 'เว้าซ้า ๆ แหน่', 'กรุณาพูดช้า ๆ หน่อย', '2019-08-12 05:45:49'),
(15, 'เจ้าสิไปไส', 'คุณจะไปไหน', '2019-08-12 05:41:57'),
(16, 'เจ้าอยู่หม่องได๋', 'คุณอาศัยอยู่ที่ไหน', '2019-08-12 05:41:57'),
(17, 'จักโมงแล้ว', 'กี่โมงแล้ว', '2019-08-12 05:42:46'),
(18, 'มื้อนี้มื้ออีหยัง', 'วันนี้วันอะไร', '2019-08-12 05:42:46'),
(19, 'จักบาท ', 'ราคาเท่าไร', '2019-08-12 05:43:50'),
(20, 'ลดให้จักหน่อยได้บ่', 'ช่วยลดให้หน่อยได้ไหม', '2019-08-12 05:43:50'),
(21, 'คิดตังให้แหน่', 'กรุณาคิดเงินให้ด้วย', '2019-08-12 05:44:26'),
(22, 'สำบายอยู่', 'สบายดี', '2019-08-12 05:44:26'),
(23, 'บ่สำบาย', 'ไม่สบาย', '2019-08-12 05:45:04'),
(24, 'พี่น้องเจ้าเป็นจักได๋แหน่หน้อ', 'ครอบครัวคุณเป็นอย่างไรบ้าง', '2019-08-12 05:45:04'),
(25, 'มวนบ่', 'สนุกไหม', '2019-08-12 05:45:19');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
