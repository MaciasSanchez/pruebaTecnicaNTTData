/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.ws.prueba.constants.MensajesDelServicio;
import com.nttdata.ws.prueba.utils.DecimalJsonSerializer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelica
 *
 */

@Getter
@Setter
public class EstadoCuentaDetalladoType {
	
	
	private String reporte = MensajesDelServicio.NOMBRE_REPORTE;
	private String nombreCliente;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
	private Date fechaInicioCorte;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
	private Date fechaFinCorte;
	private List<DetalleMovimientosCuentas> listadoCuentas;
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private double totalDepositosRealizados;
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private double totalRetirosRealizados;
	
	
}
