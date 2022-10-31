/**
 * 
 */
package com.nttdata.ws.prueba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Angelica
 *
 */
@JsonIgnoreProperties({"id", "cliente", "identificacionCliente", "saldoInicial", "saldoDisponible"})
public class CrearMovimientoRequest extends MovimientosType{

}
