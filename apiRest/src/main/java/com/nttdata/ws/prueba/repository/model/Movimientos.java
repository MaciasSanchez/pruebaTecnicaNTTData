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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.nttdata.ws.prueba.model.TiposDeCuenta;
import com.nttdata.ws.prueba.model.TiposDeMovimiento;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelica
 *
 */
@Entity
@Getter
@Setter
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
	private String identificacionCliente;
	
	@Column(name = "numero_de_cuenta")
	private String numeroDeCuenta;
	
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
