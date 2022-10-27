/**
 * 
 */
package com.nttdata.ws.prueba.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.ws.prueba.constants.MensajesDelServicio;
import com.nttdata.ws.prueba.model.DetalleMovimientosCuentas;
import com.nttdata.ws.prueba.model.EstadoCuentaDetalladoType;
import com.nttdata.ws.prueba.model.EstadoCuentaType;
import com.nttdata.ws.prueba.model.MovimientosClienteType;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.model.TiposDeMovimiento;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.contract.ICuentaRepository;
import com.nttdata.ws.prueba.repository.contract.IMovimientosRepository;
import com.nttdata.ws.prueba.repository.contract.IPersonaRepository;
import com.nttdata.ws.prueba.repository.model.Cuenta;
import com.nttdata.ws.prueba.repository.model.Movimientos;
import com.nttdata.ws.prueba.repository.model.Persona;
import com.nttdata.ws.prueba.service.contract.IMovimientosSvc;
import com.nttdata.ws.prueba.utils.MovimientosConvert;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;
import com.nttdata.ws.prueba.utils.exceptions.TipoError;

/**
 * @author Angelica
 *
 */
@Service
public class MovimientosSvc implements IMovimientosSvc {
	
	@Autowired
	IMovimientosRepository mvtsRepository;
	
	@Autowired
	ICuentaRepository cuentaRepository;
	
	@Autowired
	IClienteRepository clienteRepository;
	@Autowired
	IPersonaRepository personaRepository;


	@Override
	public MovimientosType crearMovimiento(MovimientosType movimientosType) throws BusinessException {
		String numeroDeCuenta = movimientosType.getNumeroDeCuenta().trim();
		MovimientosClienteType mov = new MovimientosClienteType();
		Cuenta datosCuenta = cuentaRepository.verificaNumeroCuenta(numeroDeCuenta);
		if (!(numeroDeCuenta.equals(datosCuenta.getNumeroDeCuenta()))) {
			throw new BusinessException(
					String.format("El número de cuenta: [%s] no se encuentra registrado, por favor verifique el numero.", movimientosType.getNumeroDeCuenta()),
					TipoError.SOLICITUD_INVALIDA);	
		}
		Persona datosCliente = clienteRepository.consultarClientePorIdentificacion(datosCuenta.getIdentificacion());
		
		mov.setCliente(datosCliente.getNombreCompleto()); 
		mov.setFecha(movimientosType.getFecha());
		mov.setEstado(movimientosType.getEstado());
		mov.setIdentificacionCliente(datosCliente.getIdentificacion());
		mov.setNumeroDeCuenta(movimientosType.getNumeroDeCuenta());
		mov.setTipo(movimientosType.getTipo());
		mov.setTipoMovimiento(movimientosType.getTipoMovimiento());
		mov.setSaldoInicial(datosCuenta.getSaldoInicial());
		
		
		// si el tipo de movimiento es DEBITO - RETIRO
		if (TiposDeMovimiento.RETIRO.equals(movimientosType.getTipoMovimiento())) {
			
			// VALIDA el valor del saldo inicial que sea > 0 ó que sea sea mayor al monto a retirar
			if(datosCuenta.getSaldoInicial() == 0.00 || datosCuenta.getSaldoInicial() < movimientosType.getMovimiento()) {
				throw new BusinessException(
						String.format(MensajesDelServicio.MENSAJE_SALDO_CERO, movimientosType.getNumeroDeCuenta()),
						TipoError.SOLICITUD_INVALIDA);
				
			}
			

			mov.setMovimiento(-(movimientosType.getMovimiento()));
			mov.setSaldoDisponible(datosCuenta.getSaldoInicial() + mov.getMovimiento());
			
			//movimientosType.setSaldoDisponible((datosCuenta.getSaldoInicial() - Math.abs(movimientosType.getMovimiento()) ));
			
		}else {
			mov.setMovimiento(movimientosType.getMovimiento());
			mov.setSaldoDisponible(datosCuenta.getSaldoInicial() + movimientosType.getMovimiento());
		}
		
		Movimientos movimientos = MovimientosConvert._typeToModel(mov);
		mvtsRepository.save(movimientos);
		return MovimientosConvert.modelToType(movimientos);
	}


	@Override
	public MovimientosType actualizarMovimiento(MovimientosClienteType movimientosType) throws BusinessException {
		return null;
	}


	@Override
	public Boolean eliminarMovimiento(String id) throws BusinessException {
		Boolean recursoBorrado = false;
		UUID movimientoID = UUID.fromString(id.trim());
		if (mvtsRepository.findById(movimientoID).isPresent()) {
			mvtsRepository.deleteById(movimientoID);
			recursoBorrado = true;
		} else {
			throw new BusinessException(MensajesDelServicio.RECURSO_NO_ENCONTRADO, TipoError.NO_ENCONTRADO);
		}

		return recursoBorrado;
	}


