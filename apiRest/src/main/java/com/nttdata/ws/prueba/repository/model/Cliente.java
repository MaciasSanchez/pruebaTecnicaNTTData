package com.nttdata.ws.prueba.repository.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Angelica
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dytype")
@DiscriminatorOptions(force = true)
@DiscriminatorValue("Cliente")
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "cl_cliente")
public class Cliente extends Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name="cliente_id", unique = true, nullable = false)
	private UUID clienteId;
	
	@Column(name = "contrasenia")
	private String contrasenia;
	
	@Column(name = "estado")
	private boolean estado;
	
	
	public Cliente(UUID clienteId, String contrasenia, boolean estado) {
		super();
		this.clienteId = clienteId;
		this.contrasenia = contrasenia;
		this.estado = estado;
	}

	public Cliente() {
		super();
	}

	/**
	 * @return the clienteId
	 */
	public UUID getClienteId() {
		return clienteId;
	}

	/**
	 * @param clienteId the clienteId to set
	 */
	public void setClienteId(UUID clienteId) {
		this.clienteId = clienteId;
	}

	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
