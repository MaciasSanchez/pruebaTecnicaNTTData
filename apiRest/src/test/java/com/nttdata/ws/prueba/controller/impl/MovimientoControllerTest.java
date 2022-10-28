/**
 * 
 */
package com.nttdata.ws.prueba.controller.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.contract.ICuentaRepository;
import com.nttdata.ws.prueba.repository.contract.IMovimientosRepository;

/**
 * @author Angelica
 *
 */
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
		this.requestMovimientosInput = new File("src/test/resourses/Movientos.json");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.mockMvc = null;
		cuentaRepo.deleteAll();
		clienteRepo.deleteAll();
		movtsRepo.deleteAll();
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#crearMovimiento(com.nttdata.ws.prueba.model.MovimientosType)}.
	 */
	@Test
	void testCrearMovimiento() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#actualizarMovimiento(com.nttdata.ws.prueba.model.MovimientosClienteType)}.
	 */
	@Test
	void testActualizarMovimiento() {
		fail("Not yet implemented");
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
