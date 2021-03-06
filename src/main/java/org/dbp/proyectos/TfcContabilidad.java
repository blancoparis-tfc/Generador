package org.dbp.proyectos;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import org.dbp.bean.Campo.TipoTs;
import org.dbp.bom.persona.PersonaFisica;
import org.dbp.util.MetaDatosBom;
import org.dbp.util.PlantillasUtil;
import org.dbp.util.TemplateUtils;
//import org.dbp.bom.localizacion.PaisV2;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class TfcContabilidad {

	
	private static final String PLANTILLAS_TFC_CONTABILIDAD = "plantillas/tfcContabilidad";
	private static final String PLANTILLAS_TFC_CONTABILIDAD_FILTRO = "plantillas/tfcContabilidad/filtro";
	private static final String PLANTILLAS_TFC_CONTABILIDAD_CRUD = "plantillas/tfcContabilidad/crud";
	private static final String PLANTILLAS_TFC_CONTABILIDAD_CRUD_AVANZADO = "plantillas/tfcContabilidad/crud/avanzado";
	private final PlantillasUtil plantillasUtil;

	public static void main(String[] args) throws IOException, TemplateException {
		TfcContabilidad tfcContabilidad= TfcContabilidad.instancia("C:\\Users\\david\\Documents\\GitHub\\tfcContabilidad\\",PLANTILLAS_TFC_CONTABILIDAD_CRUD_AVANZADO);
		//tfcContabilidad.eliminarPlantillas(DatosDeContacto.class, Long.class, ".localizacion","localizacion/datosDeContacto","Id");
		tfcContabilidad.eliminarPlantillas(PersonaFisica.class, Long.class, ".persona","persona/personaFisica","Id");
		tfcContabilidad.generarPlantilla(PersonaFisica.class, Long.class, ".persona","persona/personaFisica","Id");
		
	}
	
	public static final TfcContabilidad instancia(final String destino,String plantillas) throws IOException{
		return new TfcContabilidad(destino,plantillas);
	}
	
	public TfcContabilidad(final String destino,String plantillas) throws IOException {
		super();
		URL url=Thread.currentThread().getContextClassLoader().getResource(plantillas);
		Properties propiedades = new Properties();
		propiedades.setProperty(TemplateUtils.DIRECTORIO_PLANTILLAS, url.getFile());
		TemplateUtils templateUtils = TemplateUtils.instancia(propiedades);
		this.plantillasUtil =PlantillasUtil.instancia(templateUtils, Paths.get(destino))
				.add("dao.ftlh","/service/src/main/java/org/dbp/dao/","%sDao.java")
				.add("daoImpl.ftlh","/service/src/main/java/org/dbp/dao/impl/","%sDaoImpl.java")
				.add("filtro.ftlh","/service/src/main/java/org/dbp/dto/${subPaquete}","%sFiltro.java")
				.add("daoTest.ftlh","/service/src/test/java/org/dbp/dao/","%sDaoTest.java")
				.add("service.ftlh","/service/src/main/java/org/dbp/service/","%sService.java")
				.add("serviceImpl.ftlh","/service/src/main/java/org/dbp/service/impl/","%sServiceImpl.java")
				.add("controller.ftlh","/webapp/src/main/java/org/dbp/controller/${subPaquete}","%sController.java")
				// Angular 2
				.add("model.ftlh","/webapp/src/main/webapp/WEB-INF/cliente/app/model/${subPaquete}","%s.ts")
				.add("servicets.ftlh","/webapp/src/main/webapp/WEB-INF/cliente/app/service/${subPaquete}","%sService.ts")
				.add("plantillaHtml.ftlh","/webapp/src/main/webapp/WEB-INF/cliente/app/component/pantallas/${subPaquete}","%s.component.html")
				.add("component.ftlh","/webapp/src/main/webapp/WEB-INF/cliente/app/component/pantallas/${subPaquete}","%s.component.ts")
				.add("componentFicha.ftlh","/webapp/src/main/webapp/WEB-INF/cliente/app/component/pantallas/${subPaquete}","%sFicha.component.ts")
				.add("plantillaFichaHtml.ftlh","/webapp/src/main/webapp/WEB-INF/cliente/app/component/pantallas/${subPaquete}","%sFicha.component.html")

				
				.parametro("paqueteBase", "org.dbp")
				;
	}

	public void generarPlantilla(final Class idEntidad,final Class idClass,final String subPath
			,final String url,final String campoId) 
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		cargarElContexto(idClass, subPath, url, campoId);
		this.plantillasUtil.aplicarLasPlantillas(MetaDatosBom.instancia().obtenerMetadatos(idEntidad));
	}


	public void eliminarPlantillas(final Class idEntidad,final Class idClass,final String subPath
			,final String url,final String campoId) throws IOException{
		cargarElContexto(idClass, subPath, url, campoId);
		this.plantillasUtil.eliminarLosGenerados(MetaDatosBom.instancia().obtenerMetadatos(idEntidad));
	}
	
	private void cargarElContexto(final Class idClass, final String subPath,
			final String url, final String campoId) {
		this.plantillasUtil.parametro("classId", idClass.getSimpleName());
		this.plantillasUtil.parametro("subPaquete", subPath);

		this.plantillasUtil.parametro("url", url);
		this.plantillasUtil.parametro("campoId", campoId);
		this.plantillasUtil.parametro("classIdTs", TipoTs.getTipo(idClass).name());
		
	}
	
}
