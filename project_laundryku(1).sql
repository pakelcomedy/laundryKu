-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Dec 06, 2023 at 01:17 AM
-- Server version: 8.0.34
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_laundryku`
--

-- --------------------------------------------------------

--
-- Table structure for table `absensi`
--

CREATE TABLE `absensi` (
  `id_absensi` int NOT NULL,
  `waktu_absensi` time NOT NULL,
  `status_absensi` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_pegawai` bigint DEFAULT NULL,
  `Username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bahan_baku`
--

CREATE TABLE `bahan_baku` (
  `id_BahanBaku` bigint NOT NULL,
  `Nama_Bahan` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Stok` int DEFAULT NULL,
  `Harga_Beli` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bahan_baku`
--

INSERT INTO `bahan_baku` (`id_BahanBaku`, `Nama_Bahan`, `Stok`, `Harga_Beli`) VALUES
(551, 'Sabun Cair', 15, 10000),
(13002, 'Deterjen', 20, 16500),
(13003, 'Parfum', 15, 25000),
(13004, 'Pemutih', 12, 8000);

--
-- Triggers `bahan_baku`
--
DELIMITER $$
CREATE TRIGGER `update_bahan_baku` BEFORE UPDATE ON `bahan_baku` FOR EACH ROW BEGIN
INSERT INTO log_bahan_baku
 SET Nama_Bahan = OLD.Nama_Bahan,
 Stok = NEW.Stok,
 Harga_Beli = old.Harga_Beli,
 waktu = NOW();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail` bigint NOT NULL,
  `no_transaksi` bigint NOT NULL,
  `id_produk` bigint NOT NULL,
  `harga` int NOT NULL,
  `jumlah` int NOT NULL,
  `totalHarga` int NOT NULL,
  `detailId` int NOT NULL,
  `biayaTambahan` int NOT NULL,
  `diskon` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `laporan_keuangan`
--

CREATE TABLE `laporan_keuangan` (
  `id_laporan` int NOT NULL,
  `tgl_laporan` date DEFAULT NULL,
  `id_pemasukan` bigint DEFAULT NULL,
  `id_pengeluaran` bigint DEFAULT NULL,
  `total` int DEFAULT NULL,
  `no_transaksi` bigint DEFAULT NULL,
  `pemasukan` bigint DEFAULT NULL,
  `pengeluaran` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Triggers `laporan_keuangan`
--
DELIMITER $$
CREATE TRIGGER `insert_laporan_keuangan` AFTER INSERT ON `laporan_keuangan` FOR EACH ROW BEGIN
INSERT INTO log_laporan_keuangan
 SET tgl_laporan = NEW.tgl_laporan,
 pemasukan = NEW.pemasukan,
 pengeluaran = NEW.pengeluaran,
 total = NEW.total,
 waktu = NOW();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `log_bahan_baku`
--

CREATE TABLE `log_bahan_baku` (
  `id_log` int NOT NULL,
  `Nama_Bahan` varchar(50) NOT NULL,
  `Stok` bigint NOT NULL,
  `Harga_Beli` int NOT NULL,
  `waktu` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `log_bahan_baku`
--

INSERT INTO `log_bahan_baku` (`id_log`, `Nama_Bahan`, `Stok`, `Harga_Beli`, `waktu`) VALUES
(53, 'Deterjen', 65, 6500, '2023-12-05'),
(54, 'Deterjen', 5, 650000, '2023-12-06'),
(55, 'Deterjen', 20, 6500, '2023-12-06'),
(56, 'Sabun Cair', 15, 10000, '2023-12-06'),
(57, 'Deterjen', 15, 6500, '2023-12-06'),
(58, 'Deterjen', 12, 6500, '2023-12-06');

-- --------------------------------------------------------

--
-- Table structure for table `log_laporan_keuangan`
--

CREATE TABLE `log_laporan_keuangan` (
  `id_log` int NOT NULL,
  `tgl_laporan` date NOT NULL,
  `pemasukan` bigint NOT NULL,
  `pengeluaran` bigint NOT NULL,
  `total` int NOT NULL,
  `waktu` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `log_laporan_keuangan`
--

INSERT INTO `log_laporan_keuangan` (`id_log`, `tgl_laporan`, `pemasukan`, `pengeluaran`, `total`, `waktu`) VALUES
(451, '2023-11-28', 100000, 20000, 80000, '2023-11-28'),
(452, '2023-11-20', 100000, 20000, 80000, '2023-11-28'),
(453, '2023-12-01', 100000, 20000, 80000, '2023-12-01'),
(454, '2023-12-02', 100000, 85000, 15000, '2023-12-02'),
(455, '2023-12-05', 0, 0, 0, '2023-12-05'),
(456, '2023-12-05', 420000, 0, 420000, '2023-12-05'),
(457, '2023-12-05', 1680000, 0, 1680000, '2023-12-05'),
(458, '2023-12-05', 1680000, 63464363, -61784363, '2023-12-05'),
(459, '2023-12-05', 0, 63516885, -63516885, '2023-12-05'),
(460, '2023-12-05', 480000, 63516885, -63036885, '2023-12-05'),
(461, '2023-12-05', -526336317, 63516885, -589853202, '2023-12-05'),
(462, '2023-12-05', -526336317, 63516885, -589853202, '2023-12-05'),
(463, '2023-12-05', -526095317, 63516885, -589612202, '2023-12-05'),
(464, '2023-12-05', 240000, 100000, 140000, '2023-12-05'),
(465, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(466, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(467, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(468, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(469, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(470, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(471, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(472, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(473, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(474, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(475, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(476, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(477, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(478, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(479, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(480, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(481, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(482, '2023-12-05', 480000, 100000, 380000, '2023-12-05'),
(483, '2023-12-06', 0, 0, 0, '2023-12-06'),
(484, '2023-12-05', 480000, 100000, 380000, '2023-12-06'),
(485, '2023-12-05', 480000, 100000, 380000, '2023-12-06'),
(486, '2023-12-05', 480000, 100000, 380000, '2023-12-06');

-- --------------------------------------------------------

--
-- Table structure for table `log_member`
--

CREATE TABLE `log_member` (
  `id_log` int NOT NULL,
  `nama` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `tanggalDaftar` date NOT NULL,
  `batas_waktu` date NOT NULL,
  `statusMember` int NOT NULL,
  `waktu` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `log_member`
--

INSERT INTO `log_member` (`id_log`, `nama`, `alamat`, `no_hp`, `tanggalDaftar`, `batas_waktu`, `statusMember`, `waktu`) VALUES
(659, 'avrez', 'jember', '82347568896', '2023-12-01', '2024-01-01', 0, '2023-12-02'),
(660, 'fariz', 'jember', '82347568896', '2023-12-01', '2024-01-01', 1, '2023-12-02'),
(661, 'avrez', 'jember', '82332320707', '2023-12-02', '2024-01-02', 1, '2023-12-02'),
(662, 'avrez', 'jember', '823444455', '2023-12-02', '2024-01-02', 1, '2023-12-02'),
(663, 'nikto', 'jember', '8233', '2023-12-02', '2024-01-02', 1, '2023-12-05'),
(664, '3123123', 'o23omr2m', '12121', '2023-12-05', '2022-12-05', 1, '2023-12-05'),
(665, 'jem', 'eryer', '64645', '2023-12-05', '2023-12-05', 1, '2023-12-05'),
(666, 'Alfat', 'Banyuwangi', '12121', '2023-12-08', '2024-12-05', 0, '2023-12-06'),
(667, 'Fatin', 'Banyuwangi', '77', '2023-12-08', '2024-12-05', 0, '2023-12-06'),
(668, 'Willy', 'Banyuwangi', '77', '2023-12-08', '2023-11-05', 1, '2023-12-06'),
(669, 'MXNMSN', 'MSNS', '7733', '2023-12-07', '2023-12-06', 1, '2023-12-06'),
(670, 'JDK', 'DMDM', '4', '2023-12-07', '2023-12-06', 1, '2023-12-06'),
(671, 'JDK', 'DMDM', '276', '2023-12-07', '2023-12-06', 1, '2023-12-06'),
(672, 'JDK', 'DMDM', '276', '2023-12-07', '2024-12-06', 0, '2023-12-06'),
(673, 'JDK', 'DMDM', '276', '2023-12-07', '2024-12-06', 0, '2023-12-06'),
(674, 'Roby', 'Jember', '086754343212', '2023-12-07', '2024-01-04', 1, '2023-12-06'),
(675, 'Billy', 'Jember', '12121', '2023-12-08', '2024-12-05', 0, '2023-12-06');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id_member` bigint NOT NULL,
  `id_pelanggan` bigint DEFAULT NULL,
  `nama` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `alamat` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `tanggalDaftar` date NOT NULL,
  `batas_waktu` date NOT NULL,
  `statusMember` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Triggers `member`
--
DELIMITER $$
CREATE TRIGGER `hapus_member` BEFORE DELETE ON `member` FOR EACH ROW BEGIN 
INSERT INTO log_member  
SET nama = old.nama,  
no_hp = old.no_hp,  
alamat =old.alamat,  
tanggalDaftar = old.tanggalDaftar, 
batas_waktu = old.batas_waktu,
statusMember = old.statusMember,
waktu = NOW(); 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` bigint NOT NULL,
  `nama` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `id_pengeluaran` bigint NOT NULL,
  `tgl_pengeluaran` date NOT NULL,
  `keterangan` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `total_pengeluaran` int NOT NULL,
  `id_BahanBaku` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` bigint NOT NULL,
  `nama_produk` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `harga_produk` int NOT NULL,
  `jenis_produk` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `nama_produk`, `harga_produk`, `jenis_produk`) VALUES
