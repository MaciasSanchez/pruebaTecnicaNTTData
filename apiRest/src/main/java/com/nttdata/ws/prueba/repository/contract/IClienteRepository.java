/**
 * 
 */
package com.nttdata.ws.prueba.repository.contract;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nttdata.ws.prueba.repository.model.Cliente;

/**
 * @author Angelica
 *
 */
@Repository
public interface IClienteRepository extends JpaRepository<Cliente, UUID>{
	
	
	@Query (value = "select cliente_id, contrasenia, estado, cl.id, direccion, edad, genero, identificacion, nombre_completo, telefono\r\n"
			+ "	from persona u inner join cl_cliente cl on cl.id = u.id\r\n"
			+ "	where upper(u.identificacion) = :numIdentificacion", nativeQuery = true)
	Cliente consultarClientePorIdentificacion(
			@Param ("numIdentificacion") String numIdentificacion);
	
	
	@Query (value = "select u.identificacion from persona u where u.id = :clienteId", nativeQuery = true)
	String findByIdentificacion(
			@Param ("clienteId") UUID id);
	
	@Query (value = "select count(u.identificacion) > 0 from persona u where upper(u.identificacion) = :numIdentificacion", nativeQuery = true)
	boolean existsIdentificacion(
			@Param ("numIdentificacion") String numIdentificacion);
	
	@Query (value = "select cliente_id, contrasenia, estado, cl.id, direccion, edad, genero, identificacion, nombre_completo, telefono\r\n"
			+ "	from cl_cliente cl inner join persona p\r\n"
			+ "	on cl.id = p.id\r\n"
			+ "	where cl.estado = true", nativeQuery = true)
	List<Cliente> consultarClientes();

}
