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

import com.nttdata.ws.prueba.repository.model.Cuenta;

/**
 * @author Angelica
 *
 */
@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, UUID>{
	
	@Query (value = "select u.numero_cuenta from t_cuentas u where u.id_cuenta = :cuentaId", nativeQuery = true)
	String findByCuentaId(
			@Param ("cuentaId") UUID cuentaId);
	
	@Query (value = "select u.* from t_cuentas u where u.numero_cuenta = :numeroDeCuenta", nativeQuery = true)
	Cuenta verificaNumeroCuenta(
			@Param ("numeroDeCuenta") String numeroDeCuenta);
	
	@Query (value = "select count(u.numero_cuenta) > 0 from t_cuentas u where upper(u.numero_cuenta) = :numeroDeCuenta", nativeQuery = true)
	boolean existsAccountNumber(
			@Param ("numeroDeCuenta") String numeroDeCuenta);
	
	@Query (value = "select u.* from t_cuentas u where u.estado = true", nativeQuery = true)
	List<Cuenta> consultarCuentas();
	
	
	@Query (value = "select id_cuenta, numero_cuenta, num_identificacion_cliente,nombre_cliente,"
			+ " tipo_de_cuenta,	saldo_inicial, estado from t_cuentas u where u.num_identificacion_cliente = :identificacion", nativeQuery = true)
	List<Cuenta> consultarCuentaPorNumeroIdentificacion(
			@Param ("identificacion") String identificacion);
	
	@Query (value = "select nombre_completo	from persona u where u.identificacion = :identificacion", nativeQuery = true)
	String consultaIdentificacion(
			@Param ("identificacion") String identificacion);
	
	@Query (value = "select identificacion	from persona u where u.identificacion = :identificacion", nativeQuery = true)
	String consultaIdentificacionCliente(
			@Param ("identificacion") String identificacion);
	
	@Query (value = "select u.* from t_cuentas u where upper(u.numero_cuenta) = :numeroDeCuenta", nativeQuery = true)
	Cuenta consultarCuentaPorNumero(
			@Param ("numeroDeCuenta") String numeroDeCuenta);
	
	
	
	
	

}
