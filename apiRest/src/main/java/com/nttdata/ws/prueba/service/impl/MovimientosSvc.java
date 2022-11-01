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
import com.nttdata.ws.prueba.model.EstadoCuentaDetalladoType;
import com.nttdata.ws.prueba.model.EstadoCuentaType;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.model.TiposDeMovimiento;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.contract.ICuentaRepository;
import com.nttdata.ws.prueba.repository.contract.IMovimientosRepository;
import com.nttdata.ws.prueba.repository.contract.IPersonaRepository;
import com.nttdata.ws.prueba.repository.model.Cliente;
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
		MovimientosType mov = new MovimientosType();
		Cuenta datosCuenta = cuentaRepository.verificaNumeroCuenta(numeroDeCuenta);
		Double saldoDisponible = mvtsRepository.consultaSaldoUltimaMovimiento(numeroDeCuenta);
		if (!(numeroDeCuenta.equals(datosCuenta.getNumeroDeCuenta()))) {
			throw new BusinessException(String.format(
					MensajesDelServicio.NRO_CUENTA_NO_REGISTRADA,
					movimientosType.getNumeroDeCuenta()), TipoError.SOLICITUD_INVALIDA);
		}
		Cliente datosCliente = clienteRepository.consultarClientePorIdentificacion(datosCuenta.getIdentificacion());

		mov.setCliente(datosCliente.getNombreCompleto());
		mov.setFecha(movimientosType.getFecha());
		mov.setEstado(movimientosType.isEstado());
		mov.setIdentificacionCliente(datosCliente.getIdentificacion());
		mov.setNumeroDeCuenta(movimientosType.getNumeroDeCuenta());
		mov.setTipo(movimientosType.getTipo());
		mov.setTipoMovimiento(movimientosType.getTipoMovimiento());
		mov.setSaldoInicial(datosCuenta.getSaldoInicial());
		
		// si el tipo de movimiento es DEBITO - RETIRO
		if (TiposDeMovimiento.RETIRO.equals(movimientosType.getTipoMovimiento())) {

			// VALIDA el valor del saldo inicial que sea > 0 ó que sea sea mayor al monto a
			// retirar
			if (datosCuenta.getSaldoInicial() == 0.00
					|| datosCuenta.getSaldoInicial() < movimientosType.getMovimiento()) {
				throw new BusinessException(
						String.format(MensajesDelServicio.MENSAJE_SALDO_CERO, movimientosType.getNumeroDeCuenta()),
						TipoError.SOLICITUD_INVALIDA);

			}

			mov.setMovimiento(-(movimientosType.getMovimiento()));
			if(mvtsRepository.consultarMovimientosPorCta(numeroDeCuenta).isEmpty()) {
				mov.setSaldoDisponible(datosCuenta.getSaldoInicial() + mov.getMovimiento());
			}else {
				// valida que el saldo disponible sea mayor al monto a retirar
				if(saldoDisponible < movimientosType.getMovimiento()) {
					throw new BusinessException(
							String.format(MensajesDelServicio.MENSAJE_SALDO_CERO, movimientosType.getNumeroDeCuenta()),
							TipoError.SOLICITUD_INVALIDA);
				}
				mov.setSaldoDisponible(saldoDisponible + mov.getMovimiento());
			}

		} else {
			mov.setMovimiento(movimientosType.getMovimiento());
			if(mvtsRepository.consultarMovimientosPorCta(numeroDeCuenta).isEmpty()) {
				mov.setSaldoDisponible(datosCuenta.getSaldoInicial() + mov.getMovimiento());
			}else {
				mov.setSaldoDisponible(saldoDisponible + movimientosType.getMovimiento());
			}
			//mov.setSaldoDisponible(datosCuenta.getSaldoInicial() + movimientosType.getMovimiento());
		}

		Movimientos movimientos = MovimientosConvert.typeToModel(mov);
		mvtsRepository.save(movimientos);
		return MovimientosConvert.modelToType(movimientos);
	}

	@Override
	public MovimientosType actualizarMovimiento(MovimientosType movimientosType) throws BusinessException {
		String identificacion = movimientosType.getIdentificacionCliente().trim();
		String numCuenta = movimientosType.getNumeroDeCuenta().toUpperCase().trim();

		Cuenta datosCta = cuentaRepository.consultarCuentaPorNumero(numCuenta);
		Cliente datosClte = clienteRepository.consultarClientePorIdentificacion(identificacion);
		// valida el id del movimiento
		if (!mvtsRepository.existsById(movimientosType.getId())) {
			throw new BusinessException(
					String.format(MensajesDelServicio.MOVIMIENTO_NO_REGISTRADO, movimientosType.getId().toString()),
					TipoError.SOLICITUD_INVALIDA);
		} else {
			// valida si la cuenta existe
			if (datosCta.equals(null)) {
				throw new BusinessException(
						String.format(MensajesDelServicio.NRO_CUENTA_NO_REGISTRADA, numCuenta),
						TipoError.SOLICITUD_INVALIDA);

			} else {
				// valida que el numero de identificacion este registrado anteriormente
				if (datosClte == null) {
					throw new BusinessException(
							String.format(MensajesDelServicio.IDENTIFICACION_NO_REGISTRADA,
									identificacion),
							TipoError.SOLICITUD_INVALIDA);

				} else {
					// valida que el numero de identificacion
					if (!identificacion.equals(datosCta.getIdentificacion())) {
						throw new BusinessException(String.format(
								MensajesDelServicio.NRO_CUENTA_CTE_NO_REGISTRADO,
								identificacion, numCuenta), TipoError.SOLICITUD_INVALIDA);
					}
				}
			}

		}
		
		// guarda los cambios
		Movimientos movimiento = mvtsRepository.save(MovimientosConvert.typeToModel(movimientosType));
		return MovimientosConvert.modelToType(movimiento);

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
	public List<MovimientosType> consultarMovimientosPorNumeroDeCta(String numCuenta, Date fechaDesde,
			Date fechaHasta) {
		return MovimientosConvert
				.listModelToType(mvtsRepository.consultarMovimientosPorNumeroDeCta(numCuenta, fechaDesde, fechaHasta));
	}

	@Override
	public List<MovimientosType> consultarMovimientosPorCliente(String numIdentificacion, Date fechaDesde,
			Date fechaHasta) {
		return MovimientosConvert.listModelToType(
				mvtsRepository.consultarMovimientosPorCliente(numIdentificacion, fechaDesde, fechaHasta));
	}

	@Override
	public List<MovimientosType> consultarMovimientosPorFechas(Date fechaDesde, Date fechaHasta) {
		return MovimientosConvert.listModelToType(mvtsRepository.consultarMovimientosPorFechas(fechaDesde, fechaHasta));
	}

	@Override
	public EstadoCuentaType consultarEstadoCuenta(String numIdentificacion, Date fechaDesde, Date fechaHasta)
			throws BusinessException {
		EstadoCuentaType estadoCuenta = new EstadoCuentaType();
		double montoTotalDepositos = 0;
		double montoTotalRetiros = 0;
		double saldoDisponibleCorte = 0;

		// VALIDA QUE EL NUMERO DE IDENTIFICACION ESTE REGISTRADO
		Persona datosCliente = clienteRepository.consultarClientePorIdentificacion(numIdentificacion.trim());
		// si no esta registrada lanza la excepción de logica de negocio
		if (datosCliente == null) {
			throw new BusinessException(String.format(
					MensajesDelServicio.IDENTIFICACION_NO_REGISTRADA,
					numIdentificacion.trim()), TipoError.SOLICITUD_INVALIDA);

		}
		estadoCuenta.setNombreCliente(datosCliente.getNombreCompleto());
		estadoCuenta.setFechaInicioCorte(fechaDesde);
		estadoCuenta.setFechaFinCorte(fechaHasta);
		List<Cuenta> datosCuenta = (List<Cuenta>) cuentaRepository
				.consultarCuentaPorNumeroIdentificacion(numIdentificacion);

		if (!(datosCuenta.isEmpty())) {
			for (Cuenta cuenta : datosCuenta) {
				saldoDisponibleCorte += cuenta.getSaldoInicial();
			}
		}

		List<MovimientosType> movimientosEfectuados = (List<MovimientosType>) MovimientosConvert.listModelToType(
				mvtsRepository.consultarMovimientosPorCliente(numIdentificacion, fechaDesde, fechaHasta));

		if (movimientosEfectuados.isEmpty()) {
			estadoCuenta.setSaldoDisponibleCorte(saldoDisponibleCorte);

		} else {
			saldoDisponibleCorte = movimientosEfectuados.get(movimientosEfectuados.size() - 1).getSaldoDisponible();
			for (MovimientosType movs : movimientosEfectuados) {
				if (TiposDeMovimiento.DEPOSITO.equals(movs.getTipoMovimiento())) {
					montoTotalDepositos += movs.getMovimiento();
				} else if (TiposDeMovimiento.RETIRO.equals(movs.getTipoMovimiento())) {
					montoTotalRetiros += movs.getMovimiento();
				}
			}
		}
		estadoCuenta.setDetalleMovimientos(movimientosEfectuados);
		estadoCuenta.setSaldoDisponibleCorte(saldoDisponibleCorte);

		estadoCuenta.setMontoTotalDepositos(montoTotalDepositos);
		estadoCuenta.setMontoTotalRetiros(montoTotalRetiros);

		return estadoCuenta;
	}

	@Override
	public EstadoCuentaDetalladoType consultarEstadoCuentaDetallado(String identificacion, Date fechaDesde,
			Date fechaHasta) throws BusinessException {
		/*
		 * EstadoCuentaDetalladoType estadoCuenta = new EstadoCuentaDetalladoType();
		 * double montoTotalDepositos= 0; double montoTotalRetiros = 0; double
		 * saldoDisponibleCorte = 0;
		 * 
		 * //VALIDA QUE EL NUMERO DE IDENTIFICACION ESTE REGISTRADO Persona datosCliente
		 * = clienteRepository.consultarClientePorIdentificacion(identificacion.trim());
		 * if(datosCliente ==null) { throw new BusinessException( String.
		 * format("El número de identificación: [%s] no se encuentra registrado, por favor verifique el numero."
		 * , identificacion.trim()), TipoError.SOLICITUD_INVALIDA);
		 * 
		 * } List<DetalleMovimientosCuentas> listadoCuentas = null; List<Cuenta>
		 * datosCuenta = (List<Cuenta>)
		 * cuentaRepository.consultarCuentaPorNumeroIdentificacion(identificacion.trim()
		 * );
		 * 
		 * 
		 * for (Cuenta cta: datosCuenta) { cta.getNumeroDeCuenta();
		 * listadoCuentas.add(datosCuenta.indexOf(datosCuenta),
		 * cta.getNumeroDeCuenta()); }
		 * 
		 */
		return null;
	}

	@Override
	public List<MovimientosType> consultarMovimientosPorCta(String numCuenta) throws BusinessException {
		String numeroCta = numCuenta.trim();
		Cuenta datosCuenta = cuentaRepository.verificaNumeroCuenta(numeroCta);
		if (datosCuenta == null) {
			throw new BusinessException(String.format(
					MensajesDelServicio.NRO_CUENTA_NO_REGISTRADA,
					numCuenta), TipoError.NO_ENCONTRADO);
		}
		return MovimientosConvert.listModelToType(mvtsRepository.consultarMovimientosPorCta(numeroCta));
	}

	@Override
	public List<MovimientosType> consultarMovimientosPorCuentaTipoMovimiento(String numeroCuenta,
			TiposDeMovimiento tipoMovimiento) throws BusinessException {
		String numeroCta = numeroCuenta.trim();
		Cuenta datosCuenta = cuentaRepository.verificaNumeroCuenta(numeroCta);
		if (!(numeroCta.equals(datosCuenta.getNumeroDeCuenta()))) {
			throw new BusinessException(String.format(
					MensajesDelServicio.NRO_CUENTA_NO_REGISTRADA,
					numeroCta), TipoError.SOLICITUD_INVALIDA);
		}
		return MovimientosConvert.listModelToType(mvtsRepository.consultaTipoMovimientosCta(numeroCta, tipoMovimiento));
	}

}
