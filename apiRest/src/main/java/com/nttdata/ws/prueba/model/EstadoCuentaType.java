/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.nttdata.ws.prueba.constants.MensajesDelServicio;

/**
 * @author Angelica
 *
 */
public class EstadoCuentaType {
	
	
	private String reporte = MensajesDelServicio.NOMBRE_REPORTE;
	private String nombreCliente;
	
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
	private Date fechaInicioCorte;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
	private Date fechaFinCorte;
	private List<MovimientosClienteType> detalleMovimientos;
	private double montoTotalDepositos;
	private double montoTotalRetiros;
	private double saldoDisponibleCorte;
	/**
	 * @return the reporte
	 */
	public String getReporte() {
		return reporte;
	}
	/**
	 * @param reporte the reporte to set
	 */
	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	/**
	 * @return the fechaInicioCorte
	 */
	public Date getFechaInicioCorte() {
		return fechaInicioCorte;
	}
	/**
	 * @param fechaInicioCorte the fechaInicioCorte to set
	 */
	public void setFechaInicioCorte(Date fechaInicioCorte) {
		this.fechaInicioCorte = fechaInicioCorte;
	}
	/**
	 * @return the fechaFinCorte
	 */
	public Date getFechaFinCorte() {
		return fechaFinCorte;
	}
	/**
	 * @param fechaFinCorte the fechaFinCorte to set
	 */
	public void setFechaFinCorte(Date fechaFinCorte) {
		this.fechaFinCorte = fechaFinCorte;
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
