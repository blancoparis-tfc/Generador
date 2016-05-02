import {Component,ViewContainerRef} from 'angular2/core';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';
import {OperacionesUtils,Estado} from '../../../core/utils/components/operacionesUtil';

import {${entidad.entidad},${entidad.entidad}Filtro} from '../../../model/localizacion/${entidad.entidad}';
import {${entidad.entidad}Service} from '../../../service${subPaquete?replace(".","/")}/${entidad.entidad}Service';

@Component({
  selector:'${entidad.entidad}',
  templateUrl:'app/component/pantallas${subPaquete?replace(".","/")}/${entidad.entidad}.component.html',
  providers:[${entidad.entidad}Service],
  directives:[Grid,AutoFocus]
})
export class ${entidad.entidad}Component{
    filtro:${entidad.entidad}Filtro;
    modelo:${entidad.entidad};
    lineas:Array<${entidad.entidad}>;
    columnas:Array<Columna>;
    estado:Estado;
    eFiltro:Estado=Estado.FILTRO;
    eCrear:Estado=Estado.CREAR;
    eModificar:Estado=Estado.MODIFICAR;
    operaciones:OperacionesUtils<${entidad.entidad},${classIdTs}>;
    constructor(
       private elemento:ViewContainerRef
      ,private dialogo:DbpDialogo
      ,private ${entidad.entidad?uncap_first}Service:${entidad.entidad}Service
      ,private mensajeria:Mensajeria
      ,private dbpDialogoRef:DbpDialogoRef
    ){
      this.filtro = new ${entidad.entidad}Filtro(<#list entidad.campos as campo>""<#sep>,</#list>);
      this.modelo = new ${entidad.entidad}(<#list entidad.campos as campo>null<#sep>,</#list>);
      this.lineas=[];
      this.columnas=this.getColumnas();
      this.transitarFiltro();
      this.operaciones = new OperacionesUtils<${entidad.entidad},${classIdTs}>(dialogo,${entidad.entidad?uncap_first}Service,elemento);
    }

    consultar(){
      this.${entidad.entidad?uncap_first}Service.filtrar(this.filtro,this.elemento)
        .subscribe(res=>{
        this.lineas=res.json();
      });
    }

    crear(){ 
		this.transitarCrear();
		this.modelo = new Direccion(null,null);		  
	}

	cancelar(){
		this.transitarFiltro();
	}

    guardar(){
      this.operaciones.crear(
        new DbpDialogoConfirmarConf('¿Quiere crear una nueva ${entidad.entidad?uncap_first}?','${entidad.entidad}'),this.modelo
        ,(data)=>{
          this.mensajeria.success(this.elemento,'Se ha creado la ${entidad.entidad?uncap_first} ('+data.${campoId?uncap_first}+') correctamente.');
          this.lineas.push(data);
        });
    }

   modificar(){
      this.operaciones.actualizar(
        new DbpDialogoConfirmarConf('¿Quiere actualizar la ${entidad.entidad?uncap_first}?','${entidad.entidad}'),this.modelo
        ,(data)=>{
          this.mensajeria.success(this.elemento,'Se actualizado la ${entidad.entidad?uncap_first} ('+data.${campoId?uncap_first}+') correctamente.');
          this.transitarFiltro();
        });
    }
    
	seleccionar(fila:any){
		this.modelo=fila;
		this.transitarModificar();
	}

    eliminarOp(id:number){
        var idx = this.lineas.indexOf(this.modelo);
		this.operaciones.eliminar(
		      new DbpDialogoConfirmarConf('¿Ser va eliminar el ${entidad.entidad?uncap_first} '+${campoId?uncap_first}+'?','${entidad.entidad}'),id
		      ,(data)=>{
		        this.mensajeria.success(this.elemento,'Se ha eliminado el ${entidad.entidad?uncap_first} ('+${campoId?uncap_first}+') correctamente.');
		        this.transitarFiltro();
		        this.lineas.splice(idx,1);
		});
	}

	eliminar(elemento:any){
	    var idx = this.lineas.indexOf(elemento);
	    if(idx != -1){
	        this.dialogo.confirmar(this.elemento,new DbpDialogoConfirmarConf('¿Quieres eliminar la ${entidad.entidad?uncap_first} ('+elemento.id+')?','${entidad.entidad}')).then(dialogoComponent=>{
	            dialogoComponent.instance.cuandoOk.then((_)=>{
	                this.${entidad.entidad?uncap_first}Service.eliminar(elemento.${campoId?uncap_first},this.elemento).subscribe(res=>{
	                    this.lineas.splice(idx,1);
	                });
	            });
	          });
      }
	}

	private transitarFiltro(){ this.estado=this.eFiltro; }
	private transitarCrear(){ this.estado=this.eCrear; }
	private transitarModificar(){ this.estado=this.eModificar; }

    private getColumnas():Array<Columna>{
      return [
        <#list entidad.campos as campo>
			new Columna('${campo.campo}','${campo.campo}',TIPO_NO_EDITABLE)<#sep>,
		</#list>  
        
      ];
    }
}