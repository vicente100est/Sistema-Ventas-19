-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 27-04-2018 a las 00:02:22
-- Versión del servidor: 5.7.19
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

DROP TABLE IF EXISTS `articulo`;
CREATE TABLE IF NOT EXISTS `articulo` (
  `cod_articulo` int(11) NOT NULL,
  `referencia` varchar(30) NOT NULL,
  `cantidad` varchar(30) NOT NULL,
  `marca` varchar(15) NOT NULL,
  `valor` varchar(30) NOT NULL,
  `valor_bruto` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_articulo`),
  KEY `marca` (`marca`),
  KEY `descripcion` (`referencia`,`cantidad`,`valor`,`valor_bruto`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`cod_articulo`, `referencia`, `cantidad`, `marca`, `valor`, `valor_bruto`) VALUES
(23, 'JUGO', '23', 'SD', '3', '1'),
(98, 'COMPUTADORA', '9', 'BANGHO', '5000', ''),
(111, 'MOTO', '59', 'DISCOVERY', '3400000', ''),
(122, 'CHOCOLINO', '500', 'HHGVG', '50', ''),
(123, 'LECHE', '235', 'ORO', '3', ''),
(231, 'JIJIJI', '23', 'DD', '23', ''),
(321, 'ZAPATOS', '98', 'ADIDAS', '120000', ''),
(342, 'TECLADO', '0', 'HP', '45000', ''),
(345, 'ALA', '14', '3DD', '5', ''),
(555, 'PEPERONI', '23', 'DDDD', '12', ''),
(900, 'CAFÉ', '4893', 'SANCOR', '50', ''),
(1515, 'PLATANOS', '1992', 'VIÑETAS', '200', ''),
(1596, 'BABASONIC', '15', 'ASD', '123', ''),
(2018, 'CALZADO', '98', 'ADIDAS', '1200', ''),
(2122, 'CABLE DE PODER', '0', 'HP', '2000', ''),
(2369, 'LECHUGA VERDE ', '34', 'FSD', '345', ''),
(3232, 'MOUSE', '10', 'DDDD', '1223', ''),
(4500, 'AJO AZUL', '0', 'DFG', '25', ''),
(5051, 'FZ', '10', 'YAMAHA', '1235366', ''),
(12333, 'PIMIENTA', '12', 'SS', '23', ''),
(21121, 'ARROZZZZZZZZZZZZZZZZZZZZ', '4893', '2300', 'R48', ''),
(45646, 'JABON', '22', 'SS', '3', ''),
(90000, 'CAFÉ', '4893', 'SANCOR', '50', ''),
(123456, 'DDCDC', '7', 'DCDC', '12', ''),
(242443, 'TTTT', '5', 'UUU', '77777', ''),
(1067880102, 'PAÑOLENCI', '60', '78HHH', '8888', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `cod_cliente` int(11) NOT NULL,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `dirrecion` varchar(30) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  PRIMARY KEY (`cod_cliente`),
  KEY `nombres` (`nombres`,`apellidos`,`dirrecion`,`telefono`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cod_cliente`, `nombres`, `apellidos`, `dirrecion`, `telefono`) VALUES
