/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelica
 *
 * Modelo Canonico de Persona
 *
 */


@Getter
@Setter
public class ClienteType extends PersonaType{
	
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

	
	
}
