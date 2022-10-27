package com.nttdata.ws.prueba.utils.exceptions;


/**
 * @author Angelica
 *
 */
public enum TipoError {
	
	NO_ENCONTRADO("404"), SOLICITUD_INVALIDA("400"), SERVICIO_INACCESIBLE("502"),
	FUENTE_DE_DATOS("503"), ERROR_INESPERADO("500"), LOGICA_NEGOCIO("0004");
	
	private String value;
	
	private TipoError(String value) {
		this.value = value;
	}

	/**
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}
}