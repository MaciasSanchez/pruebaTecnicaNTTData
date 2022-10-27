/**
 * 
 */
package com.nttdata.ws.prueba.model;

/**
 * @author Angelica
 *
 */
public enum TiposDeMovimiento {
	
	DEPOSITO("DEPOSITO"),
	RETIRO("RETIRO");
	
	private String value;
	
	TiposDeMovimiento(String value){
		this.value = value;
	}
	 public String getValue() {
	      return value;
	    }

	@Override
	public String toString() {
	      return String.valueOf(value);
	    }

	public static TiposDeMovimiento fromValue(String value) {
	      for (TiposDeMovimiento b : TiposDeMovimiento.values()) {
	        if (b.value.equals(value)) {
	          return b;
	        }
	      }
	      throw new IllegalArgumentException("Unexpected value '" + value + "'");
	    }

}
