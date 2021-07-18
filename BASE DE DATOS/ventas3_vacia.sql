-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 07-01-2020 a las 13:07:34
-- Versión del servidor: 5.7.19
-- Versión de PHP: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ventas3_vacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apertura_caja`
--

DROP TABLE IF EXISTS `apertura_caja`;
CREATE TABLE IF NOT EXISTS `apertura_caja` (
  `cod_caja` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_caja` varchar(200) NOT NULL,
  `monto_apertura` double(11,2) NOT NULL,
  `vendedor` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  `hora` varchar(200) NOT NULL,
  `estado` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_caja`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

DROP TABLE IF EXISTS `articulo`;
CREATE TABLE IF NOT EXISTS `articulo` (
  `cod_articulo` varchar(150) NOT NULL,
  `referencia` varchar(150) NOT NULL,
  `cantidad` double(11,2) NOT NULL,
  `proveedor` varchar(50) DEFAULT NULL,
  `valor` double(11,2) NOT NULL,
  `valor_bruto` double(11,2) DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `ultima_actualizacion` varchar(50) NOT NULL,
  `codigo_provisorio` varchar(50) DEFAULT NULL,
  `codigo_unico` int(30) NOT NULL AUTO_INCREMENT,
  `total_con_iva` double(11,2) NOT NULL,
  `porcentaje_ganancia` double(11,2) NOT NULL,
  `iva` double(11,2) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  `valor_bruto_sin_iva` double(11,2) NOT NULL,
  `ivaCompra` double(11,2) NOT NULL,
  `impresion_etiqueta` varchar(200) NOT NULL,
  PRIMARY KEY (`codigo_unico`),
  KEY `descripcion` (`referencia`,`cantidad`,`valor`,`valor_bruto`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo_referencia`
--

