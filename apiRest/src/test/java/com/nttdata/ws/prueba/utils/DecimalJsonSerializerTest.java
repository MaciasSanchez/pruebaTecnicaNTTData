/**
 * 
 */
package com.nttdata.ws.prueba.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.ws.prueba.model.EstadoCuentaType;

/**
 * @author Angelica
 *
 */
@SpringBootTest
class DecimalJsonSerializerTest {

	/**
	 * Test method for {@link com.nttdata.ws.prueba.utils.DecimalJsonSerializer#serialize(java.lang.Double, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)}.
	 */
	@Test
	void testSerializeDoubleJsonGeneratorSerializerProvider() throws JsonProcessingException {
		EstadoCuentaType estadoCta = new EstadoCuentaType();
		estadoCta.setSaldoDisponibleCorte(Double.valueOf("20.3"));

	     ObjectMapper mapper = new ObjectMapper();
	     assertEquals("{\"reporte\":\"ESTADO DE CUENTA\",\"nombreCliente\":null,\"fechaInicioCorte\":null,\"fechaFinCorte\":null,\"detalleMovimientos\":null,\"montoTotalDepositos\":\"0,00\",\"montoTotalRetiros\":\"0,00\",\"saldoDisponibleCorte\":\"20,30\"}", mapper.writeValueAsString(estadoCta));
	}

}
