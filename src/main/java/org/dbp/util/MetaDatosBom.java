package org.dbp.util;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.dbp.bean.Campo;
import org.dbp.bean.MetaDatos;

public class MetaDatosBom {

	private static String paquetesValidos[] ={"java.lang"};
	
	public static MetaDatosBom instancia(){
		return new MetaDatosBom();
	}
	
	public <T> MetaDatos obtenerMetadatos(Class<T> clase){
		MetaDatos devolver = new MetaDatos();
		devolver.setEntidad(clase.getSimpleName());
		devolver.setCampos(Arrays.stream(clase.getDeclaredFields())
			.filter(field->ArrayUtils.contains(paquetesValidos,field.getType().getPackage().getName()))
			.map(field->new Campo(field))
			.collect(toList())
		);
		return devolver;
	}
	
}
