/**
 * 
 */
package com.nttdata.ws.prueba.service.contract;

import java.util.List;

import com.nttdata.ws.prueba.model.ClienteType;
import com.nttdata.ws.prueba.repository.model.Cliente;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;

/**
 * @author Angelica
 *
 */
public interface IClienteSvc {
	
	ClienteType crearCliente (ClienteType clienteType)throws BusinessException;
	ClienteType actualizarCliente (ClienteType clienteType)throws BusinessException;
	Boolean eliminarCliente(String clienteId) throws BusinessException;
	Cliente consultarClientePorIdentificacion (String numIdentificacion) throws BusinessException;
	List<ClienteType> consultarClientes();


}
