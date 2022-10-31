/**
 * 
 */
package com.nttdata.ws.prueba.service.contract;

import java.util.Date;
import java.util.List;

import com.nttdata.ws.prueba.model.EstadoCuentaDetalladoType;
import com.nttdata.ws.prueba.model.EstadoCuentaType;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;

/**
 * @author Angelica
 *
 */
public interface IMovimientosSvc {
	
	MovimientosType crearMovimiento (MovimientosType movimientosType) throws BusinessException;
	MovimientosType actualizarMovimiento (MovimientosType movimientosType)throws BusinessException;
	Boolean eliminarMovimiento(String id) throws BusinessException;
	List<MovimientosType> consultarMovimientosPorNumeroDeCta(String numCuenta, Date fechaDesde, Date fechaHasta);
	List<MovimientosType> consultarMovimientosPorCta(String numCuenta);
	List<MovimientosType> consultarMovimientosPorCliente(String numIdentificacion, Date fechaDesde, Date fechaHasta);
	EstadoCuentaType consultarEstadoCuenta(String numIdentificacion, Date fechaDesde, Date fechaHasta) throws BusinessException;
	EstadoCuentaDetalladoType consultarEstadoCuentaDetallado(String identificacion, Date fechaDesde, Date fechaHasta) throws BusinessException;
	List<MovimientosType> consultarMovimientosPorFechas(Date fechaDesde, Date fechaHasta);
	


}
