package ${paqueteBase}.dao.impl;

import java.util.List;

import ${paqueteBase}.bom${subPaquete}.${entidad.entidad};
import ${paqueteBase}.core.dao.impl.GenericDaoImpl;
import ${paqueteBase}.dao.${entidad.entidad}Dao;
import ${paqueteBase}.dto${subPaquete}.${entidad.entidad}Filtro;
import ${paqueteBase}.utils.JqplFilterUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class ${entidad.entidad}DaoImpl extends GenericDaoImpl<${entidad.entidad},${classId}> implements ${entidad.entidad}Dao{

	@PersistenceContext private EntityManager entityManager;
	
	public ${entidad.entidad}DaoImpl() {
		super(${entidad.entidad}.class);
	}
	@Override
	public List<${entidad.entidad}> consultarFiltro(final ${entidad.entidad}Filtro filtro){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<${entidad.entidad}> query = builder.createQuery(${entidad.entidad}.class);
		Root<${entidad.entidad}> f${entidad.entidad} = query.from(${entidad.entidad}.class);
		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
		<#list entidad.campos as campo>
			.addParametro(${campo.tipo.simpleName}.class, "${campo.campo}", f${entidad.entidad}, filtro.get${campo.campo?cap_first}())
		</#list>
		;
		query.where(jqplFilterUtils.crearLosPredicados());
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

}