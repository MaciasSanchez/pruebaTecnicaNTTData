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
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.model.TiposDeMovimiento;
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
	private IMovimientosRepository mvtsRepo;

	private File requestCuentaInput;
	
	private File requestClienteInput;
	
	private File requestMovimientoInput;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.requestClienteInput = new File("src/test/resourses/Cliente.json");
		this.requestCuentaInput = new File("src/test/resourses/Cuentas.json");
		this.requestMovimientoInput = new File("src/test/resourses/Movimientos.json");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.mockMvc = null;
		cuentaRepo.deleteAll();
		clienteRepo.deleteAll();
		mvtsRepo.deleteAll();
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#crearMovimiento(com.nttdata.ws.prueba.model.CrearMovimientoRequest)}.
	 * @throws Exception 
	 */
	@Test
	void testCrearMovimiento() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	

		MovimientosType dto = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<MovimientosType>>() {}).get(0);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/movimientos").content(objectMapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#actualizarMovimiento(com.nttdata.ws.prueba.model.MovimientosType)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	void testActualizarMovimiento() throws JsonProcessingException, Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		
		String numCta = "478758";
		List<Movimientos> movimientosCta = mvtsRepo.consultarMovimientosPorCta(numCta);
		Movimientos detalleMovimiento = movimientosCta.get(0);
		detalleMovimiento.setEstado(false);
		
		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/movimientos").content(objectMapper.writeValueAsString(detalleMovimiento))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#eliminarMovimiento(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testEliminarMovimiento() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		
		String numCta = "478758";
		List<Movimientos> movimientosCta = mvtsRepo.consultarMovimientosPorCta(numCta);
		Movimientos detalleMovimiento = movimientosCta.get(0);
		 UUID id =  detalleMovimiento.getId();
		 this.mockMvc
			.perform(MockMvcRequestBuilders.delete("/movimientos/{id}", id.toString())
					.accept(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarMovimientosPorFechas(java.util.Date, java.util.Date)}.
	 * @throws Exception 
	 */
	@Test
	void testConsultarMovimientosPorFechas() throws Exception {
		
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		
		String fechaDesde = "2022-10-27";
		String fechaHasta = "2022-10-31";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/{fechaDesde}/{fechaHasta}", fechaDesde,fechaHasta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testConsultarMovimientosPorFechasNoContent() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);
		
		String fechaDesde = "2022-10-27";
		String fechaHasta = "2022-10-31";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/{fechaDesde}/{fechaHasta}", fechaDesde,fechaHasta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarMovimientosPorCliente(java.lang.String, java.util.Date, java.util.Date)}.
	 * @throws Exception 
	 */
	@Test
	void testConsultarMovimientosPorCliente() throws Exception {
		
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		String numIdentificacion = "098254785";
		String fechaDesde = "2022-10-27";
		String fechaHasta = "2022-10-31";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/cliente/{numIdentificacion}/{fechaDesde}/{fechaHasta}", numIdentificacion, fechaDesde, fechaHasta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());		
		
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarMovimientosPorCta(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testConsultarMovimientosPorCta() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		String numCuenta = "478758";

		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/cuenta/{numCuenta}", numCuenta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	
	@Test
	void testConsultarMovimientosPorCtaNotFound() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		String numCuenta = "4787584444444";

		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/cuenta/{numCuenta}", numCuenta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());	
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarMovimientosPorNumeroDeCta(java.lang.String, java.util.Date, java.util.Date)}.
	 * @throws Exception 
	 */
	@Test
	void testConsultarMovimientosPorNumeroDeCta() throws Exception {
		
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		String numIdentificacion = "098254785";
		String fechaDesde = "2022-10-27";
		String fechaHasta = "2022-10-31";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/cliente/{numIdentificacion}/{fechaDesde}/{fechaHasta}", numIdentificacion, fechaDesde, fechaHasta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());	
	}

	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarMovimientosPorCuentaTipoMovimiento(java.lang.String, com.nttdata.ws.prueba.model.TiposDeMovimiento)}.
	 * @throws Exception 
	 */
	@Test
	void testConsultarMovimientosPorCuentaTipoMovimiento() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		String numCuenta = "478758";
		TiposDeMovimiento tipoMovimiento = TiposDeMovimiento.RETIRO;

		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/cuenta/tipo/{numeroCuenta}/{tipoMovimiento}", numCuenta, tipoMovimiento)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());	
	
	}
	
	@Test
	void testConsultarMovimientosPorCuentaTipoMovimientoNotFound() throws Exception {
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		String numCuenta = "478758";
		TiposDeMovimiento tipoMovimiento = TiposDeMovimiento.DEPOSITO;

		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/cuenta/tipo/{numeroCuenta}/{tipoMovimiento}", numCuenta, tipoMovimiento)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNoContent());	
	
	}


	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarEstadoCuenta(java.lang.String, java.util.Date, java.util.Date)}.
	 * @throws Exception 
	 */
	@Test
	void testConsultarEstadoCuenta() throws Exception {
		
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	
		
		List<Cuenta> cuentas = objectMapper.readValue(requestCuentaInput, new TypeReference<List<Cuenta>>() {});
		cuentaRepo.saveAll(cuentas);	
		
		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		String numIdentificacion = "098254785";
		String fechaDesde = "2022-10-27";
		String fechaHasta = "2022-10-31";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/reportes/{identificacion}/{fechaDesde}/{fechaHasta}", numIdentificacion, fechaDesde,fechaHasta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	
	/**
	 * Test method for {@link com.nttdata.ws.prueba.controller.impl.MovimientoController#consultarEstadoCuenta(java.lang.String, java.util.Date, java.util.Date)}.
	 * @throws Exception 
	 */
	@Test
	void testConsultarEstadoCuentaDetallado() throws Exception {
		
		List<Cliente> clientes = objectMapper.readValue(requestClienteInput, new TypeReference<List<Cliente>>() {});
		clienteRepo.saveAll(clientes);	

		List<Movimientos> movimientos = objectMapper.readValue(requestMovimientoInput, new TypeReference<List<Movimientos>>() {});
		mvtsRepo.saveAll(movimientos);
		String numIdentificacion = "098254785";
		String fechaDesde = "2022-10-27";
		String fechaHasta = "2022-10-31";
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/movimientos/reportes/cliente/{identificacion}/{fechaDesde}/{fechaHasta}", numIdentificacion, fechaDesde,fechaHasta)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	



}
