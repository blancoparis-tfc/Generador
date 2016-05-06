package ${paqueteBase}.dao;

import java.util.List;

import ${paqueteBase}.bom${subPaquete}.${entidad.entidad};
import ${paqueteBase}.core.dao.GenericDao;
import ${paqueteBase}.dto${subPaquete}.${entidad.entidad}Filtro;


public interface ${entidad.entidad}Dao extends GenericDao<${entidad.entidad},${classId}>{

	public abstract List<${entidad.entidad}> consultarFiltro(final ${entidad.entidad}Filtro filtro);

}