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
public class EstadoCuentaDetalladoType {
	
	
	private String reporte = MensajesDelServicio.NOMBRE_REPORTE;
	private String nombreCliente;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
	private Date fechaInicioCorte;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
	private Date fechaFinCorte;
	private List<DetalleMovimientosCuentas> listadoCuentas;
	private double totalDepositosRealizados;
	private double totalRetirosRealizados;
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
	 * @return the listadoCuentas
	 */
	public List<DetalleMovimientosCuentas> getListadoCuentas() {
		return listadoCuentas;
	}
	/**
	 * @param listadoCuentas the listadoCuentas to set
	 */
	public void setListadoCuentas(List<DetalleMovimientosCuentas> listadoCuentas) {
		this.listadoCuentas = listadoCuentas;
	}
	/**
	 * @return the totalDepositosRealizados
	 */
	public double getTotalDepositosRealizados() {
		return totalDepositosRealizados;
	}
	/**
	 * @param totalDepositosRealizados the totalDepositosRealizados to set
	 */
	public void setTotalDepositosRealizados(double totalDepositosRealizados) {
		this.totalDepositosRealizados = totalDepositosRealizados;
	}
	/**
	 * @return the totalRetirosRealizados
	 */
	public double getTotalRetirosRealizados() {
		return totalRetirosRealizados;
	}
	/**
	 * @param totalRetirosRealizados the totalRetirosRealizados to set
	 */
	public void setTotalRetirosRealizados(double totalRetirosRealizados) {
		this.totalRetirosRealizados = totalRetirosRealizados;
	}
	
	
	
}
