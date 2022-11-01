/**
 * 
 */
package com.nttdata.ws.prueba.controller.impl;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.ws.prueba.constants.MensajesDelServicio;
import com.nttdata.ws.prueba.controller.contract.IMovimientoController;
import com.nttdata.ws.prueba.model.CrearMovimientoRequest;
import com.nttdata.ws.prueba.model.EstadoCuentaDetalladoType;
import com.nttdata.ws.prueba.model.EstadoCuentaType;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.model.RespuestaType;
import com.nttdata.ws.prueba.model.TiposDeMovimiento;
import com.nttdata.ws.prueba.service.contract.IMovimientosSvc;
import com.nttdata.ws.prueba.utils.DataValidator;
import com.nttdata.ws.prueba.utils.MovimientosValidator;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;

/**
 * @author Angelica
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class MovimientoController implements IMovimientoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MovimientoController.class);
	@Autowired
	IMovimientosSvc mvtsSvc;
	@Override
	public ResponseEntity<RespuestaType> crearMovimiento(@Valid CrearMovimientoRequest body) {
		ResponseEntity<RespuestaType> respuestaCrear;
		try {	
			LOG.info("INICIA PROCESO DE CREAR MOVIMIENTO");
			MovimientosType result = mvtsSvc.crearMovimiento(body);
			respuestaCrear = MovimientosValidator.validarResultadoaByCreate(result);
		} catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO al CREAR MOVIMIENTO {}",e.getMessage());;
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("ERROR PROCESO DE CREAR MOVIMIENTO {}",  e.getMessage());
			return respuestaCrear = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA PROCESO DE CREAR MOVIMIENTO");
		return respuestaCrear;
	}
	@Override
	public ResponseEntity<RespuestaType> actualizarMovimiento(@Valid MovimientosType body) {
		ResponseEntity<RespuestaType> respuestaActualizar;
		try {	
			LOG.info("INICIA PROCESO DE ACTUALIZAR MOVIMIENTO");
			MovimientosType result = mvtsSvc.actualizarMovimiento(body);
			respuestaActualizar = MovimientosValidator.validarResultadoaByUpdate(result, body.getId(), body.getNumeroDeCuenta());
			LOG.info("FINALIZA PROCESO DE ACTUALIZAR MOVIMIENTO");
			return respuestaActualizar;
		} catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO EN ACTUALIZAR MOVIMIENTO", e.getMessage());
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("ERROR PROCESO DE ACTUALIZAR MOVIMIENTO",  e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion("ERROR INTERNO"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	public ResponseEntity<?> eliminarMovimiento(String movimientoId) {
		try {
			LOG.info("INICIA ELIMINAR MOVIMIENTO POR ID");
			Boolean movimiento = mvtsSvc.eliminarMovimiento(movimientoId);
			return new ResponseEntity<Boolean>(movimiento, HttpStatus.OK);
		}catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO ELIMINAR MOVIMIENTO POR ID ->{}", e.getMessage());
			return DataValidator.validarResultado(e);
		}catch (Exception e) {
			LOG.error("ERROR EN EXCEPTION AL ELIMINAR MOVIMIENTO POR ID {}",e.getMessage());
			return new ResponseEntity<RespuestaType>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<?> consultarMovimientosPorFechas(Date fechaDesde, Date fechaHasta) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA MOVIMIENTOS DE UN RANGO DE FECHAS");
		try {
			List<MovimientosType> movimientosCliente = mvtsSvc.consultarMovimientosPorFechas(fechaDesde, fechaHasta);
			respuesta = MovimientosValidator.validarResultado(movimientosCliente);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA MOVIMIENTOS DE UN RANGO DE FECHAS {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA MOVIMIENTOS DE UN RANGO DE FECHAS");
		return respuesta;
	}
	@Override
	public ResponseEntity<?> consultarMovimientosPorCliente(String numIdentificacion, Date fechaDesde,
			Date fechaHasta) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA MOVIMIENTOS POR CLIENTE");
		try {
			List<MovimientosType> movimientosCliente = mvtsSvc.consultarMovimientosPorCliente(numIdentificacion, fechaDesde, fechaHasta);
			respuesta = MovimientosValidator.validarResultado(movimientosCliente);
		}catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO CONSULTA MOVIMIENTOS POR CLIENTE ->{}", e.getMessage());
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA MOVIMIENTOS POR CLIENTE {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA MOVIMIENTOS POR CLIENTE");
		return respuesta;
	}
	
	
	@Override
	public ResponseEntity<?> consultarMovimientosPorCta(String numCuenta) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA");
		try {
			List<MovimientosType> movimientosCliente = mvtsSvc.consultarMovimientosPorCta(numCuenta);
			respuesta = MovimientosValidator.validarResultado(movimientosCliente);
		} catch (BusinessException e) {
			LOG.error("ERROR EN CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA {}",e.getMessage());;
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA MOVIMIENTOS POR NUMERO DE CUENTA");
		return respuesta;
	}
	
	
	@Override
	public ResponseEntity<?> consultarMovimientosPorNumeroDeCta(String numCuenta, Date fechaDesde, Date fechaHasta) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA");
		try {
			List<MovimientosType> movimientosCliente = mvtsSvc.consultarMovimientosPorNumeroDeCta(numCuenta, fechaDesde, fechaHasta);
			respuesta = MovimientosValidator.validarResultado(movimientosCliente);
		} catch (BusinessException e) {
			LOG.error("ERROR EN CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA {}",e.getMessage());;
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA MOVIMIENTOS POR NUMERO DE CUENTA");
		return respuesta;
	}
	@Override
	public ResponseEntity<?> consultarMovimientosPorCuentaTipoMovimiento(String numeroCuenta,
			TiposDeMovimiento tipoMovimiento) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA  Y TIPO DE MOVIMIENTO");
		try {
			List<MovimientosType> movimientosCliente = mvtsSvc.consultarMovimientosPorCuentaTipoMovimiento(numeroCuenta, tipoMovimiento);
			respuesta = MovimientosValidator.validarResultado(movimientosCliente);
		} catch (BusinessException e) {
			LOG.error("ERROR EN CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA  Y TIPO DE MOVIMIENTO {}",e.getMessage());;
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA  Y TIPO DE MOVIMIENTO {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("INICIA CONSULTA MOVIMIENTOS POR NUMERO DE CUENTA  Y TIPO DE MOVIMIENTO");
		return respuesta;
	}
	@Override
	public ResponseEntity<?> consultarEstadoCuenta(String identificacion, Date fechaDesde, Date fechaHasta) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA DEL ESTADO DE CUENTA POR IDENTIFICACION DEL CLIENTE");
		try {
			EstadoCuentaType estadoCtaCliente = mvtsSvc.consultarEstadoCuenta(identificacion, fechaDesde, fechaHasta);
			respuesta = MovimientosValidator.validarResultado(estadoCtaCliente);
		}catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO CONSULTA DEL ESTADO DE CUENTA POR IDENTIFICACION DEL CLIENTE ->{}", e.getMessage());
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA DEL ESTADO DE CUENTA POR IDENTIFICACION DEL CLIENTE {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CONSULTA DEL ESTADO DE CUENTA POR IDENTIFICACION DEL CLIENTE");
		return respuesta;
	}
	@Override
	public ResponseEntity<?> consultarEstadoCuentaDetallado(String identificacion, Date fechaDesde, Date fechaHasta) {
		ResponseEntity<?> respuesta;
		LOG.info("INICIA CONSULTA DEL ESTADO DE CUENTA DETALLADO POR IDENTIFICACION DEL CLIENTE");
		try {
			EstadoCuentaDetalladoType estadoCtaCliente = mvtsSvc.consultarEstadoCuentaDetallado(identificacion, fechaDesde, fechaHasta);
			respuesta = MovimientosValidator.validarResultado(estadoCtaCliente);
		}catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO CONSULTA DEL ESTADO DE CUENTA DETALLADO POR IDENTIFICACION DEL CLIENTE ->{}", e.getMessage());
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.info("ERROR EN CONSULTA DEL ESTADO DE CUENTA DETALLADO POR IDENTIFICACION DEL CLIENTE {}", e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CONSULTA DEL ESTADO DE CUENTA DETALLADO POR IDENTIFICACION DEL CLIENTE");
		return respuesta;
	}
	
}
