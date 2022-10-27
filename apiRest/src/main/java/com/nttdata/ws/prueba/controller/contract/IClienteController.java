/**
 * 
 */
package com.nttdata.ws.prueba.controller.contract;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nttdata.ws.prueba.model.ClienteType;
import com.nttdata.ws.prueba.model.RespuestaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author Angelica
 *
 */
@Validated
public interface IClienteController {

	/**
	 * Operación POST para la creación de clientes
	 * 
	 * @param body
	 * @return
	 */
	@Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente", tags = { "Clientes" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/clientes", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8", method = RequestMethod.POST)
	ResponseEntity<?> crearCliente(
			@Parameter(in = ParameterIn.DEFAULT, description = "Cliente", required = true, schema = @Schema()) @Valid @RequestBody ClienteType body);

	/**
	 * Operación PUT para la actualización de cliente
	 * 
	 * @param body
	 * @return
	 */
	@Operation(summary = "Actualizar la información de un cliente existente", description = "Actualizar Cliente registrado", tags = {
			"Clientes" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/clientes", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8", method = RequestMethod.PUT)
	ResponseEntity<?> actualizarCliente(
			@Parameter(in = ParameterIn.DEFAULT, description = "Cliente", required = true, schema = @Schema()) @Valid @RequestBody ClienteType body);

	/**
	 * Operación DELETE para la eliminar cliente
	 * 
	 * @param clienteId
	 * @return
	 */
	@Operation(summary = "eliminarCliente", description = "Elimina un Cliente por ID", tags = { "Clientes" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/clientes/{clienteId}", produces = "application/json; charset=UTF-8", method = RequestMethod.DELETE)
	ResponseEntity<?> eliminarCliente(
			@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("clienteId") String clienteId);

	/**
	 * Operación GET para obtener la inforamción del cliente por su identificación
	 * 
	 * @param numIdentificacion
	 * @return
	 */
	@Operation(summary = "consulta cliente por identificación", description = "consulta cliente por identificación", tags = {
			"Clientes" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ClienteType.class))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/clientes/{numIdentificacion}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	ResponseEntity<?> consultarClientePorIdentificacion(
			@Parameter(in = ParameterIn.PATH, description = "num.de Identificación del Cliente", required = true, schema = @Schema()) @PathVariable("numIdentificacion") String numIdentificacion);

	/**
	 * Operación GET para consutar los clientes activos
	 * @return
	 */
	@Operation(summary = "consultar clientes activos", description = "Retorna la lista clientes con estado ACTIVO", tags = {
			"Clientes" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClienteType.class)))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/clientes", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	ResponseEntity<?> consultarClientes();

}
