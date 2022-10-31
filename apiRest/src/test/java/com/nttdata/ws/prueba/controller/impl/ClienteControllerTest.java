/**
 * 
 */
package com.nttdata.ws.prueba.controller.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.ws.prueba.model.ClienteType;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.model.Cliente;
import com.nttdata.ws.prueba.utils.ClienteConvert;


/**
 * @author Angelica
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private IClienteRepository clienteRepo;

	private File requestClienteInput;

	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.requestClienteInput = new File("src/test/resourses/Cliente.json");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.mockMvc = null;
		clienteRepo.deleteAll();
	}

	/**
	 * Test method for
	 * {@link com.nttdata.ws.prueba.controller.impl.ClienteController#crearCliente(com.nttdata.ws.prueba.model.CrearUsuarioRequest)}.
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 */

	@Test
	void testCrearCliente() throws JsonProcessingException, Exception {
		ClienteType dto = objectMapper.readValue(requestClienteInput, new TypeReference<List<ClienteType>>() {
		}).get(0);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/clientes").content(objectMapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());

	}

	/**
	 * Test method for
	 * {@link com.nttdata.ws.prueba.controller.impl.ClienteController#actualizarCliente(com.nttdata.ws.prueba.model.ClienteType)}.
	 * 
	 * @throws Exception
	 * @throws JsonProcessingException
	 */
	@Test
	void testActualizarCliente() throws JsonProcessingException, Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);
		Cliente cliente = clienteRepo.consultarClientePorIdentificacion("098254785");
		cliente.setDireccion("");
		
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/clientes").content(objectMapper.writeValueAsString(ClienteConvert.modelToType(cliente)))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test method for
	 * {@link com.nttdata.ws.prueba.controller.impl.ClienteController#eliminarCliente(java.lang.String)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */

	 @Test 
	 void testEliminarCliente() throws JsonProcessingException, Exception { 
		 List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
			clienteRepo.saveAll(clientes);
		 ClienteType cliente = ClienteConvert.modelToType(clienteRepo.consultarClientePorIdentificacion("098254785"));
		 UUID id =  cliente.getId();
		 this.mockMvc
			.perform(MockMvcRequestBuilders.delete("/clientes/{id}", id.toString())
					.accept(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
		 
	}
	 

	/**
	 * Test method for
	 * {@link com.nttdata.ws.prueba.controller.impl.ClienteController#consultarClientePorIdentificacion(java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testConsultarClientePorIdentificacion() throws Exception, Throwable {
		
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);
		
		String numIdentificacion = "098254785";
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/clientes/{numIdentificacion}", numIdentificacion)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	
	/**
	 * Test method for
	 * {@link com.nttdata.ws.prueba.controller.impl.ClienteController#consultarClientePorIdentificacion(java.lang.String)}.
	 * @throws Exception
	 * @throws Throwable
	 */
	@Test
	void testConsultarClientePorIdentificacionNotFound() throws Exception, Throwable {

		String numIdentificacion = "098254785";
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/clientes/{numIdentificacion}", numIdentificacion)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Test method for
	 * {@link com.nttdata.ws.prueba.controller.impl.ClienteController#consultarClientes()}.
	 * 
	 * @throws Throwable
	 */
	@Test
	void testConsultarClientes() throws Exception, Throwable {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clientes").accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	/**
	 * Test method for
	 * {@link com.nttdata.ws.prueba.controller.impl.ClienteController#consultarClientes()}.
	 * 
	 * @throws Throwable
	 */
	@Test
	void testConsultarClientesNoContent() throws Exception, Throwable {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clientes").accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
