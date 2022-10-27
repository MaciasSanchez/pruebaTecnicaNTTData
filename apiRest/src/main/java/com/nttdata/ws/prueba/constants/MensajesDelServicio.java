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
	public static final String RECURSO_NO_ENCONTRADO = "EL RECURSO NO FUE ENCONTRADO";
	public static final String INFO_204 = "NO HAY CONTENIDO";
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
	public static final String MENSAJE_CUPO_MAXIMO = "Cupo diario Excedido";
	
	
	public static final String CREACION_COMPLETADA = "Se ha creado el recurso con el ID %s";
	public static final String BUSQUEDA_SIN_EXITO = "Estimado usuario, el recurso con ID %s no existe";
	public static final String ELIMINACION_EXISTOSA = "Su registro fue eliminado con éxito";
	public static final String ACTUALIZACION_CORRECTA = "Información actualizada correctamente";
	public static final String CEDULA_EXISTENTE = "Estimado cliente, el número de identificación %s ya se encuentra registrado.";
	public static final String CUENTA_EXISTENTE = "Estimado cliente, el número de cuenta %s ya se encuentra registrado.";	
	public static final Double VALOR_TOPE = 1000.00;
	public static final String SALDO_CERO = "Saldo no disponible";
	public static final String CUPO_MAXIMO = "Cupo diario Excedido";
}
