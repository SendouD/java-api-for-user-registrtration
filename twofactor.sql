-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2024 at 05:29 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `twofactor`
--

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE `sessions` (
  `id` int(11) DEFAULT NULL,
  `signin_time` timestamp NULL DEFAULT NULL,
  `session_begin` timestamp NULL DEFAULT NULL,
  `duration` time DEFAULT NULL,
  `session_end` timestamp NULL DEFAULT NULL,
  `flag` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`id`, `signin_time`, `session_begin`, `duration`, `session_end`, `flag`) VALUES
(9, '2023-12-29 08:41:09', NULL, NULL, NULL, NULL),
(9, '2023-12-29 08:46:07', NULL, NULL, NULL, NULL),
(9, '2023-12-29 08:46:35', NULL, NULL, NULL, NULL),
(9, '2023-12-29 08:49:20', '2023-12-29 08:49:20', NULL, NULL, NULL),
(9, '2023-12-29 08:49:25', NULL, NULL, NULL, NULL),
(9, '2023-12-29 08:56:12', NULL, NULL, NULL, NULL),
(9, '2023-12-29 08:57:13', '2023-12-29 08:57:13', NULL, NULL, NULL),
(9, '2023-12-29 08:57:14', '2023-12-29 08:57:14', NULL, NULL, NULL),
(9, '2023-12-29 08:57:15', NULL, NULL, NULL, 0),
(9, '2023-12-29 08:58:27', NULL, NULL, NULL, NULL),
(9, '2023-12-29 10:14:30', NULL, NULL, NULL, NULL),
(9, '2023-12-29 10:17:58', NULL, NULL, NULL, NULL),
(9, '2023-12-29 10:23:30', NULL, NULL, NULL, NULL),
(1, '2023-12-29 10:23:42', NULL, NULL, NULL, NULL),
(1, '2023-12-29 10:26:42', NULL, NULL, NULL, NULL),
(1, '2023-12-29 10:36:58', '2023-12-29 10:36:59', NULL, NULL, NULL),
(1, '2023-12-29 10:37:28', '2023-12-29 10:37:28', NULL, NULL, NULL),
(1, '2023-12-29 10:50:35', '2023-12-29 10:50:35', NULL, NULL, NULL),
(1, '2023-12-29 10:53:01', '2023-12-29 10:53:01', NULL, NULL, NULL),
(1, '2023-12-29 10:54:51', NULL, NULL, NULL, NULL),
(1, '2023-12-29 10:54:57', NULL, NULL, NULL, NULL),
(1, '2023-12-29 10:55:06', '2023-12-29 10:55:06', NULL, NULL, NULL),
(9, '2023-12-29 11:24:18', NULL, NULL, NULL, NULL),
(9, '2023-12-29 11:24:38', '2023-12-29 11:24:38', NULL, NULL, NULL),
(9, '2023-12-29 11:24:42', '2023-12-29 11:24:42', NULL, NULL, NULL),
(9, '2023-12-29 11:24:43', NULL, NULL, NULL, 0),
(9, '2023-12-29 11:26:06', NULL, NULL, NULL, NULL),
(9, '2023-12-29 11:35:22', NULL, NULL, NULL, NULL),
(9, '2023-12-29 11:35:30', NULL, NULL, NULL, NULL),
(9, '2023-12-29 11:35:34', '2023-12-29 11:35:35', NULL, NULL, NULL),
(9, '2023-12-29 11:35:34', '2023-12-29 11:35:35', NULL, NULL, NULL),
(9, '2023-12-29 11:35:35', '2023-12-29 11:38:09', NULL, NULL, 0),
(9, '2023-12-29 11:38:46', NULL, NULL, NULL, NULL),
(9, '2023-12-29 13:37:31', NULL, NULL, NULL, NULL),
(9, '2023-12-29 13:37:42', '2023-12-29 13:37:42', NULL, NULL, NULL),
(9, '2023-12-29 13:37:44', '2023-12-29 13:37:44', NULL, NULL, NULL),
(9, '2023-12-29 13:37:45', NULL, NULL, NULL, 0),
(9, '2023-12-31 14:22:30', NULL, NULL, NULL, NULL),
(9, '2023-12-31 14:41:37', NULL, NULL, NULL, NULL),
(9, '2023-12-31 15:04:15', NULL, NULL, NULL, NULL),
(10, '2023-12-31 15:16:10', NULL, NULL, NULL, NULL),
(10, '2024-01-01 04:06:42', NULL, NULL, NULL, NULL),
(10, '2024-01-01 04:12:02', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `roles` varchar(50) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `phoneno` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `roles`, `level`, `phoneno`) VALUES
(1, 'User', '￠ﾲﾭﾪ', 'HR', 2, '9644101679'),
(2, 'User2', '￣ﾱﾮﾩ', 'elec', 2, '6382768621'),
(3, 'user3', '￢ﾰﾯﾨ', 'intern', 1, '6382768621'),
(4, 'user4', '￥ﾷﾨﾯ', 'fac', 2, '6382768621'),
(5, 'user5', '￤ﾶﾩﾮ', 'assist', 1, '6382768621'),
(6, 'user6', '￧ﾵﾪﾭ', 'hr//dean', 2, '6382768621'),
(7, 'user7', '￦ﾴﾫﾬ', 'hr//assist', 2, '6382768621'),
(8, 'user8', '￩ﾻﾤﾣ', 'hr//fac', 2, '6382768621'),
(9, 'user9', '￨ﾺﾥﾢ', 'dean', 1, '6382768621'),
(10, 'user10', '￠ﾳﾭﾫ', 'assist//fac', 2, '6382768621');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
