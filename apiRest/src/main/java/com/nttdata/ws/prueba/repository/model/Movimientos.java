/**
 * 
 */
package com.nttdata.ws.prueba.repository.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.nttdata.ws.prueba.model.TiposDeCuenta;
import com.nttdata.ws.prueba.model.TiposDeMovimiento;

/**
 * @author Angelica
 *
 */
@Entity
@Table(name = "t_movimientos")
public class Movimientos implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_movimiento", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "fecha")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date fecha;
	
	@Column(name = "cliente")
	private String cliente;
	
	@Column(name = "num_identificacion")
	private String identificacion;
	
	@Column(name = "numero_de_cuenta")
	private String numeroCuenta;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_cuenta")
	private TiposDeCuenta tipo;

	
	@Column(name = "saldo_inicial", precision = 19, scale = 2)
	private double saldoInicial;
	
	@Column(name = "estado")
	private Boolean estado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_mov")
	private TiposDeMovimiento tipoMovimiento;
	
	@Column(name = "movimiento", precision = 19, scale = 2)
	private double movimiento;
	
	@Column(name = "saldo_disponible", precision = 19, scale = 2)
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
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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
	 * @return the saldoDisponible
	 */
	public double getSaldoDisponible() {
		return saldoDisponible;
	}

	/**
	 * @param saldoDisponible the saldoDisponible to set
	 */
	public void setSaldoDisponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the tipoMovimiento
	 */
	public TiposDeMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param tipoMovimiento the tipoMovimiento to set
	 */
	public void setTipoMovimiento(TiposDeMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
	
	
	

}
