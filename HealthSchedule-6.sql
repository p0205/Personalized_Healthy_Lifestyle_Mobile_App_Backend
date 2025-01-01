-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 01, 2025 at 08:12 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `HealthSchedule`
--

-- --------------------------------------------------------

--
-- Table structure for table `daily_cateogry_percentage`
--

CREATE TABLE `daily_cateogry_percentage` (
  `id` int(11) NOT NULL,
  `category` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `percentage` double NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `daily_performance`
--

CREATE TABLE `daily_performance` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `total_percentage` double NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `health_test`
--

CREATE TABLE `health_test` (
  `id` int(11) NOT NULL,
  `disease_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `meal`
--

CREATE TABLE `meal` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `unit_weight` double DEFAULT NULL,
  `energy_per100g` double DEFAULT NULL,
  `carbs_per100g` double DEFAULT NULL,
  `protein_per100g` double DEFAULT NULL,
  `fat_per100g` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `meal`
--

INSERT INTO `meal` (`id`, `name`, `unit_weight`, `energy_per100g`, `carbs_per100g`, `protein_per100g`, `fat_per100g`) VALUES
(1, 'MARGARINE SPREAD, WITHOUT SALT (SAPUAN MERJERIN TANPA GARAM)', 16.488, 519, 0.14, 0.6, 58.34),
(2, 'CHEESE SPREAD (KEJU SAPUAN)', 15, 295, 3.5, 7, 29),
(3, 'MARGARINE SPREAD, WITH SALT (SAPUAN MERJERIN BERGARAM)', 13.796, 621, NULL, 0.29, 70),
(4, 'CHOCOLATE, WHITE (COKLAT PUTIH)', 5.81094, 539, 59, 6, 32),
(5, 'RICE, ARAB (NASI ARAB)', 200, 222, 40.78, 5.2, 4.27),
(6, 'CAKE, CUP, WITH FROSTING/GLAZE (KEK CAWAN)', 48.36, 370, 67.8, 2.85, 10.96),
(7, 'YOGURT, DRINKING, PASTEURISED, NATURAL FLAVOUR', 250, 61, 4.7, 3.5, 3.3),
(8, 'KOLEH KACANG', 49, 175.52, 24.27, 4.65, 6.65),
(9, 'SAUCE, OYSTER FLAVOURED', 11.37, 51, 11, 1.4, 0.3),
(10, 'DESSERT, HONEYCOMB CAKE (KEK GULA HANGUS)', 88.16, 250, 42, 5.1, 7.1),
(11, 'TRADITIONAL MALAYSIAN KUIH, SWEET FERMENTED TAPIOCA (TAPAI UBI KAYU)', 272.26, 246, 59.96, 0.85, 0.31),
(12, 'OIL, BLEND (MINYAK CAMPURAN)', 2.794, 896.14, 0.46, 0, 99.37),
(13, 'COFFEE, WITH MILK AND SUGAR, FROM MAMAK STALLS', 200, 207, 51.54, 0.17, 0.02),
(14, 'COOKIES/CRACKERS, SANDWICH WITH FLAVOURED CREAM OREO', 9.65, 468, 69.86, 5.79, 18.43),
(15, 'OIL, VEGETABLE (MINYAK SAYURAN)', 3.308, 897.68, 0.08, 0, 99.71),
(16, 'POMEGRANATE (DELIMA)', 174.693, 80, 14.4, 1.53, 0.8),
(17, 'OIL, COCONUT (MINYAK KELAPA)', 3.899, 897.33, 0.09, 0.08, 99.63),
(18, 'CAKE, CHEESE (KEK KEJU)', 129.63, 321, 26, 6, 23),
(19, 'TRADITIONAL MALAYSIAN KUIH, SESAME BALLS (KUIH BOM)', 33.48, 335, 51.21, 6.54, 11.59),
(20, 'RICE, BRIYANI (DAGING)', 271.62, 195, 37.07, 7.85, 8.07),
(21, 'PASTRY, PUFF, CHOCOLATE FILLING', 31.08, 531.25, 56.25, 6.25, 35),
(22, 'TRADITIONAL MALAYSIAN KUIH, SWEET GLUTINOUS RICE CAKE (WAJIK)', 20, 290, 56, 3, 5),
(23, 'CEREAL, BREAKFAST, OAT/MULTIGRAIN', 100, 370, 65, 12, 6),
(24, 'BISCUIT, MILK (BISKUT SUSU)', 29, 473, 58.25, 8.83, 21.42),
(25, 'SUGAR, ICING (GULA ISING)', 14.568, 389, 100, NULL, NULL),
(26, 'SOFT MARGARINE (MARJERIN LEMBUT)', 18.606, 720, NULL, NULL, 80),
(27, 'CAROB FLOUR (TEPUNG KACANG KUDA)', 3, 311.01, 52.73, 21.11, 1.74),
(28, 'DESEERT, COCONUT MILK JELLY (AGAR-AGAR SANTAN)', 38.09, 120, 15, 1, 6),
(29, 'RICE, BRIYANI (AYAM)', 404.76, 159, 24.36, 3.24, 5.36),
(30, 'FRUIT JUICE ORANGE', 240, 33.47, 5.1, 0.64, 1.17),
(31, 'JAM, BLACKCURRENT (JEM ANGGUR HITAM)', 28.538, 266, 65.99, 0.27, 0.02),
(32, 'CEREAL, BREAKFAST, GRANOLA', 45, 451, 62.58, 9.44, 18.15),
(33, 'CHEESE, CHEDDAR, REDUCED FAT (KEJU CHEDDAR, KURANG LEMAK)', 68.99, 200, 3, 30, 8),
(34, 'DESSERT, BROWNIES, CHOCOLATE', 20.68, 380, 50, 5, 18),
(35, 'MILK, COW, FRESH', 200, 61, 4.8, 3.2, 3.3),
(36, 'DESSERT, MOON CAKES RED BEAN', 180, 260, 50, 6, 6),
(37, 'MUFFIN, CHOCOLATE', 60.93, 441, 58.92, 7.5, 19.45),
(38, 'BISCUIT, MARIE', 5.13, 446, 65.07, 8.46, 16.89),
(39, 'CHICKEN, SAUSAGE (SOSEJ DAGING AYAM)', 3.227, 193, 9.95, 11.68, 10.47),
(40, 'CAKE, SWISS ROLL (KEK SWISS ROLL)', 28.63, 310, 50, 5, 12),
(41, 'YOGURT, FLAVOURED (STRAWBERRY, ETC)', 120, 120, 18, 4, 3),
(42, 'FISH OIL, COD LIVER OIL (MINYAK IKAN KOD)', 10.719, 900, NULL, NULL, 100),
(43, 'CANNED COFFEE', 200, 40, 9, 1, NULL),
(44, 'BISCUIT, LEMON PUFF', 30, 455, 63.51, 7.87, 18.8),
(45, 'PASTRY, PUFF, APPLE FILLING', 84.34, 392, 52.15, 5.46, 17.99),
(46, 'CREAM CHEESE (KRIM KEJU)', 25.989, 350, 4, 6, 34),
(47, 'DESSERT, COCONUT MILK BASED, SWEET POTATOES AND TARO IN COCONUT MILK (BUBUR CHA CHA)', 334.33, 219, 39.54, 2.52, 5.61),
(48, 'BUN, POTATO (BAN KENTANG)', 36.267, 303, 46.27, 9.15, 8.98),
(49, 'TRADITIONAL MALAYSIAN KUIH, MINI EGG SPONGE CAKE (BAHULU CERMAI)', 7.47, 417, 82.55, 8.89, 5.68),
(50, 'STRAWBERRY FLAVOURED CANDY (GULA-GULA PERISA STRAWBERI)', 3.74, 400, 95, NULL, NULL),
(51, 'DESSERT, FRUIT CAKE (KEK BUAH)', 25.3, 250, 50, 4, 5),
(52, 'CEREAL, BREAKFAST, MULTICOLOURED, SUGAR-FILLED RING SHAPED', 30, 400, 80, 4, 5),
(53, 'PINEAPPLE FLAVOURED CANDY (GULA-GULA PERISA NANAS)', 4.073, 390, 95, NULL, NULL),
(54, 'CAKAR AYAM', 37.5, 423.03, 66.4, 1, 17.05),
(55, 'SAMOSA', 29.5, 218.04, 9.74, 5.4, 17.5),
(56, 'CAPSICUM, GREEN (LADA BENGALA, HIJAU)', 47.004, 20, 3.4, 0.68, 0.15),
(57, 'DESSERT, CORN IN SWEETENED COCONUT MILK (BUBUR JAGUNG)', 342.21, 213, 41.16, 2.62, 4.24),
(58, 'BREADFRUITS CHIPS (KEREPEK SUKUN)', 30, 556.85, 45.8, 2.19, 40.54),
(59, 'MILK, PASTEURISED, CHOCOLATE FLAVOUR', 200, 85, 13, 3, 3),
(60, 'CHOCOLATE, RAISIN (COKLAT BERKISMIS)', 2.81, 539, 53.52, 7.78, 32.63),
(61, 'KELEDEK GORENG', 8, 234.71, 33.26, 1.9, 10.45),
(62, 'HEN EGG, LOWER CHOLESTROL, WITH VITAMIN E (TELUR AYAM RENDAH KOLESTROL, DENGAN VITAMIN E)', 48.71, 136, 0.37, 2.03, 1.35),
(63, 'TAPIOCA CHIPS, PLAIN, SALTED (KEREPEK UBI KAYU, BERGARAM)', 30, 478.96, 65.52, 1.95, 23.23),
(64, 'CORDIAL, KIWI (KORDIAL KIWI)', 10.825, 258.58, 64.59, 0.06, 0),
(65, 'YOGURT, LOW FAT, NATURAL', 140, 50, 7, 4, 1),
(66, 'WAFER, STRAWBERRY FLAVOR (WAFER BERPERISA STRAWBERI)', 2.981, 520, 70, 4, 2),
(67, 'FRUIT JUICE, FRESH, ORANGE', 250, 47, 10.99, 0.13, 0.28),
(68, 'CHOCOLATE, MIXED NUTS (COKLAT KACANG CAMPURAN)', 4.2022, 550, 45, 8, 35),
(69, 'BABY CORN (JAGUNG SAYUR)', 12.52, 37, 6.12, 2.17, 0.1),
(70, 'DESSERT, SWEET LOTUS SEED SOUP (LIN-CHI-KANG)', 150, 120, 30, 2, NULL),
(71, 'CHICKEN SATAY (SATAY AYAM)', 80, 187, 5.28, 28.32, 11.77),
(72, 'BUN, RED BEANS (BAN KACANG MERAH)', 42.7, 294, 52.4, 7.93, 5.83),
(73, 'CORDIAL, GRAPE (KORDIAL ANGGUR)', 7.473, 150.15, 37.51, 0.02, 0),
(74, 'PEARL MILK TEA (WITH BREWED TEA, MILK, SUGAR SYRUP, TAPIOCA PEARLS), NORMAL SUGAR', 250, 60, 14, 1, 1),
(75, 'CUCUR BAWANG', 27, 241.41, 39.26, 6.64, 6.42),
(76, 'TEA, STEVIA (TEH STEVIA)', 240, 2, NULL, NULL, NULL),
(77, 'CROISSANT', 60, 414, 49.16, 8.67, 20.25),
(78, 'CAPSICUM, YELLOW (LADA BENGALA, KUNING)', 40.517, 29, 5.37, 0.85, 0.03),
(79, 'MAYONNAISE (MAYONIS)', 9.118, 433, 37.88, 1.09, 30.77),
(80, 'DESSERT, MOON CAKES PURE LOTUS', 180, 350, 50, 8, 12),
(81, 'BISCUIT, COCONUT', 13.02, 457, 66.75, 8.32, 17.44),
(82, 'SAUCE, CHILI THAI', 12.43, 80, 16, 1, 2),
(83, 'CORDIAL, SOURSOP (KORDIAL DURIAN BELANDA)', 10.055, 143.78, 35.83, 0.12, 0),
(84, 'FRUIT DRINK, ORANGE', 250, 45, 11, NULL, NULL),
(85, 'TRADITIONAL MALAYSIAN KUIH, COCONUT CREAM COOKIES (KUIH BANGKIT)', 30, 480, 60, 5, 25),
(86, 'BEEF RENDANG (RENDANG DAGING LEMBU)', 150, 360, 18.48, 18.76, 23.46),
(87, 'OIL, SUNFLOWER (MINYAK BUNGA MATAHARI)', 3.293, 896.21, 0.58, 0, 99.32),
(88, 'DRAGON FRUIT, WHITE (BUAH NAGA, ISI PUTIH)', 246.355, 53, 10.55, 1.6, 0.15),
(89, 'TRADITIONAL MALAYSIAN KUIH, FLOUR BASED, WITH RED PALM SUGAR  (KUIH CINCIN)', 50, 534, 54.06, 14.66, 28.75),
(90, 'BREAD, RAISIN (ROTI KISMIS)', 32.85, 449, 59.06, 9.92, 19.25),
(91, 'TAPIOCA CHIPS, PLAIN, UNSALTED (KEREPEK UBI KAYU, TANPA GARAM)', 30, 497.54, 62.67, 1.3, 26.85),
(92, 'JAM, ORANGE MARMALADE', 25.07, 250, 63, NULL, NULL),
(93, 'TRADITIONAL MALAYSIAN KUIH, GLUTINUOS RICE FLOUR BASED STUFFED WITH GRATED COCONUT COATED WITH MUNG BEAN POWDER (KUIH TEPUNG GOMAK)', 48.82, 510, 60, 3, 30),
(94, 'ASPARTAME (ASPARTAM)', 3.63, 4, 1, NULL, NULL),
(95, 'DESSERT, SOY MILK CUSTARD (TAUFU-FAH)', 297.37, 135, 28.43, 3.34, 0.93),
(96, 'SPINACH, (BAYAM MASAK AIR)', 50, 91, 13.92, 3.46, 2.41),
(97, 'CORDIAL, GUAVA (KORDIAL JAMBU BATU)', 11.603, 266.96, 66.74, NULL, NULL),
(98, 'DESSERT, BLACK GLUTINOUS RICE PORRIDGE (BUBUR PULUT HITAM)', 396.96, 229, 45.28, 2.58, 4.17),
(99, 'FLAVOURED DRINK, COLA TYPE', 250, 45, 11, NULL, NULL),
(100, 'CREAM ROLL, VANILLA FLAVOUR', 40.13, 370, 38.96, 6.86, 20.71),
(101, 'CHOCOLATE, HAZELNUT (COKLAT KACANG BADAM)', 5.2405, 544.49, 59.36, 9.43, 29.93),
(102, 'TEA, ICE LEMON, PACKET, WITH ADDED SUGAR', 200, 30, 8, NULL, NULL),
(103, 'NASI ARAB SET KHABSYAH (KAMBING)', 319.63, 206, 29.96, 3.59, 7.93),
(104, 'DESSERT, CHILLED COCONUT MILK- & PALM SUGAR-BASED, WITH PANDAN JELLY NOODLES AND RED BEAN (CENDOL)', 150, 110, 25, 1, 1),
(105, 'DESSERT, CHEESE TART', 14.92, 330, 30, 5, 20),
(106, 'CORDIAL, SARSI (KORDIAL SARSI)', 9.554, 153.01, 38.14, 0.09, 0.01),
(107, 'BEEF, FLOSS (SERUNDING DAGING)', 50, 435, 40.07, 23.08, 20.25),
(108, 'CEREAL, BREAKFAST, BRAN FLAKES', 30, 280, 51.87, 14.6, 1.59),
(109, 'TRADITIONAL MALAYSIAN KUIH, SWEET FERMENTED GLUTINOUS RICE (TAPAI PULUT)', 26.76, 120, 27, 2, NULL),
(110, 'CORDIAL, ORANGE (KORDIAL OREN)', 9.675, 122.35, 30.5, 0.09, 0),
(111, 'KEK PISANG', 15, 316.58, 42.19, 5.67, 13.91),
(112, 'CHEESE, CHESDALE SLICES, PROCESSED (KEPINGAN KEJU CHESDALE)', 19.636, 320, 4, 15, 27),
(113, 'CUTTLEFISH, DRIED, FRIED IN CHILLI (SAMBAL SOTONG KERING)', 30, 283, 18.75, 15.45, 16.29),
(114, 'CAPSICUM, RED (LADA BENGALA, MERAH)', 158.391, 29, 4.93, 0.87, 0.18),
(115, 'CORDIAL, LIME (KORDIAL LIMAU NIPIS)', 11.182, 118.69, 29.57, 0.1, 0),
(116, 'KALE WITH SALTED FISH (KAILAN IKAN MASIN)', NULL, 288, 17.35, 8.23, 20.58),
(117, 'CHEESE, RICOTTA (KEJU, RICOTTA)', 19.295, 150, 3, 11, 10),
(118, 'MILK, UHT, LOW FAT', 200, 45, 5, 2.96, 1.09),
(119, 'HEN EGG, OMEGA 3 (TELUR AYAM OMEGA 3)', 55.445, 56, 0.16, 5.06, 3.72),
(120, 'DOUGHNUT, WITH FROSTING/GLAZE (DONUT)', 60, 450, 50, 5, 25),
(121, 'ICE-CREAM, VANILLA', 100, 200, 22, 4, 11),
(122, 'SAUCE, SOYA, LIGHT', 6.07, 99.64, 22.23, 2.68, NULL),
(123, 'CHEESE, PARMESAN (KEJU PARMESAN)', 20, 430, 4, 38, 29),
(124, 'YOGURT, DRINKING, PASTEURISED, MIX FRUIT FLAVOURS', 250, 80, 15, 3, 1),
(125, 'COCKLES, BOILED (KERANG REBUS)', 100, 136, 14.3, 14.74, 2.18),
(126, 'BUTTER (MENTEGA KURANG MASIN)', 11.993, 715, NULL, NULL, 80),
(127, 'HOT CHOCOLATE, INSTANT PREMIX (WITH SUGAR, COCOA POWDER, CREAMER)', 40, 431, 69.89, 4.34, 14.88),
(128, 'ROJAK PASTE', 23.13, 150, 35, 2, NULL),
(129, 'OIL, CANOLA (MINYAK CANOLA)', 4.675, 896.87, 0.41, NULL, 99.47),
(130, 'CABBAGE, FRIED (KOBIS GORENG)', 100, 159, 10.98, 2.51, 11.68),
(131, 'TEA, CHRYSANTHEMUM, PACKET, WITH ADDED SUGAR', 200, 35, 8, NULL, NULL),
(132, 'PEANUT BUTTER CHOCOLATE STRIPES', 30, 514, 9.97, 20.38, 45.28),
(133, 'TEA, GREEN, WITH ADDED SUGAR', 200, 40, 9, 0.03, NULL),
(134, 'HONEYDEW (TEMBIKAI SUSU)', 165.748, 24, 4.33, 0.35, 0.35),
(135, 'DESSERT, SAGO PORRIDGE (BUBUR SAGU)', 371.07, 240, 47.18, 2.85, 4.47),
(136, 'SWAMP CABBAGE, FRIED WITH BELACAN (KANGKUNG GORENG BELACAN)', 150, 132, 11.63, 3.59, 7.93),
(137, 'GRAPE FLAVOURED CANDY (GULA-GULA PERISA ANGGUR)', 3.563, 400, 98, NULL, NULL),
(138, 'DESSERT, SHAVED ICE, ABC (AIR BATU CAMPUR/AIS KACANG)', 250, 80, 20, NULL, NULL),
(139, 'CHOCOLATE, BREAD SPREAD', 30, 550, 57, 6, 32),
(140, 'TRADITIONAL MALAYSIAN KUIH, SWEET BLACK GLUTINOUS RICE DUMPLING STUFFED WITH GRATED COCONUT FILLING (KUIH KOCI PULUT HITAM)', 35.81, 250, 50, 4, 6),
(141, 'BUN, COCONUT (BAN KELAPA)', 41.15, 295, 51.05, 7.22, 6.88),
(142, 'CAKE, CHOCOLATE (KEK COKLAT)', 74.74, 350, 45, 4, 18),
(143, 'ORANGE FLAVOURED CANDY (GULA-GULA PERISA OREN)', 3.599, 380, 95, NULL, NULL),
(144, 'TAPIOCA CHIPS, BARBEQUE (KEREPEK UBI KAYU PERISA BARBEQUE)', 30, 465.27, 65.53, 1.76, 21.79),
(145, 'GLUCOSE (GLUKOSA)', 15.163, 400, 100, NULL, NULL),
(146, 'ICE CHOCHOLATE BLENDED, WITH WHIPPING CREAM', 250, 235, 58.26, 0.68, 0.11),
(147, 'CRAB, IN COCONUT MILK (KETAM MASAK LEMAK CILI API)', 121.7, 117, 6.17, 9.69, 5.91),
(148, 'LEPAT LABU', 65, 169.23, 32.6, 3, 2.98),
(149, 'CAKE, SWISS ROLL, CHOCOLATE FLAVOUR (KEK SWISS ROLL PERASA COKLAT)', 52.15, 502, 51.18, 10.91, 28.18),
(150, 'CONDENSED/EVAPORATED MILK WITH ROSE CORDIAL (SIRAP BANDUNG)', 200, 77, 8.68, 4.67, 2.66),
(151, 'TRADITIONAL MALAYSIAN KUIH, TWO LAYERED STEAMED CAKE, TARO AND COCONUT MILK (KUIH TALAM KELADI)', 38.47, 200, 35, 3, 5),
(152, 'WAFER, ORANGE FLAVOR (WAFER BERPERISA OREN)', 4.127, 550, 65, 6, 25),
(153, 'BAHULU KEMBOJA', 236.5, 209.53, 26.43, 6.5, 8.65),
(154, 'CHOCOLATE, ROASTED ALMOND (COKLAT DENGAN KACANG BADAM)', 5.43825, 550, 50, 10, 35),
(155, 'CHEESE, BRIE (KEJU, BRIE)', 21.202, 334, 0.5, 19, 28),
(156, 'JAM, STRAWBERRY (JEM STRAWBERI)', 31.72, 274, 67.65, 0.41, 0.02),
(157, 'TEA, KUNDUR, PACKET, WITH ADDED SUGAR', 100, 60, 10, NULL, NULL),
(158, 'CORDIAL, ROSELLE (KORDIAL ROSEL)', 8.286, 209.63, 52.24, 0.16, 0),
(159, 'TRADITIONAL MALAYSIAN KUIH, STEAMED GLUTINOUS RICE BALLS COATED WITH SESAME/PEANUT SUGAR CRUMBS (MOCHI@CHEE-PAH)', 13.32, 392, 68.93, 8.74, 15.58),
(160, 'NASI ARAB SET MANDY (AYAM)', 468.26, 148, 24.53, 3.74, 3.84),
(161, 'CAKE, SPONGE (KEK SPAN)', 18.78, 471, 68.41, 7.12, 18.78),
(162, 'BEEF, SAUSAGE (SOSEJ DAGING LEMBU)', 32.295, 194, 6.1, 12.6, 11.4),
(163, 'MILK, UHT, FULL CREAM', 200, 70, 5, 2.83, 3.42),
(164, 'PLUM SAUCE (SOS PLUM)', 9.567, 220, 50, 1, NULL),
(165, 'JAM, PINEAPPLE (JEM NENAS)', 18.2, 250, 65, NULL, NULL),
(166, 'ROTI JALA', 23, 184.12, 33.25, 5.2, 3.37),
(167, 'CREAMER, POWDER (KRIMER SERBUK)', 9.77, 500, 50, 2, 25),
(168, 'EEEL, SWAMP, RAW (BELUT)', 100, 150, NULL, 18, 2.5),
(169, 'TRADITIONAL MALAYSIAN KUIH, STEAMED RICE FLOUR WITH COCONUT MILK AND SUGAR IN SMALL MOULD, SERVED WITH GRATED COCONUT (KUIH LOMPANG)', 50, 253, 56.77, 2.88, 1.65),
(170, 'DESSERT, BREAD PUDDING', 132.68, 200, 30, 4, 10),
(171, 'CHEESE, CAMEMBERT (KEJU, CAMEMBERT)', 30, 350, 1, 20, 25),
(172, 'SWEET POTATO, RED, CHIPS (KEREPEK UBI KELEDEK)', 30, 456.09, 67.09, 3.82, 19.16),
(173, 'CORDIAL ROOT BEER (KORDIAL RUT BIR)', 11.534, 223.16, 55.64, 0.15, 0),
(174, 'BUN, KAYA (BAN KAYA)', 37.8, 274, 54.73, 7.13, 2.9),
(175, 'CHOCOLATE, CASHEW NUT (COKLAT KACANG GAJUS)', 4.17, 500, 50, 7, 30),
(176, 'CHOCOLATE SPREAD', 25.48, 550, 60, 5, 30),
(177, 'SAUCE, SOYA, DARK', 20, 100, 8, 8, NULL),
(178, 'TAPIOCA CHIPS, BLACK PEPPER (KEREPEK UBI KAYU PERISA LADA HITAM)', 30, 483.05, 64.62, 0.87, 24.57),
(179, 'DESSERT , CARAMEL PUDDING', 36.89, 200, 25, 3, 6),
(180, 'FRUIT JUICE DRINK, ORANGE', 200, 50, 11, NULL, NULL),
(181, 'DESSERT, PUDDING, MILK (PUDING SUSU)', 39.06, 223, 46.99, 2.02, 2.94),
(182, 'TEA MIX, INSTANT', 14.5, 450, 90, 5, 3),
(183, 'CHOCOLATE, MILK (COKLAT SUSU)', 4.2, 550, 50, 7, 30),
(184, 'RICE, BRIYANI (RICE ONLY) (BASMATHI RICE)', 150, 190, 31.61, 2.59, 5.96),
(185, 'SOYA BEAN DRINK, PACKET, WITH REGULAR ADDED SUGAR', 200, 405.99, 96.97, 1.58, 1.31),
(186, 'BUN, CHEESE (BAN KEJU)', 55, 415, 46.06, 12.22, 20.26),
(187, 'LYCHEE IN TIN', 337.1, 70, 16, NULL, NULL),
(188, 'TRADITIONAL MALAYSIAN KUIH, GLUTINOUS RICE FLOUR AND COCONUT MILK BASED, BOILED PALM SUGAR TOFFEE-LIKE SWEET (DODOL)', 15.68, 350, 70, 3, 5),
(189, 'RICE, KERABU (NASI KERABU)(COMPLETE SET)', 168.47, 209, 41.52, 4, 2.99),
(190, 'JAM, MANGO (JEM MEMPELAM)', 31.34, 300, 65, NULL, NULL),
(191, 'ISOTONIC DRINK', 250, 41.23, 10.3, 0.01, NULL),
(192, 'RICE, \"DAGANG\" (NASI DAGANG)(COMPLETE SET)', 214, 196, 28.19, 6.66, 6.33),
(193, 'PASTRY, DANISH', 77.26, 400, 40, 5, 20),
(194, 'CHEESE, BLUE (KEJU, BIRU)', 27.251333, 372, 20.95, 18.06, 27.86),
(195, 'MILK DRINK, CULTURED', 200, 100, 10, 4, 3),
(196, 'CORDIAL, MANGO (KORDIAL MANGGA)', 10.482, 177.69, 44.37, 0.05, 0),
(197, 'CHEESE, MOZARELLA (KEJU MOZARELLA)', 30, 320, 2, 20, 22),
(198, 'BLACK EYE BEAN (KACANG MATA HITAM)', 50, 264.32, 44.6, 20.26, 0.54),
(199, 'VANILLA WAFER (WAFER VANILLA)', 9.664, 510, 75, 4, 22),
(200, 'DRAGON FRUIT, RED (BUAH NAGA, ISI MERAH)', 406.283, 51, 10.9, 1.37, 0.07),
(201, 'KUIH TALAM LABU', 51.5, 160, 35, 2, 2),
(202, 'HONEY', 11.63, 399.81, 99.91, 0.05, NULL),
(203, 'JAM, BLUEBERRY (JEM BLUEBERI)', 28.351, 272, 67.25, 0.31, 0.01),
(204, 'RICE, \"DAGANG\" (NASI DAGANG)(RICE ONLY)', 150, 183, 38.29, 3.27, 1.89),
(205, 'ENERGY DRINK', 250, 45, 11, NULL, NULL),
(206, 'SAUCE, THOUSAND ISLAND', 14.983, 150, 8, 1, 12),
(207, 'CREAM, WHIPPED (KRIM PUTAR)', 10.583, 340, 4, 1, 35),
(208, 'JAM, APRICOT (JEM APRIKOT)', 23.18, 272, 67.21, 0.43, 0),
(209, 'KARIPAP SARDIN', 26, 388.02, 39.76, 8.2, 21.8),
(210, 'MILK, SWEETENED, CONDENSED', 13.17, 320, 55, 7, 5),
(211, 'TRADITIONAL MALAYSIAN KUIH, GLUTINOUS RICE BALLS IN COCONUT MILK (BADAK BERENDAM)', 38.72, 190, 45, 2, 1),
(212, 'DESSERT, LAYER CAKE (KEK LAPIS SARAWAK)', 29.6, 280, 40, 5, 12),
(213, 'TAPIOCA CHIPS, SPICY (KEREPEK UBI KAYU PEDAS)', 30, 470.21, 68.1, 1.24, 21.43),
(214, 'COOKIES, PEANUT', 34.47, 514, 57.86, 11.61, 26.19),
(215, 'CHEESE, COLBY (KEJU, COLBY)', 30, 350, 1, 22, 28),
(216, 'CORDIAL, PINEAPPLE(KORDIAL NENAS)', 7.32, 237, 59.06, 0.19, 0),
(217, 'CARA MANIS', 27, 169.61, 31.27, 4.71, 2.85),
(218, 'STINGRAY, COOKED IN TAMARIND (IKAN PARI MASAK ASAM PEDAS)', 108.98, 115, 2.57, 21.68, 2.58),
(219, 'CATFISH, FRIED IN CHILLI (IKAN KELI GORENG BERLADA)', 174.64, 425, 18.07, 16.27, 31.96),
(220, 'CHOCOLATE (COKLAT BERTIH)', 11, 550, 60, 6, 30),
(221, 'CEREAL, BREAKFAST, CHOCOLATE FLAVOURED', 40, 400, 70, 6, 10),
(222, 'MUFFIN, VANILLA', 58.82, 290, 40, 4, 12),
(223, 'TRADITIONAL MALAYSIAN KUIH, FLOUR AND GHEE SUGAR BASED, SPHERE-SHAPED SWEETS (LADDU)', 63.05, 480, 60, 5, 25),
(224, 'DESSERT, RED TORTOISE CAKE (KUIH ANG KOO)', 60.63, 180, 40, 3, 1),
(225, 'MILK, MALTED (CHOCOLATE FLAVOUR)', 200, 85, 14, 3, 3),
(226, 'MIX VEGETABLES, STIR FRIED (SAYUR CAMPUR GORENG)', 150, 147, 17.86, 4.4, 6.39),
(227, 'FLOUR, SAGO (TEPUNG SAGU)', 30, 348.21, 86.63, 0.2, 0.1),
(228, 'ANCHOVY, DRIED, FRIED IN CHILLI (IKAN BILIS SAMBAL)', 30, 365, 26.87, 16.21, 21.38),
(229, 'MILK, UHT, CHOCOLATE FLAVOUR', 200, 90, 12, 3, 3),
(230, 'CHICKEN CHOP (BELAHAN AYAM TANPA TULANG)', 172.8, 264, 14.15, 16.9, 15.5),
(231, 'POTATO CHIPS, SPICY (KEROPOK UBI KENTANG BERPERISA REMPAH PEDAS)', 30, 483.27, 55.25, 6.05, 26.45);

-- --------------------------------------------------------

--
-- Table structure for table `monthly_performance`
--

CREATE TABLE `monthly_performance` (
  `id` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `percentage` double NOT NULL,
  `year` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `risk_assessment_question`
--

CREATE TABLE `risk_assessment_question` (
  `id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `health_test_id` int(11) NOT NULL,
  `followup_question` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `risk_assessment_scoring_rules`
--

CREATE TABLE `risk_assessment_scoring_rules` (
  `id` int(11) NOT NULL,
  `risk_assessment_question_id` int(11) NOT NULL,
  `condition` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `weight` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `risk_level`
--

CREATE TABLE `risk_level` (
  `id` int(11) NOT NULL,
  `risk_level` varchar(255) NOT NULL,
  `health_test_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `score_max` int(11) NOT NULL,
  `score_min` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `day` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `schedule_task`
--

CREATE TABLE `schedule_task` (
  `id` int(11) NOT NULL,
  `end_date_time` datetime(6) NOT NULL,
  `start_date_time` datetime(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `schedule_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sport`
--

CREATE TABLE `sport` (
  `id` int(11) NOT NULL,
  `calories_burnt_per_hour_per_kg` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sport`
--

INSERT INTO `sport` (`id`, `calories_burnt_per_hour_per_kg`, `name`, `type`) VALUES
(1, 8.5, 'Cycling, mountain bike, bmx', 'CYCLING'),
(2, 4, 'Cycling, <10 mph, leisure bicycling', 'CYCLING'),
(3, 16, 'Cycling, >20 mph, racing', 'CYCLING'),
(4, 6, 'Cycling, 10-11.9 mph, light', 'CYCLING'),
(5, 8, 'Cycling, 12-13.9 mph, moderate', 'CYCLING'),
(6, 10, 'Cycling, 14-15.9 mph, vigorous', 'CYCLING'),
(7, 12, 'Cycling, 16-19 mph, very fast, racing', 'CYCLING'),
(8, 5, 'Unicycling', 'CYCLING'),
(9, 3, 'Stationary cycling, very light', 'CYCLING'),
(10, 5.5, 'Stationary cycling, light', 'CYCLING'),
(11, 7, 'Stationary cycling, moderate', 'CYCLING'),
(12, 10.5, 'Stationary cycling, vigorous', 'CYCLING'),
(13, 12.5, 'Stationary cycling, very vigorous', 'CYCLING'),
(14, 8, 'Calisthenics, vigorous, pushups, situps', 'STRENGTH'),
(15, 3.5, 'Calisthenics, light', 'STRENGTH'),
(16, 8, 'Circuit training, minimal rest', 'STRENGTH'),
(17, 6, 'Weight lifting, body building, vigorous', 'STRENGTH'),
(18, 3, 'Weight lifting, light workout', 'STRENGTH'),
(19, 5.5, 'Health club exercise', 'STRENGTH'),
(20, 9, 'Stair machine', 'CARDIO'),
(21, 3.5, 'Rowing machine, light', 'CARDIO'),
(22, 7, 'Rowing machine, moderate', 'CARDIO'),
(23, 8.5, 'Rowing machine, vigorous', 'CARDIO'),
(24, 12, 'Rowing machine, very vigorous', 'CARDIO'),
(25, 7, 'Ski machine', 'CARDIO'),
(26, 5, 'Aerobics, low impact', 'AEROBICS'),
(27, 7, 'Aerobics, high impact', 'AEROBICS'),
(28, 8.5, 'Aerobics, step aerobics', 'AEROBICS'),
(29, 6.5, 'Aerobics, general', 'AEROBICS'),
(30, 6, 'Jazzercise', 'DANCE'),
(31, 4, 'Stretching, hatha yoga', 'FLEXIBILITY'),
(32, 2.5, 'Mild stretching', 'FLEXIBILITY'),
(33, 6, 'Instructing aerobic class', 'AEROBICS'),
(34, 4, 'Water aerobics', 'AEROBICS'),
(35, 4.5, 'Ballet, twist, jazz, tap', 'DANCE'),
(36, 3, 'Ballroom dancing, slow', 'DANCE'),
(37, 5.5, 'Ballroom dancing, fast', 'DANCE'),
(38, 8, 'Running, 5 mph (12 minute mile)', 'RUNNING'),
(39, 9, 'Running, 5.2 mph (11.5 minute mile)', 'RUNNING'),
(40, 10, 'Running, 6 mph (10 min mile)', 'RUNNING'),
(41, 11, 'Running, 6.7 mph (9 min mile)', 'RUNNING'),
(42, 11.5, 'Running, 7 mph (8.5 min mile)', 'RUNNING'),
(43, 12.5, 'Running, 7.5mph (8 min mile)', 'RUNNING'),
(44, 13.5, 'Running, 8 mph (7.5 min mile)', 'RUNNING'),
(45, 14, 'Running, 8.6 mph (7 min mile)', 'RUNNING'),
(46, 15, 'Running, 9 mph (6.5 min mile)', 'RUNNING'),
(47, 16, 'Running, 10 mph (6 min mile)', 'RUNNING'),
(48, 18, 'Running, 10.9 mph (5.5 min mile)', 'RUNNING'),
(49, 9, 'Running, cross country', 'RUNNING'),
(50, 8, 'Running, general', 'RUNNING'),
(51, 10, 'Running, on a track, team practice', 'RUNNING'),
(52, 15, 'Running, stairs, up', 'RUNNING'),
(53, 4, 'Track and field (shot, discus)', 'TRACK_FIELD'),
(54, 6, 'Track and field (high jump, pole vault)', 'TRACK_FIELD'),
(55, 10, 'Track and field (hurdles)', 'TRACK_FIELD'),
(56, 3.5, 'Archery', 'OTHER'),
(57, 4.5, 'Badminton', 'RACQUET_SPORTS'),
(58, 8, 'Basketball game, competitive', 'BASKETBALL'),
(59, 6, 'Playing basketball, non game', 'BASKETBALL'),
(60, 7, 'Basketball, officiating', 'BASKETBALL'),
(61, 4.5, 'Basketball, shooting baskets', 'BASKETBALL'),
(62, 6.5, 'Basketball, wheelchair', 'BASKETBALL'),
(63, 8, 'Running, training, pushing wheelchair', 'OTHER'),
(64, 2.5, 'Billiards', 'OTHER'),
(65, 3, 'Bowling', 'OTHER'),
(66, 12, 'Boxing, in ring', 'BOXING'),
(67, 6, 'Boxing, punching bag', 'BOXING'),
(68, 9, 'Boxing, sparring', 'BOXING'),
(71, 4, 'Coaching: football, basketball, soccer', 'COACHING'),
(72, 5, 'Cricket (batting, bowling)', 'CRICKET'),
(73, 2.5, 'Croquet', 'OUTDOOR_GAMES'),
(74, 4, 'Curling', 'ICE_SPORTS'),
(75, 2.5, 'Darts (wall or lawn)', 'INDOOR_GAMES'),
(76, 6, 'Fencing', 'COMBAT_SPORTS'),
(77, 9, 'Football, competitive', 'FOOTBALL'),
(78, 8, 'Football, touch, flag, general', 'FOOTBALL'),
(79, 2.5, 'Football or baseball, playing catch', 'BALL_GAMES'),
(80, 3, 'Frisbee playing, general', 'OUTDOOR_GAMES'),
(81, 8, 'Frisbee, ultimate frisbee', 'OUTDOOR_GAMES'),
(82, 4.5, 'Golf, general', 'GOLF'),
(83, 4.5, 'Golf, walking and carrying clubs', 'GOLF'),
(84, 3, 'Golf, driving range', 'GOLF'),
(85, 3, 'Golf, miniature golf', 'GOLF'),
(86, 4.3, 'Golf, walking and pulling clubs', 'GOLF'),
(87, 3.5, 'Golf, using power cart', 'GOLF'),
(88, 4, 'Gymnastics', 'GYMNASTICS'),
(89, 4, 'Hacky sack', 'OUTDOOR_GAMES'),
(90, 12, 'Handball', 'BALL_GAMES'),
(91, 8, 'Handball, team', 'BALL_GAMES'),
(92, 8, 'Hockey, field hockey', 'HOCKEY'),
(93, 8, 'Hockey, ice hockey', 'HOCKEY'),
(94, 4, 'Riding a horse, general', 'EQUESTRIAN'),
(95, 3.5, 'Horseback riding, saddling horse', 'EQUESTRIAN'),
(96, 3.5, 'Horseback riding, grooming horse', 'EQUESTRIAN'),
(97, 6.5, 'Horseback riding, trotting', 'EQUESTRIAN'),
(98, 2.5, 'Horseback riding, walking', 'EQUESTRIAN'),
(99, 8, 'Horse racing, galloping', 'EQUESTRIAN'),
(100, 6, 'Horse grooming, moderate', 'EQUESTRIAN'),
(101, 3, 'Horseshoe pitching', 'OUTDOOR_GAMES'),
(102, 12, 'Jai alai', 'BALL_GAMES'),
(103, 10, 'Martial arts, judo, karate, jujitsu', 'MARTIAL_ARTS'),
(104, 10, 'Martial arts, kick boxing', 'MARTIAL_ARTS'),
(105, 10, 'Martial arts, tae kwan do', 'MARTIAL_ARTS'),
(106, 10, 'Krav maga training', 'MARTIAL_ARTS'),
(107, 4, 'Juggling', 'SKILL_GAMES'),
(108, 7, 'Kickball', 'BALL_GAMES'),
(109, 8, 'Lacrosse', 'BALL_GAMES'),
(110, 9, 'Orienteering', 'OUTDOOR_ACTIVITIES'),
(111, 6, 'Playing paddleball', 'RACQUET_SPORTS'),
(112, 10, 'Paddleball, competitive', 'RACQUET_SPORTS'),
(113, 8, 'Polo', 'EQUESTRIAN'),
(114, 10, 'Racquetball, competitive', 'RACQUET_SPORTS'),
(115, 7, 'Playing racquetball', 'RACQUET_SPORTS'),
(116, 11, 'Rock climbing, ascending rock', 'CLIMBING'),
(117, 8, 'Rock climbing, rappelling', 'CLIMBING'),
(118, 12, 'Jumping rope, fast', 'CARDIO'),
(119, 10, 'Jumping rope, moderate', 'CARDIO'),
(120, 8, 'Jumping rope, slow', 'CARDIO'),
(121, 10, 'Rugby', 'BALL_GAMES'),
(122, 3, 'Shuffleboard, lawn bowling', 'OUTDOOR_GAMES'),
(123, 5, 'Skateboarding', 'BOARD_SPORTS'),
(124, 7, 'Roller skating', 'SKATING'),
(125, 12, 'Roller blading, in-line skating', 'SKATING'),
(126, 3, 'Sky diving', 'EXTREME_SPORTS'),
(127, 10, 'Soccer, competitive', 'SOCCER'),
(128, 7, 'Playing soccer', 'SOCCER'),
(129, 5, 'Softball or baseball', 'BALL_GAMES'),
(130, 4, 'Softball, officiating', 'BALL_GAMES'),
(131, 6, 'Softball, pitching', 'BALL_GAMES'),
(132, 12, 'Squash', 'RACQUET_SPORTS'),
(133, 4, 'Table tennis, ping pong', 'RACQUET_SPORTS'),
(134, 4, 'Tai chi', 'MARTIAL_ARTS'),
(135, 7, 'Playing tennis', 'RACQUET_SPORTS'),
(136, 6, 'Tennis, doubles', 'RACQUET_SPORTS'),
(137, 8, 'Tennis, singles', 'RACQUET_SPORTS'),
(138, 3.5, 'Trampoline', 'GYMNASTICS'),
(139, 8, 'Volleyball, competitive', 'VOLLEYBALL'),
(140, 3, 'Playing volleyball', 'VOLLEYBALL'),
(141, 8, 'Volleyball, beach', 'VOLLEYBALL'),
(142, 6, 'Wrestling', 'COMBAT_SPORTS'),
(143, 7, 'Wallyball', 'BALL_GAMES'),
(144, 7, 'Backpacking, Hiking with pack', 'HIKING'),
(145, 3.5, 'Carrying infant, level ground', 'DAILY_ACTIVITIES'),
(146, 5, 'Carrying infant, upstairs', 'DAILY_ACTIVITIES'),
(147, 6, 'Carrying 16 to 24 lbs, upstairs', 'DAILY_ACTIVITIES'),
(148, 8, 'Carrying 25 to 49 lbs, upstairs', 'DAILY_ACTIVITIES'),
(149, 2.8, 'Standing, playing with children, light', 'DAILY_ACTIVITIES'),
(150, 4, 'Walk/run, playing with children, moderate', 'DAILY_ACTIVITIES'),
(151, 5, 'Walk/run, playing with children, vigorous', 'DAILY_ACTIVITIES'),
(152, 3, 'Carrying small children', 'DAILY_ACTIVITIES'),
(153, 3, 'Loading, unloading car', 'DAILY_ACTIVITIES'),
(154, 7, 'Climbing hills, carrying up to 9 lbs', 'HIKING'),
(155, 7.5, 'Climbing hills, carrying 10 to 20 lb', 'HIKING'),
(156, 8, 'Climbing hills, carrying 21 to 42 lb', 'HIKING'),
(157, 9, 'Climbing hills, carrying over 42 lb', 'HIKING'),
(158, 3, 'Walking downstairs', 'WALKING'),
(159, 6, 'Hiking, cross country', 'HIKING'),
(160, 2.5, 'Bird watching', 'OUTDOOR_ACTIVITIES'),
(161, 6.5, 'Marching, rapidly, military', 'WALKING'),
(162, 5, 'Children\'s games, hopscotch, dodgeball', 'CHILDREN_GAMES'),
(163, 2.5, 'Pushing stroller or walking with children', 'DAILY_ACTIVITIES'),
(164, 4, 'Pushing a wheelchair', 'DAILY_ACTIVITIES'),
(165, 6.5, 'Race walking', 'WALKING'),
(166, 8, 'Rock climbing, mountain climbing', 'CLIMBING'),
(167, 5, 'Walking using crutches', 'WALKING'),
(168, 3, 'Walking the dog', 'DAILY_ACTIVITIES'),
(169, 2, 'Walking, under 2.0 mph, very slow', 'WALKING'),
(170, 2.5, 'Walking 2.0 mph, slow', 'WALKING'),
(171, 3, 'Walking 2.5 mph', 'WALKING'),
(172, 3.3, 'Walking 3.0 mph, moderate', 'WALKING'),
(173, 3.8, 'Walking 3.5 mph, brisk pace', 'WALKING'),
(174, 6, 'Walking 3.5 mph, uphill', 'WALKING'),
(175, 5, 'Walking 4.0 mph, very brisk', 'WALKING'),
(176, 6.3, 'Walking 4.5 mph', 'WALKING'),
(177, 8, 'Walking 5.0 mph', 'WALKING'),
(178, 2.5, 'Boating, power, speed boat', 'WATER_ACTIVITIES'),
(179, 4, 'Canoeing, camping trip', 'WATER_ACTIVITIES'),
(180, 3, 'Canoeing, rowing, light', 'WATER_ACTIVITIES'),
(181, 7, 'Canoeing, rowing, moderate', 'WATER_ACTIVITIES'),
(182, 12, 'Canoeing, rowing, vigorous', 'WATER_ACTIVITIES'),
(183, 12, 'Crew, sculling, rowing, competition', 'WATER_ACTIVITIES'),
(184, 5, 'Kayaking', 'WATER_ACTIVITIES'),
(185, 4, 'Paddle boat', 'WATER_ACTIVITIES'),
(186, 3, 'Windsurfing, sailing', 'WATER_ACTIVITIES'),
(187, 5, 'Sailing, competition', 'WATER_ACTIVITIES'),
(188, 3, 'Sailing, yachting, ocean sailing', 'WATER_ACTIVITIES'),
(189, 6, 'Skiing, water skiing', 'WATER_SPORTS'),
(190, 7, 'Ski mobiling', 'WINTER_SPORTS'),
(191, 16, 'Skin diving, fast', 'WATER_ACTIVITIES'),
(192, 12.5, 'Skin diving, moderate', 'WATER_ACTIVITIES'),
(193, 7, 'Skin diving, scuba diving', 'WATER_ACTIVITIES'),
(194, 5, 'Snorkeling', 'WATER_ACTIVITIES'),
(195, 3, 'Surfing, body surfing or board surfing', 'WATER_SPORTS'),
(196, 5, 'Whitewater rafting, kayaking, canoeing', 'WATER_SPORTS'),
(197, 10, 'Swimming laps, freestyle, fast', 'SWIMMING'),
(198, 7, 'Swimming laps, freestyle, slow', 'SWIMMING'),
(199, 7, 'Swimming backstroke', 'SWIMMING'),
(200, 10, 'Swimming breaststroke', 'SWIMMING'),
(201, 11, 'Swimming butterfly', 'SWIMMING'),
(202, 6, 'Swimming leisurely, not laps', 'SWIMMING'),
(203, 8, 'Swimming sidestroke', 'SWIMMING'),
(204, 8, 'Swimming synchronized', 'SWIMMING'),
(205, 10, 'Swimming, treading water, fast, vigorous', 'SWIMMING'),
(206, 4, 'Swimming, treading water, moderate', 'SWIMMING'),
(207, 4, 'Water aerobics, water calisthenics', 'WATER_ACTIVITIES'),
(208, 10, 'Water polo', 'WATER_SPORTS'),
(209, 3, 'Water volleyball', 'WATER_SPORTS'),
(210, 8, 'Water jogging', 'WATER_ACTIVITIES'),
(211, 3, 'Diving, springboard or platform', 'WATER_SPORTS'),
(212, 5.5, 'Ice skating, < 9 mph', 'ICE_SPORTS'),
(213, 7, 'Ice skating, average speed', 'ICE_SPORTS'),
(214, 9, 'Ice skating, rapidly', 'ICE_SPORTS'),
(215, 15, 'Speed skating, ice, competitive', 'ICE_SPORTS'),
(216, 7, 'Cross country snow skiing, slow', 'WINTER_SPORTS'),
(217, 8, 'Cross country skiing, moderate', 'WINTER_SPORTS'),
(218, 9, 'Cross country skiing, vigorous', 'WINTER_SPORTS'),
(219, 14, 'Cross country skiing, racing', 'WINTER_SPORTS'),
(220, 16.5, 'Cross country skiing, uphill', 'WINTER_SPORTS'),
(221, 5, 'Snow skiing, downhill skiing, light', 'WINTER_SPORTS'),
(222, 6, 'Downhill snow skiing, moderate', 'WINTER_SPORTS'),
(223, 8, 'Downhill snow skiing, racing', 'WINTER_SPORTS'),
(224, 7, 'Sledding, tobagganing, luge', 'WINTER_SPORTS'),
(225, 8, 'Snow shoeing', 'WINTER_SPORTS'),
(226, 3.5, 'Snowmobiling', 'WINTER_SPORTS'),
(227, 3.5, 'General housework', 'HOUSEHOLD'),
(228, 5, 'Cleaning gutters', 'HOUSEHOLD'),
(229, 4.5, 'Painting', 'HOUSEHOLD'),
(230, 2.5, 'Sit, playing with animals', 'DAILY_ACTIVITIES'),
(231, 4, 'Walk / run, playing with animals', 'DAILY_ACTIVITIES'),
(232, 3.5, 'Bathing dog', 'DAILY_ACTIVITIES'),
(233, 5.5, 'Mowing lawn, walk, power mower', 'YARD_WORK'),
(234, 2.5, 'Mowing lawn, riding mower', 'YARD_WORK'),
(235, 3.5, 'Walking, snow blower', 'YARD_WORK'),
(236, 3, 'Riding, snow blower', 'YARD_WORK'),
(237, 6, 'Shoveling snow by hand', 'YARD_WORK'),
(238, 4.3, 'Raking lawn', 'YARD_WORK'),
(239, 4, 'Gardening, general', 'YARD_WORK'),
(240, 4, 'Bagging grass, leaves', 'YARD_WORK'),
(241, 1.5, 'Watering lawn or garden', 'YARD_WORK'),
(242, 4.5, 'Weeding, cultivating garden', 'YARD_WORK'),
(243, 3.5, 'Carpentry, general', 'HOUSEHOLD'),
(244, 8, 'Carrying heavy loads', 'DAILY_ACTIVITIES'),
(245, 8, 'Carrying moderate loads upstairs', 'DAILY_ACTIVITIES'),
(246, 3.5, 'General cleaning', 'HOUSEHOLD'),
(247, 2.5, 'Cleaning, dusting', 'HOUSEHOLD'),
(248, 3, 'Taking out trash', 'HOUSEHOLD'),
(249, 4, 'Walking, pushing a wheelchair', 'DAILY_ACTIVITIES'),
(250, 4, 'Teach physical education,exercise class', 'TEACHING');

-- --------------------------------------------------------

--
-- Table structure for table `todo`
--

CREATE TABLE `todo` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `todo_task`
--

CREATE TABLE `todo_task` (
  `id` int(11) NOT NULL,
  `end_time` datetime(6) NOT NULL,
  `is_complete` tinyint(1) NOT NULL DEFAULT 0,
  `start_time` datetime(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `todo_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gender` char(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `weight` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `goal_calories` int(11) DEFAULT NULL,
  `area_of_living` varchar(255) DEFAULT NULL,
  `health_history` varchar(255) DEFAULT NULL,
  `no_of_family_member` int(11) DEFAULT NULL,
  `occupation_time` varchar(255) DEFAULT NULL,
  `occupation_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `age`, `email`, `gender`, `name`, `password`, `weight`, `height`, `goal_calories`, `area_of_living`, `health_history`, `no_of_family_member`, `occupation_time`, `occupation_type`) VALUES
(1, 20, 'example@gmail.com', 'F', 'Miss Example', '123456', 55, 160, 2000, 'Bukit Jalil', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_meal`
--

CREATE TABLE `user_meal` (
  `id` int(11) NOT NULL,
  `amount_in_grams` double NOT NULL,
  `calories` double DEFAULT NULL,
  `carbs_in_grams` double DEFAULT NULL,
  `date` date NOT NULL,
  `fat_in_grams` double DEFAULT NULL,
  `meal_type` enum('BREAKFAST','DINNER','LUNCH','SNACK') NOT NULL,
  `protein_in_grams` double DEFAULT NULL,
  `meal_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_meal`
--

INSERT INTO `user_meal` (`id`, `amount_in_grams`, `calories`, `carbs_in_grams`, `date`, `fat_in_grams`, `meal_type`, `protein_in_grams`, `meal_id`, `user_id`) VALUES
(4, 29, 137.17, 16.89, '2024-12-14', 6.21, 'BREAKFAST', 2.56, 24, 1),
(6, 38.09, 45.71, 5.71, '2024-12-15', 2.29, 'LUNCH', 0.38, 28, 1),
(12, 29, 137.17, 16.89, '2024-12-15', 6.21, 'BREAKFAST', 2.56, 24, 1),
(13, 200, 414, 103.08, '2024-12-15', 0.04, 'BREAKFAST', 0.34, 13, 1),
(14, 1, 0.61, 0.05, '2024-12-15', 0.03, 'BREAKFAST', 0.03, 35, 1),
(21, 337.1, 235.97, 53.94, '2024-12-15', NULL, 'DINNER', NULL, 187, 1),
(33, 13.8, 85.67, NULL, '2024-12-15', 9.66, 'SNACK', 0.04, 3, 1),
(39, 123, 86.1, 19.68, '2024-12-15', NULL, 'BREAKFAST', NULL, 187, 1),
(40, 12, 47.04, 6.26, '2024-12-15', 2.16, 'BREAKFAST', 0.66, 45, 1),
(41, 12, 24.84, 6.18, '2024-12-15', 0, 'BREAKFAST', 0.02, 13, 1),
(42, 123, 86.1, 19.68, '2024-12-15', NULL, 'BREAKFAST', NULL, 187, 1),
(43, 123, 638.37, 0.17, '2024-12-15', 71.76, 'BREAKFAST', 0.74, 1, 1),
(45, 123, 254.61, 63.39, '2024-12-15', 0.02, 'DINNER', 0.21, 13, 1),
(46, 123, 86.1, 19.68, '2024-12-15', NULL, 'BREAKFAST', NULL, 187, 1),
(47, 23, 47.61, 11.85, '2024-12-15', 0, 'SNACK', 0.04, 13, 1),
(48, 337.1, 235.97, 53.94, '2024-12-15', NULL, 'BREAKFAST', NULL, 187, 1),
(65, 123, 254.61, 63.39, '2024-12-16', 0.02, 'LUNCH', 0.21, 13, 1),
(67, 48.36, 178.93, 32.79, '2024-12-18', 5.3, 'LUNCH', 1.38, 6, 1),
(71, 200, 200, 20, '2024-12-18', 6, 'DINNER', 8, 195, 1),
(77, 100, 207, 51.54, '2024-12-18', 0.02, 'SNACK', 0.17, 13, 1),
(78, 200, 122, 9.6, '2024-12-18', 6.6, 'BREAKFAST', 6.4, 35, 1),
(86, 123, 147.6, 18.45, '2024-12-30', 7.38, 'BREAKFAST', 1.23, 28, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_score`
--

CREATE TABLE `user_score` (
  `id` int(11) NOT NULL,
  `test_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_sport`
--

CREATE TABLE `user_sport` (
  `id` int(11) NOT NULL,
  `calories_burnt` double NOT NULL,
  `date` date NOT NULL,
  `duration_in_hours` double NOT NULL,
  `sport_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_sport`
--

INSERT INTO `user_sport` (`id`, `calories_burnt`, `date`, `duration_in_hours`, `sport_id`, `user_id`) VALUES
(7, 660, '2024-12-15', 1, 24, 1),
(10, 660, '2024-12-15', 1, 182, 1),
(13, 440, '2024-12-15', 1, 14, 1),
(15, 495, '2024-12-15', 1, 218, 1),
(19, 5610, '2024-12-15', 12, 1, 1),
(26, 96.25, '2024-12-16', 0.5833333333333334, 9, 1),
(34, 467.5, '2024-12-18', 1, 23, 1),
(35, 550, '2024-12-18', 0.8333333333333334, 183, 1),
(40, 256.6666666666667, '2024-12-18', 0.5833333333333334, 14, 1),
(49, 233.75, '2024-12-30', 0.5, 23, 1),
(56, 660, '2025-01-01', 1, 24, 1);

-- --------------------------------------------------------

--
-- Table structure for table `weekly_performance`
--

CREATE TABLE `weekly_performance` (
  `id` int(11) NOT NULL,
  `end_date` date NOT NULL,
  `percentage` double NOT NULL,
  `start_date` date NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `daily_cateogry_percentage`
--
ALTER TABLE `daily_cateogry_percentage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7w96q4eujlfavoq6f9rxaogra` (`user_id`);

--
-- Indexes for table `daily_performance`
--
ALTER TABLE `daily_performance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1dm88iow138ixkcdmpbwvv15k` (`user_id`);

--
-- Indexes for table `health_test`
--
ALTER TABLE `health_test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `meal`
--
ALTER TABLE `meal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `monthly_performance`
--
ALTER TABLE `monthly_performance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2py6pmexgdjvhb0e3pciafwi5` (`user_id`);

--
-- Indexes for table `risk_assessment_question`
--
ALTER TABLE `risk_assessment_question`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKgk00f36ojtpygnj29972b3ra8` (`followup_question`),
  ADD KEY `FKhw406sp0a4wd31vcmtfpk9c6x` (`health_test_id`);

--
-- Indexes for table `risk_assessment_scoring_rules`
--
ALTER TABLE `risk_assessment_scoring_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbgs3jvcay7btu1s30kj45lim0` (`risk_assessment_question_id`);

--
-- Indexes for table `risk_level`
--
ALTER TABLE `risk_level`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhuteb0sgjlavt3my3cmnjco46` (`health_test_id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa50n59y1j4a6qwa42p8jiguds` (`user_id`);

--
-- Indexes for table `schedule_task`
--
ALTER TABLE `schedule_task`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnjlqddci8gqo3qym2olwqssim` (`schedule_id`);

--
-- Indexes for table `sport`
--
ALTER TABLE `sport`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `todo`
--
ALTER TABLE `todo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2ft3dfk1d3uw77pas3xqwymm7` (`user_id`);

--
-- Indexes for table `todo_task`
--
ALTER TABLE `todo_task`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjf0kcetseqhyjktgdxlfauidn` (`todo_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_meal`
--
ALTER TABLE `user_meal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmsj87125lkdjxqkao2nxo0y3a` (`meal_id`),
  ADD KEY `FK7thj0rs404250o81vjl46ifhd` (`user_id`);

--
-- Indexes for table `user_score`
--
ALTER TABLE `user_score`
  ADD PRIMARY KEY (`id`),
  ADD KEY `test_id` (`test_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_sport`
--
ALTER TABLE `user_sport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj0w7peexst59vbyyr4ohet5cc` (`sport_id`),
  ADD KEY `FKjv60l7te5e3vqs07pf4rtl2dh` (`user_id`);

--
-- Indexes for table `weekly_performance`
--
ALTER TABLE `weekly_performance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgaf54wght46abs382hhjdu6i7` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `daily_cateogry_percentage`
--
ALTER TABLE `daily_cateogry_percentage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `daily_performance`
--
ALTER TABLE `daily_performance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `health_test`
--
ALTER TABLE `health_test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `meal`
--
ALTER TABLE `meal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=283;

--
-- AUTO_INCREMENT for table `monthly_performance`
--
ALTER TABLE `monthly_performance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `risk_assessment_question`
--
ALTER TABLE `risk_assessment_question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `risk_assessment_scoring_rules`
--
ALTER TABLE `risk_assessment_scoring_rules`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `risk_level`
--
ALTER TABLE `risk_level`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `schedule_task`
--
ALTER TABLE `schedule_task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sport`
--
ALTER TABLE `sport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=270;

--
-- AUTO_INCREMENT for table `todo`
--
ALTER TABLE `todo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `todo_task`
--
ALTER TABLE `todo_task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_meal`
--
ALTER TABLE `user_meal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT for table `user_score`
--
ALTER TABLE `user_score`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_sport`
--
ALTER TABLE `user_sport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `weekly_performance`
--
ALTER TABLE `weekly_performance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `daily_cateogry_percentage`
--
ALTER TABLE `daily_cateogry_percentage`
  ADD CONSTRAINT `FK7w96q4eujlfavoq6f9rxaogra` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `daily_performance`
--
ALTER TABLE `daily_performance`
  ADD CONSTRAINT `FK1dm88iow138ixkcdmpbwvv15k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `monthly_performance`
--
ALTER TABLE `monthly_performance`
  ADD CONSTRAINT `FK2py6pmexgdjvhb0e3pciafwi5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `risk_assessment_question`
--
ALTER TABLE `risk_assessment_question`
  ADD CONSTRAINT `FKh384fld2dkxejee0d9l494t1n` FOREIGN KEY (`followup_question`) REFERENCES `risk_assessment_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKhw406sp0a4wd31vcmtfpk9c6x` FOREIGN KEY (`health_test_id`) REFERENCES `health_test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `risk_assessment_scoring_rules`
--
ALTER TABLE `risk_assessment_scoring_rules`
  ADD CONSTRAINT `FKbgs3jvcay7btu1s30kj45lim0` FOREIGN KEY (`risk_assessment_question_id`) REFERENCES `risk_assessment_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `risk_level`
--
ALTER TABLE `risk_level`
  ADD CONSTRAINT `FKhuteb0sgjlavt3my3cmnjco46` FOREIGN KEY (`health_test_id`) REFERENCES `health_test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `FKa50n59y1j4a6qwa42p8jiguds` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `schedule_task`
--
ALTER TABLE `schedule_task`
  ADD CONSTRAINT `FKnjlqddci8gqo3qym2olwqssim` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `todo`
--
ALTER TABLE `todo`
  ADD CONSTRAINT `FK2ft3dfk1d3uw77pas3xqwymm7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `todo_task`
--
ALTER TABLE `todo_task`
  ADD CONSTRAINT `FKjf0kcetseqhyjktgdxlfauidn` FOREIGN KEY (`todo_id`) REFERENCES `todo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_meal`
--
ALTER TABLE `user_meal`
  ADD CONSTRAINT `FK7thj0rs404250o81vjl46ifhd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKmsj87125lkdjxqkao2nxo0y3a` FOREIGN KEY (`meal_id`) REFERENCES `meal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_score`
--
ALTER TABLE `user_score`
  ADD CONSTRAINT `user_score_ibfk_1` FOREIGN KEY (`test_id`) REFERENCES `health_test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_score_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_sport`
--
ALTER TABLE `user_sport`
  ADD CONSTRAINT `FKj0w7peexst59vbyyr4ohet5cc` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKjv60l7te5e3vqs07pf4rtl2dh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `weekly_performance`
--
ALTER TABLE `weekly_performance`
  ADD CONSTRAINT `FKgaf54wght46abs382hhjdu6i7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
