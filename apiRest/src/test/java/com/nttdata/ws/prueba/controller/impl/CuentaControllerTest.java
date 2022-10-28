/**
 * 
 */
package com.nttdata.ws.prueba.controller.impl;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.ws.prueba.model.ClienteType;
import com.nttdata.ws.prueba.model.CuentaType;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.contract.ICuentaRepository;
import com.nttdata.ws.prueba.repository.model.Cliente;
import com.nttdata.ws.prueba.repository.model.Cuenta;
import com.nttdata.ws.prueba.utils.ClienteConvert;
import com.nttdata.ws.prueba.utils.CuentaConvert;

/**
 * @author Angelica
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class CuentaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private IClienteRepository clienteRepo;

	@Autowired
	private ICuentaRepository cuentaRepo;

	private File requestCuentaInput;
	
	private File requestClienteInput;

	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.requestClienteInput = new File("src/test/resourses/Cliente.json");
		this.requestCuentaInput = new File("src/test/resourses/Cuentas.json");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.mockMvc = null;
		cuentaRepo.deleteAll();
		clienteRepo.deleteAll();
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#crearCuenta(com.nttdata.ws.prueba.model.CrearCuentaRequest)}.
	 * @throws Exception 
	 */
	@Test
	void testCrearCuenta() throws Exception {
		cuentaRepo.deleteAll();
		clienteRepo.deleteAll();
		
		ClienteType cliente = objectMapper.readValue(requestClienteInput, new TypeReference<List<ClienteType>>() {
		}).get(0);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/clientes").content(objectMapper.writeValueAsString(cliente))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
		

		CuentaType dto = objectMapper.readValue(requestCuentaInput, new TypeReference<List<CuentaType>>() {}).get(0);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/cuentas").content(objectMapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());	
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#actualizarCuenta(com.nttdata.ws.prueba.model.CuentaType)}.
	 */
	@Test
	void testActualizarCuenta() throws Exception {
		cuentaRepo.deleteAll();
		clienteRepo.deleteAll();
		
		ClienteType cliente = objectMapper.readValue(requestClienteInput, new TypeReference<List<ClienteType>>() {
		}).get(0);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/clientes").content(objectMapper.writeValueAsString(cliente))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
		
		
		Cuenta cuenta = cuentaRepo.consultarCuentaPorNumeroIdentificacion("098254785").get(0);
		
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/clientes").content(objectMapper.writeValueAsString(CuentaConvert.modelToType(cuenta)))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
		
		CuentaType dto = objectMapper.readValue(requestCuentaInput, new TypeReference<List<CuentaType>>() {}).get(0);
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/cuentas").content(objectMapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#eliminarCuenta(java.lang.String)}.
	 */
	@Test
	void testEliminarCuenta() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#consultarCuentaPorNumero(java.lang.String)}.
	 */
	@Test
	void testConsultarCuentaPorNumero() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#consultarCuentaPorNumeroIdentificacion(java.lang.String)}.
	 */
	@Test
	void testConsultarCuentaPorNumeroIdentificacion() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#consultarCuentas()}.
	 */
	@Test
	void testConsultarCuentas() {
		fail("Not yet implemented");
	}

}