(2018, 'FFF', 'FFF', 'FFF', '444'),
(1067880102, 'INELSA', 'CENTENO', 'JDJDJJDDJDJ', '6567557'),
(9090, 'IVAN DARIO', 'CASTAÑEDA', 'BARRANQUILLA', '32344545'),
(3334, 'Jaider', 'BOLSA', 'VillaJuiana', '323432234'),
(33202465, 'Kamilo', 'Perez', 'Pastrana', '321453435'),
(8454587, 'KFFVFV', 'KFDKFDKN', 'FKFNFN', '1234567891'),
(1052784358, 'MANUEL', 'ROMERO', 'YRYR656', '310674'),
(106780102, 'MARIA', 'BALDOVINO', 'HUU34', '66474747'),
(76543, 'mario', 'rosales', 'barrio la granja', '7896543'),
(45667, 'MATEYE', 'URJRJRJ', 'HFJFJ', '7674748'),
(696969, 'nksdvbjv', 'ndnjc', 'nsks', '134');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contiene`
--

DROP TABLE IF EXISTS `contiene`;
CREATE TABLE IF NOT EXISTS `contiene` (
  `cod_contiene` int(11) NOT NULL,
  `cod_articulo` int(11) NOT NULL,
  `cod_factura` int(11) NOT NULL,
  PRIMARY KEY (`cod_contiene`),
  KEY `cod.articulo` (`cod_articulo`,`cod_factura`),
  KEY `cod_factura` (`cod_factura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distribuye`
--

DROP TABLE IF EXISTS `distribuye`;
CREATE TABLE IF NOT EXISTS `distribuye` (
  `cod_distribuye` int(11) NOT NULL,
  `cod_proveedor` int(11) NOT NULL,
  `cod_articulo` int(11) NOT NULL,
  KEY `cod_articulo` (`cod_articulo`),
  KEY `cod_proveedor` (`cod_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `cod_empleado` int(11) NOT NULL,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `dirrecion` varchar(30) NOT NULL,
  `telefono` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_empleado`),
  KEY `nombres` (`nombres`,`apellidos`,`dirrecion`,`telefono`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`cod_empleado`, `nombres`, `apellidos`, `dirrecion`, `telefono`) VALUES
(123, 'CARLOS', 'ORDOÑEZ', 'HSHR637', '3436647'),
(123456, 'DANIEL', 'ROMERO', 'HFJFJFJ774', '314678'),
(7477, 'JHON JAIRO', 'MORALES', 'VERSALLES', '3004564234'),
(231445, 'LUIS', 'gomez', 'barrio prado', '7797544'),
(1067880102, 'NOBEL', 'DAVID', 'MAGA', '301001010'),
(5151, 'PATRICIA', 'ROMERO', 'MONTECARLOS', '453452'),
(111, 'SSSDDD', 'SSS', 'SSS', '111');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE IF NOT EXISTS `factura` (
  `cod_factura` int(11) NOT NULL,
  `fecha` varchar(30) NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `cod_empleado` int(11) NOT NULL,
  PRIMARY KEY (`cod_factura`),
  KEY `descripcion` (`fecha`),
  KEY `cod_cliente` (`cod_cliente`,`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`cod_factura`, `fecha`, `cod_cliente`, `cod_empleado`) VALUES
