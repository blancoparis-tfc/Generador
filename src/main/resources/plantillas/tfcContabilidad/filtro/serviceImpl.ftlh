package ${paqueteBase}.service.impl;

import java.util.List;

import ${paqueteBase}.bom${subPaquete}.${entidad.entidad};
import ${paqueteBase}.core.service.impl.GenericServiceImpl;
import ${paqueteBase}.dao.${entidad.entidad}Dao;
import ${paqueteBase}.service.${entidad.entidad}Service;
import ${paqueteBase}.dto${subPaquete}.${entidad.entidad}Filtro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${entidad.entidad}ServiceImpl extends GenericServiceImpl<${entidad.entidad},${classId}> implements ${entidad.entidad}Service {

	private ${entidad.entidad}Dao dao;
		
	@Autowired
	public ${entidad.entidad}ServiceImpl(final ${entidad.entidad}Dao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<${entidad.entidad}> consultarFiltro(final ${entidad.entidad}Filtro filtro){
		return dao.consultarFiltro(filtro);
	}
}