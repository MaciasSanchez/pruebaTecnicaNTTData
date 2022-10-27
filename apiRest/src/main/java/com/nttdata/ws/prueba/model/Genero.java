/**
 * 
 */
package com.nttdata.ws.prueba.model;

/**
 * @author Angelica
 *
 */
public enum Genero {
	
	MASCULINO("MASCULINO"),
	FEMENINO("FEMENINO"),
	NEUTRO("NEUTRO");
	
	private String value;
	
	Genero(String value){
		this.value = value;
	}
	 public String getValue() {
	      return value;
	    }

	@Override
	public String toString() {
	      return String.valueOf(value);
	    }

	public static Genero fromValue(String value) {
	      for (Genero b : Genero.values()) {
	        if (b.value.equals(value)) {
	          return b;
	        }
	      }
	      throw new IllegalArgumentException("Unexpected value '" + value + "'");
	    }

}
