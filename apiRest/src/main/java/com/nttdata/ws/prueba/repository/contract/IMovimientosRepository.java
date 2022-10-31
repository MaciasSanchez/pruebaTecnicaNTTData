/**
 * 
 */
package com.nttdata.ws.prueba.repository.contract;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nttdata.ws.prueba.repository.model.Movimientos;

/**
 * @author Angelica
 *
 */
@Repository

public interface IMovimientosRepository extends JpaRepository<Movimientos, UUID>{
	
	
	@Query(value = "select COALESCE(u.saldo_disponible) from t_movimientos u "
			+ "where u.numero_de_cuenta = :cuenta and u.estado = true order by u.fecha desc  limit 1", nativeQuery = true)
	Double consultaSaldoUltimaMovimiento(@Param("cuenta") String cuenta);
	
	@Query(value = "SELECT u.saldo_disponible FROM t_movimientos u WHERE u.numero_de_cuenta = :cuenta order by fecha desc  limit 1", nativeQuery = true)
	String consultaUltimoMovimiento(@Param("cuenta") String cuenta);

	
	@Query(value = "select u.* from t_movimientos u where u.num_identificacion = :identificacion"
			+ " and u.fecha \\:\\:DATE between :fechaDesde and :fechaHasta \\:\\:DATE order by u.fecha desc", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorCliente(
			@Param("identificacion") String identificacion,
			@Param("fechaDesde") Date fechaInicio, 
			@Param("fechaHasta") Date fechaFin);
	
	
	@Query(value = "select u.* from t_movimientos u where u.numero_de_cuenta = :numCuenta and u.estado = true", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorCta(@Param("numCuenta") String numCuenta);
	
	@Query(value = "select u.* from t_movimientos u where u.numero_de_cuenta = :numCuenta"
			+ " and u.fecha \\:\\:DATE between :fechaDesde and :fechaHasta \\:\\:DATE order by u.fecha desc", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorNumeroDeCta(
			@Param("numCuenta") String numCuenta,
			@Param("fechaDesde") Date fechaInicio, 
			@Param("fechaHasta") Date fechaFin);
	
	@Query(value = "select u.* from t_movimientos u where "
			+ "  u.fecha \\:\\:DATE between :fechaDesde and :fechaHasta \\:\\:DATE order by u.fecha desc", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorFechas(
			@Param("fechaDesde") Date fechaInicio, 
			@Param("fechaHasta") Date fechaFin);
	
	
	

 
}
