-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Creato il: Set 28, 2024 alle 13:35
-- Versione del server: 5.7.39
-- Versione PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `labair`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `carrello`
--

CREATE TABLE `carrello` (
  `id` int(11) NOT NULL,
  `p_utente` int(11) NOT NULL,
  `importo` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `carrello`
--

INSERT INTO `carrello` (`id`, `p_utente`, `importo`) VALUES
(22, 55, NULL),
(27, 54, 0.00),
(28, 56, NULL),
(29, 57, 0.00),
(30, 58, NULL),
(31, 59, NULL),
(32, 60, 0.00);

-- --------------------------------------------------------

--
-- Struttura della tabella `carrello_scarpe`
--

CREATE TABLE `carrello_scarpe` (
  `id` int(11) NOT NULL,
  `p_scarpa` int(11) NOT NULL,
  `p_taglia` int(11) NOT NULL,
  `p_colore` int(11) NOT NULL,
  `p_carrello` int(11) NOT NULL,
  `quantita` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `descrizione` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `categorie`
--

INSERT INTO `categorie` (`id`, `descrizione`) VALUES
(1, 'Sneakers'),
(2, 'Running'),
(3, 'Lifestyle'),
(4, 'Casual'),
(5, 'Basketball'),
(6, 'Trail Running'),
(7, 'Walking'),
(8, 'Outdoor'),
(9, 'Tennis');

-- --------------------------------------------------------

--
-- Struttura della tabella `colori`
--

CREATE TABLE `colori` (
  `id` int(11) NOT NULL,
  `colore` varchar(50) NOT NULL,
  `esadecimale` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `colori`
--

INSERT INTO `colori` (`id`, `colore`, `esadecimale`) VALUES
(1, 'Nero', NULL),
(2, 'Bianco', NULL),
(3, 'Grigio', NULL),
(4, 'Blu', NULL),
(5, 'Rosso', NULL),
(6, 'Verde', NULL),
(7, 'Giallo', NULL),
(8, 'Arancione', NULL),
(9, 'Rosa', NULL),
(10, 'Marrone', NULL),
(11, 'Cyan', NULL),
(12, 'Magenta', NULL),
(13, 'Turchese', NULL),
(14, 'Lilla', NULL),
(15, 'Beige', NULL),
(16, 'Menta', NULL),
(17, 'Oro', NULL),
(18, 'Argento', NULL),
(19, 'Lavanda', NULL),
(20, 'Bordeaux', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `Indirizzi`
--

CREATE TABLE `Indirizzi` (
  `id` int(11) NOT NULL,
  `indirizzo` varchar(100) NOT NULL,
  `civico` varchar(10) NOT NULL,
  `cap` char(5) NOT NULL,
  `citta` varchar(100) NOT NULL,
  `provincia` char(2) NOT NULL,
  `paese` varchar(50) DEFAULT NULL,
  `p_utente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Indirizzi`
--

INSERT INTO `Indirizzi` (`id`, `indirizzo`, `civico`, `cap`, `citta`, `provincia`, `paese`, `p_utente`) VALUES
(24, 'Via Delle Pilozze', '22', '00018', 'Palombara Sabina', 'Rm', 'Italia', 54),
(25, 'Via Delle Pilozze', '22', '00018', 'Palombara Sabina', 'Rm', 'Italia', 55),
(26, 'Via delle Ninfee', '25', '00160', 'Cerveteri', 'RM', 'Italia', 56),
(27, 'Via delle merendine', '33', '00012', 'Firenze', 'FI', 'Italia', 57),
(28, 'Via Dei Pini', '25', '00123', 'Roma', 'rm', 'Italia', 58),
(29, 'Via delle Roscie', '23', '00120', 'Roma', 'Rm', 'Italia', 59),
(30, 'Via Roma', '27', '00120', 'Roma', 'Rm', 'Italia', 60);

-- --------------------------------------------------------

--
-- Struttura della tabella `ordini`
--

CREATE TABLE `ordini` (
  `id` int(11) NOT NULL,
  `data` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `spese_spedizione` decimal(10,2) DEFAULT NULL,
  `importo` double NOT NULL,
  `p_utente` int(11) NOT NULL,
  `p_Indirizzo` int(11) NOT NULL,
  `p_pagamento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `ordini`
--

INSERT INTO `ordini` (`id`, `data`, `spese_spedizione`, `importo`, `p_utente`, `p_Indirizzo`, `p_pagamento`) VALUES
(32, '2024-09-21 00:00:00', '7.50', 117.49, 54, 24, 13),
(33, '2024-09-21 00:00:00', '0.00', 219.98, 54, 24, 14),
(34, '2024-09-21 00:00:00', '0.00', 269.98, 54, 24, 15),
(35, '2024-09-21 00:00:00', '0.00', 159.99, 54, 24, 16),
(36, '2024-09-21 00:00:00', '7.50', 117.49, 54, 24, 17),
(37, '2024-09-21 00:00:00', '0.00', 159.99, 54, 24, 18),
(38, '2024-09-22 00:00:00', '0.00', 289.98, 54, 24, 19),
(39, '2024-09-22 00:00:00', '0.00', 219.98, 57, 27, 20),
(40, '2024-09-22 00:00:00', '0.00', 269.98, 54, 24, 21),
(41, '2024-09-22 00:00:00', '0.00', 269.98, 54, 24, 22),
(42, '2024-09-22 00:00:00', '0.00', 159.99, 54, 24, 23),
(43, '2024-09-23 00:00:00', '0.00', 289.98, 54, 24, 24),
(44, '2024-09-24 00:00:00', '0.00', 159.99, 54, 24, 25),
(45, '2024-09-24 00:00:00', '7.50', 117.49, 60, 30, 26),
(46, '2024-09-24 00:00:00', '0.00', 269.98, 54, 24, 27);

-- --------------------------------------------------------

--
-- Struttura della tabella `pagamenti`
--

CREATE TABLE `pagamenti` (
  `id` int(11) NOT NULL,
  `metodo_pagamento` varchar(50) NOT NULL,
  `numero_carta` varchar(50) DEFAULT NULL,
  `data_scadenza` datetime DEFAULT NULL,
  `cvv` int(4) NOT NULL,
  `importo` decimal(10,2) NOT NULL,
  `data_pagamento` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `p_utente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `pagamenti`
--

INSERT INTO `pagamenti` (`id`, `metodo_pagamento`, `numero_carta`, `data_scadenza`, `cvv`, `importo`, `data_pagamento`, `p_utente`) VALUES
(13, 'carta credito', '2133132132123131312', '2024-12-01 00:00:00', 123, '117.49', '2024-09-21 00:00:00', 54),
(14, 'carta credito', '2133132132123131312', '2024-11-01 00:00:00', 123, '219.98', '2024-09-21 00:00:00', 54),
(15, 'carta credito', '2133132132123131312', '2024-12-01 00:00:00', 123, '269.98', '2024-09-21 00:00:00', 54),
(16, 'carta credito', '2133132132123131312', '2024-12-01 00:00:00', 123, '159.99', '2024-09-21 00:00:00', 54),
(17, 'carta credito', '2133132132123131312', '2024-12-01 00:00:00', 123, '117.49', '2024-09-21 00:00:00', 54),
(18, 'carta credito', '2133132132123131312', '2024-12-01 00:00:00', 123, '159.99', '2024-09-21 00:00:00', 54),
(19, 'carta credito', '2332133131233131', '2024-11-01 00:00:00', 123, '289.98', '2024-09-22 00:00:00', 54),
(20, 'carta credito', '23313123131231313', '2024-11-01 00:00:00', 321, '219.98', '2024-09-22 00:00:00', 57),
(21, 'carta credito', '1233123123131313', '2024-12-01 00:00:00', 131, '269.98', '2024-09-22 00:00:00', 54),
(22, 'carta credito', '2312312313123132', '2024-12-01 00:00:00', 123, '269.98', '2024-09-22 00:00:00', 54),
(23, 'carta credito', '3424242342424234', '2024-12-01 00:00:00', 242, '159.99', '2024-09-22 00:00:00', 54),
(24, 'carta credito', '213131313123131231', '2024-12-01 00:00:00', 132, '289.98', '2024-09-23 00:00:00', 54),
(25, 'carta credito', '1233131313212313', '2024-12-01 00:00:00', 123, '159.99', '2024-09-24 00:00:00', 54),
(26, 'carta credito', '2321321313131313', '2024-11-01 00:00:00', 123, '117.49', '2024-09-24 00:00:00', 60),
(27, 'carta credito', '43242423423424234', '2024-11-01 00:00:00', 231, '269.98', '2024-09-24 00:00:00', 54);

-- --------------------------------------------------------

--
-- Struttura della tabella `profili`
--

CREATE TABLE `profili` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `token` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `profili`
--

INSERT INTO `profili` (`id`, `username`, `password`, `token`) VALUES
(5, 'nicola.giovannini@gmail.it', '$2a$10$NiJL/7Rpy34uzNJaIBVGDO947Y4mqEjX1fFNEnHUEoQu9QZgfOjM.', NULL),
(18, 'gianni.verdi', '$2a$10$7bbyMQDloNKcea1MsYBViu0MI.o02H8Lb4zM3pGR8fyA.ydB2LTSa', NULL),
(19, 'nicola.pasquarelli', '$2a$10$ZG0uguDfdEqGKCHoTMDE7e/uWTtaH9qvB5USOX9brWANyTgapVahy', NULL),
(27, 'laura.verdana', '$2a$10$WtFi0grB/dBWVwC5DGOhSOc/bvwYsI.qeWh9ao14UXMgIhard7siu', NULL),
(32, 'clara.vergari95', '$2a$10$MgrOGc96BHyqBvZb9NTihuc50luLHERhI5qUFG0pVRDBIp/CIXmgK', NULL),
(51, 'Nicola', '$2a$10$KlKMyBf1VlGkTS2j0eNFVeJp6LqpjGp/Mi0r6icJZjO8gT7LxySxu', 'Tmljb2xh_1726821129000'),
(52, 'Nicola', '$2a$10$MH4z7Qt9MhuVPsFcrQ2HROZN7PPR5sj984Fy8nIX4RohfcDIdFbRm', 'Tmljb2xh_1727198122000'),
(53, 'clara.ver', '$2a$10$.LDg5.5FjD49RTqmkYGpy.gqz7zQP17Cz2zZ18V4UDbRd2LGwg2Ae', NULL),
(54, 'laura.verdana', '$2a$10$Hmc9tAxmaVsfS8knGrXmbe6HbZhNjyEG0An0vrwKNjOWg9lEd4BKS', NULL),
(55, 'puzzettapiccoletta', '$2a$10$o9cZcIZ2NhjKyKXTKDMkpOoa5AvkpmsfYQi8Th7H95HS5g9rhows6', 'cHV6emV0dGFwaWNjb2xldHRh_1727019167000'),
(56, 'giovanni.verga', '$2a$10$xovH4TowIUb8r2BhHz5XbOUQNv/g5HzzIjztTAeBKLznHnuW.F9qS', NULL),
(57, 'stefano.rossi', '$2a$10$4Ptd5HU/8bnQ.KLUU4pLmuOMyBV7J0OP62h44RX6i9mispTFhCstG', NULL),
(58, 'antonio.giovanni', '$2a$10$WWYJLKw2mncGY8Kn6cYNju6H/5F2WI8J6xYj4tGP0dUAVgvRsUEpS', 'YW50b25pby5naW92YW5uaQ==_1727195829000');

-- --------------------------------------------------------

--
-- Struttura della tabella `Scarpe`
--

CREATE TABLE `Scarpe` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `prezzo` decimal(10,2) NOT NULL,
  `descrizione` longtext NOT NULL,
  `immagine` longtext,
  `pk_categoria` int(11) NOT NULL,
  `best_seller` int(4) NOT NULL,
  `nuovo_arrivi` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Scarpe`
--

INSERT INTO `Scarpe` (`id`, `nome`, `prezzo`, `descrizione`, `immagine`, `pk_categoria`, `best_seller`, `nuovo_arrivi`) VALUES
(1, 'Nike Air Max 270', '129.99', 'Le Nike Air Max 270 offrono un comfort incredibile grazie all\'ammortizzazione Air Max. Ideali per l\'uso quotidiano e l\'attività sportiva.', 'https://static.nike.com/a/images/t_prod_ps/w_1536,c_limit,f_auto/kmvyvbiikg4voy7ydvvx/nike-air-max-270-react-bauhaus-release-date.jpg', 1, 5, 0),
(2, 'Nike React Infinity Run Flyknit', '159.99', 'Le Nike React Infinity Run Flyknit sono progettate per la massima ammortizzazione e stabilità durante la corsa. Con tomaia in Flyknit per un comfort superiore.', 'https://static.nike.com/a/videos/t_PDP_1280_v1/f_auto,q_auto:eco,so_1.68/5fbbef40-4127-4464-8b61-8860eef6f550/react-infinity-run-flyknit-2-road-running-shoes-n77q2N.jpg', 2, 2, 0),
(3, 'Nike Air Force 1', '109.99', 'Le Nike Air Force 1 sono un\'icona streetwear, caratterizzate da un design classico e un comfort eccezionale.', 'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/67031162-9cc5-481d-8ffe-7ada8f3d78bd/custom-nike-air-force-1-high-by-you-shoes.png', 1, 1, 1),
(4, 'Adidas Ultraboost 21', '179.99', 'Le Adidas Ultraboost 21 offrono un\'ammortizzazione e un\'energia di ritorno eccezionali grazie alla tecnologia Boost. Ideali per le lunghe distanze.', 'https://www.zeuscalabria.it/34975-large_default/adidas-ultraboost-21-fy0378.jpg', 2, 4, 0),
(5, 'Puma RS-X3', '139.99', 'Le Puma RS-X3 offrono un design audace e una suola ammortizzata per un comfort eccezionale. Perfette per un look urbano.', 'https://d18c63gzk0ps2w.cloudfront.net/media/catalog/product/cache/8df74eb6ed21ef6294ab845393704e49/r/s/rs_x_puzzle_gribluross_puma371570_005_1_6d84.jpg', 3, 6, 1),
(6, 'New Balance 990v5', '189.99', 'Le New Balance 990v5 offrono un\'eccellente stabilità e supporto grazie alla loro costruzione premium e alla tecnologia di ammortizzazione.', 'https://nb.scene7.com/is/image/NB/w990nv5_nb_02_i?$dw_detail_main_lg$&bgc=f1f1f1&layer=1&bgcolor=f1f1f1&blendMode=mult&scale=10&wid=1600&hei=1600', 3, 3, 0),
(7, 'Asics Gel-Kayano 28', '169.99', 'Le Asics Gel-Kayano 28 offrono un\'ammortizzazione e un supporto eccellenti per la corsa grazie alla tecnologia Gel e alla stabilità migliorata.', 'https://images.asics.com/is/image/asics/1011B189_003_SR_RT_GLB?$sfcc-product$', 2, 7, 0),
(8, 'Converse Chuck Taylor All Star', '79.99', 'Le Converse Chuck Taylor All Star sono un\'icona del casual style, conosciute per la loro semplicità e comfort.', 'https://www.converse.com/dw/image/v2/BJJF_PRD/on/demandware.static/-/Sites-cnv-master-catalog-we/default/dw83ac5808/images/a_107/M9160_A_107X1.jpg?sw=964', 3, 8, 1),
(9, 'Vans Old Skool', '89.99', 'Le Vans Old Skool sono famose per il loro design classico e il comfort duraturo. Ideali per un look casual e rilassato.', 'https://images.vans.com/is/image/VansEU/VN000D3HY28-HERO?$PDP-MEDIA-SET-PREVIEW-RESOLUTION$', 3, 9, 0),
(10, 'Reebok Classic Leather', '99.99', 'Le Reebok Classic Leather offrono un design senza tempo e un comfort eccellente, perfette per un uso quotidiano.', 'https://images.reebok.eu/22/25/09/40/22250940_52160385_2048.jpg?c=1', 3, 10, 1),
(11, 'Nike ZoomX Vaporfly NEXT%', '249.99', 'Le Nike ZoomX Vaporfly NEXT% sono progettate per le gare di corsa con una tecnologia di ammortizzazione avanzata per una spinta massima.', 'https://www.misterrunning.com/images/2024-media-01/nike-zoomx-vaporfly-next-3-scarpe-da-running-uomo-volt-black-dv4129-700_A.jpg', 4, 3, 1),
(12, 'Adidas NMD_R1', '139.99', 'Le Adidas NMD_R1 combinano uno stile moderno con un comfort eccezionale grazie alla tecnologia Boost.', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/38e7263475474054b17ead1f00925f6b_9366/Scarpe_NMD_R1_Refined_Nero_H02333_01_standard.jpg', 4, 4, 0),
(13, 'Puma Future Rider', '119.99', 'Le Puma Future Rider offrono un look retro con un comfort moderno grazie alla loro ammortizzazione leggera.', 'https://static.ftshp.digital/img/p/1/0/0/1/4/8/5/1001485-full_product.jpg', 4, 5, 1),
(14, 'New Balance 574', '99.99', 'Le New Balance 574 combinano un design classico con un comfort duraturo, perfette per l\'uso quotidiano.', 'https://nb.scene7.com/is/image/NB/ml574evn_nb_02_i?$dw_detail_main_lg$&bgc=f1f1f1&layer=1&bgcolor=f1f1f1&blendMode=mult&scale=10&wid=1600&hei=1600', 4, 6, 0),
(15, 'Asics Gel-Nimbus 24', '159.99', 'Le Asics Gel-Nimbus 24 offrono un\'ammortizzazione avanzata e un comfort eccellente per le corse su terreni variabili.', 'https://areterunning.com/storage/2022/12/Asics-nimbus-1011B359-403.jpg', 6, 7, 0),
(16, 'Converse One Star', '89.99', 'Le Converse One Star sono un\'icona del design streetwear, con una costruzione robusta e un comfort eccellente.', 'https://www.comunelloshop.it/media/catalog/product/cache/0cfa508e8ad9284dac4cdec807f181eb/C/O/CONVERSE_ONE_STAR_PRO_OX_SEQUOIA_SEQUOIA_WHITE_157872C_02_62622_.JPG', 5, 8, 1),
(17, 'Vans Sk8-Hi', '99.99', 'Le Vans Sk8-Hi offrono un design alto e un comfort eccellente, perfette per uno stile di vita urbano.', 'https://images.vans.com/is/image/VansEU/VN000D5IB8C-HERO?$PDP-MEDIA-SET-PREVIEW-RESOLUTION$', 5, 9, 0),
(18, 'Reebok Zig Kinetica', '129.99', 'Le Reebok Zig Kinetica sono progettate per una reattività eccezionale e un design futuristico.', 'https://images.reebok.eu/22/25/23/14/22252314_52242104_2048.jpg?c=1', 5, 10, 1),
(19, 'Nike Air Max 90', '129.99', 'Le Nike Air Max 90 combinano un design iconico con un comfort migliorato grazie all\'ammortizzazione Air Max.', 'https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco/wzitsrb4oucx9jukxsmc/AIR+MAX+90.png', 7, 11, 0),
(20, 'Adidas Terrex Agravic GTX', '179.99', 'Le Adidas Terrex Agravic GTX sono progettate per il trail running e offrono una protezione impermeabile e una trazione superiore.', 'https://www.latinisport.it/wp-content/uploads/2021/02/FW5133_FTW_photo_side-lateral-center_white-800x800.png', 8, 12, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `scarpe_colori`
--

CREATE TABLE `scarpe_colori` (
  `id_scarpa` int(11) NOT NULL,
  `id_colore` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `scarpe_colori`
--

INSERT INTO `scarpe_colori` (`id_scarpa`, `id_colore`) VALUES
(1, 1),
(3, 1),
(1, 2),
(4, 2),
(2, 3),
(6, 3),
(2, 4),
(7, 4),
(3, 5),
(8, 5),
(4, 6),
(5, 6),
(8, 6),
(5, 7),
(9, 7),
(6, 8),
(9, 8),
(7, 9),
(10, 9),
(10, 10),
(11, 11),
(16, 11),
(11, 12),
(16, 12),
(12, 13),
(17, 13),
(12, 14),
(17, 14),
(13, 15),
(18, 15),
(13, 16),
(18, 16),
(14, 17),
(19, 17),
(14, 18),
(19, 18),
(15, 19),
(20, 19),
(15, 20),
(20, 20);

-- --------------------------------------------------------

--
-- Struttura della tabella `scarpe_ordinate`
--

CREATE TABLE `scarpe_ordinate` (
  `id` int(11) NOT NULL,
  `quantita` int(11) NOT NULL,
  `pk_scarpa` int(11) NOT NULL,
  `pk_ordine` int(11) NOT NULL,
  `p_colore` int(11) NOT NULL,
  `p_taglia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `scarpe_ordinate`
--

INSERT INTO `scarpe_ordinate` (`id`, `quantita`, `pk_scarpa`, `pk_ordine`, `p_colore`, `p_taglia`) VALUES
(24, 1, 3, 32, 1, 3),
(25, 2, 3, 33, 5, 3),
(26, 1, 3, 34, 1, 5),
(27, 1, 2, 34, 4, 6),
(28, 1, 2, 35, 4, 4),
(29, 1, 3, 36, 5, 3),
(30, 1, 2, 37, 3, 4),
(31, 1, 2, 38, 3, 4),
(32, 1, 1, 38, 1, 4),
(33, 1, 16, 39, 12, 6),
(34, 1, 18, 39, 16, 9),
(35, 1, 2, 40, 3, 4),
(36, 1, 3, 40, 5, 3),
(37, 1, 3, 41, 5, 4),
(38, 1, 2, 41, 4, 6),
(39, 1, 2, 42, 3, 4),
(40, 1, 3, 43, 5, 5),
(41, 1, 4, 43, 6, 5),
(42, 1, 2, 44, 3, 4),
(43, 1, 3, 45, 5, 3),
(44, 1, 5, 46, 6, 4),
(45, 1, 1, 46, 1, 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `scarpe_taglie`
--

CREATE TABLE `scarpe_taglie` (
  `id_scarpa` int(11) NOT NULL,
  `id_taglia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `scarpe_taglie`
--

INSERT INTO `scarpe_taglie` (`id_scarpa`, `id_taglia`) VALUES
(1, 3),
(3, 3),
(1, 4),
(2, 4),
(3, 4),
(5, 4),
(8, 4),
(9, 4),
(12, 4),
(1, 5),
(3, 5),
(4, 5),
(5, 5),
(6, 5),
(8, 5),
(9, 5),
(10, 5),
(11, 5),
(12, 5),
(14, 5),
(16, 5),
(19, 5),
(2, 6),
(4, 6),
(6, 6),
(7, 6),
(10, 6),
(11, 6),
(12, 6),
(13, 6),
(14, 6),
(16, 6),
(17, 6),
(19, 6),
(20, 6),
(7, 7),
(11, 7),
(12, 7),
(13, 7),
(14, 7),
(15, 7),
(16, 7),
(17, 7),
(18, 7),
(19, 7),
(20, 7),
(11, 8),
(13, 8),
(14, 8),
(15, 8),
(16, 8),
(17, 8),
(18, 8),
(19, 8),
(13, 9),
(15, 9),
(17, 9),
(18, 9),
(15, 10),
(18, 10);

-- --------------------------------------------------------

--
-- Struttura della tabella `taglie`
--

CREATE TABLE `taglie` (
  `id` int(11) NOT NULL,
  `taglia` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `taglie`
--

INSERT INTO `taglie` (`id`, `taglia`) VALUES
(1, 38),
(2, 39),
(3, 40),
(4, 41),
(5, 42),
(6, 43),
(7, 44),
(8, 45),
(9, 46),
(10, 47),
(11, 21),
(12, 31),
(13, 32),
(14, 33),
(15, 34);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `pk_profilo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `nome`, `cognome`, `pk_profilo`) VALUES
(54, 'Nicola', 'Pasquarelli', 52),
(55, 'Clara', 'Vergari', 53),
(56, 'Laura', 'Verdana', 54),
(57, 'Cosetta', 'Piccoletta', 55),
(58, 'Giovanni', 'Verga', 56),
(59, 'Stefano', 'Rossi', 57),
(60, 'Antonio', 'Giovanni', 58);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `carrello`
--
ALTER TABLE `carrello`
  ADD PRIMARY KEY (`id`),
  ADD KEY `utente_carrello` (`p_utente`);

--
-- Indici per le tabelle `carrello_scarpe`
--
ALTER TABLE `carrello_scarpe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `scarpa` (`p_scarpa`),
  ADD KEY `colore_Scarpa` (`p_colore`),
  ADD KEY `taglia_Scarpa` (`p_taglia`),
  ADD KEY `scarpa_carrello` (`p_carrello`);

--
-- Indici per le tabelle `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `colori`
--
ALTER TABLE `colori`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Indirizzi`
--
ALTER TABLE `Indirizzi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Utente_indirizzo` (`p_utente`);

--
-- Indici per le tabelle `ordini`
--
ALTER TABLE `ordini`
  ADD PRIMARY KEY (`id`),
  ADD KEY `utente` (`p_utente`),
  ADD KEY `Indirizzi` (`p_Indirizzo`),
  ADD KEY `pagamento` (`p_pagamento`);

--
-- Indici per le tabelle `pagamenti`
--
ALTER TABLE `pagamenti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `utenti` (`p_utente`);

--
-- Indici per le tabelle `profili`
--
ALTER TABLE `profili`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Scarpe`
--
ALTER TABLE `Scarpe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `xd__Scarpe__nome` (`nome`),
  ADD KEY `categoria` (`pk_categoria`);

--
-- Indici per le tabelle `scarpe_colori`
--
ALTER TABLE `scarpe_colori`
  ADD PRIMARY KEY (`id_scarpa`,`id_colore`),
  ADD KEY `colore` (`id_colore`);

--
-- Indici per le tabelle `scarpe_ordinate`
--
ALTER TABLE `scarpe_ordinate`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ordine` (`pk_ordine`),
  ADD KEY `xd__scarpe_ordinate__scarpa` (`pk_scarpa`) USING BTREE,
  ADD KEY `p_colore` (`p_colore`),
  ADD KEY `taglie` (`p_taglia`);

--
-- Indici per le tabelle `scarpe_taglie`
--
ALTER TABLE `scarpe_taglie`
  ADD PRIMARY KEY (`id_scarpa`,`id_taglia`),
  ADD KEY `id_taglia` (`id_taglia`);

--
-- Indici per le tabelle `taglie`
--
ALTER TABLE `taglie`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `xd__utenti__cognome` (`cognome`),
  ADD KEY `profilo` (`pk_profilo`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `carrello`
--
ALTER TABLE `carrello`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT per la tabella `carrello_scarpe`
--
ALTER TABLE `carrello_scarpe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT per la tabella `colori`
--
ALTER TABLE `colori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT per la tabella `Indirizzi`
--
ALTER TABLE `Indirizzi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT per la tabella `ordini`
--
ALTER TABLE `ordini`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT per la tabella `pagamenti`
--
ALTER TABLE `pagamenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT per la tabella `profili`
--
ALTER TABLE `profili`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT per la tabella `Scarpe`
--
ALTER TABLE `Scarpe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT per la tabella `scarpe_ordinate`
--
ALTER TABLE `scarpe_ordinate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT per la tabella `taglie`
--
ALTER TABLE `taglie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `carrello`
--
ALTER TABLE `carrello`
  ADD CONSTRAINT `utente_carrello` FOREIGN KEY (`p_utente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `carrello_scarpe`
--
ALTER TABLE `carrello_scarpe`
  ADD CONSTRAINT `colore_Scarpa` FOREIGN KEY (`p_colore`) REFERENCES `colori` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `scarpa` FOREIGN KEY (`p_scarpa`) REFERENCES `Scarpe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `scarpa_carrello` FOREIGN KEY (`p_carrello`) REFERENCES `carrello` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `taglia_Scarpa` FOREIGN KEY (`p_taglia`) REFERENCES `taglie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `Indirizzi`
--
ALTER TABLE `Indirizzi`
  ADD CONSTRAINT `Utente_indirizzo` FOREIGN KEY (`p_utente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `ordini`
--
ALTER TABLE `ordini`
  ADD CONSTRAINT `Indirizzi` FOREIGN KEY (`p_Indirizzo`) REFERENCES `Indirizzi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pagamento` FOREIGN KEY (`p_pagamento`) REFERENCES `pagamenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `utente` FOREIGN KEY (`p_utente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `pagamenti`
--
ALTER TABLE `pagamenti`
  ADD CONSTRAINT `utenti` FOREIGN KEY (`p_utente`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `Scarpe`
--
ALTER TABLE `Scarpe`
  ADD CONSTRAINT `categoria` FOREIGN KEY (`pk_categoria`) REFERENCES `categorie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `scarpe_colori`
--
ALTER TABLE `scarpe_colori`
  ADD CONSTRAINT `colore` FOREIGN KEY (`id_colore`) REFERENCES `colori` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `scarpe_colori_ibfk_1` FOREIGN KEY (`id_scarpa`) REFERENCES `Scarpe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `scarpe_ordinate`
--
ALTER TABLE `scarpe_ordinate`
  ADD CONSTRAINT `ordine` FOREIGN KEY (`pk_ordine`) REFERENCES `ordini` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `scarpe_ordinate_ibfk_1` FOREIGN KEY (`pk_scarpa`) REFERENCES `Scarpe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `scarpe_ordinate_ibfk_2` FOREIGN KEY (`p_colore`) REFERENCES `colori` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `taglie` FOREIGN KEY (`p_taglia`) REFERENCES `taglie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `scarpe_taglie`
--
ALTER TABLE `scarpe_taglie`
  ADD CONSTRAINT `scarpe_taglie_ibfk_2` FOREIGN KEY (`id_taglia`) REFERENCES `taglie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `scarpe_taglie_ibfk_3` FOREIGN KEY (`id_scarpa`) REFERENCES `Scarpe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `utenti`
--
ALTER TABLE `utenti`
  ADD CONSTRAINT `profilo` FOREIGN KEY (`pk_profilo`) REFERENCES `profili` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
