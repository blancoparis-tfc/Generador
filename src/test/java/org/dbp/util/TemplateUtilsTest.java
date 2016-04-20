package org.dbp.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import freemarker.template.TemplateException;

public class TemplateUtilsTest {
	@Test
	public void testPlantilla() throws IOException, TemplateException{
		URL url=Thread.currentThread().getContextClassLoader().getResource("plantillas");
		Properties propiedades = new Properties();
		propiedades.setProperty(TemplateUtils.DIRECTORIO_PLANTILLAS, url.getFile());
		TemplateUtils templateUtils = TemplateUtils.instancia(propiedades);
		
		ByteArrayOutputStream os= new ByteArrayOutputStream();
		Writer out = new OutputStreamWriter(os);
		Map<String,Object> contexto = new HashMap<String,Object>();
		contexto.put("nombre", "david");
		templateUtils.crearPlantilla(contexto, "test.ftlh", out);
		assertEquals("Hola david",os.toString("UTF-8"));
	}
	@Test
	public void testCrearFichero() throws IOException, TemplateException{
		String pathSalida=System.getProperty("java.io.tmpdir")+"\\david.txt";
		Path temporal=Paths.get(pathSalida);
		Files.deleteIfExists(temporal);
		
		URL url=Thread.currentThread().getContextClassLoader().getResource("plantillas");
		Properties propiedades = new Properties();
		propiedades.setProperty(TemplateUtils.DIRECTORIO_PLANTILLAS, url.getFile());
		TemplateUtils templateUtils = TemplateUtils.instancia(propiedades);
		Map<String,Object> contexto = new HashMap<String,Object>();
		contexto.put("nombre", "david");
		assertFalse(Files.exists(temporal));
		templateUtils.crearPlantilla(contexto, "test.ftlh", pathSalida);
		assertTrue(Files.exists(temporal));
		try(BufferedReader reader=Files.newBufferedReader(temporal)){
			assertEquals("Hola david",reader.readLine());
		}
		contexto.put("nombre", "perico");
		templateUtils.crearPlantilla(contexto, "test.ftlh", pathSalida);
		try(BufferedReader reader=Files.newBufferedReader(temporal)){
			assertEquals("Hola david",reader.readLine());
		}
	}

}
