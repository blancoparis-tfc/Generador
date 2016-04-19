package org.dbp.util;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
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

}
