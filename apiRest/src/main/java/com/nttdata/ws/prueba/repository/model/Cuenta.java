/**
 * 
 */
package com.nttdata.ws.prueba.repository.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nttdata.ws.prueba.model.TiposDeCuenta;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelica
 *
 */
@Getter
@Setter
@Entity
@Table(name = "t_cuentas")
public class Cuenta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_cuenta", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "numero_cuenta", unique = true, nullable = false)
	private String numeroDeCuenta;
	
	@Column(name = "num_identificacion_cliente", nullable = false)
	private String identificacion;
	
	@Column(name = "nombre_cliente", nullable = false)
	private String cliente;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_de_cuenta")
	private TiposDeCuenta tipoCuenta;
	
	@Column(name = "saldo_inicial", columnDefinition = "Decimal(10,2)")
	private double saldoInicial;
	
	@Column(name = "estado")
	private Boolean estado;


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}


