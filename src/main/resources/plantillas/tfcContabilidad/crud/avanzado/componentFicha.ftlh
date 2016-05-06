import {Component,ViewContainerRef} from '@angular/core';

import {Router,RouteParams} from '@angular/router-deprecated';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';
import {DbpDialogo,DbpDialogoBaseConf,DbpDialogoConfirmarConf} from '../../../core/modal/dialogo';
import {OperacionesUtils,Estado} from '../../../core/utils/components/operacionesUtil';
import {${entidad.entidad}} from '../../../model${subPaquete?replace(".","/")}/${entidad.entidad}';
import {${entidad.entidad}Service} from '../../../service${subPaquete?replace(".","/")}/${entidad.entidad}Service';

@Component({
  selector:'${entidad.entidad}Ficha',
  templateUrl:'app/component/pantallas${subPaquete?replace(".","/")}/${entidad.entidad}Ficha.component.html',
  providers:[${entidad.entidad}Service]
})
export class ${entidad.entidad}FichaComponent{

	modelo:${entidad.entidad};
	estado:Estado;
	etiquetaEstado:string;
	operaciones:OperacionesUtils<${entidad.entidad},${classIdTs}>;
	
	constructor(
		private ${entidad.entidad?uncap_first}Service:${entidad.entidad}Service,
		private viewContainerRef:ViewContainerRef,
		private dialogo:DbpDialogo,
		private mensajeria:Mensajeria,
		params:RouteParams,
		private router:Router
	){
		var identificador:string=params.get("${campoId?uncap_first}");
	 	this.transitarCrear();
		if(identificador!=null){
			this.${entidad.entidad?uncap_first}Service.obtenerId(Number(identificador),this.viewContainerRef).subscribe(data=>{
			this.modelo=data;
			this.transitarModificar();
			})
		}
		this.operaciones = new OperacionesUtils<${entidad.entidad},${classIdTs}>(dialogo,${entidad.entidad?uncap_first}Service,viewContainerRef);
	  }
	  
	onSubmit(){
		console.info('Moddelo',this.modelo);
		switch(this.estado){
			case Estado.CREAR:
				this.crear();
			break;
			case Estado.MODIFICAR:
				this.modificar();
			break;
		}
	}
	
	crear(){
	    this.operaciones.crear(
	      new DbpDialogoConfirmarConf('¿Quiere crear una nueva ${entidad.entidad?uncap_first}?','${entidad.entidad?uncap_first}'),this.modelo
	      ,(data)=>{
	        console.info('Crear',data);
	        this.mensajeria.success(this.viewContainerRef,'Se actualizado la ${entidad.entidad?uncap_first} ('+data.${campoId?uncap_first}+') correctamente.');
	        this.transitarModificar();
	      });
	}
	
	modificar(){
		this.operaciones.actualizar(
			new DbpDialogoConfirmarConf('¿Quiere actualizar el ${entidad.entidad?uncap_first}?','${entidad.entidad?uncap_first}'),this.modelo
			,(data)=>{
				this.mensajeria.success(this.viewContainerRef,'Se actualizado el ${entidad.entidad?uncap_first} ('+data.${campoId?uncap_first}+') correctamente.');
		});
	}
	
	eliminar(id:${classIdTs}){
		this.operaciones.eliminar(
			new DbpDialogoConfirmarConf('¿Ser va eliminar el ${entidad.entidad?uncap_first} '+id+'?','${entidad.entidad?uncap_first}'),id
			,(data)=>{
				this.mensajeria.success(this.viewContainerRef,'Se ha eliminado el ${entidad.entidad?uncap_first} ('+id+') correctamente.');
				this.transitarCrear();
		});
	}

	isModificar():boolean{
		return this.estado == Estado.MODIFICAR;
	}
	
	volver(){
		this.router.navigate(['/${entidad.entidad}']);
	}	
	transitarModificar(){
		this.estado = Estado.MODIFICAR;
		this.etiquetaEstado= 'Modifcar';
	}
	
	transitarCrear(){
		this.modelo=this.inicializarModelo();
		this.estado = Estado.CREAR;
		this.etiquetaEstado = 'Crear';
	}
	
	inicializarModelo():${entidad.entidad}{
		return  new ${entidad.entidad}(<#list entidad.campos as campo>null<#sep>,</#list>);
	}	  
}