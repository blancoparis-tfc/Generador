package org.dbp.util;

import static java.util.stream.Collectors.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
		devolver.setCampos(obtenerLosField(clase).stream()
			.filter(field->ArrayUtils.contains(paquetesValidos,field.getType().getPackage().getName()))
			.map(field->new Campo(field))
			.collect(toList())
		);
		return devolver;
	}
	/**
	 * 
	 * Se encarga de recuperar todos los campos, asociados a las clases 
	 * 
	 * @param clase
	 * @return
	 */
	private <T> List<Field> obtenerLosField(Class<T> clase) {
		final List<Field> valdev = new ArrayList<Field>();
		final List<Class> clases = obtenerLasClases(clase);
		for(Class claseAux:clases){
			valdev.addAll(Arrays.stream(claseAux.getDeclaredFields()).collect(toList()));
		}
		return valdev;
	}
	/**
	 * Obtenemos las clases, que tiene campos y invertimo el orden, para que aparezcan los campos ordenados del padre al hijo.
	 * @param clase
	 * @return
	 */
	private <T> List<Class> obtenerLasClases(Class<T> clase) {
		final List<Class> clases=new ArrayList<Class>();
		Class clasAux=clase;
		do{
			if(clasAux.getDeclaredFields().length>0){
				clases.add(clasAux);
			}
			clasAux=clasAux.getSuperclass();
		}while(clasAux!=null);
		Collections.reverse(clases);
		return clases;
	}
	
}
