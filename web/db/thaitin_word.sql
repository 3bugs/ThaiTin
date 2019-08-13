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
-- Table structure for table `thaitin_word`
--

CREATE TABLE IF NOT EXISTS `thaitin_word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(50) CHARACTER SET tis620 NOT NULL,
  `translation` varchar(50) CHARACTER SET tis620 NOT NULL,
  `category` enum('food','appliance','greeting','relative','fruits/vegetables') NOT NULL COMMENT 'food = อาหาร, appliance = ของใช้, greeting = การทักทาย, relative = เครือญาติ',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=127 ;

--
-- Dumping data for table `thaitin_word`
--

INSERT INTO `thaitin_word` (`id`, `word`, `translation`, `category`, `created_at`) VALUES
(1, 'บักนาว', 'มะนาว', 'fruits/vegetables', '2019-07-14 05:53:49'),
(2, 'ลาบงัว', 'ลาบวัว', 'food', '2019-07-14 05:53:49'),
(3, 'ตำบักหุ่ง', 'ส้มตำ', 'food', '2019-07-14 05:55:25'),
(4, 'ข้าวปุ้น', 'ขนมจีน', 'food', '2019-07-14 05:55:25'),
(5, 'สาด', 'เสื่อ', 'appliance', '2019-07-14 06:00:50'),
(6, 'บักจก', 'จอบ', 'appliance', '2019-07-14 06:02:17'),
(7, 'โจก', 'แก้วน้ำ', 'appliance', '2019-07-14 06:02:17'),
(8, 'บักมี่', 'ขนุน', 'fruits/vegetables', '2019-07-14 06:04:17'),
(9, 'บักอึ', 'ฟักทอง', 'fruits/vegetables', '2019-07-14 06:04:17'),
(10, 'บ่วง', 'ช้อน', 'appliance', '2019-07-14 06:05:45'),
(11, 'บักเขียบ', 'น้อยหน่า', 'fruits/vegetables', '2019-07-14 06:42:47'),
(12, 'บักแงว', 'คอแลน (ผลไม้ลูกเหมือนลิ้นจี่)', 'fruits/vegetables', '2019-07-14 06:42:47'),
(13, 'บักนัด', 'สับปะรด', 'fruits/vegetables', '2019-07-14 06:43:38'),
(14, 'บักสีดา', 'ฝรั่ง', 'fruits/vegetables', '2019-07-14 06:43:38'),
(15, 'ปลาแดกบอง', 'ปลาร้าทรงเครื่อง', 'food', '2019-07-14 06:45:13'),
(16, 'บักขาม', 'มะขาม', 'fruits/vegetables', '2019-07-14 07:11:19'),
(17, 'บักทัน', 'พุดทรา', 'fruits/vegetables', '2019-07-14 07:11:19'),
(18, 'บักชมพู่', 'ชมพู่', 'fruits/vegetables', '2019-07-14 07:11:57'),
(19, 'บักส่มโอ', 'ส้มโอ', 'fruits/vegetables', '2019-07-14 07:11:57'),
(20, 'บักกะทกลก', 'เสาวรส', 'fruits/vegetables', '2019-07-14 07:12:40'),
(21, 'บักมวง', 'มะม่วง', 'fruits/vegetables', '2019-07-14 07:12:40'),
(22, 'บักพิลา', 'ทับทิม', 'fruits/vegetables', '2019-07-14 07:13:12'),
(23, 'บักองุ่น', 'องุ่น', 'fruits/vegetables', '2019-07-14 07:13:12'),
(24, 'ก้วย', 'กล้วย', 'fruits/vegetables', '2019-07-14 07:13:39'),
(25, 'บักแตงโม', 'แตงโม', 'fruits/vegetables', '2019-07-14 07:13:39'),
(26, 'บักมั๊งคุด', 'มังคุด', 'fruits/vegetables', '2019-07-14 07:14:11'),
(27, 'บักส่ม', 'ส้ม', 'fruits/vegetables', '2019-07-14 07:14:11'),
(28, 'บักละกำ', 'ระกำ', 'fruits/vegetables', '2019-07-14 07:15:03'),
(29, 'บักกะถ้อน', 'กระท้อน', 'fruits/vegetables', '2019-07-14 07:15:03'),
(30, 'ทุเลียน', 'ทุเรียน', 'fruits/vegetables', '2019-07-14 07:15:32'),
(31, 'ต้นกะเดา', 'สะเดา', 'fruits/vegetables', '2019-07-14 07:19:17'),
(32, 'บักปาง', 'มะปราง', 'fruits/vegetables', '2019-07-14 07:19:17'),
(33, 'บักหุ่ง', 'มะละกอ', 'fruits/vegetables', '2019-07-14 07:19:58'),
(34, 'บักญม', 'มะยม', 'fruits/vegetables', '2019-07-14 07:19:58'),
(35, 'บักไฟ', 'มะไฟ', 'fruits/vegetables', '2019-07-14 07:20:59'),
(36, 'บักเฟียง', 'มะเฟือง', 'fruits/vegetables', '2019-07-14 07:20:59'),
(37, 'บักกอก', 'มะกอก', 'fruits/vegetables', '2019-07-14 07:22:06'),
(38, 'คะน่า', 'คะน้า', 'fruits/vegetables', '2019-07-14 07:22:06'),
(39, 'ผักขา', 'ชะอม', 'fruits/vegetables', '2019-07-14 07:23:04'),
(40, 'บักบวบ', 'บวบ', 'fruits/vegetables', '2019-07-14 07:23:04'),
(41, 'บักถั่ว', 'ถั่วฝักยาว', 'fruits/vegetables', '2019-07-14 07:24:00'),
(42, 'อีตู่', 'แมงรัก', 'fruits/vegetables', '2019-07-14 07:24:00'),
(43, 'บักเขียเคีย', 'มะเขือเทศ', 'fruits/vegetables', '2019-07-14 07:25:08'),
(44, 'บักเขียผ่อย', 'มะเขือเปราะ', 'fruits/vegetables', '2019-07-14 07:25:08'),
(45, 'บักแค่ง', 'มะเขือพวง', 'fruits/vegetables', '2019-07-14 07:25:47'),
(46, 'เข่าโคด', 'ข้าวโพด', 'fruits/vegetables', '2019-07-14 07:25:47'),
(47, 'เผียก', 'เผือก', 'fruits/vegetables', '2019-07-14 07:29:05'),
(48, 'มันแก่ว', 'มันเทศ', 'fruits/vegetables', '2019-07-14 07:29:05'),
(49, 'ถั่วเหลียง', 'ถั่วเหลือง', 'fruits/vegetables', '2019-07-14 07:29:41'),
(50, 'ถั่วดิน', 'ถั่วลิสง', 'fruits/vegetables', '2019-07-14 07:29:41'),
(51, 'ผักซี', 'ผักชี', 'fruits/vegetables', '2019-07-14 07:30:48'),
(52, 'ใบผักอีเลิด', 'ชะพลู', 'fruits/vegetables', '2019-07-14 07:30:48'),
(53, 'หัวสีไค', 'ตะไคร้', 'fruits/vegetables', '2019-07-14 07:31:36'),
(54, 'ผักอีตู่ไทย', 'กะเพรา', 'fruits/vegetables', '2019-07-14 07:31:36'),
(55, 'เซื้ยก', 'เชือก', 'appliance', '2019-07-14 07:34:58'),
(56, 'เสี้ย', 'เสื้อ', 'appliance', '2019-07-14 07:34:58'),
(57, 'ซ้ง', 'กางเกง', 'appliance', '2019-07-14 07:35:40'),
(58, 'ซิ่น', 'ผ้าถุง', 'appliance', '2019-07-14 07:35:40'),
(59, 'เกิบ', 'รองเท้า', 'appliance', '2019-07-14 07:36:21'),
(60, 'มีดเซีย', 'กรรไกร', 'appliance', '2019-07-14 07:36:21'),
(61, 'ถุงย้าม', 'กระเป๋า', 'appliance', '2019-07-14 07:37:23'),
(62, 'เกิบบู๊ท', 'รองเท้าบู๊ท', 'appliance', '2019-07-14 07:37:23'),
(63, '่จ้อง', 'ร่ม', 'appliance', '2019-07-14 07:37:50'),
(64, 'ก่องข้าว', 'ก่องข้าวเหนียวนิ่ง', 'appliance', '2019-07-14 07:47:15'),
(65, 'กระติบข้าว', 'ก่องไส่ข้าวเหนียว', 'appliance', '2019-07-14 07:47:15'),
(66, 'กระต่า', 'ตระเกร้า', 'appliance', '2019-07-14 07:48:18'),
(67, 'แกงเจาะ', 'แกงไส่น้ำนิดๆ', 'food', '2019-07-14 07:48:18'),
(68, 'แกงซั้ว', 'แกงธรรมดา', 'food', '2019-07-14 07:48:35'),
(69, 'กับไฟ', 'ไม้ขีด', 'appliance', '2019-07-14 07:50:59'),
(70, 'ก้านจอง', 'ทับพี', 'appliance', '2019-07-14 07:50:59'),
(71, 'กระจอน', 'ตุ้มหู', 'appliance', '2019-07-14 07:52:53'),
(72, 'กระป่อม (ตักน้ำ)', 'ภาชนะสานด้วยไม้ไผ่ ใช้เชือกร้อยหย่อนลงตักน้ำในบ่อล', 'appliance', '2019-07-14 07:58:04'),
(73, 'กระป่อม', 'ตาข่ายดักปลา', 'appliance', '2019-07-14 07:58:04'),
(74, 'กระพ้อม', 'อุปกรณ์ที่มีไว้สำหรับเก็บข้าวเปลือก', 'appliance', '2019-07-14 07:59:03'),
(75, 'กล้องขา', 'กำไลขา', 'appliance', '2019-07-14 07:59:03'),
(76, 'กล้องแขน', 'กำไลแขน', 'appliance', '2019-07-14 07:59:36'),
(77, 'กล้องผม', 'ที่คาดผม', 'appliance', '2019-07-14 07:59:36'),
(78, 'ข่อง', 'ภาชนะสำหรับใส่ปลา', 'appliance', '2019-07-14 08:02:57'),
(79, 'พ่อปู่', 'ปู่', 'relative', '2019-07-14 08:08:43'),
(80, 'แม่ย่า', 'ย่า', 'relative', '2019-07-14 08:08:43'),
(81, 'พ่อใหญ่', 'ตา', 'relative', '2019-07-14 08:09:10'),
(82, 'แม่ใหญ่', 'ยาย', 'relative', '2019-07-14 08:09:10'),
(83, 'พ่อลุง', 'ลุง', 'relative', '2019-07-14 08:09:38'),
(84, 'แม่ป้า', 'ป้า', 'relative', '2019-07-14 08:09:38'),
(85, 'อีพ่อ', 'พ่อ', 'relative', '2019-07-14 08:10:06'),
(86, 'อีแม่', 'แม่', 'relative', '2019-07-14 08:10:06'),
(87, 'อาว', 'อาผู้ชาย', 'relative', '2019-07-14 08:10:41'),
(88, 'อา', 'อา', 'relative', '2019-07-14 08:10:41'),
(89, 'อ้าย', 'พี่ชาย', 'relative', '2019-07-14 08:11:24'),
(90, 'พี่อ้าย', 'สามีพี่สาว', 'relative', '2019-07-14 08:11:24'),
(91, 'พี่ี่นาง', 'ภรรยาพี่ชาย', 'relative', '2019-07-14 08:11:59'),
(92, 'เอื้อย', 'พี่่สาว', 'relative', '2019-07-14 08:11:59'),
(93, 'อีหล่า', 'น้องสาว', 'relative', '2019-07-14 08:12:44'),
(94, 'บักท้าว', 'น้องชาย', 'relative', '2019-07-14 08:12:44'),
(95, 'มู', 'เพื่อน', 'relative', '2019-07-14 08:13:47'),
(96, 'เสี่ยว', 'เพื่อนสนิท', 'relative', '2019-07-14 08:13:47'),
(97, 'ผู้บ่าว ผู้สาว', 'แฟน', 'relative', '2019-07-14 08:14:27'),
(98, 'น่า', 'น้า', 'relative', '2019-07-14 08:14:27'),
(99, 'พิอ้าย', 'พี่เขย', 'relative', '2019-07-14 08:15:14'),
(100, 'พินาง', 'พี่สะใภ้', 'relative', '2019-07-14 08:15:14'),
(101, 'น้าบ่าว', 'น้องภรรยา (ผู้ชาย)', 'relative', '2019-07-14 08:16:32'),
(102, 'น้าสาว', 'น้องภรรยา (ผู้หญิง)', 'relative', '2019-07-14 08:16:32'),
(103, 'ผัดเผ็ดเอี่ยน', 'ผัดเผ็ดปลาไหล', 'food', '2019-07-14 08:25:50'),
(104, 'ข้าวก่ำ', 'ข้าวเหนียวดำ', 'food', '2019-07-14 08:25:50'),
(105, 'ตำหมากต้อง', 'ตำกระท้อน', 'food', '2019-07-14 08:27:06'),
(106, 'ตำซั่ว', 'ส้มตำใส่ขนมจีน', 'food', '2019-07-14 08:27:06'),
(107, 'ซิ้นหลอด', 'เนื้อแดดเดียว', 'food', '2019-07-14 08:27:54'),
(108, 'ซอยจุ๊', 'เนื้อดิบ', 'food', '2019-07-14 08:27:54'),
(109, 'อุเพลี๊ย', 'อ่อมเนื้อใส่เพลี๊ย', 'food', '2019-07-14 08:29:04'),
(110, 'เซิ้มหน่อไม้', 'หน่อไม้ต้ม', 'food', '2019-07-14 08:29:04'),
(111, 'แจ่วบอง', 'ปลาร้าบอง', 'food', '2019-07-14 08:30:03'),
(112, 'แจ่ว ', 'น้ำพริก', 'food', '2019-07-14 08:30:03'),
(113, 'ส่มผัก', 'ผักดอง', 'food', '2019-07-14 08:31:08'),
(114, 'ซุปบักมี่', 'ตำขนุนอ่อน', 'food', '2019-07-14 08:31:08'),
(115, 'ป่นปลาดุก', 'น้ำพริกปลาดุก', 'food', '2019-07-14 08:32:30'),
(116, 'ซุปบักเขีย', 'ซุปมะเขือเปราะ', 'food', '2019-07-14 08:32:30'),
(117, 'ส่มหมู', 'แหนมปลา', 'food', '2019-07-14 08:33:13'),
(118, 'คันแทนา', 'เครื่องในวัว', 'food', '2019-07-14 08:33:13'),
(119, 'หมกฮวก', 'ห่อหมกลูกอ๊อด', 'food', '2019-07-14 08:33:54'),
(120, 'หวัดดีจ้า หวัดดีคับ', 'สวัสดี', 'greeting', '2019-07-14 08:49:14'),
(121, 'ขอโทษ', 'ขอโทษ', 'greeting', '2019-07-14 08:49:14'),
(122, 'ข่อยฮักเจ้า', 'ฉันรักคุณ', 'greeting', '2019-07-14 08:50:13'),
(123, 'ขอบคุณ', 'ขอบคุณ', 'greeting', '2019-07-14 08:50:13'),
(124, 'ลาก่อน', 'ลาก่อน', 'greeting', '2019-07-14 08:50:51'),
(125, 'โซคดี', 'โชคดี', 'greeting', '2019-07-14 08:50:51'),
(126, 'พ้อกันใหม่', 'พบกันใหม่', 'greeting', '2019-07-14 08:51:13');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;