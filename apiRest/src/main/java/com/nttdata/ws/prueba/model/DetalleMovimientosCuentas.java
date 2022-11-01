/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.ws.prueba.utils.DecimalJsonSerializer;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelica
 *
 */

@Getter
@Setter
public class DetalleMovimientosCuentas {
	
	private String numeroDeCuenta;
	private List<MovimientosType> detalleMovimientos;
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private double montoTotalDepositos;
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private double montoTotalRetiros;
	@JsonSerialize(using = DecimalJsonSerializer.class)
	private double saldoDisponibleCorte;

}
