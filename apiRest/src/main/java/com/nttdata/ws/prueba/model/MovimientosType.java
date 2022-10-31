/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelica
 * 
 *         Modelo Canonico de Movimientos • Un movimiento tiene: Fecha, tipo
 *         movimiento, valor, saldo
 * 
 */

@Getter
@Setter
public class MovimientosType {

	private UUID id;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date fecha;
	private String cliente;
	private String identificacionCliente;
	@NotNull(message = "el campo <numero de cuenta> no puede estar vacío")
	private String numeroDeCuenta;
	private TiposDeCuenta tipo;
	private double saldoInicial;
	private boolean estado;
	private TiposDeMovimiento tipoMovimiento;
	private double movimiento;
	private double saldoDisponible;

}
