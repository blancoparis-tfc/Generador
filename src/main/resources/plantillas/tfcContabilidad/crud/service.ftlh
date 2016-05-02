package ${paqueteBase}.service;

import java.util.List;

import ${paqueteBase}.bom${subPaquete}.${entidad.entidad};
import ${paqueteBase}.core.service.GenericService;
import ${paqueteBase}.dto${subPaquete}.${entidad.entidad}Filtro;

public interface ${entidad.entidad}Service extends GenericService<${entidad.entidad},${classId}> {

	public abstract List<${entidad.entidad}> consultarFiltro(final ${entidad.entidad}Filtro filtro);

}
