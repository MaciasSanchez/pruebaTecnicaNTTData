/**
 * 
 */
package com.nttdata.ws.prueba.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

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

	


}