(1, '2015/07/14', 3001, 123),
(2, '2015/09/20', 1067880102, 123456),
(3, '2015/09/28', 1052784358, 123456),
(4, '2015/10/31', 1052784358, 123456),
(5, '2015/11/16', 1067880102, 123),
(6, '2015/11/16', 1052784358, 231445),
(7, '2015/11/16', 1067880102, 123456),
(8, '2015/11/16', 1052784358, 231445),
(9, '2015/11/16', 1067880102, 123),
(10, '2015/11/16', 1052784358, 231445),
(11, '2015/11/18', 1052784358, 123),
(12, '2015/11/18', 1067880102, 123),
(13, '2015/11/18', 1067880102, 123),
(14, '2015/11/18', 1052784358, 123456),
(15, '2015/11/18', 1067880102, 123),
(16, '2015/11/18', 106780102, 231445),
(17, '2015/11/18', 1052784358, 123),
(18, '2015/11/18', 3000, 123456),
(19, '2015/11/18', 1067880102, 123),
(20, '2015/11/18', 1067880102, 231445),
(21, '2015/11/18', 1067880102, 123),
(22, '2015/11/18', 1067880102, 123456),
(23, '2015/11/19', 1067880102, 123456),
(24, '2015/07/14', 1052784358, 123),
(25, '2015/11/29', 1052784358, 231445),
(26, '2015/12/09', 76543, 5151),
(27, '2015/12/27', 1052784358, 123456),
(28, '2016/01/30', 34345, 123456),
(29, '2016/07/17', 3000, 123456),
(30, '2016/08/09', 2014, 123),
(31, '2016/08/09', 1053, 123),
(32, '2016/08/09', 2014, 123),
(33, '2016/08/09', 3334, 123),
(34, '2016/08/11', 33202465, 1067880102),
(35, '2016/08/11', 9090, 7477),
(36, '2016/08/12', 1067880102, 123),
(37, '2016/08/15', 9090, 123456),
(38, '2016/08/15', 9090, 123),
(39, '2016/08/15', 9090, 123),
(40, '2018/04/18', 33202465, 123),
(41, '2018/04/18', 1067880102, 123),
(42, '2018/04/18', 9090, 123),
(43, '2018/04/18', 106780102, 111),
(44, '2018/04/18', 1067880102, 123),
(45, '2018/04/18', 2018, 123),
(46, '2018/04/18', 1067880102, 123),
(47, '2018/04/18', 2018, 123),
(48, '2018/04/18', 1067880102, 123),
(49, '2018/04/18', 1067880102, 123),
(50, '2018/04/18', 1067880102, 123),
(51, '2018/04/19', 9090, 123456),
(52, '2018/04/19', 1067880102, 123456),
(53, '2018/04/19', 1067880102, 123456),
(54, '2018/04/19', 1067880102, 123),
(55, '2018/04/19', 1067880102, 123),
(56, '2018/04/19', 9090, 1067880102),
(57, '2018/04/19', 1067880102, 123),
(58, '2018/04/19', 1067880102, 123),
(59, '2018/04/19', 1067880102, 123),
(60, '2018/04/19', 1067880102, 123),
(61, '2018/04/19', 1067880102, 123),
(62, '2018/04/19', 9090, 123),
(63, '2018/04/19', 1067880102, 123),
(64, '2018/04/19', 2018, 123),
(65, '2018/04/19', 1052784358, 123456),
(66, '2018/04/19', 1052784358, 123456),
(67, '2018/04/19', 1067880102, 123456),
(68, '2018/04/25', 1067880102, 123),
(69, '2018/04/25', 1067880102, 7477);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

DROP TABLE IF EXISTS `hotel`;
CREATE TABLE IF NOT EXISTS `hotel` (
  `idhotel` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(150) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idhotel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `cod_proveedor` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `dirrecion` varchar(30) NOT NULL,
  `telefono` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`cod_proveedor`, `nombre`, `dirrecion`, `telefono`) VALUES
(1234, 'MARIA', 'SAN JOSE', '357688990'),
(2121, 'GERMAN GALENO', 'GDNDJ4', '31289929'),
(6262, 'JOSEFINA', 'MOCARI', '4567'),
(12345, 'MAMMA', 'YTUTU', '758559'),
(123321, 'MERCEDES', 'YATI', '321456434535'),
(123456, 'kdsjfjk', 'jjbh', '5544'),
(616161, 'FRANK', 'BARRANQUILLA', '452662'),
(898765, 'DRE', 'HVGHVJ67', '09776553212'),
(33353219, 'NUBIS MONTES BALDOVINO', 'MONTE CARLOS', '23456'),
(1052983753, 'LUIS', 'MARACANA', '3216765875');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `realiza`
--

