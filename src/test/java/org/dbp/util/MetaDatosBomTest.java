package org.dbp.util;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.dbp.bean.Campo;
import org.dbp.bean.MetaDatos;
import org.junit.Test;

public class MetaDatosBomTest {

	@Test
	public void test001NombreDeLaClase(){
		MetaDatos metaDatos = MetaDatosBom.instancia().obtenerMetadatos(MetaDatos.class);
		assertEquals("La entidad","MetaDatos",metaDatos.getEntidad());
	}
	@Test
	public void test002ValidarLosCamposDeMetaDatos(){
		MetaDatos metaDatos = MetaDatosBom.instancia().obtenerMetadatos(MetaDatos.class);
		assertEquals("La entidad","MetaDatos",metaDatos.getEntidad());
		assertThat(metaDatos.getCampos(), contains(new Campo("entidad",String.class)));
	}
	
	@Test
	public void test003ValidarLosCamposDeCampo(){
		MetaDatos metaDatos = MetaDatosBom.instancia().obtenerMetadatos(Campo.class);
		assertEquals("La entidad","Campo",metaDatos.getEntidad());
		assertThat(metaDatos.getCampos(), contains(new Campo("campo",String.class),new Campo("tipo",Class.class)));
		
	}
}
