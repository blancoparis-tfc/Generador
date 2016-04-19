package org.dbp.util;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class TemplateUtils {

	public static final String ENCODING = "encoding";
	public static final String DIRECTORIO_PLANTILLAS = "directorioPlantillas";
	
	private Configuration configuracion;
	
	public static TemplateUtils instancia(final Properties properties) throws IOException{
		return new TemplateUtils(properties);
	}
	
	private TemplateUtils(Properties properties) throws IOException{
		configuracion = new Configuration(Configuration.VERSION_2_3_23);
		configuracion.setDirectoryForTemplateLoading(new File(properties.getProperty(DIRECTORIO_PLANTILLAS,"")));
		configuracion.setDefaultEncoding(properties.getProperty(ENCODING,"UTF-8"));
		configuracion.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	/**
	 * 
	 * Se encarga de generar la plantilla.
	 * 
	 * @param contexto			Es el contexto, con los datos.
	 * @param pathPlantilla		Es donde se encuentra la plantilla.
	 * @param salida			La salida donde vamos a volcar la plantilla.
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void crearPlantilla(final Map<String,Object> contexto,final String pathPlantilla,final Writer salida) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		Template plantilla = configuracion.getTemplate(pathPlantilla);
		plantilla.process(contexto, salida);
	}
	
}
