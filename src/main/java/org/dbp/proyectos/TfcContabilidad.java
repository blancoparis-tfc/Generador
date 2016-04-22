package org.dbp.proyectos;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import org.dbp.util.MetaDatosBom;
import org.dbp.util.PlantillasUtil;
import org.dbp.util.TemplateUtils;

//import org.dbp.bom.localizacion.PaisV2;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class TfcContabilidad {

	
	private final PlantillasUtil plantillasUtil;

	/*public static void main(String[] args) throws IOException, TemplateException {
		TfcContabilidad tfcContabilidad= TfcContabilidad.instancia("C:\\Users\\david\\Documents\\GitHub\\tfcContabilidad\\");
		tfcContabilidad.generarPlantilla(PaisV2.class, String.class, ".localizacion","localizacion/paisv2","IdAlfa2");
	}*/
	
	public static final TfcContabilidad instancia(final String destino) throws IOException{
		return new TfcContabilidad(destino);
	}
	
	public TfcContabilidad(final String destino) throws IOException {
		super();
		URL url=Thread.currentThread().getContextClassLoader().getResource("plantillas/tfcContabilidad");
		Properties propiedades = new Properties();
		propiedades.setProperty(TemplateUtils.DIRECTORIO_PLANTILLAS, url.getFile());
		TemplateUtils templateUtils = TemplateUtils.instancia(propiedades);
		this.plantillasUtil =PlantillasUtil.instancia(templateUtils, Paths.get(destino))
				.add("dao.ftlh","/service/src/main/java/org/dbp/dao/","%sDao.java")
				.add("daoImpl.ftlh","/service/src/main/java/org/dbp/dao/impl/","%sDaoImpl.java")
				.add("daoTest.ftlh","/service/src/test/java/org/dbp/dao/","%sDaoTest.java")
				.add("service.ftlh","/service/src/main/java/org/dbp/service/","%sService.java")
				.add("serviceImpl.ftlh","/service/src/main/java/org/dbp/service/impl/","%sServiceImpl.java")
				.add("controller.ftlh","/webapp/src/main/java/org/dbp/controller/","%sController.java")
				.parametro("paqueteBase", "org.dbp")
				;
	}

	public void generarPlantilla(final Class idEntidad,final Class idClass,final String subPath
			,final String url,final String campoId) 
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		this.plantillasUtil.parametro("classId", idClass.getSimpleName());
		this.plantillasUtil.parametro("subPaquete", subPath);

		this.plantillasUtil.parametro("url", url);
		this.plantillasUtil.parametro("campoId", campoId);
		
		this.plantillasUtil.aplicarLasPlantillas(MetaDatosBom.instancia().obtenerMetadatos(idEntidad));
	}
	
}
