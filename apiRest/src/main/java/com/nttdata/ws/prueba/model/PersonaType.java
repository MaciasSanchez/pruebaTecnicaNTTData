/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Max(value = 150)
	private int edad;
	@Pattern(regexp = "[0-9]{8}[A-Z]", message = "Nro. de identificación no válido")
	@Size(min = 10, max = 12 , message = "Nro. de identificación no válido")
	private String identificacion;
	private String direccion;
	private String telefono;

}