DROP TABLE IF EXISTS `articulo_referencia`;
CREATE TABLE IF NOT EXISTS `articulo_referencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cod_articulo` varchar(20) NOT NULL,
  `proveedor` int(11) NOT NULL,
  `precio` double(11,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cajas`
--

DROP TABLE IF EXISTS `cajas`;
CREATE TABLE IF NOT EXISTS `cajas` (
  `cod_caja` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_caja` varchar(200) NOT NULL,
  `estado` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_caja`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cajas`
--

INSERT INTO `cajas` (`cod_caja`, `nombre_caja`, `estado`) VALUES
(1, 'CAJA PRINCIPAL', 'CERRADA'),
(2, 'CAJA 2', 'CERRADA'),
(3, 'CAJA 3', 'CERRADA'),
(4, 'CAJA 4', 'CERRADA'),
(5, 'CAJA 5', 'CERRADA'),
(6, 'CAJA 6', 'CERRADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cierre_caja`
--

DROP TABLE IF EXISTS `cierre_caja`;
CREATE TABLE IF NOT EXISTS `cierre_caja` (
  `cod_cierre` int(11) NOT NULL AUTO_INCREMENT,
  `cod_caja` varchar(200) NOT NULL,
  `nombre_caja` varchar(200) NOT NULL,
  `monto_apertura` double(11,2) NOT NULL,
  `monto_compras` double(11,2) NOT NULL,
  `monto_ventas` double(11,2) NOT NULL,
  `monto_ingresos` double(11,2) NOT NULL,
  `monto_retiros` double(11,2) NOT NULL,
  `monto_saldos_cobrados` double(11,2) NOT NULL,
  `monto_saldos_pagados` double(11,2) NOT NULL,
  `monto_devolucion_ventas` double(11,2) NOT NULL,
  `monto_devolucion_compras` double(11,2) NOT NULL,
  `total_caja` double(11,2) NOT NULL,
  `vendedor` double(11,2) NOT NULL,
  `fecha` date NOT NULL,
  `hora` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_cierre`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `cod_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) NOT NULL,
  `localidad` varchar(50) DEFAULT NULL,
  `dirrecion` varchar(50) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `cuit` varchar(20) DEFAULT NULL,
  `contribuyente` varchar(25) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_cliente`),
  KEY `nombres` (`nombres`,`localidad`,`dirrecion`,`telefono`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cod_cliente`, `nombres`, `localidad`, `dirrecion`, `telefono`, `estado`, `dni`, `cuit`, `contribuyente`, `provincia`, `email`) VALUES
(0, 'CONSUMIDOR FINAL', ' ', ' ', ' ', '', ' ', ' ', 'Consumidor Final', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

DROP TABLE IF EXISTS `compra`;
CREATE TABLE IF NOT EXISTS `compra` (
  `cod_compra` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `cod_proveedor` int(11) NOT NULL,
  `condicion` varchar(50) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  `tipo_pago` varchar(20) NOT NULL,
  `tipo_factura` varchar(10) NOT NULL,
  `iva` varchar(10) NOT NULL,
  `total_con_iva` double(11,2) NOT NULL,
  `total_sin_iva` double(11,2) NOT NULL,
  `ivaDiscriminado` varchar(10) NOT NULL,
  PRIMARY KEY (`cod_compra`),
  KEY `descripcion` (`fecha`),
  KEY `cod_cliente` (`cod_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`cod_compra`, `fecha`, `cod_proveedor`, `condicion`, `categoria`, `tipo_pago`, `tipo_factura`, `iva`, `total_con_iva`, `total_sin_iva`, `ivaDiscriminado`) VALUES
(0, '2012-02-01', 0, '0', '0', '0', '0', '0', 0.00, 0.00, '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra_cheque`
--

DROP TABLE IF EXISTS `compra_cheque`;
CREATE TABLE IF NOT EXISTS `compra_cheque` (
  `cod_cheque` int(11) NOT NULL AUTO_INCREMENT,
  `cod_compra` varchar(200) NOT NULL,
  `num_cheque` varchar(200) NOT NULL,
  `fecha_emision` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `monto` double(11,2) NOT NULL,
  PRIMARY KEY (`cod_cheque`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra_credito`
--

DROP TABLE IF EXISTS `compra_credito`;
CREATE TABLE IF NOT EXISTS `compra_credito` (
  `cod_credito` int(11) NOT NULL AUTO_INCREMENT,
  `cod_compra` varchar(200) NOT NULL,
  `total` double(11,2) NOT NULL,
  `acuenta` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  `cod_caja` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_credito`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra_deposito`
--

DROP TABLE IF EXISTS `compra_deposito`;
CREATE TABLE IF NOT EXISTS `compra_deposito` (
  `cod_deposito` int(11) NOT NULL AUTO_INCREMENT,
  `cod_compra` varchar(200) NOT NULL,
  `num_cuenta` varchar(200) NOT NULL,
  `num_deposito` varchar(200) NOT NULL,
  `monto` double(11,2) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`cod_deposito`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra_efectivo`
--

DROP TABLE IF EXISTS `compra_efectivo`;
CREATE TABLE IF NOT EXISTS `compra_efectivo` (
  `cod_efectivo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_compra` varchar(200) NOT NULL,
  `subtotal` double(11,2) NOT NULL,
  `iva` double(11,2) NOT NULL,
  `total` double(11,2) NOT NULL,
  `cod_caja` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_efectivo`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra_mixta`
--

DROP TABLE IF EXISTS `compra_mixta`;
CREATE TABLE IF NOT EXISTS `compra_mixta` (
  `cod_mixta` int(11) NOT NULL AUTO_INCREMENT,
  `cod_compra` varchar(200) NOT NULL,
  `total` double(11,2) NOT NULL,
  `monto_efectivo` double(11,2) NOT NULL,
  `monto_tarjeta` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  `vuelto` double(11,2) NOT NULL,
  `cod_caja` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_mixta`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

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
-- Estructura de tabla para la tabla `cuenta_corriente`
--

DROP TABLE IF EXISTS `cuenta_corriente`;
CREATE TABLE IF NOT EXISTS `cuenta_corriente` (
  `cod_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `cod_cliente` varchar(150) NOT NULL,
  `ultimo_pago` double(11,2) NOT NULL,
  `fecha_ultimo_pago` date NOT NULL,
  `total_pago` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  PRIMARY KEY (`cod_cuenta`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cuenta_corriente`
--

INSERT INTO `cuenta_corriente` (`cod_cuenta`, `cod_cliente`, `ultimo_pago`, `fecha_ultimo_pago`, `total_pago`, `saldo_restante`) VALUES
(0, '0', 0.00, '2019-03-16', 0.00, 0.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta_credito_compra`
--

DROP TABLE IF EXISTS `cuenta_credito_compra`;
CREATE TABLE IF NOT EXISTS `cuenta_credito_compra` (
  `cod_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `cod_proveedor` varchar(150) NOT NULL,
  `ultimo_pago` double(11,2) NOT NULL,
  `fecha_ultimo_pago` date NOT NULL,
  `total_pago` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  PRIMARY KEY (`cod_cuenta`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta_credito_venta`
--

DROP TABLE IF EXISTS `cuenta_credito_venta`;
CREATE TABLE IF NOT EXISTS `cuenta_credito_venta` (
  `cod_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `cod_cliente` varchar(150) NOT NULL,
  `ultimo_pago` double(11,2) NOT NULL,
  `fecha_ultimo_pago` date NOT NULL,
  `total_pago` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  PRIMARY KEY (`cod_cuenta`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devolucion_compra`
--

DROP TABLE IF EXISTS `devolucion_compra`;
CREATE TABLE IF NOT EXISTS `devolucion_compra` (
  `cod_devolucion` int(11) NOT NULL AUTO_INCREMENT,
  `cod_caja` varchar(200) NOT NULL,
  `cod_compra` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  `monto_efectivo` double(11,2) NOT NULL,
  PRIMARY KEY (`cod_devolucion`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devolucion_venta`
--

DROP TABLE IF EXISTS `devolucion_venta`;
CREATE TABLE IF NOT EXISTS `devolucion_venta` (
  `cod_devolucion` int(11) NOT NULL AUTO_INCREMENT,
  `cod_caja` varchar(200) NOT NULL,
  `cod_venta` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  `monto_efectivo` double(11,2) NOT NULL,
  PRIMARY KEY (`cod_devolucion`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

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
  `cod_empleado` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `dirrecion` varchar(30) NOT NULL,
  `telefono` varchar(30) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `localidad` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_empleado`),
  KEY `nombres` (`nombres`,`apellidos`,`dirrecion`,`telefono`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`cod_empleado`, `nombres`, `apellidos`, `dirrecion`, `telefono`, `estado`, `dni`, `localidad`) VALUES
(1, 'HUGO', 'FLORES', 'SAN JUAN 3456', '2302532220', 'ACTIVO', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

DROP TABLE IF EXISTS `empresa`;
CREATE TABLE IF NOT EXISTS `empresa` (
  `cod_empresa` int(3) NOT NULL,
  `nombreTitular` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `cuit` varchar(50) NOT NULL,
  `tipo` varchar(60) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `localidad` varchar(50) NOT NULL,
  `provincia` varchar(30) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `imagen` blob NOT NULL,
  `fechaInicio` varchar(30) NOT NULL,
  `ingresosBrutos` varchar(50) NOT NULL,
  `punto_de_venta` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_empresa`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`cod_empresa`, `nombreTitular`, `nombre`, `cuit`, `tipo`, `direccion`, `localidad`, `provincia`, `telefono`, `correo`, `imagen`, `fechaInicio`, `ingresosBrutos`, `punto_de_venta`) VALUES
(1, 'USUARIO PRUEBA', 'HSOFT', '20229053834', 'Responsable Monotributo', 'RECONQUISTA 579', 'HUINCA RENANCÓ', 'CÓRDOBA', '2302532220', '', 0x433a5c55736572735c4875676f5c446f63756d656e74735c626572746f313533303139333933392e706e67, '01/10/2013', '0111358856656', '3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE IF NOT EXISTS `factura` (
  `cod_factura` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `cod_empleado` int(11) NOT NULL,
  `tipo_pago` varchar(20) NOT NULL,
  `condicion` varchar(50) NOT NULL,
  `iva` varchar(10) NOT NULL,
  `tipo_factura` varchar(20) NOT NULL,
  `total_con_iva` double(11,2) NOT NULL,
  `total_sin_iva` double(11,2) NOT NULL,
  `ivaDiscriminado` varchar(10) NOT NULL,
  `estado_afip` varchar(30) NOT NULL,
  `cae` varchar(200) DEFAULT NULL,
  `vencimiento_cae` date DEFAULT NULL,
  PRIMARY KEY (`cod_factura`),
  KEY `descripcion` (`fecha`),
  KEY `cod_cliente` (`cod_cliente`,`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`cod_factura`, `fecha`, `cod_cliente`, `cod_empleado`, `tipo_pago`, `condicion`, `iva`, `tipo_factura`, `total_con_iva`, `total_sin_iva`, `ivaDiscriminado`, `estado_afip`, `cae`, `vencimiento_cae`) VALUES
(0, '2014-01-23', 0, 0, '0', '0', '0', '0', 0.00, 0.00, '0.00', '', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturable`
--

DROP TABLE IF EXISTS `facturable`;
CREATE TABLE IF NOT EXISTS `facturable` (
  `fecha` varchar(8) NOT NULL DEFAULT '',
  `tipo` varchar(3) NOT NULL DEFAULT '011',
  `pto` varchar(5) NOT NULL DEFAULT '00002',
  `numero` varchar(20) NOT NULL DEFAULT '',
  `gravado` double NOT NULL DEFAULT '0',
  `alicuota` varchar(4) NOT NULL DEFAULT '0005',
  `impuesto` double NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` double NOT NULL DEFAULT '0',
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` int(11) NOT NULL DEFAULT '0',
  `idcliente` int(11) NOT NULL DEFAULT '1',
  `tipoClienteId` int(11) NOT NULL DEFAULT '99',
  `razon` varchar(30) NOT NULL DEFAULT '',
  `cuit` varchar(20) NOT NULL DEFAULT '',
  `afip` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `numero_2` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fiscal`
--

DROP TABLE IF EXISTS `fiscal`;
CREATE TABLE IF NOT EXISTS `fiscal` (
  `fecha` varchar(8) NOT NULL DEFAULT '',
  `tipo` varchar(3) NOT NULL DEFAULT '011',
  `pto` varchar(5) NOT NULL DEFAULT '00002',
  `numero` varchar(20) NOT NULL DEFAULT '',
  `gravado` double NOT NULL DEFAULT '0',
  `alicuota` varchar(4) NOT NULL DEFAULT '0005',
  `impuesto` double NOT NULL DEFAULT '0',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` double NOT NULL DEFAULT '0',
  `fechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` int(11) NOT NULL DEFAULT '0',
  `idcliente` int(11) NOT NULL DEFAULT '1',
  `tipoClienteId` int(11) NOT NULL DEFAULT '99',
  `razon` varchar(30) NOT NULL DEFAULT '',
  `cuit` varchar(20) NOT NULL DEFAULT '',
  `cae` varchar(200) NOT NULL,
  `vencimiento_cae` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `numero_2` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=365 DEFAULT CHARSET=utf8;

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
-- Estructura de tabla para la tabla `ingreso_efectivo`
--

DROP TABLE IF EXISTS `ingreso_efectivo`;
CREATE TABLE IF NOT EXISTS `ingreso_efectivo` (
  `cod_ingreso` int(11) NOT NULL AUTO_INCREMENT,
  `cod_caja` varchar(200) NOT NULL,
  `monto_ingresado` double(11,2) NOT NULL,
  `concepto` varchar(200) NOT NULL,
  `vendedor` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  `hora` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_ingreso`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingreso_usuarios`
--

DROP TABLE IF EXISTS `ingreso_usuarios`;
CREATE TABLE IF NOT EXISTS `ingreso_usuarios` (
  `cod_ingreso` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(200) NOT NULL,
  `fecha_ingreso` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_salida` timestamp NULL DEFAULT NULL,
  `estado` varchar(200) NOT NULL,
  `tipo_usuario` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_ingreso`)
) ENGINE=MyISAM AUTO_INCREMENT=125 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `localidad`
--

DROP TABLE IF EXISTS `localidad`;
CREATE TABLE IF NOT EXISTS `localidad` (
  `cod_localidad` int(11) NOT NULL AUTO_INCREMENT,
  `localidad` varchar(50) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `codigo_postal` varchar(50) NOT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_localidad`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `monedas`
--

DROP TABLE IF EXISTS `monedas`;
CREATE TABLE IF NOT EXISTS `monedas` (
  `cod_moneda` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_moneda` varchar(150) NOT NULL,
  `signo_moneda` varchar(150) CHARACTER SET utf8 NOT NULL,
  `estado` varchar(50) NOT NULL,
  `seleccion_moneda` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_moneda`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `monedas`
--

INSERT INTO `monedas` (`cod_moneda`, `nombre_moneda`, `signo_moneda`, `estado`, `seleccion_moneda`) VALUES
(1, 'DOLARES ESTADO ESTADOUNIDENCES', 'USD', 'ACTIVA', ''),
(3, 'PESOS ARGENTINOS', '$', 'ACTIVA', 'SELECCIONADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nota_credito`
--

DROP TABLE IF EXISTS `nota_credito`;
CREATE TABLE IF NOT EXISTS `nota_credito` (
  `cod_nota_credito` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `cod_empleado` int(11) NOT NULL,
  `tipo_pago` varchar(20) NOT NULL,
  `condicion` varchar(50) NOT NULL,
  `iva` varchar(10) NOT NULL,
  `tipo_nota_credito` varchar(20) NOT NULL,
  `total_con_iva` double(11,2) NOT NULL,
  `total_sin_iva` double(11,2) NOT NULL,
  `ivaDiscriminado` varchar(10) NOT NULL,
  `referencia_factura` int(11) NOT NULL,
  PRIMARY KEY (`cod_nota_credito`),
  KEY `descripcion` (`fecha`),
  KEY `cod_cliente` (`cod_cliente`,`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nota_credito`
--

INSERT INTO `nota_credito` (`cod_nota_credito`, `fecha`, `cod_cliente`, `cod_empleado`, `tipo_pago`, `condicion`, `iva`, `tipo_nota_credito`, `total_con_iva`, `total_sin_iva`, `ivaDiscriminado`, `referencia_factura`) VALUES
(0, '2015-02-01', 0, 0, '0', '0', '0', '0', 0.00, 0.00, '0', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nota_credito_compra`
--

DROP TABLE IF EXISTS `nota_credito_compra`;
CREATE TABLE IF NOT EXISTS `nota_credito_compra` (
  `cod_nota_credito_compra` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `cod_proveedor` int(11) NOT NULL,
  `cod_empleado` int(11) NOT NULL,
  `tipo_pago` varchar(20) NOT NULL,
  `condicion` varchar(50) NOT NULL,
  `iva` varchar(10) NOT NULL,
  `tipo_nota_credito` varchar(20) NOT NULL,
  `total_con_iva` double(11,2) NOT NULL,
  `total_sin_iva` double(11,2) NOT NULL,
  `ivaDiscriminado` varchar(10) NOT NULL,
  `referencia_factura` int(11) NOT NULL,
  PRIMARY KEY (`cod_nota_credito_compra`),
  KEY `descripcion` (`fecha`),
  KEY `cod_proveedor` (`cod_proveedor`,`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nota_credito_compra`
--

INSERT INTO `nota_credito_compra` (`cod_nota_credito_compra`, `fecha`, `cod_proveedor`, `cod_empleado`, `tipo_pago`, `condicion`, `iva`, `tipo_nota_credito`, `total_con_iva`, `total_sin_iva`, `ivaDiscriminado`, `referencia_factura`) VALUES
(0, '2012-02-03', 0, 0, '0', '0', '0', '0', 0.00, 0.00, '0', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `porcentajes`
--

DROP TABLE IF EXISTS `porcentajes`;
CREATE TABLE IF NOT EXISTS `porcentajes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `porcentaje_ganancia` varchar(30) NOT NULL,
  `porcentaje_iva_compra` varchar(30) NOT NULL,
  `porcentaje_iva_venta` varchar(30) NOT NULL,
  `actualizacionPrecios` varchar(50) NOT NULL,
  `porcentaje_iva_factura` varchar(50) NOT NULL,
  `impresion` varchar(200) NOT NULL,
  `tipo_factura_electronica` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `porcentajes`
--

INSERT INTO `porcentajes` (`id`, `porcentaje_ganancia`, `porcentaje_iva_compra`, `porcentaje_iva_venta`, `actualizacionPrecios`, `porcentaje_iva_factura`, `impresion`, `tipo_factura_electronica`) VALUES
(1, '65', '0%', '0%', 'INACTIVO', '0%', '56', '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presupuesto`
--

DROP TABLE IF EXISTS `presupuesto`;
CREATE TABLE IF NOT EXISTS `presupuesto` (
  `cod_presupuesto` int(11) NOT NULL,
  `fecha` varchar(30) NOT NULL,
  `cod_cliente` varchar(30) NOT NULL,
  `cod_empleado` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_presupuesto`),
  KEY `descripcion` (`fecha`),
  KEY `cod_cliente` (`cod_cliente`,`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `presupuesto`
--

INSERT INTO `presupuesto` (`cod_presupuesto`, `fecha`, `cod_cliente`, `cod_empleado`) VALUES
(0, '20130102', '0', '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `cod_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_firma` varchar(30) NOT NULL,
  `responsable_firma` varchar(40) NOT NULL,
  `direccion_firma` varchar(40) NOT NULL,
  `ciudad_firma` varchar(40) NOT NULL,
  `cod_postal_firma` varchar(30) NOT NULL,
  `provincia_firma` varchar(30) NOT NULL,
  `cuit` varchar(50) NOT NULL,
  `ingresos_brutos` varchar(40) CHARACTER SET utf8mb4 NOT NULL,
  `cbu` varchar(50) NOT NULL,
  `telefono_firma` varchar(30) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `condicion` varchar(30) NOT NULL,
  PRIMARY KEY (`cod_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`cod_proveedor`, `nombre_firma`, `responsable_firma`, `direccion_firma`, `ciudad_firma`, `cod_postal_firma`, `provincia_firma`, `cuit`, `ingresos_brutos`, `cbu`, `telefono_firma`, `estado`, `condicion`) VALUES
(0, 'SIN PROVEEDOR', '', '-', '', '', '', '', '', '', '', 'ACTIVO', 'Consumidor Final');

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
-- Estructura de tabla para la tabla `referencia_compra`
--

DROP TABLE IF EXISTS `referencia_compra`;
CREATE TABLE IF NOT EXISTS `referencia_compra` (
  `cod_compra` int(11) NOT NULL,
  `codigo_producto` varchar(150) NOT NULL,
  `referencia` varchar(100) NOT NULL,
  `cantidad` double(11,2) NOT NULL,
  `unitario_costo` double(11,2) NOT NULL,
  `total_costo` double(11,2) NOT NULL,
  `valor_unitario` double(11,2) NOT NULL,
  `valor_total` double(11,2) NOT NULL,
  `total_compra` double(11,2) NOT NULL,
  `descuento` double(11,2) NOT NULL,
  `unitario_sin_iva` double(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_cuenta_corriente`
--

DROP TABLE IF EXISTS `referencia_cuenta_corriente`;
CREATE TABLE IF NOT EXISTS `referencia_cuenta_corriente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cod_cuenta` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `pago_abonado` double(11,2) NOT NULL,
  `total_pago` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  `total_remitos` double(11,2) NOT NULL,
  `actualizacion_precios` double(11,2) NOT NULL,
  `cod_caja` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_cuenta_credito_compra`
--

DROP TABLE IF EXISTS `referencia_cuenta_credito_compra`;
CREATE TABLE IF NOT EXISTS `referencia_cuenta_credito_compra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cod_cuenta` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `pago_abonado` double(11,2) NOT NULL,
  `total_pago` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  `total_factura` double(11,2) NOT NULL,
  `cod_factura` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_cuenta_credito_venta`
--

DROP TABLE IF EXISTS `referencia_cuenta_credito_venta`;
CREATE TABLE IF NOT EXISTS `referencia_cuenta_credito_venta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cod_cuenta` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `pago_abonado` double(11,2) NOT NULL,
  `total_pago` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  `total_factura` double(11,2) NOT NULL,
  `cod_factura` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=143 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_factura`
--

DROP TABLE IF EXISTS `referencia_factura`;
CREATE TABLE IF NOT EXISTS `referencia_factura` (
  `cod_factura` int(11) NOT NULL,
  `codigo_producto` varchar(150) NOT NULL,
  `valor_unitario` double(11,2) NOT NULL,
  `valor_total` double(11,2) NOT NULL,
  `referencia` varchar(150) NOT NULL,
  `cantidad` double(11,2) NOT NULL,
  `Total` double(11,2) NOT NULL,
  `unitarioBruto` double(11,2) NOT NULL,
  `totalBruto` double(11,2) NOT NULL,
  `descuento` double(11,2) NOT NULL,
  KEY `cod_factura` (`cod_factura`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_nota_credito`
--

DROP TABLE IF EXISTS `referencia_nota_credito`;
CREATE TABLE IF NOT EXISTS `referencia_nota_credito` (
  `cod_nota_credito` int(11) NOT NULL,
  `codigo_producto` varchar(150) NOT NULL,
  `valor_unitario` double(11,2) NOT NULL,
  `valor_total` double(11,2) NOT NULL,
  `referencia` varchar(150) NOT NULL,
  `cantidad` double(11,2) NOT NULL,
  `Total` double(11,2) NOT NULL,
  `unitarioBruto` double(11,2) NOT NULL,
  `totalBruto` double(11,2) NOT NULL,
  `descuento` double(11,2) NOT NULL,
  KEY `cod_nota_credito` (`cod_nota_credito`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_nota_credito_compra`
--

DROP TABLE IF EXISTS `referencia_nota_credito_compra`;
CREATE TABLE IF NOT EXISTS `referencia_nota_credito_compra` (
  `cod_nota_credito_compra` int(11) NOT NULL,
  `codigo_producto` varchar(150) NOT NULL,
  `valor_unitario` double(11,2) NOT NULL,
  `valor_total` double(11,2) NOT NULL,
  `referencia` varchar(150) NOT NULL,
  `cantidad` double(11,2) NOT NULL,
  `Total` double(11,2) NOT NULL,
  `unitarioBruto` double(11,2) NOT NULL,
  `totalBruto` double(11,2) NOT NULL,
  `descuento` double(11,2) NOT NULL,
  KEY `cod_nota_credito_compra` (`cod_nota_credito_compra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_presupuesto`
--

DROP TABLE IF EXISTS `referencia_presupuesto`;
CREATE TABLE IF NOT EXISTS `referencia_presupuesto` (
  `cod_presupuesto` int(11) NOT NULL,
  `valor_unitario` double(11,2) NOT NULL,
  `valor_total` double(11,2) NOT NULL,
  `referencia` varchar(150) NOT NULL,
  `cantidad` double(11,2) NOT NULL,
  `Total` double(11,2) NOT NULL,
  KEY `cod_presupuesto` (`cod_presupuesto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `referencia_remito`
--

DROP TABLE IF EXISTS `referencia_remito`;
CREATE TABLE IF NOT EXISTS `referencia_remito` (
  `cod_remito` int(11) NOT NULL,
  `valor_unitario` double(11,2) NOT NULL,
  `valor_total` double(11,2) NOT NULL,
  `cod_articulo` varchar(150) NOT NULL,
  `referencia` varchar(150) DEFAULT NULL,
  `cantidad` double(11,2) DEFAULT NULL,
  `total` double(11,2) NOT NULL,
  `unitarioBruto` double(11,2) NOT NULL,
  `totalBruto` double(11,2) NOT NULL,
  `unitario_sin_iva` double(11,2) NOT NULL,
  KEY `cod_remito` (`cod_remito`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remito`
--

DROP TABLE IF EXISTS `remito`;
CREATE TABLE IF NOT EXISTS `remito` (
  `cod_remito` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `cod_cliente` int(11) NOT NULL,
  `cod_empleado` int(11) NOT NULL,
  `tipo_pago` varchar(20) NOT NULL,
  `condicion` varchar(20) NOT NULL,
  `total` double(11,2) NOT NULL,
  PRIMARY KEY (`cod_remito`),
  KEY `descripcion` (`fecha`),
  KEY `cod_cliente` (`cod_cliente`,`cod_empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `representante_empresa`
--

DROP TABLE IF EXISTS `representante_empresa`;
CREATE TABLE IF NOT EXISTS `representante_empresa` (
  `cod_representante` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_apellido` varchar(40) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `empresa` int(11) NOT NULL,
  PRIMARY KEY (`cod_representante`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `retiro_efectivo`
--

DROP TABLE IF EXISTS `retiro_efectivo`;
CREATE TABLE IF NOT EXISTS `retiro_efectivo` (
  `cod_retiro` int(11) NOT NULL AUTO_INCREMENT,
  `cod_caja` varchar(200) NOT NULL,
  `monto_retirado` double(11,2) NOT NULL,
  `concepto` varchar(200) NOT NULL,
  `vendedor` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  `hora` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_retiro`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

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
  `clave` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `usuario` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `apellido` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `acceso` varchar(25) NOT NULL,
  `cod_usuario` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cod_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`clave`, `usuario`, `nombre`, `apellido`, `direccion`, `telefono`, `estado`, `acceso`, `cod_usuario`) VALUES
('admin', 'admin', 'HUGO', 'FLORES', '', '', 'ACTIVO', 'Administrador', 1),
('facturador', 'facturador', 'FACTURADOR', 'PRUEBA', '', '', 'ACTIVO', 'Facturador', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_cheque`
--

DROP TABLE IF EXISTS `venta_cheque`;
CREATE TABLE IF NOT EXISTS `venta_cheque` (
  `cod_cheque` int(11) NOT NULL AUTO_INCREMENT,
  `cod_venta` varchar(200) NOT NULL,
  `num_cheque` varchar(200) NOT NULL,
  `fecha_emision` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `monto` double(11,2) NOT NULL,
  PRIMARY KEY (`cod_cheque`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_credito`
--

DROP TABLE IF EXISTS `venta_credito`;
CREATE TABLE IF NOT EXISTS `venta_credito` (
  `cod_credito` int(11) NOT NULL AUTO_INCREMENT,
  `cod_venta` varchar(200) NOT NULL,
  `total` double(11,2) NOT NULL,
  `acuenta` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  `cod_caja` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_credito`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_deposito`
--

DROP TABLE IF EXISTS `venta_deposito`;
CREATE TABLE IF NOT EXISTS `venta_deposito` (
  `cod_deposito` int(11) NOT NULL AUTO_INCREMENT,
  `cod_venta` varchar(200) NOT NULL,
  `num_cuenta` varchar(200) NOT NULL,
  `num_deposito` varchar(200) NOT NULL,
  `monto` double(11,2) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`cod_deposito`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_efectivo`
--

DROP TABLE IF EXISTS `venta_efectivo`;
CREATE TABLE IF NOT EXISTS `venta_efectivo` (
  `cod_efectivo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_venta` varchar(200) NOT NULL,
  `subtotal` double(11,2) NOT NULL,
  `iva` double(11,2) NOT NULL,
  `total` double(11,2) NOT NULL,
  `cod_caja` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_efectivo`)
) ENGINE=MyISAM AUTO_INCREMENT=129 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_mixta`
--

DROP TABLE IF EXISTS `venta_mixta`;
CREATE TABLE IF NOT EXISTS `venta_mixta` (
  `cod_mixta` int(11) NOT NULL AUTO_INCREMENT,
  `cod_venta` varchar(200) NOT NULL,
  `total` double(11,2) NOT NULL,
  `monto_efectivo` double(11,2) NOT NULL,
  `monto_tarjeta` double(11,2) NOT NULL,
  `saldo_restante` double(11,2) NOT NULL,
  `vuelto` double(11,2) NOT NULL,
  `cod_caja` varchar(200) NOT NULL,
  PRIMARY KEY (`cod_mixta`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