	@Override
	public List<MovimientosClienteType> consultarMovimientosPorNumeroDeCta(String numCuenta, Date fechaDesde,
			Date fechaHasta) {
		return MovimientosConvert.listModelToTypeCliente(
				mvtsRepository.consultarMovimientosPorNumeroDeCta(numCuenta, fechaDesde, fechaHasta));
	}


	@Override
	public List<MovimientosClienteType> consultarMovimientosPorCliente(String numIdentificacion, Date fechaDesde,
			Date fechaHasta) {
		return MovimientosConvert.listModelToTypeCliente(
				mvtsRepository.consultarMovimientosPorCliente(numIdentificacion, fechaDesde, fechaHasta));
	}


	@Override
	public List<MovimientosClienteType> consultarMovimientosPorFechas(Date fechaDesde, Date fechaHasta) {
		return MovimientosConvert.listModelToTypeCliente(
				mvtsRepository.consultarMovimientosPorFechas(fechaDesde, fechaHasta));
	}


	@Override
	public EstadoCuentaType consultarEstadoCuenta(String numIdentificacion, Date fechaDesde,
			Date fechaHasta) throws BusinessException {
		EstadoCuentaType estadoCuenta = new EstadoCuentaType();
		double montoTotalDepositos= 0;
		double montoTotalRetiros = 0;
		double saldoDisponibleCorte = 0;
		//VALIDA QUE EL NUMERO DE IDENTIFICACION ESTE REGISTRADO
		Persona datosCliente = clienteRepository.consultarClientePorIdentificacion(numIdentificacion.trim());
		// si no esta registrada lanza la excepción de logica de negocio
		if(datosCliente ==null) {
			throw new BusinessException(
					String.format("El número de identificación: [%s] no se encuentra registrado, por favor verifique el numero.", numIdentificacion.trim()),
					TipoError.SOLICITUD_INVALIDA);
			
		}
		
		//Cuenta datosCuenta = (Cuenta) cuentaRepository.consultarCuentaPorNumeroIdentificacion(numIdentificacion);
		
		List<MovimientosClienteType> movimientosEfectuados = (List<MovimientosClienteType>) MovimientosConvert.listModelToTypeCliente(
				mvtsRepository.consultarMovimientosPorCliente(numIdentificacion, fechaDesde, fechaHasta));
		saldoDisponibleCorte = movimientosEfectuados.get(movimientosEfectuados.size()-1).getSaldoDisponible();
		
		
		estadoCuenta.setNombreCliente(datosCliente.getNombreCompleto());
		estadoCuenta.setFechaInicioCorte(fechaDesde);
		estadoCuenta.setFechaFinCorte(fechaHasta);
		estadoCuenta.setDetalleMovimientos(movimientosEfectuados);
		estadoCuenta.setSaldoDisponibleCorte(saldoDisponibleCorte);
		
		for(MovimientosClienteType movs: movimientosEfectuados) {
			if(TiposDeMovimiento.DEPOSITO.equals(movs.getTipoMovimiento())){
				montoTotalDepositos += movs.getMovimiento();
			}else if (TiposDeMovimiento.RETIRO.equals(movs.getTipoMovimiento())) {
				montoTotalRetiros += movs.getMovimiento();
			}
		}
		
		estadoCuenta.setMontoTotalDepositos(montoTotalDepositos);
		estadoCuenta.setMontoTotalRetiros(montoTotalRetiros);
		
		
		return estadoCuenta;
	}


	@Override
	public EstadoCuentaDetalladoType consultarEstadoCuentaDetallado(String identificacion, Date fechaDesde,
			Date fechaHasta) throws BusinessException {
		/*
		EstadoCuentaDetalladoType estadoCuenta = new EstadoCuentaDetalladoType();
		double montoTotalDepositos= 0;
		double montoTotalRetiros = 0;
		double saldoDisponibleCorte = 0;
		
		//VALIDA QUE EL NUMERO DE IDENTIFICACION ESTE REGISTRADO
		Persona datosCliente = clienteRepository.consultarClientePorIdentificacion(identificacion.trim());
				if(datosCliente ==null) {
					throw new BusinessException(
							String.format("El número de identificación: [%s] no se encuentra registrado, por favor verifique el numero.", identificacion.trim()),
							TipoError.SOLICITUD_INVALIDA);
					
				}
		List<DetalleMovimientosCuentas> listadoCuentas = null;		
		List<Cuenta> datosCuenta = (List<Cuenta>) cuentaRepository.consultarCuentaPorNumeroIdentificacion(identificacion.trim());
		
		
		for (Cuenta cta: datosCuenta) {
			   cta.getNumeroDeCuenta();
			   listadoCuentas.add(datosCuenta.indexOf(datosCuenta), cta.getNumeroDeCuenta());
		}
		
		*/
		return null;
	}
	
	
	


}
