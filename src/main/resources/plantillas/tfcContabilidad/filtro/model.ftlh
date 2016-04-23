export class ${entidad.entidad}{

  constructor(
  	<#list entidad.campos as campo>
  	  public  ${campo.campo}: ${campo.tipoTs}<#sep>,
	</#list>  
  ){}
}

export class ${entidad.entidad}Filtro{
  constructor(
   	<#list entidad.campos as campo>
  	  public  ${campo.campo}: string<#sep>,
	</#list>  
  ){}
}