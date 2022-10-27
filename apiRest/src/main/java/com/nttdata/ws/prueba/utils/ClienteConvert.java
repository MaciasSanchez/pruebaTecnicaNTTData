/**
 * 
 */
package com.nttdata.ws.prueba.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;

import com.nttdata.ws.prueba.model.ClienteType;
import com.nttdata.ws.prueba.repository.model.Cliente;

/**
 * @author Angelica
 *
 */

public final class ClienteConvert {
	
	private ClienteConvert() {
		
	}
	
	public static ClienteType modelToType(Cliente cliente) {
		return new ModelMapper().map(cliente, ClienteType.class);
	}
	
	public static Cliente typeToModel(@Valid ClienteType clienteType) {
		return new ModelMapper().map(clienteType, Cliente.class);
	}
	
	public static List<ClienteType> listModelToType(List<Cliente> clientes){
		return clientes.stream().map(cliente -> (modelToType(cliente))).collect(Collectors.toList());
	}

}
