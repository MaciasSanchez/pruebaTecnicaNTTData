/**
 * 
 */
package com.nttdata.ws.prueba.utils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nttdata.ws.prueba.model.ClienteType;
import com.nttdata.ws.prueba.model.RespuestaType;
import com.nttdata.ws.prueba.repository.model.Cliente;

/**
 * @author Angelica
 *
 */
public final class ClienteValidator {

	private ClienteValidator() {

	}

	/**
	 * VALIDAMOS EL CONTENIDO DEL RESULTADO CUANDO CREAMOS UNA USUARIO PARA DEFINIR
	 * EL CODIGO HTTP CORRESPONDIENTE
	 * 
	 * @param resultado
	 * @return el dato de repuesta con el codigo Http correspondiente
	 */
	public static ResponseEntity<RespuestaType> validarResultadoaByCreate(@Valid ClienteType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion("El recurso no pudo ser creado"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(
				new RespuestaType().codigoRespuesta("201")
						.descripcion(String.format("ID del recurso creado: [%s]", resultado.getId().toString())),
				HttpStatus.CREATED);
	}

	/**
	 * VALIDAMOS EL CONTENIDO DEL RESULTADOS CUANDO ES LISTA PARA DEFINIR EL CONDIGO
	 * HTTP CORRESPONDIENTE
	 * 
	 * @param resultado
	 * @return
	 */
	public static ResponseEntity<Object> validarResultado(List<?> resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(new RespuestaType().codigoRespuesta("500")
					.descripcion("ALCO OCURRIO, NO PODIMOS OBTENER LO SOLICITADO"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (resultado.isEmpty()) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("204").descripcion("NO SE ENCONTRARON RESULTADOS"),
					HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(resultado);

	}

	/**
	 * VALIDAMOS EL CONTENIDO DEL RESULTADOS CUANDO ES "CLIENTE" PARA DEFINIR EL
	 * CONDIGO HTTP CORRESPONDIENTE
	 * 
	 * @param resultado
	 * @return
	 */
	public static ResponseEntity<Object> validarResultado(Cliente resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion("LA SOLICITUD NO SE PROCESO EXITOSAMENTE"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(resultado);
	}

	/**
	 * VALIDAMOS EL CONTENIDO DEL RESULTADO CUANDO ES UN "ClienteType" PARA DEFINIR
	 * EL CODIGO HTTP CORRESPONDIENTE
	 * 
	 * @param resultado
	 * @return
	 */
	public static ResponseEntity<RespuestaType> validarResultado(@Valid ClienteType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion("LA PETICION NO SE PROCESADO EXITOSAMENTE"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(new RespuestaType().codigoRespuesta("200").descripcion("PROCESO EXITOSO"));
	}

	/**
	 * VALIDAMOS EL CONTENIDO DEL RESULTADOS CUANDO ES "Cliente" PARA DEFINIR EL
	 * CONDIGO HTTP CORRESPONDIENTE
	 * 
	 * @param resultado
	 * @return
	 */
	public static ResponseEntity<Object> validarResultado(Optional<Cliente> resultado) {
		if (resultado.isEmpty()) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("204").descripcion("NO SE ENCONTRARON RESULTADOS"),
					HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(resultado);

	}
	public static ResponseEntity<RespuestaType> validarResultadoaByUpdate(@Valid ClienteType resultado, UUID uuid,
			String identificacion) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("404").descripcion(
							String.format("El recurso ID: [%s] - identificacion : [%s] no existe", uuid, identificacion)),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new RespuestaType().codigoRespuesta("200")
				.descripcion(String.format("Usuario con el ID: [%s] - identificacion : [%s] se actualizo con éxito",
						resultado.getId().toString(), resultado.getIdentificacion())),
				HttpStatus.OK);
	}

}
