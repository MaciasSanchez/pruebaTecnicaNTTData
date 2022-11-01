/**
 * 
 */
package com.nttdata.ws.prueba.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.ws.prueba.constants.MensajesDelServicio;
import com.nttdata.ws.prueba.model.ClienteType;
import com.nttdata.ws.prueba.repository.contract.IClienteRepository;
import com.nttdata.ws.prueba.repository.model.Cliente;
import com.nttdata.ws.prueba.service.contract.IClienteSvc;
import com.nttdata.ws.prueba.utils.ClienteConvert;
import com.nttdata.ws.prueba.utils.exceptions.BusinessException;
import com.nttdata.ws.prueba.utils.exceptions.TipoError;

/**
 * @author Angelica
 *
 */
@Service
public class ClienteSvc implements IClienteSvc {

	@Autowired
	IClienteRepository clienteRepository;

	@Override
	public ClienteType crearCliente(ClienteType clienteType) {
		clienteType.setClienteId(UUID.randomUUID());
		Cliente cliente = clienteRepository.save(ClienteConvert.typeToModel(clienteType));
		return ClienteConvert.modelToType(cliente);
	}

	@Override
	public ClienteType actualizarCliente(ClienteType clienteType) throws BusinessException {
		Cliente cliente = null;
		UUID id = clienteType.getId();
		if (clienteRepository.findById(id).isPresent()) {
			//valida que no se pueda ingresar un numero de identificacion previamente registrado
			String numIdentificacion = clienteRepository.findByIdentificacion(id);
			if (!(numIdentificacion.equals(clienteType.getIdentificacion().toUpperCase()))
					&& clienteRepository.existsIdentificacion(clienteType.getIdentificacion().toUpperCase())) {
				throw new BusinessException(String.format(MensajesDelServicio.IDENTIFICACION_REGISTRADA,
						clienteType.getIdentificacion()), TipoError.SOLICITUD_INVALIDA);
			}
			cliente = clienteRepository.save(ClienteConvert.typeToModel(clienteType));
		}
		return ClienteConvert.modelToType(cliente);
	}

	@Override
	public Boolean eliminarCliente(String clientId) throws BusinessException {
		Boolean recursoBorrado = false;
		UUID clienteId = UUID.fromString(clientId.trim());
		if (clienteRepository.findById(clienteId).isPresent()) {
			clienteRepository.deleteById(clienteId);
			recursoBorrado = true;
		} else {
			throw new BusinessException(MensajesDelServicio.RECURSO_NO_ENCONTRADO, TipoError.NO_ENCONTRADO);
		}

		return recursoBorrado;
	}

	@Override
	public Cliente consultarClientePorIdentificacion(String numIdentificacion) throws BusinessException {
		Cliente cliente = clienteRepository.consultarClientePorIdentificacion(numIdentificacion);
		if (cliente == null) {
			throw new BusinessException(MensajesDelServicio.RECURSO_NO_ENCONTRADO, TipoError.NO_ENCONTRADO);
		}
		return cliente;
	}

	@Override
	public List<ClienteType> consultarClientes() {
		return ClienteConvert.listModelToType(clienteRepository.consultarClientes());
	}

}
