package org.dbp.util;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.dbp.bean.Campo;
import org.junit.Test;

import freemarker.template.TemplateException;

public class PlantillasUtilsTest {

	@Test
	public void test001Plantillas() throws IOException, TemplateException{
		URL url=Thread.currentThread().getContextClassLoader().getResource("plantillas");
		Properties propiedades = new Properties();
		propiedades.setProperty(TemplateUtils.DIRECTORIO_PLANTILLAS, url.getFile());
		TemplateUtils templateUtils = TemplateUtils.instancia(propiedades);
		String destino =System.getProperty("java.io.tmpdir"); 
		PlantillasUtil plantillasUtils = PlantillasUtil.instancia(templateUtils, Paths.get(destino))
				.add("p1.ftlh","f1","%sF1.java")
				.add("p2.ftlh","f1","%sTest.java")
				;
		Path ficheroDestinoF1 = Paths.get(destino,"f1","CampoF1.java");
		Path ficheroDestinoTest = Paths.get(destino,"f1","CampoTest.java");
		Files.deleteIfExists(ficheroDestinoF1);
		Files.deleteIfExists(ficheroDestinoTest);
		assertFalse("Existe el fichero",Files.exists(ficheroDestinoF1));
		plantillasUtils.aplicarLasPlantillas(MetaDatosBom.instancia().obtenerMetadatos(Campo.class));
		assertTrue("Existe el fichero",Files.exists(ficheroDestinoF1));
		assertTrue("Existe el fichero",Files.exists(ficheroDestinoTest));
		try(BufferedReader reader=Files.newBufferedReader(ficheroDestinoF1)){
			assertEquals("Prueba Campo",reader.readLine());
		}
		try(BufferedReader reader=Files.newBufferedReader(ficheroDestinoTest)){
			assertEquals("Test Campo",reader.readLine());
		}

	}
}
