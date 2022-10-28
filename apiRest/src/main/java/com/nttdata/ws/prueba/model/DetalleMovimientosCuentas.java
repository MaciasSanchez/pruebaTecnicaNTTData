/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.List;

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
	private List<MovimientosClienteType> detalleMovimientos;
	private double montoTotalDepositos;
	private double montoTotalRetiros;
	private double saldoDisponibleCorte;

}
