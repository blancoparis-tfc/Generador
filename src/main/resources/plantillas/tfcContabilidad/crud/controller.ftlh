package ${paqueteBase}.controller${subPaquete};

import java.util.List;

import ${paqueteBase}.bom${subPaquete}.${entidad.entidad};
import ${paqueteBase}.core.controller.GenericRestController;
import ${paqueteBase}.service.${entidad.entidad}Service;
import ${paqueteBase}.dto${subPaquete}.${entidad.entidad}Filtro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url}")
public class ${entidad.entidad}Controller extends GenericRestController<${entidad.entidad},${classId}> {

	private ${entidad.entidad}Service service;

	@Autowired
	public ${entidad.entidad}Controller(final ${entidad.entidad}Service service) {
		super(service, entidad->entidad.get${campoId}());
		this.service = service;
	}

	@RequestMapping(value="/filtro",method=RequestMethod.GET)
	public List<${entidad.entidad}> consultarFiltro(final ${entidad.entidad}Filtro filtro){
		return service.consultarFiltro(filtro);
	}
}
