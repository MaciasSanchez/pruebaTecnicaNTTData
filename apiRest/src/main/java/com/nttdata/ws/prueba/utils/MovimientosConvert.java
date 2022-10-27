/**
 * 
 */
package com.nttdata.ws.prueba.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;

import com.nttdata.ws.prueba.model.MovimientosClienteType;
import com.nttdata.ws.prueba.model.MovimientosType;
import com.nttdata.ws.prueba.repository.model.Movimientos;

/**
 * @author Angelica
 *
 */

public final class MovimientosConvert {
	
	private MovimientosConvert() {
		
	}
	
	public static MovimientosType modelToType(Movimientos movimiento) {
		return new ModelMapper().map(movimiento, MovimientosType.class);
	}
	
	public static Movimientos typeToModel(MovimientosType movimientoType) {
		return new ModelMapper().map(movimientoType, Movimientos.class);
	}
	
	public static List<MovimientosType> listModelToType(List<Movimientos> movimientos){
		return movimientos.stream().map(movimiento -> (modelToType(movimiento))).collect(Collectors.toList());
	}
	
	
	
	
	public static MovimientosClienteType _modelToType(Movimientos movimiento) {
		return new ModelMapper().map(movimiento, MovimientosClienteType.class);
	}
	
	public static Movimientos _typeToModel(MovimientosClienteType movimientoType) {
		return new ModelMapper().map(movimientoType, Movimientos.class);
	}
	
	public static List<MovimientosClienteType> _listModelToType(List<Movimientos> movimientos){
		return movimientos.stream().map(movimiento -> (_modelToType(movimiento))).collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	
	public static MovimientosClienteType modelToTypeCliente(Movimientos movimientoCliente) {
		return new ModelMapper().map(movimientoCliente, MovimientosClienteType.class);
	}
	
	public static Movimientos typeToModelCliente(@Valid MovimientosClienteType movimientoClienteType) {
		return new ModelMapper().map(movimientoClienteType, Movimientos.class);
	}
	
	public static List<MovimientosClienteType> listModelToTypeCliente(List<Movimientos> movimientosCliente){
		return movimientosCliente.stream().map(movimientoCliente -> (modelToTypeCliente(movimientoCliente))).collect(Collectors.toList());
	}
	

}
