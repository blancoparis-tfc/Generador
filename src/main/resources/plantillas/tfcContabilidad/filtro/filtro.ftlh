package ${paqueteBase}.dto${subPaquete};


public class ${entidad.entidad}Filtro {
	<#list entidad.campos as campo>
		private String ${campo.campo};
	</#list>
	
	<#list entidad.campos as campo>
		public String get${campo.campo?cap_first}() {
			return ${campo.campo};
		}
		public void set${campo.campo?cap_first}(String ${campo.campo}) {
			this.${campo.campo} = ${campo.campo};
		}
	</#list>
	
}
