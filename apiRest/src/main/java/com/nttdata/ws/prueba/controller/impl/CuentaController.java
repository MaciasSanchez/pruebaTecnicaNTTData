/**
 * 
 */
package com.nttdata.ws.prueba.controller.impl;

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
import com.nttdata.ws.prueba.controller.contract.ICuentaController;
import com.nttdata.ws.prueba.model.CrearCuentaRequest;
import com.nttdata.ws.prueba.model.CuentaType;
import com.nttdata.ws.prueba.model.RespuestaType;
import com.nttdata.ws.prueba.repository.model.Cuenta;
import com.nttdata.ws.prueba.service.contract.ICuentasSvc;
import com.nttdata.ws.prueba.utils.CuentaValidator;
import com.nttdata.ws.prueba.utils.DataValidator;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;

/**
 * @author Angelica
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class CuentaController implements ICuentaController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CuentaController.class);
	@Autowired
	ICuentasSvc cuentasSvc;
	@Override
	public ResponseEntity<?> crearCuenta(@Valid CrearCuentaRequest body) {
		ResponseEntity<?> respuestaCrear;
		try {	
			LOG.info("INICIA PROCESO DE CREAR CUENTA");
			CuentaType result = cuentasSvc.crearCuenta(body);
			respuestaCrear = CuentaValidator.validarResultadoaByCreate(result);
		} catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO al CREAR CUENTA {}",e.getMessage());;
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("ERROR PROCESO DE CREAR CUENTA");
			return respuestaCrear = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		LOG.info("FINALIZA PROCESO DE CREAR CUENTA");
		return respuestaCrear;
	}
	@Override
	public ResponseEntity<?> actualizarCuenta(@Valid CuentaType body) {
		ResponseEntity<?> respuestaActualizar;
		try {	
			LOG.info("INICIA PROCESO DE ACTUALIZAR CUENTA");
			CuentaType result = cuentasSvc.actualizarCuenta(body);
			respuestaActualizar = CuentaValidator.validarResultadoaByUpdate(result, body.getId(), body.getNumeroDeCuenta());
			LOG.info("FINALIZA PROCESO DE ACTUALIZAR CUENTA");
			return respuestaActualizar;
		} catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO EN ACTUALIZAR CUENTA", e.getMessage());
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("ERROR PROCESO DE ACTUALIZAR CUENTA",  e.getMessage());
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion("ERROR INTERNO"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	public ResponseEntity<?> eliminarCuenta(String cuentaId) {
		try {
			LOG.info("INICIA ELIMINAR CUENTA POR ID");
			Boolean cuenta = cuentasSvc.eliminarCuenta(cuentaId);
			return new ResponseEntity<Boolean>(cuenta, HttpStatus.OK);
		}catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO ELIMINAR CUENTA POR ID ->{}", e.getMessage());
			return DataValidator.validarResultado(e);
		}catch (Exception e) {
			LOG.error("ERROR EN EXCEPTION AL ELIMINAR CUENTA POR ID {}",e.getMessage());
			return new ResponseEntity<RespuestaType>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	public ResponseEntity<?> consultarCuentaPorNumero(String numCuenta) {
		ResponseEntity<?> respuestaConsultarCuentaPorNumero;
		try {
			LOG.info("INICIA CONSULTA CUENTA POR NUMERO");
			Cuenta cuenta = cuentasSvc.consultarCuentaPorNumero(numCuenta);
			respuestaConsultarCuentaPorNumero = CuentaValidator.validarResultado(cuenta);
		} catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO CONSULTAR CUENTA POR NUMERO ->{}", e.getMessage());
			respuestaConsultarCuentaPorNumero = DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("ERROR EN EXCEPTION CONSULTA CUENTA POR NUMERO");
			return respuestaConsultarCuentaPorNumero = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CONSULTA CUENTA POR NUMERO");
		return respuestaConsultarCuentaPorNumero;
	}
	@Override
	public ResponseEntity<?> consultarCuentaPorNumeroIdentificacion(String numIdentificacion) {
		ResponseEntity<?> respuestaConsultarCuentasPorNumeroIdentificacion;
		try {
			LOG.info("INICIA CONSULTAR CUENTAS POR CLIENTE");
			List<CuentaType> listadoCuentas = cuentasSvc.consultarCuentaPorNumeroIdentificacion(numIdentificacion);
			respuestaConsultarCuentasPorNumeroIdentificacion = CuentaValidator.validarResultado(listadoCuentas);
		}catch (Exception e) {
			LOG.error("ERROR EN EXCEPTION AL CONSULTAR CUENTAS POR CLIENTE {}",e.getMessage());
			return new ResponseEntity<RespuestaType>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CONSULTA CLIENTES POR CLIENTE");
		return respuestaConsultarCuentasPorNumeroIdentificacion;
	}

	@Override
	public ResponseEntity<?> consultarCuentas() {
		ResponseEntity<?> respuestaConsultarCuentasPorEstado;
		try {
			LOG.info("INICIA CONSULTAR CUENTAS POR ESTADO");
			List<CuentaType> listadoCuentas = cuentasSvc.consultarCuentas();
			respuestaConsultarCuentasPorEstado = CuentaValidator.validarResultado(listadoCuentas);
		}catch (Exception e) {
			LOG.error("ERROR EN EXCEPTION AL CONSULTAR CUENTAS POR ESTADO {}",e.getMessage());
			return new ResponseEntity<RespuestaType>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CONSULTA CLIENTES POR ESTADO");
		return respuestaConsultarCuentasPorEstado;
	}

	


}
