/**
 * 
 */
package com.nttdata.ws.prueba.service.contract;

import java.util.List;

import com.nttdata.ws.prueba.model.CuentaType;
import com.nttdata.ws.prueba.repository.model.Cuenta;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;

/**
 * @author Angelica
 *
 */
public interface ICuentasSvc {
	
	CuentaType crearCuenta (CuentaType cuentaType) throws BusinessException;
	CuentaType actualizarCuenta (CuentaType cuentaType)throws BusinessException;
	Boolean eliminarCuenta(String id) throws BusinessException;
	Cuenta consultarCuentaPorNumero (String numeroDeCuenta) throws BusinessException;
	List<CuentaType> consultarCuentas();
	List<CuentaType> consultarCuentaPorNumeroIdentificacion (String identificacion);
	
	



}