DROP TABLE IF EXISTS `realiza`;
CREATE TABLE IF NOT EXISTS `realiza` (
  `cod_realiza` int(11) NOT NULL,
  `cod_factura` int(11) NOT NULL,
  `cod_empleado` int(11) NOT NULL,
  PRIMARY KEY (`cod_realiza`),
  KEY `cod.factura` (`cod_factura`,`cod_empleado`),
  KEY `cod_factura` (`cod_factura`),
  KEY `cod_empleado` (`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_factura`
--

DROP TABLE IF EXISTS `referencia_factura`;
CREATE TABLE IF NOT EXISTS `referencia_factura` (
  `cod_factura` int(11) NOT NULL,
  `valor_unitario` int(11) NOT NULL,
  `valor_total` int(11) NOT NULL,
  `referencia` varchar(15) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `Total` int(11) NOT NULL,
  KEY `cod_factura` (`cod_factura`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `referencia_factura`
--

INSERT INTO `referencia_factura` (`cod_factura`, `valor_unitario`, `valor_total`, `referencia`, `cantidad`, `Total`) VALUES
(1, 24523, 73569, 'EFD', 3, 0),
(1, 12000, 12000, 'df56', 1, 0),
(2, 45000, 540000, 'TECLADO', 12, 0),
(2, 12000, 144000, 'df56', 12, 0),
(3, 45000, 90000, 'TECLADO', 2, 0),
(3, 24523, 564029, 'EFD', 23, 0),
(4, 24523, 98092, 'EFD', 4, 0),
(4, 45000, 90000, 'TECLADO', 2, 0),
(4, 12000, 24000, '345', 2, 0),
(5, 12000, 24000, '345', 2, 0),
(5, 24523, 73569, 'EFD', 3, 0),
(5, 15000, 60000, 'fg34r', 4, 0),
(5, 45000, 90000, 'TECLADO', 2, 0),
(9, 12000, 24000, '345', 2, 27840),
(10, 45000, 180000, 'TECLADO', 4, 377660),
(10, 24523, 73569, 'EFD', 3, 377660),
(10, 12000, 48000, '345', 4, 377660),
(11, 45000, 180000, 'TECLADO', 4, 258448),
(11, 12000, 36000, '345', 3, 258448),
(11, 3400, 6800, 'sd32', 2, 258448),
(14, 12000, 24000, '345', 2, 27840),
(18, 15000, 30000, 'fg34r', 2, 62640),
(19, 15000, 45000, 'fg34r', 3, 52200),
(20, 15000, 45000, 'fg34r', 3, 104400),
(21, 24523, 49046, 'EFD', 2, 165237),
(21, 3400, 3400, 'sd32', 1, 165237),
(21, 45000, 90000, 'TECLADO', 2, 165237),
(22, 45000, 135000, 'TECLADO', 3, 321837),
(23, 24523, 73569, 'EFD', 3, 189740),
(23, 45000, 90000, 'TECLADO', 2, 189740),
(24, 24523, 7454992, 'EFD', 304, 9115930),
(24, 12000, 240000, '345', 20, 9115930),
(25, 45000, 45000, 'TECLADO', 1, 74008),
(25, 3400, 6800, 'sd32', 2, 74008),
(25, 12000, 12000, '345', 1, 74008),
(26, 500, 5000, 'YUCA', 10, 14732),
(26, 200, 800, 'PLATANOS', 4, 14732),
(26, 2300, 6900, 'ARROZ', 3, 14732),
(27, 2300, 6900, 'ARROZ', 3, 13688),
(27, 500, 1500, 'YUCA', 3, 13688),
(27, 200, 800, 'PLATANOS', 4, 13688),
(28, 12000, 36000, 'MOUSE', 3, 41760),
(29, 2300, 11500, 'ARROZ', 5, 13340),
(30, 2000, 6000, 'CBLE DE PODER', 3, 6960),
(31, 2000, 4000, 'CBLE DE PODER', 2, 99644),
(31, 2300, 6900, 'ARROZ', 3, 99644),
(31, 15000, 75000, 'fg34r', 5, 99644),
(32, 1235366, 1235366, 'FZ', 1, 1442304),
(33, 1235366, 1235366, 'FZ', 1, 1433024),
(34, 1235366, 12353660, 'FZ', 10, 14330245),
(35, 2300, 6900, 'ARROZ', 3, 1441028),
(35, 1235366, 1235366, 'FZ', 1, 1441028),
(36, 1235366, 2470732, 'FZ', 2, 2866049),
(37, 2000, 4000, 'CABLE DE PODER', 2, 15780640),
(37, 3400000, 13600000, 'moto', 4, 15780640),
(38, 8888, 44440, 'HHEHHEH', 5, 51550),
(39, 1235366, 1235366, 'FZ', 1, 1433024),
(40, 2300, 48300, 'ARROZ', 21, 7944028),
(40, 3400000, 6800000, 'moto', 2, 7944028),
(41, 2300, 52900, 'ARROZ', 23, 61364),
(42, 1235366, 42002444, 'FZ', 34, 48784199),
(43, 3400000, 6800000, 'moto', 2, 7896004),
(43, 2300, 6900, 'ARROZ', 3, 7896004),
(44, 2300, 4600, 'ARROZ', 2, 1438360),
(44, 1235366, 1235366, 'FZ', 1, 1438360),
(45, 2300, 6900, 'ARROZ', 3, 8004),
(46, 12, 24, 'DDCDC', 2, 27),
(47, 2300, 6900, 'ARROZ', 3, 8004),
(48, 2000, 2000, 'CABLE DE PODER', 1, 2320),
(49, 2000, 2000, 'CABLE DE PODER', 1, 2320),
(50, 2000, 2000, 'CABLE DE PODER', 1, 2320),
(51, 1235366, 1235366, 'FZ', 1, 1433024),
(52, 2300, 2300, 'ARROZ', 1, 2668),
(53, 2300, 2300, 'ARROZ', 1, 2668),
(54, 12, 12, 'DDCDC', 1, 13),
(55, 1235366, 1235366, 'FZ', 1, 1433024),
(56, 2300, 2300, 'ARROZ', 1, 2668),
(57, 2300, 2300, 'ARROZ', 1, 2668),
(58, 2300, 2300, 'ARROZ', 1, 2668),
(59, 2300, 2300, 'ARROZ', 1, 2668),
(60, 2300, 2300, 'ARROZ', 1, 2668),
(61, 2300, 2300, 'ARROZ', 1, 2668),
(62, 12, 12, 'DDCDC', 1, 13),
(63, 2300, 4600, 'ARROZ', 2, 5336),
(64, 2300, 2300, 'ARROZ', 1, 2668),
(65, 2300, 4600, 'ARROZ', 2, 373957),
(65, 77777, 77777, 'tttt', 1, 373957),
(65, 120000, 240000, 'ZAPATOS', 2, 373957),
(66, 2300, 9200, 'ARROZ', 4, 91872),
(66, 5000, 70000, 'COMPUTADORA', 14, 91872),
(67, 50, 1100, 'ARROZ', 22, 1289),
(67, 12, 12, 'DDCDC', 1, 1289),
(68, 8888, 26664, 'PAÑOLENCI', 3, 30930),
(69, 25, 300, 'AJO AZUL', 12, 348);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_remito`
--

DROP TABLE IF EXISTS `referencia_remito`;
CREATE TABLE IF NOT EXISTS `referencia_remito` (
  `cod_remito` int(11) NOT NULL,
  `valor_unitario` int(11) NOT NULL,
  `valor_total` int(11) NOT NULL,
  `referencia` varchar(50) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `total` int(11) NOT NULL,
  KEY `cod_remito` (`cod_remito`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `referencia_remito`
--

INSERT INTO `referencia_remito` (`cod_remito`, `valor_unitario`, `valor_total`, `referencia`, `cantidad`, `total`) VALUES
(69, 1223, 2446, 'MOUSE', 2, 2837),
(70, 5, 10, 'ALA', 2, 11),
(2, 5, 10, 'ALA', 2, 11),
(3, 5, 15, 'ALA', 3, 17),
(4, 5, 10, 'ALA', 2, 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remito`
--

DROP TABLE IF EXISTS `remito`;
CREATE TABLE IF NOT EXISTS `remito` (
  `cod_remito` int(11) NOT NULL,
  `fecha` varchar(30) NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `cod_empleado` int(11) NOT NULL,
  PRIMARY KEY (`cod_remito`),
  KEY `descripcion` (`fecha`),
  KEY `cod_cliente` (`cod_cliente`,`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `remito`
--

INSERT INTO `remito` (`cod_remito`, `fecha`, `cod_cliente`, `cod_empleado`) VALUES
(2, '2018/04/26', 1067880102, 123),
(3, '2018/04/26', 9090, 123456),
(4, '2018/04/26', 1067880102, 123),
(70, '2018/04/25', 9090, 123456);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicita`
--

DROP TABLE IF EXISTS `solicita`;
CREATE TABLE IF NOT EXISTS `solicita` (
  `cod_solicita` int(11) NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `cod_factura` int(11) NOT NULL,
  PRIMARY KEY (`cod_solicita`),
  KEY `cod.cliente` (`cod_cliente`,`cod_factura`),
  KEY `cod_cliente` (`cod_cliente`),
  KEY `cod_cliente_2` (`cod_cliente`),
  KEY `cod_factura` (`cod_factura`),
  KEY `cod_cliente_3` (`cod_cliente`),
  KEY `cod_factura_2` (`cod_factura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `clave` varchar(5) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `usuario` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `apellido` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `telefono` int(11) NOT NULL,
  `estado` varchar(1) NOT NULL,
  `acceso` varchar(25) NOT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`clave`, `usuario`, `nombre`, `apellido`, `direccion`, `telefono`, `estado`, `acceso`) VALUES
('admin', 'admin', '', '', '', 324342342, 'A', 'Administrador'),
('admin', 'frank', 'Frank Bequenbawer', 'Soto', 'san Jose', 2147483647, 'A', 'Administrador'),
('ADMIN', 'HUGACHO', 'HUGACHO', 'AAA', 'AAA', 333, 'A', 'Administrador'),
('1002', 'maria', '', '', '', 2147483647, 'A', 'Facturador'),
('admin', 'mitchell', 'MITCHELL', 'MARSIGLIA', 'SAN MARTIN', 313458565, 'A', 'Facturador'),
('ndag', 'Nobel', 'Nobel David', 'Anaya Garcia', 'B. Olaya', 2147483647, 'A', 'Administrador'),
('1234', 'ubadel', '', '', '', 0, 'I', '');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contiene`
--
ALTER TABLE `contiene`
  ADD CONSTRAINT `contiene_ibfk_1` FOREIGN KEY (`cod_articulo`) REFERENCES `articulo` (`cod_articulo`) ON DELETE CASCADE,
  ADD CONSTRAINT `contiene_ibfk_2` FOREIGN KEY (`cod_factura`) REFERENCES `factura` (`cod_factura`) ON DELETE CASCADE;

--
-- Filtros para la tabla `distribuye`
--
ALTER TABLE `distribuye`
  ADD CONSTRAINT `distribuye_ibfk_1` FOREIGN KEY (`cod_proveedor`) REFERENCES `proveedor` (`cod_proveedor`) ON DELETE CASCADE,
  ADD CONSTRAINT `distribuye_ibfk_2` FOREIGN KEY (`cod_articulo`) REFERENCES `articulo` (`cod_articulo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `realiza`
--
ALTER TABLE `realiza`
  ADD CONSTRAINT `realiza_ibfk_2` FOREIGN KEY (`cod_empleado`) REFERENCES `empleado` (`cod_empleado`) ON DELETE CASCADE,
  ADD CONSTRAINT `realiza_ibfk_3` FOREIGN KEY (`cod_factura`) REFERENCES `factura` (`cod_factura`) ON DELETE CASCADE;

--
-- Filtros para la tabla `referencia_factura`
--
ALTER TABLE `referencia_factura`
  ADD CONSTRAINT `referencia_factura_ibfk_1` FOREIGN KEY (`cod_factura`) REFERENCES `factura` (`cod_factura`) ON DELETE CASCADE;

--
-- Filtros para la tabla `solicita`
--
ALTER TABLE `solicita`
  ADD CONSTRAINT `solicita_ibfk_1` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`cod_cliente`) ON DELETE CASCADE,
  ADD CONSTRAINT `solicita_ibfk_2` FOREIGN KEY (`cod_factura`) REFERENCES `factura` (`cod_factura`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;