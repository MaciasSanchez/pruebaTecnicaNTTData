/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Angelica
 * 
 * Modelo Canonico de Movimientos por Cliente
 *
 */
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
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * @return the tipo
	 */
	public TiposDeCuenta getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TiposDeCuenta tipo) {
		this.tipo = tipo;
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
	public boolean isEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	/**
	 * @return the movimiento
	 */
	public double getMovimiento() {
		return movimiento;
	}
	/**
	 * @param movimiento the movimiento to set
	 */
	public void setMovimiento(double movimiento) {
		this.movimiento = movimiento;
	}
	
	/**
	 * @return the saldo_disponible
	 */
	public double getSaldoDisponible() {
		return saldoDisponible;
	}
	/**
	 * @param saldo_disponible the saldo_disponible to set
	 */
	public void setSaldoDisponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	/**
	 * @return the identificacionCliente
	 */
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}
	/**
	 * @param identificacionCliente the identificacionCliente to set
	 */
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}
	public TiposDeMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(TiposDeMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
	
	
	
}
