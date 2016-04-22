package ${paqueteBase}.service.impl;

import ${paqueteBase}.bom${subPaquete}.${entidad.entidad};
import ${paqueteBase}.core.service.impl.GenericServiceImpl;
import ${paqueteBase}.dao.${entidad.entidad}Dao;
import ${paqueteBase}.service.${entidad.entidad}Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${entidad.entidad}ServiceImpl extends GenericServiceImpl<${entidad.entidad},${classId}> implements ${entidad.entidad}Service {

		
	@Autowired
	public ${entidad.entidad}ServiceImpl(final ${entidad.entidad}Dao dao) {
		super(dao);
	}

}