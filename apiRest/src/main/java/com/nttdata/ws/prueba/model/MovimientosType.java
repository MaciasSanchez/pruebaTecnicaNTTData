/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	private UUID id;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date fecha;
	@JsonIgnore
	private String cliente;
	@JsonIgnore
	private String identificacionCliente;
	@NotNull(message = "numero de cuenta no puede estar vacío")
	private String numeroDeCuenta;
	private TiposDeCuenta tipo;
	@JsonIgnore
	private double saldoInicial;
	private boolean estado;
	private TiposDeMovimiento tipoMovimiento;
	private double movimiento;
	@JsonIgnore
	private double saldoDisponible;

}
