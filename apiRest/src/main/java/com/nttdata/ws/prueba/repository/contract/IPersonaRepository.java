/**
 * 
 */
package com.nttdata.ws.prueba.repository.contract;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nttdata.ws.prueba.repository.model.Persona;

/**
 * @author Angelica
 *
 */
@Repository
public interface IPersonaRepository extends JpaRepository<Persona, UUID>{
	
	
	
	@Query (value = "select pe.* from persona pe inner join t_cuentas u\r\n "
			+ "	on pe.identificacion = u.num_identificacion_cliente \r\n "
			+ "	inner join cl_cliente cl on cl.id = pe.id\r\n"
			+ "	where upper(pe.identificacion) = :identificacion", nativeQuery = true)
	Persona verificaCliente(
			@Param ("identificacion") String identificacion);


}
