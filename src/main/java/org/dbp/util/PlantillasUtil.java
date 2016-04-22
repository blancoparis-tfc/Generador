package org.dbp.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dbp.bean.MetaDatos;
import org.dbp.bean.Plantillas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class PlantillasUtil {

	private static Logger logger = LoggerFactory.getLogger(PlantillasUtil.class);
	
	private final List<Plantillas> plantillas;
	
	private final Path destino;
	
	private final TemplateUtils templateUtils;

	private final Map<String,Object> parametros;
	
	public static final PlantillasUtil instancia(final TemplateUtils templateUtils,final Path destino){
		return new PlantillasUtil(templateUtils,destino);
	}

	public PlantillasUtil(TemplateUtils templateUtils,final Path destino) {
		super();
		this.plantillas = new ArrayList<Plantillas>();
		this.templateUtils =templateUtils;
		this.destino = destino;
		this.parametros = new HashMap<String,Object>();
	}
	
	public PlantillasUtil add(String template,String subPath,String formatNombre){
		plantillas.add(new Plantillas(template,subPath,formatNombre));
		return this;
	}
	
	public PlantillasUtil parametro(final String clave,final Object valor){
		parametros.put(clave, valor);
		return this;
	}

	public void aplicarLasPlantillas(MetaDatos metaDatos) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		for(Plantillas plantilla:this.plantillas){
			Path destinoFichero = Paths.get( 
					 this.destino.toString()
					,plantilla.getSubPath()
					,String.format(plantilla.getFormatNombre(),metaDatos.getEntidad()));
			logger.info(" Destino fichero [{}]",destinoFichero);
			Map<String,Object> contexto = new HashMap<String,Object>(this.parametros);
			contexto.put("entidad", metaDatos);
			templateUtils.crearPlantilla(contexto, plantilla.getTemplate(), destinoFichero);
			
		}
	}
	
}
