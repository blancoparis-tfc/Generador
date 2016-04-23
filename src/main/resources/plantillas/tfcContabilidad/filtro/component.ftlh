import {Component,ElementRef} from 'angular2/core';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {${entidad.entidad},${entidad.entidad}Filtro} from '../../../model/localizacion/${entidad.entidad}';
import {${entidad.entidad}Service} from '../../../service${subPaquete?replace(".","/")}/${entidad.entidad}Service';

@Component({
  selector:'${entidad.entidad}',
  templateUrl:'app/component/pantallas${subPaquete?replace(".","/")}/${entidad.entidad}.component.html',
  directives:[Grid,AutoFocus]
})
export class ${entidad.entidad}Component{
    modelo:${entidad.entidad}Filtro;
    lineas:Array<${entidad.entidad}>;
    columnas:Array<Columna>;
    constructor(
      private elemento:ElementRef
      ,private dialogo:DbpDialogo
      ,private ${entidad.entidad}Service:${entidad.entidad}Service
    ){
    
      this.modelo = new ${entidad.entidad}Filtro(<#list entidad.campos as campo>""<#sep>,</#list>);
      this.lineas=[];
      this.columnas=this.getColumnas();
    }

    consultar(){
      this.${entidad.entidad}Service.filtrar(this.modelo,this.elemento)
        .subscribe(res=>{
        this.lineas=res.json();
      });
    }

    private getColumnas():Array<Columna>{
      return [
        <#list entidad.campos as campo>
			new Columna('${campo.campo}','${campo.campo}',TIPO_NO_EDITABLE)<#sep>,
		</#list>  
        
      ];
    }
}