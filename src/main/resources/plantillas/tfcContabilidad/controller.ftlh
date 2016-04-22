package ${paqueteBase}.controller;

import ${paqueteBase}.bom${subPaquete}.${entidad.entidad};
import ${paqueteBase}.core.controller.GenericRestController;
import ${paqueteBase}.service.${entidad.entidad}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url}")
public class ${entidad.entidad}Controller extends GenericRestController<${entidad.entidad},${classId}> {

	@Autowired
	public ${entidad.entidad}Controller(final ${entidad.entidad}Service service) {
		super(service, entidad->entidad.get${campoId}());
	}

}
