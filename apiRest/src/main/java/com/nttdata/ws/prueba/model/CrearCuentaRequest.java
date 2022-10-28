/**
 * 
 */
package com.nttdata.ws.prueba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Angelica
 *
 */

@JsonIgnoreProperties({"cliente", "id"})
public class CrearCuentaRequest extends CuentaType {

}
