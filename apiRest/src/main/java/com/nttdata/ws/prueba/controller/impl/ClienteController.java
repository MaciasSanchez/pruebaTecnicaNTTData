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
import com.nttdata.ws.prueba.controller.contract.IClienteController;
import com.nttdata.ws.prueba.model.ClienteType;
import com.nttdata.ws.prueba.model.CrearUsuarioRequest;
import com.nttdata.ws.prueba.model.RespuestaType;
import com.nttdata.ws.prueba.repository.model.Cliente;
import com.nttdata.ws.prueba.service.contract.IClienteSvc;
import com.nttdata.ws.prueba.utils.ClienteValidator;
import com.nttdata.ws.prueba.utils.DataValidator;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;


/**
 * @author Angelica
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class ClienteController implements IClienteController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	IClienteSvc clienteSvc;

	@Override
	public ResponseEntity<?> crearCliente(@Valid CrearUsuarioRequest body) {
		ResponseEntity<?> respuestaCrear;
		try {	
			LOG.info("INICIA PROCESO DE CREAR CLIENTE");
			ClienteType result = clienteSvc.crearCliente(body);
			respuestaCrear = ClienteValidator.validarResultadoaByCreate(result);
		} catch (Exception e) {
			LOG.error("ERROR PROCESO DE CREAR CLIENTE {}",e.getMessage());
			return respuestaCrear = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		LOG.info("FINALIZA PROCESO DE CREAR CLIENTE");
		return respuestaCrear;
	}

	@Override
	public ResponseEntity<?> actualizarCliente(@Valid ClienteType body) {
		ResponseEntity<?> respuestaActualizar;
		try {
			LOG.info("INICIA PROCESO DE ACTUALIZAR CLIENTE");
			ClienteType result = clienteSvc.actualizarCliente(body);
			respuestaActualizar = ClienteValidator.validarResultadoaByUpdate(result, body.getId(),
					body.getIdentificacion());
		} catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO EN ACTUALIZAR CLIENTE {}",e.getMessage());;
			return DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("ERROR PROCESO DE ACTUALIZAR CLIENTE {}",e.getMessage());
			return new ResponseEntity<>(new RespuestaType().codigoRespuesta("500").descripcion("ERROR INTERNO"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA PROCESO DE ACTUALIZAR CLIENTE");
		return respuestaActualizar;
	}
	
	

	@Override
	public ResponseEntity<?> eliminarCliente(String clienteId) {
		try {
			LOG.info("INICIA ELIMINAR CLIENTE POR ID");
			Boolean cliente = clienteSvc.eliminarCliente(clienteId);
			return new ResponseEntity<Boolean>(cliente, HttpStatus.OK);
		}catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO ELIMINAR CLIENTE POR ID ->{}", e.getMessage());
			return DataValidator.validarResultado(e);
		}catch (Exception e) {
			LOG.error("ERROR EN EXCEPTION AL ELIMINAR CLIENTE POR ID {}",e.getMessage());
			return new ResponseEntity<RespuestaType>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> consultarClientePorIdentificacion(String numIdentificacion) {
		ResponseEntity<?> respuestaConsultarCliente;
		try {
			LOG.info("INICIA CONSULTA CLIENTE POR IDENTIFICACION");
			Cliente cliente = clienteSvc.consultarClientePorIdentificacion(numIdentificacion);
			respuestaConsultarCliente = ClienteValidator.validarResultado(cliente);
		} catch (BusinessException e) {
			LOG.error("ERROR DE NEGOCIO CONSULTAR CLIENTE POR IDENTIFICACION ->{}", e.getMessage());
			respuestaConsultarCliente = DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("ERROR EN EXCEPTION CONSULTA CLIENTE POR IDENTIFICACION");
			return respuestaConsultarCliente = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CONSULTA CLIENTE POR IDENTIFICACION");
		return respuestaConsultarCliente;
	}

	@Override
	public ResponseEntity<?> consultarClientes() {
		ResponseEntity<?> respuestaConsultarClientePorEstado;
		try {
			LOG.info("INICIA CONSULTAR CLIENTES ACTIVOS");
			List<ClienteType> listadoClientes = clienteSvc.consultarClientes();
			respuestaConsultarClientePorEstado = ClienteValidator.validarResultado(listadoClientes);
		}catch (Exception e) {
			LOG.error("ERROR EN EXCEPTION AL CONSULTAR CLIENTES POR ESTADO {}",e.getMessage());
			return new ResponseEntity<RespuestaType>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajesDelServicio.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CONSULTA CLIENTES POR ESTADO");
		return respuestaConsultarClientePorEstado;
	}


}
