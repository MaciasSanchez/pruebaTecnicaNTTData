/**
 * 
 */
package com.nttdata.ws.prueba.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.ws.prueba.constants.MensajesDelServicio;
import com.nttdata.ws.prueba.model.CuentaType;
import com.nttdata.ws.prueba.repository.contract.ICuentaRepository;
import com.nttdata.ws.prueba.repository.model.Cuenta;
import com.nttdata.ws.prueba.service.contract.ICuentasSvc;
import com.nttdata.ws.prueba.utils.CuentaConvert;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;
import com.nttdata.ws.prueba.utils.exceptions.TipoError;

/**
 * @author Angelica
 *
 */
@Service
public class CuentasSvc implements ICuentasSvc {

	@Autowired
	ICuentaRepository cuentaRepository;

	@Override
	public CuentaType crearCuenta(CuentaType cuentaType) throws BusinessException {
		String identificacion = cuentaType.getIdentificacion().trim();
		String numCuenta = cuentaType.getNumeroDeCuenta().trim();
		String nombreCliente = cuentaRepository.consultaIdentificacion(identificacion);
		if (!(identificacion.equals(cuentaRepository.consultaIdentificacionCliente(identificacion)))) {
			throw new BusinessException(
					String.format("El cliente con número de identificación: [%s] no se encuentra registrado",
							cuentaType.getNumeroDeCuenta()),
					TipoError.SOLICITUD_INVALIDA);
		} else {

			List<Cuenta> cuentas = (List<Cuenta>) cuentaRepository.consultarCuentas();
			for (Cuenta cta : cuentas) {
				if (numCuenta.equals(cta.getNumeroDeCuenta())) {
					throw new BusinessException(String.format("El número de cuenta: [%s] ya se encuentra registrado",
							cuentaType.getNumeroDeCuenta()), TipoError.SOLICITUD_INVALIDA);
				}
			}

		}
		cuentaType.setCliente(nombreCliente);
		Cuenta cuenta = cuentaRepository.save(CuentaConvert.typeToModel(cuentaType));
		return CuentaConvert.modelToType(cuenta);
	}

	@Override
	public CuentaType actualizarCuenta(CuentaType cuentaType) throws BusinessException {

		UUID cuentaId = cuentaType.getId();
		if (cuentaRepository.findById(cuentaId).isPresent()) {

			String numeroDeCuenta = cuentaRepository.findByCuentaId(cuentaId).toUpperCase();
			if (!(numeroDeCuenta.equals(cuentaType.getNumeroDeCuenta().toUpperCase()))
					&& cuentaRepository.existsAccountNumber(cuentaType.getNumeroDeCuenta().toUpperCase())) {
				throw new BusinessException(String.format("El num. de cuenta: [%s] ya se encuentra registrado",
						cuentaType.getNumeroDeCuenta()), TipoError.SOLICITUD_INVALIDA);
			}
			Cuenta cuenta = cuentaRepository.save(CuentaConvert.typeToModel(cuentaType));
			return CuentaConvert.modelToType(cuenta);
		}
		return null;
	}

	@Override
	public Boolean eliminarCuenta(String id) throws BusinessException {
		Boolean recursoBorrado = false;
		UUID cuentaId = UUID.fromString(id.trim());
		if (cuentaRepository.findById(cuentaId).isPresent()) {
			cuentaRepository.deleteById(cuentaId);
			recursoBorrado = true;
		} else {
			throw new BusinessException(MensajesDelServicio.RECURSO_NO_ENCONTRADO, TipoError.NO_ENCONTRADO);
		}

		return recursoBorrado;
	}

	@Override
	public List<CuentaType> consultarCuentas() {
		return CuentaConvert.listModelToType(cuentaRepository.consultarCuentas());
	}

	@Override
	public Cuenta consultarCuentaPorNumero(String numeroDeCuenta) throws BusinessException {
		Cuenta cuenta = cuentaRepository.consultarCuentaPorNumero(numeroDeCuenta.trim().toUpperCase());
		if (cuenta == null) {
			throw new BusinessException(MensajesDelServicio.RECURSO_NO_ENCONTRADO, TipoError.NO_ENCONTRADO);
		}
		return cuenta;
	}

	@Override
	public List<CuentaType> consultarCuentaPorNumeroIdentificacion(String identificacion) {
		return CuentaConvert.listModelToType(
				cuentaRepository.consultarCuentaPorNumeroIdentificacion(identificacion.trim().toUpperCase()));
	}

}
