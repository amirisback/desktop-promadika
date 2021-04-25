-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2017 at 07:05 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `promadika`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_calon_nikah`
--

CREATE TABLE `data_calon_nikah` (
  `id_calon` varchar(15) NOT NULL,
  `nik_csuami` varchar(25) NOT NULL,
  `nama_csuami` varchar(25) NOT NULL,
  `ttl_csuami` varchar(30) NOT NULL,
  `alamat_csuami` varchar(40) NOT NULL,
  `foto_csuami` varchar(60) NOT NULL,
  `nik_cistri` varchar(25) NOT NULL,
  `nama_cistri` varchar(25) NOT NULL,
  `ttl_cistri` varchar(30) NOT NULL,
  `alamat_cistri` varchar(40) NOT NULL,
  `foto_cistri` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data_calon_nikah`
--

INSERT INTO `data_calon_nikah` (`id_calon`, `nik_csuami`, `nama_csuami`, `ttl_csuami`, `alamat_csuami`, `foto_csuami`, `nik_cistri`, `nama_cistri`, `ttl_cistri`, `alamat_cistri`, `foto_cistri`) VALUES
('IDCX40EP', '1', '123', '12312', '3123123123', 'FTCLN_11SE4_IDCX40EPS.jpg', '12312', '3123', '12312', '3123', 'FTCLN_MDEE9_IDCX40EPI.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `data_jadwal_nikah`
--

CREATE TABLE `data_jadwal_nikah` (
  `id_nikah` varchar(10) NOT NULL,
  `tgl_nikah` date NOT NULL,
  `tempat_nikah` varchar(50) NOT NULL,
  `biaya_nikah` int(20) NOT NULL,
  `id_calon` varchar(15) NOT NULL,
  `id_petugas` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data_jadwal_nikah`
--

INSERT INTO `data_jadwal_nikah` (`id_nikah`, `tgl_nikah`, `tempat_nikah`, `biaya_nikah`, `id_calon`, `id_petugas`) VALUES
('IDJB42NH', '2017-11-29', 'Luar Kantor Urusan Agama (Luar KUA)', 600000, 'IDCX40EP', '6706160014');

-- --------------------------------------------------------

--
-- Table structure for table `data_petugas`
--

CREATE TABLE `data_petugas` (
  `id_petugas` varchar(15) NOT NULL,
  `nama_petugas` varchar(25) NOT NULL,
  `tempat_kua` varchar(30) NOT NULL,
  `foto` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data_petugas`
--

INSERT INTO `data_petugas` (`id_petugas`, `nama_petugas`, `tempat_kua`, `foto`) VALUES
('6706160014', 'Muhammad Faisal Amir', 'Rancaekek', 'FTPTGS_7U3EQ_6706160014.jpg'),
('zdasdsad', 'dsadasd', 'dsadasd', 'FTPTGS_C341S_zdasdsad.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_calon_nikah`
--
ALTER TABLE `data_calon_nikah`
  ADD PRIMARY KEY (`id_calon`);

--
-- Indexes for table `data_jadwal_nikah`
--
ALTER TABLE `data_jadwal_nikah`
  ADD PRIMARY KEY (`id_nikah`),
  ADD KEY `fk_id_calon` (`id_calon`),
  ADD KEY `fk_id_petugas` (`id_petugas`);

--
-- Indexes for table `data_petugas`
--
ALTER TABLE `data_petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data_jadwal_nikah`
--
ALTER TABLE `data_jadwal_nikah`
  ADD CONSTRAINT `data_jadwal_nikah_ibfk_1` FOREIGN KEY (`id_petugas`) REFERENCES `data_petugas` (`id_petugas`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `data_jadwal_nikah_ibfk_2` FOREIGN KEY (`id_calon`) REFERENCES `data_calon_nikah` (`id_calon`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
