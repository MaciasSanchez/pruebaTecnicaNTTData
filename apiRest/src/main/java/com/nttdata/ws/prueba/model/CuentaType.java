/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelica
 * 
 *         Modelo Canonico de Cuenta
 *
 */

@Getter
@Setter
public class CuentaType {

	private UUID id;
	@Pattern(regexp = "[0-9]*")
	@NotNull(message = "el campo numero de cuenta no puede estar vacío")
	private String numeroDeCuenta;
	@NotNull(message = "numero de identificación no puede estar vacío")
	private String identificacion;
	private String cliente;
	private TiposDeCuenta tipoCuenta;
	@DecimalMin(value = "0.00")
	private double saldoInicial;
	private Boolean estado;

}
