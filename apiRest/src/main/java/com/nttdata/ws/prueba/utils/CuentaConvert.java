/**
 * 
 */
package com.nttdata.ws.prueba.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;

import com.nttdata.ws.prueba.model.CuentaType;
import com.nttdata.ws.prueba.repository.model.Cuenta;

/**
 * @author Angelica
 *
 */

public final class CuentaConvert {
	
	private CuentaConvert() {
		
	}
	
	public static CuentaType modelToType(Cuenta cuenta) {
		return new ModelMapper().map(cuenta, CuentaType.class);
	}
	
	public static Cuenta typeToModel(@Valid CuentaType cuentaType) {
		return new ModelMapper().map(cuentaType, Cuenta.class);
	}
	
	
	public static List<CuentaType> listModelToType(List<Cuenta> cuentas){
		return cuentas.stream().map(cuenta -> (modelToType(cuenta))).collect(Collectors.toList());
	}
	

}
