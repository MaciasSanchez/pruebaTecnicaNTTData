package com.nttdata.ws.prueba.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nttdata.ws.prueba.model.RespuestaType;
import com.nttdata.ws.prueba.utils.exceptions.ApplicationException;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;


/**
 * @author Angelica
 *
 */
public final  class DataValidator {
	
	public static final boolean validarFecha(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);
			return true;
		} catch (ParseException e) {
			return false;
		}			
	}

	/**
	 * VALIDAMOS EL CONTENIDO DE UNA EXCEPCION DE NEGOCION  "BusinessException" 
	 * PARA DEFINIR EL CODIGO HTTP CORRESPONDIENTE
	 * 
	 * @param ex
	 * @return el dato de repuesta con el codigo Http correspondiente
	 */
	public static ResponseEntity<RespuestaType> validarResultado(BusinessException ex) {
		ResponseEntity<RespuestaType> respuesta;
		switch (ex.getError()) {
		case NO_ENCONTRADO:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("404").descripcion(ex.getMessage()),
					HttpStatus.NOT_FOUND);
			break;
		case SOLICITUD_INVALIDA:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("400").descripcion(ex.getMessage()),
					HttpStatus.BAD_REQUEST);
			break;
		case SERVICIO_INACCESIBLE:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("502").descripcion(ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		case FUENTE_DE_DATOS:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("503").descripcion(ex.getMessage()),
					HttpStatus.SERVICE_UNAVAILABLE);
			break;
		case LOGICA_NEGOCIO:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("0004").descripcion(ex.getMessage()),
					HttpStatus.BAD_REQUEST);
			break;
		default:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("500").descripcion(ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		}

		return respuesta;
	}
	
	/**
	 * VALIDAMOS EL CONTENIDO DE UNA EXCEPCION DE NEGOCION  "ApplicationException" 
	 * PARA DEFINIR EL CODIGO HTTP CORRESPONDIENTE
	 * 
	 * @param ex
	 * @return el dato de repuesta con el codigo Http correspondiente
	 */
	public static ResponseEntity<RespuestaType> validarResultado(ApplicationException ex) {
		ResponseEntity<RespuestaType> respuesta;
		switch (ex.getError()) {
		case NO_ENCONTRADO:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("404").descripcion(ex.getMessage()),
					HttpStatus.NOT_FOUND);
			break;
		case SOLICITUD_INVALIDA:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("400").descripcion(ex.getMessage()),
					HttpStatus.BAD_REQUEST);
			break;
		case SERVICIO_INACCESIBLE:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("502").descripcion(ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		case FUENTE_DE_DATOS:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("503").descripcion(ex.getMessage()),
					HttpStatus.SERVICE_UNAVAILABLE);
			break;
		case LOGICA_NEGOCIO:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("0004").descripcion(ex.getMessage()),
					HttpStatus.BAD_REQUEST);
			break;
		default:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("500").descripcion(ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		}

		return respuesta;
	}

	public static ResponseEntity<Object> validarResultadoObj(Object exe) {
		ResponseEntity<Object> respuesta;
		BusinessException ex = (BusinessException) exe;
		switch (ex.getError()) {
		case NO_ENCONTRADO:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("404").descripcion(ex.getMessage()),
					HttpStatus.NOT_FOUND);
			break;
		case SOLICITUD_INVALIDA:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("400").descripcion(ex.getMessage()),
					HttpStatus.BAD_REQUEST);
			break;
		case SERVICIO_INACCESIBLE:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("502").descripcion(ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		case FUENTE_DE_DATOS:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("503").descripcion(ex.getMessage()),
					HttpStatus.SERVICE_UNAVAILABLE);
			break;
		case LOGICA_NEGOCIO:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("0004").descripcion(ex.getMessage()),
					HttpStatus.BAD_REQUEST);
			break;
		default:
			respuesta = new ResponseEntity<>(new RespuestaType().codigoRespuesta("500").descripcion(ex.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		}

		return respuesta;
	}
}
