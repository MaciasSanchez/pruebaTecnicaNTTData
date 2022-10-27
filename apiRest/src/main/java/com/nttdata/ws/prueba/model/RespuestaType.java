/**
 * 
 */
package com.nttdata.ws.prueba.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * @author Angelica
 * 
 * Modelo Canonico de Respuesta
 * 
 */

public class RespuestaType {
	
	@JsonProperty("codigoRespuesta")
	private String codigoRespuesta;

	@JsonProperty("descripcion")
	private String descripcion;
	
	
	public RespuestaType codigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
		return this;
	}

	/**
	 * Get codigoRespuesta
	 * 
	 * @return codigoRespuesta
	 */
	@Schema(defaultValue = "")

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public RespuestaType descripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	/**
	 * Get descripcion
	 * 
	 * @return descripcion
	 */
	@Schema(defaultValue = "")

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RespuestaType respuestaType = (RespuestaType) o;
		return Objects.equals(this.codigoRespuesta, respuestaType.codigoRespuesta)
				&& Objects.equals(this.descripcion, respuestaType.descripcion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoRespuesta, descripcion);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class RespuestaType {\n");

		sb.append("    codigoRespuesta: ").append(toIndentedString(codigoRespuesta)).append("\n");
		sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
