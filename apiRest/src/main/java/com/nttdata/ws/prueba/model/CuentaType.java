/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * @author Angelica
 * 
 * Modelo Canonico de Cuenta
 *
 */
public class CuentaType {
	
	
	private UUID id;
	@Pattern(regexp = "[0-9]*")
	@NotNull(message = "numero de identificación no puede estar vacío")
	private String numeroDeCuenta;
	@NotNull(message = "numero de identificación no puede estar vacío")
	private String identificacion;
	private String cliente;
	private TiposDeCuenta tipoCuenta;
	@DecimalMin(value = "0.00")
	private double saldoInicial;
	private Boolean estado;
	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	/**
	 * @return the numeroDeCuenta
	 */
	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}
	/**
	 * @param numeroDeCuenta the numeroDeCuenta to set
	 */
	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}
	/**
	 * @return the tipoCuenta
	 */
	public TiposDeCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(TiposDeCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	/**
	 * @return the saldoInicial
	 */
	public double getSaldoInicial() {
		return saldoInicial;
	}
	/**
	 * @param saldoInicial the saldoInicial to set
	 */
	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	/**
	 * @return the estado
	 */
	public Boolean getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}
	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	
}
