/**
 * 
 */
package com.nttdata.ws.prueba.controller.contract;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nttdata.ws.prueba.model.CrearMovimientoRequest;
import com.nttdata.ws.prueba.model.EstadoCuentaDetalladoType;
import com.nttdata.ws.prueba.model.EstadoCuentaType;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.model.RespuestaType;
import com.nttdata.ws.prueba.model.TiposDeMovimiento;

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
public interface IMovimientoController {

	/**
	 * Operación POST crearMovimiento
	 * 
	 * @param body
	 * @return
	 */
	@Operation(summary = "Crear un movimiento", description = "Crear una nuevo movimiento", tags = { "Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8", method = RequestMethod.POST)
	ResponseEntity<?> crearMovimiento(
			@Parameter(in = ParameterIn.DEFAULT, description = "Movimiento", required = true, schema = @Schema()) @Valid @RequestBody CrearMovimientoRequest body);

	/**
	 * Operación PUT actualizarMovimiento
	 * 
	 * @param body
	 * @return
	 */
	@Operation(summary = "Actualizar un movimiento", description = "Actualizar Movimiento existente", tags = {
			"Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos", produces = "application/json; charset=UTF-8", consumes = "application/json; charset=UTF-8", method = RequestMethod.PUT)
	ResponseEntity<?> actualizarMovimiento(
			@Parameter(in = ParameterIn.DEFAULT, description = "Movimiento", required = true, schema = @Schema()) @Valid @RequestBody MovimientosType body);

	/**
	 * Operación DELETE eliminarMovimiento
	 * 
	 * @param movimientoId
	 * @return
	 */
	@Operation(summary = "Eliminar un movimiento existente", description = "Elimina un movimiento por ID", tags = {
			"Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos/{movimientoId}", produces = "application/json; charset=UTF-8", method = RequestMethod.DELETE)
	ResponseEntity<?> eliminarMovimiento(
			@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("movimientoId") String movimientoId);

	/**
	 * Operación GET consultarMovimientosPorCuentaTipoMovimiento
	 * 
	 * /movimientos/cuenta/tipo/{numeroCuenta}/{tipoMovimiento}
	 * 
	 * @param numeroCuenta
	 * @param tipoMovimiento
	 * @return
	 */
	@Operation(summary = "Consultar los movimientos por un numero de cuenta y el tipo de movimiento", description = "Retorna el listado de movimientos según el número de cuenta y el tipo de movimiento ingresados", tags = {
			"Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovimientosType.class)))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos/cuenta/tipo/{numeroCuenta}/{tipoMovimiento}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	ResponseEntity<?> consultarMovimientosPorCuentaTipoMovimiento(
			@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("numeroCuenta") String numeroCuenta,
			@Parameter(in = ParameterIn.PATH, description = "Tipo Movimiento", required = true, schema = @Schema(allowableValues = {
					"RETIRO", "DEPOSITO" })) @PathVariable("tipoMovimiento") TiposDeMovimiento tipoMovimiento);

	/**
	 * Operación GET consultarMovimientosPorFechas
	 * 
	 * /movimientos/{fechaDesde}/{fechaHasta}
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Operation(summary = "Consultar los movimientos por un rango de fechas", description = "Consultar los movimientos por un rango de fechas", tags = {
			"Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovimientosType.class)))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos/{fechaDesde}/{fechaHasta}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	ResponseEntity<?> consultarMovimientosPorFechas(
			@Parameter(in = ParameterIn.PATH, description = "Fecha de inicio del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27")
			@PathVariable(name = "fechaDesde", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
			@Parameter(in = ParameterIn.PATH, description = "Fecha final del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27")
			@PathVariable(name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta);

	
	/**
	 * Operación GET consultarMovimientosPorNumeroDeCta
	 * 
	 * /movimientos/cuenta/{numCuenta}/{fechaDesde}/{fechaHasta}
	 * 
	 * @param numCuenta
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Operation(summary = "Consultar los movimientos por num. de cuenta", description = "Consultar los movimientos por num de cuenta", tags = {
			"Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovimientosType.class)))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos/cuenta/{numCuenta}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	ResponseEntity<?> consultarMovimientosPorCta(
			@Parameter(in = ParameterIn.PATH, description = "Número de cuenta", required=true, schema=@Schema(), example = "")
			@PathVariable(name = "numCuenta", required = true) String numCuenta);
	
	
	/**
	 * Operación GET consultarMovimientosPorNumeroDeCta
	 * 
	 * /movimientos/cuenta/{numCuenta}/{fechaDesde}/{fechaHasta}
	 * 
	 * @param numCuenta
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Operation(summary = "Consultar los movimientos por num. de cuenta y un rango de fechas", description = "Consultar los movimientos por num de cuenta y un rango de fechas", tags = {
			"Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovimientosType.class)))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos/cuenta/{numCuenta}/{fechaDesde}/{fechaHasta}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	ResponseEntity<?> consultarMovimientosPorNumeroDeCta(
			@Parameter(in = ParameterIn.PATH, description = "Número de cuenta", required=true, schema=@Schema(), example = "")
			@PathVariable(name = "numCuenta", required = true) String numCuenta,
			@Parameter(in = ParameterIn.PATH, description = "Fecha de inicio del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27")
			@PathVariable(name = "fechaDesde", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
			@Parameter(in = ParameterIn.PATH, description = "Fecha final del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27")
			@PathVariable(name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta);

	/**
	 * Operación GET consultarMovimientosPorCliente
	 * 
	 * /movimientos/cliente/{numIdentificacion}/{fechaDesde}/{fechaHasta}
	 * 
	 * @param numIdentificacion
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Operation(summary = "Consultar los movimientos por cliente y un rango de fechas", description = "Consultar los movimientos por usuario y un rango de fechas", tags = {
			"Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovimientosType.class)))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos/cliente/{numIdentificacion}/{fechaDesde}/{fechaHasta}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	ResponseEntity<?> consultarMovimientosPorCliente(
			@Parameter(in = ParameterIn.PATH, description = "Número de identificación del cliente", required=true, schema=@Schema(), example = "")
			@PathVariable(name = "numIdentificacion", required = true) String numIdentificacion,
			@Parameter(in = ParameterIn.PATH, description = "Fecha de inicio del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27")
			@PathVariable(name = "fechaDesde", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
			@Parameter(in = ParameterIn.PATH, description = "Fecha final del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27")
			@PathVariable(name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta);

	
	
	/**
	 * Operación GET consultarEstadoCuenta
	 * 
	 * /movimientos/reportes/{identificacion}/{fechaDesde}/{fechaHasta}
	 * 
	 * @param numIdentificacion
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Operation(summary = "Consultar estado de cuenta de un cliente", description = "Consultar estado de cuenta de un cliente", tags = {
			"Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstadoCuentaType.class))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos/reportes/{identificacion}/{fechaDesde}/{fechaHasta}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	ResponseEntity<?> consultarEstadoCuenta(
			@Parameter(in = ParameterIn.PATH, description = "Número de identificación del cliente", required=true, schema=@Schema(), example = "") 
			@PathVariable("identificacion") String identificacion,
			@Parameter(in = ParameterIn.PATH, description = "Fecha de inicio del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27") 
			@PathVariable (name = "fechaDesde", required = true)@DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde, 
			@Parameter(in = ParameterIn.PATH, description = "Fecha final del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27") 
			@PathVariable (name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta);
	
	
	/**
	 * Operación GET consultarEstadoCuenta
	 * 
	 * @param numIdentificacion
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Operation(summary = "Consultar estado de cuenta de un cliente agrupado por cuentas", description = "Consultar estado de cuenta de un cliente", tags = {
			"Movimientos" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstadoCuentaDetalladoType.class))),
			@ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@RequestMapping(value = "/movimientos/reportes/cliente/{identificacion}/{fechaDesde}/{fechaHasta}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
	ResponseEntity<?> consultarEstadoCuentaDetallado(
			@Parameter(in = ParameterIn.PATH, description = "Número de identificación del cliente", required=true, schema=@Schema(), example = "") 
			@PathVariable("identificacion") String identificacion,
			@Parameter(in = ParameterIn.PATH, description = "Fecha de inicio del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27") 
			@PathVariable (name = "fechaDesde", required = true)@DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde, 
			@Parameter(in = ParameterIn.PATH, description = "Fecha final del rango de fechas ", required=true, schema=@Schema(), example = "2022-10-27") 
			@PathVariable (name = "fechaHasta", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta);

}
