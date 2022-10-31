/**
 * 
 */
package com.nttdata.ws.prueba.controller.impl;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.contract.ICuentaRepository;
import com.nttdata.ws.prueba.repository.contract.IMovimientosRepository;
import com.nttdata.ws.prueba.repository.model.Cliente;
import com.nttdata.ws.prueba.repository.model.Cuenta;
import com.nttdata.ws.prueba.repository.model.Movimientos;

/**
 * @author Angelica
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class MovimientoControllerTest {
	
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
	
	@Autowired
	private IMovimientosRepository movtsRepo;

	private File requestCuentaInput;
	
	private File requestClienteInput;
	
	private File requestMovimientosInput;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.requestClienteInput = new File("src/test/resourses/Cliente.json");
		this.requestCuentaInput = new File("src/test/resourses/Cuentas.json");
		this.requestMovimientosInput = new File("src/test/resourses/Movimientos.json");
	}
	

	@AfterEach
	void tearDown() throws Exception {
		this.mockMvc = null;
		clienteRepo.deleteAll();
		cuentaRepo.deleteAll();
		movtsRepo.deleteAll();
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#crearMovimiento(com.nttdata.ws.prueba.model.MovimientosType)}.
	 * @throws Exception 
	 */
	@Test
	void testCrearMovimiento() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);
		
		MovimientosType dto = objectMapper.readValue(requestMovimientosInput, new TypeReference<List<MovimientosType>>() {}).get(0);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/movimientos").content(objectMapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#actualizarMovimiento(com.nttdata.ws.prueba.model.MovimientosClienteType)}.
	 * @throws Exception 
	 */
	@Test
	void testActualizarMovimiento() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);
		String numeroCta = "478758";
		Date fechaInicio;
		Date fechaFinal;

		fechaInicio = dateFormat.parse("2022-10-27");
		fechaFinal  = dateFormat.parse("2022-10-27");
		    
		
		List<Movimientos> mvtsCliente = movtsRepo.consultarMovimientosPorNumeroDeCta(numeroCta, fechaInicio, fechaFinal);
		Movimientos mvtCuenta = mvtsCliente.get(0);
		mvtCuenta.setEstado(false);
		
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/cuentas").content(objectMapper.writeValueAsString(mvtCuenta))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#eliminarMovimiento(java.lang.String)}.
	 */
	@Test
	void testEliminarMovimiento() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarMovimientosPorFechas(java.util.Date, java.util.Date)}.
	 */
	@Test
	void testConsultarMovimientosPorFechas() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarMovimientosPorCliente(java.lang.String, java.util.Date, java.util.Date)}.
	 */
	@Test
	void testConsultarMovimientosPorCliente() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarMovimientosPorNumeroDeCta(java.lang.String, java.util.Date, java.util.Date)}.
	 */
	@Test
	void testConsultarMovimientosPorNumeroDeCta() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarMovimientosPorCuentaTipoMovimiento(java.lang.String, com.nttdata.ws.prueba.model.TiposDeMovimiento)}.
	 */
	@Test
	void testConsultarMovimientosPorCuentaTipoMovimiento() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarEstadoCuenta(java.lang.String, java.util.Date, java.util.Date)}.
	 */
	@Test
	void testConsultarEstadoCuenta() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarEstadoCuentaDetallado(java.lang.String, java.util.Date, java.util.Date)}.
	 */
	@Test
	void testConsultarEstadoCuentaDetallado() {
		fail("Not yet implemented");
	}

}