(3745, 'Paket Kilat', 12000, 'Kilogram'),
(3747, 'Paket Reguler', 6000, 'Kilogram'),
(3748, 'Paket Komplit', 7000, 'Kilogram');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `no_transaksi` bigint NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `id_pelanggan` bigint DEFAULT NULL,
  `id_pegawai` bigint DEFAULT NULL,
  `dibayar_secara` varchar(20) NOT NULL,
  `status_laundry` int DEFAULT NULL,
  `status_pembayaran` varchar(20) NOT NULL,
  `grandTotal` int DEFAULT NULL,
  `biayaTambahan` int DEFAULT NULL,
  `diskon` int DEFAULT NULL,
  `totalPembayaran` int DEFAULT NULL,
  `pembayaran` int DEFAULT NULL,
  `kembalian` int DEFAULT NULL,
  `statusPengiriman` int DEFAULT NULL,
  `tanggal_masuk` date NOT NULL,
  `batas_waktu` date NOT NULL,
  `alamat_pengiriman` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_pegawai` bigint NOT NULL,
  `Username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `no_hp` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `jabatan` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `alamat` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absensi`
--
ALTER TABLE `absensi`
  ADD PRIMARY KEY (`id_absensi`),
  ADD UNIQUE KEY `id_pegawai` (`id_pegawai`);

--
-- Indexes for table `bahan_baku`
--
ALTER TABLE `bahan_baku`
  ADD PRIMARY KEY (`id_BahanBaku`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail`),
  ADD UNIQUE KEY `no_transaksi` (`no_transaksi`,`id_produk`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Indexes for table `laporan_keuangan`
--
ALTER TABLE `laporan_keuangan`
  ADD PRIMARY KEY (`id_laporan`),
  ADD UNIQUE KEY `id_pemasukan` (`id_pemasukan`,`id_pengeluaran`),
  ADD UNIQUE KEY `no_transaksi` (`no_transaksi`),
  ADD KEY `id_pengeluaran` (`id_pengeluaran`);

--
-- Indexes for table `log_bahan_baku`
--
ALTER TABLE `log_bahan_baku`
  ADD PRIMARY KEY (`id_log`);

--
-- Indexes for table `log_laporan_keuangan`
--
ALTER TABLE `log_laporan_keuangan`
  ADD PRIMARY KEY (`id_log`);

--
-- Indexes for table `log_member`
--
ALTER TABLE `log_member`
  ADD PRIMARY KEY (`id_log`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id_member`),
  ADD KEY `fk_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`id_pengeluaran`),
  ADD UNIQUE KEY `id_BahanBaku` (`id_BahanBaku`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`no_transaksi`),
  ADD UNIQUE KEY `id_pelanggan` (`id_pelanggan`,`id_pegawai`),
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_pegawai`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absensi`
--
ALTER TABLE `absensi`
  MODIFY `id_absensi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1223;

--
-- AUTO_INCREMENT for table `bahan_baku`
--
ALTER TABLE `bahan_baku`
  MODIFY `id_BahanBaku` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13006;

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2326;

--
-- AUTO_INCREMENT for table `laporan_keuangan`
--
ALTER TABLE `laporan_keuangan`
  MODIFY `id_laporan` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=534496;

--
-- AUTO_INCREMENT for table `log_bahan_baku`
--
ALTER TABLE `log_bahan_baku`
  MODIFY `id_log` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT for table `log_laporan_keuangan`
--
ALTER TABLE `log_laporan_keuangan`
  MODIFY `id_log` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=487;

--
-- AUTO_INCREMENT for table `log_member`
--
ALTER TABLE `log_member`
  MODIFY `id_log` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=676;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id_member` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=877686;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=768791;

--
-- AUTO_INCREMENT for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  MODIFY `id_pengeluaran` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id_produk` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3750;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `no_transaksi` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=890574;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_pegawai` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `absensi`
--
ALTER TABLE `absensi`
  ADD CONSTRAINT `absensi_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `user` (`id_pegawai`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`no_transaksi`) REFERENCES `transaksi` (`no_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `laporan_keuangan`
--
ALTER TABLE `laporan_keuangan`
  ADD CONSTRAINT `laporan_keuangan_ibfk_1` FOREIGN KEY (`no_transaksi`) REFERENCES `transaksi` (`no_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `laporan_keuangan_ibfk_2` FOREIGN KEY (`id_pengeluaran`) REFERENCES `pengeluaran` (`id_pengeluaran`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `member`
--
ALTER TABLE `member`
  ADD CONSTRAINT `member_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD CONSTRAINT `pengeluaran_ibfk_1` FOREIGN KEY (`id_BahanBaku`) REFERENCES `bahan_baku` (`id_BahanBaku`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`id_pegawai`) REFERENCES `user` (`id_pegawai`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
