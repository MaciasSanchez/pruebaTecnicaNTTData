/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelica
 * 
 *         Modelo Canonico de Movimientos por Cliente
 *
 */

@Getter
@Setter
public class MovimientosClienteType {

	private UUID id;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date fecha;
	private String cliente;
	private String identificacionCliente;
	private String numeroDeCuenta;
	private TiposDeCuenta tipo;
	private double saldoInicial;
	private boolean estado;
	private TiposDeMovimiento tipoMovimiento;
	private double movimiento;
	private double saldoDisponible;

}
