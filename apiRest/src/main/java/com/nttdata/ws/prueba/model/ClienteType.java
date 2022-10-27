/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Angelica
 *
 * Modelo Canonico de Persona
 *
 */
public class ClienteType extends PersonaType{
	
	@JsonIgnore
	private UUID clienteId;
	private String contrasenia;
	private Boolean estado;
	
	public ClienteType() {
		super();
	}
	

	public ClienteType(UUID clienteId, String contrasenia, Boolean estado) {
		super();
		this.clienteId = clienteId;
		this.contrasenia = contrasenia;
		this.estado = estado;
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
	public Boolean getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
}
