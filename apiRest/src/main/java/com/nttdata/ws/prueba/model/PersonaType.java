/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.UUID;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Angelica
 * 
 *         Modelo Canonico de Persona
 *
 */
@Getter
@Setter
public class PersonaType {

	private UUID id;
	private String nombreCompleto;
	private Genero genero;
	@JsonProperty(defaultValue = "18")
	@Min(value = 18)
	private int edad;
	private String identificacion;
	private String direccion;
	private String telefono;

}
