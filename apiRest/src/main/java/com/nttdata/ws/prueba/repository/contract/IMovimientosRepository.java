/**
 * 
 */
package com.nttdata.ws.prueba.repository.contract;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nttdata.ws.prueba.model.TiposDeMovimiento;
import com.nttdata.ws.prueba.repository.model.Movimientos;

/**
 * @author Angelica
 *
 */
@Repository

public interface IMovimientosRepository extends JpaRepository<Movimientos, UUID>{
	
	

	@Query (value = "select COALESCE(count(u.id_movimiento)) from t_movimientos u where u.fecha \\:\\:DATE = :fecha \\:\\:DATE and u.tipo_mov = :#{#tiposDeMovimiento.name()}", nativeQuery = true)
	int consultaMovimientosDia (@Param ("fecha") Date fecha, @Param ("tiposDeMovimiento") TiposDeMovimiento tipo);
	
	
	//@Query (value = "select sum(u.movimiento) from t_movimientos u where u.fecha \\:\\:DATE= :fecha \\:\\:DATE and u.tipo_mov = :#{#tiposDeMovimiento.name()}", nativeQuery = true)
	@Query (value = "select COALESCE(sum(u.movimiento)) from t_movimientos u where u.tipo_mov = :#{#tiposDeMovimiento.name()}", nativeQuery = true)
	Float cupoMovimientosDia (/*@Param ("fecha") Date fecha, */@Param ("tiposDeMovimiento") TiposDeMovimiento tipo);
	
	
	@Query(value = "select COALESCE(sum(u.movimiento)) from movimientos u where DATE_TRUNC('day', u.fecha) = DATE_TRUNC('day', :fecha\\:\\:DATE) and u.NUMERO_CUENTA = :cuenta and u.tipo_movimiento = :#{#tiposDeMovimiento.name()}", nativeQuery = true)
	String cupoMovimientosDia(@Param("fecha") LocalDateTime fecha, @Param("tiposDeMovimiento") TiposDeMovimiento tipo,
			@Param("cuenta") String cuenta);
	
	@Query(value = "SELECT saldo_disponible FROM public.movimientos WHERE numero_cuenta = :cuenta order by fecha desc  limit 1", nativeQuery = true)
	String consultaUltimoMovimiento(@Param("cuenta") String cuenta);

	
	@Query(value = "select u.* from t_movimientos u where u.num_identificacion = :identificacion"
			+ " and u.fecha \\:\\:DATE between :fechaDesde and :fechaHasta \\:\\:DATE "
			+ " order by u.fecha desc ", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorCliente(
			@Param("identificacion") String identificacion,
			@Param("fechaDesde") Date fechaInicio, 
			@Param("fechaHasta") Date fechaFin);
	
	
	@Query(value = "select u.* from t_movimientos u where u.numero_de_cuenta = :numCuenta"
			+ " and u.fecha \\:\\:DATE between :fechaDesde and :fechaHasta \\:\\:DATE "
			+ " order by u.fecha desc ", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorNumeroDeCta(
			@Param("numCuenta") String numCuenta,
			@Param("fechaDesde") Date fechaInicio, 
			@Param("fechaHasta") Date fechaFin);
	
	@Query(value = "select u.* from t_movimientos u where "
			+ "  u.fecha \\:\\:DATE between :fechaDesde and :fechaHasta \\:\\:DATE "
			+ " order by u.fecha desc ", nativeQuery = true)
	List<Movimientos> consultarMovimientosPorFechas(
			@Param("fechaDesde") Date fechaInicio, 
			@Param("fechaHasta") Date fechaFin);
	
	
	

 
}
