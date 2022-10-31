/**
 * 
 */
package com.nttdata.ws.prueba.controller.impl;

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
import com.nttdata.ws.prueba.model.CuentaType;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.contract.ICuentaRepository;
import com.nttdata.ws.prueba.repository.model.Cliente;
import com.nttdata.ws.prueba.repository.model.Cuenta;

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
		
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);		

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
		
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);
		
		List<Cuenta> cuentasCliente = cuentaRepo.consultarCuentaPorNumeroIdentificacion("098254785");
		Cuenta detalleCuenta = cuentasCliente.get(0);
		detalleCuenta.setEstado(false);
		
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/cuentas").content(objectMapper.writeValueAsString(detalleCuenta))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#eliminarCuenta(java.lang.String)}.
	 * @throws IOException 
	 * @throws DatabindException 
	 * @throws StreamReadException 
	 */
	@Test
	void testEliminarCuenta() throws StreamReadException, DatabindException, IOException {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#consultarCuentaPorNumero(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testConsultarCuentaPorNumero() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);
		
		String numCuenta = "496825";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/cuentas/numero/{numCuenta}", numCuenta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	void testConsultarCuentaPorNumeroNotFound() throws Exception {
		
		String numCuenta = "496825";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/cuentas/numero/{numCuenta}", numCuenta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#consultarCuentaPorNumeroIdentificacion(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testConsultarCuentaPorNumeroIdentificacion() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);
		String numCuenta = "097548965";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/cuentas/cliente/{numIdentificacion}", numCuenta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	
	/**
	 * @throws Exception
	 */
	@Test
	void testConsultarCuentaPorNumeroIdentificacionNoContent() throws Exception {
		
		String numIdentificacion = "097548965";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/cuentas/cliente/{numIdentificacion}", numIdentificacion)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNoContent());
		
		
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.CuentaController#consultarCuentas()}.
	 * @throws Exception 
	 * @throws DatabindException 
	 * @throws StreamReadException 
	 */
	@Test
	void testConsultarCuentas() throws StreamReadException, DatabindException, Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/cuentas").accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testConsultarCuentasNoContent() throws StreamReadException, DatabindException, Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/cuentas").accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
