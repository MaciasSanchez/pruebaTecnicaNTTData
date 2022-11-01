/**
 * 
 */
package com.nttdata.ws.prueba.constants;

/**
 * @author Angelica
 *	en esta clase se almancenan todas las constantes utilizadas en las api rest
 */
public class MensajesDelServicio {

	private MensajesDelServicio() {
	}

	public static final String ERROR_INTERNO = "ALGO MALO OCURIO, LO SOLUCIONAREMOS PRONTO";
	public static final String RECURSO_NO_ENCONTRADO = "Estimado usuario, el recurso con ID [[%s]] no existe";
	
	public static final String FECHA_ERROR = "FORMATO DE FECHA INCORRECTO";

	public static final String RECURSO_CREADO = "REGISTRO CREADO";
	public static final String RECURSO_NO_CREADO = "EL REGISTRO NO PUDO SER CREADO";
	public static final String RECURSO_ACTUALIZADO = "REGISTRO ACTUALIZADO";
	public static final String RECURSO_ELIMINADO = "REGISTRO ELIMIDADO";

	public static final String PROCESO_EXITOSO = "OK";

	// PARAMETROS
	public static final String NOMBRE_REPORTE = "ESTADO DE CUENTA";
	// MENSAJES DE RESPUESTA
	public static final String MENSAJE_SALDO_CERO = "Saldo no disponible";
	public static final String IDENTIFICACION_REGISTRADA = "El num. identificación: [[%s]] ya se encuentra registrado";
	public static final String NRO_CUENTA_REGISTRADA = "El número de cuenta: [[%s]] ya se encuentra registrado";
	public static final String NRO_CUENTA_NO_REGISTRADA ="El número de cuenta: [[%s]] no se encuentra registrado, por favor verifique el numero.";
	public static final String IDENTIFICACION_NO_REGISTRADA = "El cliente con número de identificación: [[%s]] no se encuentra registrado, por favor verifique el numero.";
	public static final String MOVIMIENTO_NO_REGISTRADO = "El movimiento: [[%s]] no se encuentra registrado";
	public static final String NRO_CUENTA_CTE_NO_REGISTRADO = "El cliente con número de identificación: [[%s]] no esta asociado a nro. de cuenta [[%s]]";
	public static final String CREACION_COMPLETADA = "Se ha creado el recurso con el ID [[%s]]";
	public static final String BUSQUEDA_SIN_EXITO = "Estimado usuario, el recurso con ID <[%s]>  no existe";
	public static final String ELIMINACION_EXISTOSA = "Su registro fue eliminado con éxito";
	public static final String ACTUALIZACION_CORRECTA = "Información actualizada correctamente";
	public static final String CEDULA_EXISTENTE = "Estimado cliente, el número de identificación [[%s]] ya se encuentra registrado.";
	public static final String CUENTA_EXISTENTE = "Estimado cliente, el número de cuenta [[%s]] ya se encuentra registrado.";	
	
}
