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
public class EstadoCuentaType {
	
	
	private String reporte = MensajesDelServicio.NOMBRE_REPORTE;
	private String nombreCliente;
	
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
	private Date fechaInicioCorte;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
	private Date fechaFinCorte;
	private List<MovimientosType> detalleMovimientos;
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private double montoTotalDepositos;
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private double montoTotalRetiros;
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private double saldoDisponibleCorte;
	
}
