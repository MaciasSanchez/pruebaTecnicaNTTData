package com.nttdata.ws.prueba.utils.exceptions;


/**
 * @author Angelica
 *
 */

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TipoError error;
	
	public BusinessException(String mensaje, TipoError error) {
		super(mensaje);
		this.error = error;
	}
	
	public BusinessException(String mensaje, TipoError error, Throwable causa) {
		super(mensaje,causa);
		this.error = error;
	}
	
	/**
	 * Por defecto esta definido como tipo de error: ERROR_INESPERADO
	 * @param mensaje
	 */
	public BusinessException(String mensaje) {
		super(mensaje);
		this.error = TipoError.ERROR_INESPERADO;
	}
	
	/**
	 * @return the error
	 */
	public TipoError getError() {
		return error;
	}

}
