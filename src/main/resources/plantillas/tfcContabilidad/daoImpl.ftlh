package ${paqueteBase}.dao.impl;

import ${paqueteBase}.bom${subPaquete}.${entidad.entidad};
import ${paqueteBase}.core.dao.impl.GenericDaoImpl;
import ${paqueteBase}.dao.${entidad.entidad}Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ${entidad.entidad}DaoImpl extends GenericDaoImpl<${entidad.entidad},${classId}> implements ${entidad.entidad}Dao{

	@PersistenceContext private EntityManager entityManager;
	
	public ${entidad.entidad}DaoImpl() {
		super(${entidad.entidad}.class);
	}

}