/**
 * 
 */
package com.nttdata.ws.prueba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Angelica
 *
 */
@JsonIgnoreProperties({"clienteId", "id"})
public class CrearUsuarioRequest extends ClienteType{

}
