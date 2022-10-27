package com.nttdata.ws.prueba;

import java.io.File;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.ws.prueba.model.ClienteType;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.contract.IPersonaRepository;
import com.nttdata.ws.prueba.repository.model.Cliente;
import com.nttdata.ws.prueba.utils.ClienteConvert;



@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllertest {
	
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private IClienteRepository clienteRepo;
	@Autowired
	private IPersonaRepository personaRepo;

	private File clienteJson;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.clienteJson = new File("src/test/resources/Cliente.json");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		clienteRepo.deleteAll();
		personaRepo.deleteAll();
		this.clienteJson = null;
		this.mockMvc = null;
	}

	@Test
	void testActualizarCliente() throws JsonProcessingException, Exception {
		Cliente model = objectMapper.readValue(clienteJson, new TypeReference<List<Cliente>>() {
		}).get(1);
		ClienteType dto = ClienteConvert.modelToType(clienteRepo.save(model));
		dto.setIdentificacion("099999999999");

		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/clientes").content(objectMapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testActualizarClienteNotfound() throws Exception {
		Cliente model = objectMapper.readValue(clienteJson, new TypeReference<List<Cliente>>() {}).get(1);
		ClienteType dto = ClienteConvert.modelToType(clienteRepo.save(model));
		dto.setIdentificacion("099999999999");
		dto.setId(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				.put("/clientes")
				.content(objectMapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	      		.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testConsultarClientePorIdentificacion() throws Exception {
		List<Cliente> model = objectMapper.readValue(clienteJson, new TypeReference<List<Cliente>>() {
		});
		clienteRepo.saveAll(model);
		String identificacion = "99999999998";

		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/clientes/{numIdentificacion}", identificacion)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testConsultarClientePorIdentificacionNotFound() throws Exception {
		List<Cliente> model = objectMapper.readValue(clienteJson, new TypeReference<List<Cliente>>() {
		});
		clienteRepo.saveAll(model);
		
		String identificacion = "1234";
		
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/clientes/{numIdentificacion}",identificacion)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testCrearCliente() throws JsonProcessingException, Exception {
		ClienteType dto = objectMapper.readValue(clienteJson, new TypeReference<List<ClienteType>>() {
		}).get(0);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/clientes").content(objectMapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testConsultarClientes() throws JsonProcessingException, Exception {
		List<Cliente> entity = objectMapper.readValue(clienteJson, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(entity);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/clientes","")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
