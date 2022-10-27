/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.List;

/**
 * @author Angelica
 *
 */
public class DetalleMovimientosCuentas {
	
	private String numeroDeCuenta;
	private List<MovimientosClienteType> detalleMovimientos;
	private double montoTotalDepositos;
	private double montoTotalRetiros;
	private double saldoDisponibleCorte;
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
	 * @return the detalleMovimientos
	 */
	public List<MovimientosClienteType> getDetalleMovimientos() {
		return detalleMovimientos;
	}
	/**
	 * @param detalleMovimientos the detalleMovimientos to set
	 */
	public void setDetalleMovimientos(List<MovimientosClienteType> detalleMovimientos) {
		this.detalleMovimientos = detalleMovimientos;
	}
	/**
	 * @return the montoTotalDepositos
	 */
	public double getMontoTotalDepositos() {
		return montoTotalDepositos;
	}
	/**
	 * @param montoTotalDepositos the montoTotalDepositos to set
	 */
	public void setMontoTotalDepositos(double montoTotalDepositos) {
		this.montoTotalDepositos = montoTotalDepositos;
	}
	/**
	 * @return the montoTotalRetiros
	 */
	public double getMontoTotalRetiros() {
		return montoTotalRetiros;
	}
	/**
	 * @param montoTotalRetiros the montoTotalRetiros to set
	 */
	public void setMontoTotalRetiros(double montoTotalRetiros) {
		this.montoTotalRetiros = montoTotalRetiros;
	}
	/**
	 * @return the saldoDisponibleCorte
	 */
	public double getSaldoDisponibleCorte() {
		return saldoDisponibleCorte;
	}
	/**
	 * @param saldoDisponibleCorte the saldoDisponibleCorte to set
	 */
	public void setSaldoDisponibleCorte(double saldoDisponibleCorte) {
		this.saldoDisponibleCorte = saldoDisponibleCorte;
	}
	
	
	
	

}
