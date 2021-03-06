package org.dbp.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import junit.extensions.ActiveTestSuite;
import junit.extensions.RepeatedTest;
import junit.extensions.TestDecorator;
import junit.extensions.TestSetup;

import org.dbp.bean.MetaDatos;
import org.junit.Test;

public class EscanerUtilTest {

	@Test
	public void testEscanearUnPaquete() throws ClassNotFoundException, IOException, URISyntaxException{
		final List<Class> clases =EscanearUtils.instancia().escanearPaquete("org.dbp.bean");
		assertThat("Esperamos encontrar esta clase",clases,hasItem(MetaDatos.class));
	}
	
	@Test
	public void testEscanearUnPaqueteVacio() throws ClassNotFoundException, IOException, URISyntaxException{
		assertEquals("No espera nada",0,EscanearUtils.instancia().escanearPaquete("org.dbp").size());
	}
	
	@Test
	public void testEscanearUnPaqueteDeUnaSolaPalabra() throws ClassNotFoundException, IOException, URISyntaxException{
		assertEquals("No espera nada",0,EscanearUtils.instancia().escanearPaquete("dbp").size());
	}
	
	@Test
	public void testEscanearUnPaqueteJar() throws ClassNotFoundException, IOException, URISyntaxException{
		List<Class> clases=EscanearUtils.instancia().escanearPaquete("junit.extensions");
		assertThat("Esperamos encontrar esta clase",clases,hasItem(ActiveTestSuite.class));
		assertThat("Esperamos encontrar esta clase",clases,hasItem(RepeatedTest.class));
		assertThat("Esperamos encontrar esta clase",clases,hasItem(TestDecorator.class));
		assertThat("Esperamos encontrar esta clase",clases,hasItem(TestSetup.class));
	}
}
