package org.dbp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class TemplateUtils {

	private static Logger logger = LoggerFactory.getLogger(TemplateUtils.class);
	
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
	 * Se encarga de crear una plantilla.
	 * 
	 * 	En este caso si existe el fichero no hacemos y en caso de no existir lo crea y aplicamos la plantilla.
	 * 
	 * @param contexto		Es el contexto.
	 * @param pathPlantilla	Es el path de la plantilla.
	 * @param pathSalida	Es el path de salida.
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void crearPlantilla(final Map<String,Object> contexto,final String pathPlantilla,final String pathSalida) throws IOException, TemplateException{
		logger.debug("Vamos a crear el fichero[{}]",pathSalida);
		crearPlantilla(contexto, pathPlantilla, Paths.get(pathSalida));
	}

	public void crearPlantilla(final Map<String, Object> contexto,
			final String pathPlantilla, Path pathS) throws IOException,
			TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, TemplateException {
		Optional<Path> path =obtenerFichero(pathS);
		if(path.isPresent()){
			try(BufferedWriter writer = Files.newBufferedWriter(path.get())){
				crearPlantilla(contexto,pathPlantilla,writer);
			}
		}
	}
	
	/**
	 * Se encarga de crear el fichero si no existe.
	 * 
	 * @param pathSalida
	 * @return
	 * @throws IOException
	 */
	private Optional<Path> obtenerFichero(final Path salida) throws IOException {
		//Path salida = Paths.get(pathSalida);
		if(!Files.exists(salida)){
			if(!Files.exists(salida.getParent())){
				Files.createDirectories(salida.getParent());
			}
			Files.createFile(salida);
			return Optional.ofNullable(salida);
		}
		return Optional.empty();
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
