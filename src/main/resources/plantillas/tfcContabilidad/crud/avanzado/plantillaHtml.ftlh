<div [hidden]="dbpDialogoRef!=null" class="page-header">
  <h1>${entidad.entidad}</h1>
</div>
<p class="lead" [hidden]="dbpDialogoRef!=null" >Esta la operación del ${entidad.entidad}.</p>
<form (ngSubmit)="onSubmit()" #formularioForm="ngForm">
<#assign cerrado = "SI">
<#list entidad.campos as campo>
	<#if (campo?counter % 2) == 1>
	<div [hidden]="estado!=eFiltro" class="row">
	</#if>
		<div  class="col-md-6 form-group">
			<label>${campo.campo}</label>
			<input type="text" class="form-control" [(ngModel)]="filtro.${campo.campo}"/>
		</div>
    <#if (campo?counter % 2) ==0 >
	</div>
  	<#assign cerrado = "SI">
  	<#else>
  	<#assign cerrado = "NO">
	</#if>
</#list>  
	<#if cerrado =="NO" >
	</div>
	<#assign cerrado = "SI">
  	</#if>
<#list entidad.campos as campo>
	<#if (campo?counter % 2) == 1>
	<div [hidden]="estado!=eCrear && estado!=eModificar" class="row">
	</#if>
		<div  class="col-md-6 form-group">
			<label>${campo.campo}</label>
			<#if campo.campo == campoId?uncap_first >
			<span>{{modelo.${campo.campo}}}</span>
			<#else>
			<input type="text" class="form-control" [(ngModel)]="modelo.${campo.campo}"/>
			</#if>
		</div>
    <#if (campo?counter % 2) ==0 >
	</div>
  	<#assign cerrado = "SI">
  	<#else>
  	<#assign cerrado = "NO">
	</#if>
</#list>  
	<#if cerrado =="NO" >
		</div>
  	</#if>
	<div [hidden]="estado!=eFiltro"  class="row">
		<div  class="col-md-6 form-group">
			<br/>
			<button  type="button" class="btn btn-success" (click)="consultar()"> Consultar </button>
			<button  type="button" class="btn btn-success" (click)="crear()"> Crear </button>
		</div>
	</div>
	<div [hidden]="!(estado==eCrear && estado!=eFiltro && estado!=eModificar)"   class="row">
		<div  class="col-md-6 form-group">
			<br/>
			<button  type="button" class="btn btn-success" (click)="guardar()"> guardar </button>
			<button  type="button" class="btn btn-info" (click)="cancelar()"> cancelar </button>
		</div>
	</div>
	<div [hidden]="!(estado!=eCrear && estado!=eFiltro && estado==eModificar)"   class="row">
		<div  class="col-md-6 form-group">
			<br/>
			<button  type="button" class="btn btn-success" (click)="modificar()"> guardar </button>
			<button  type="button" class="btn btn-danger" (click)="eliminarOp(modelo.id)"> eliminar </button>
			<button  type="button" class="btn btn-info" (click)="cancelar()"> cancelar </button>
		</div>
	</div>
	<dbp-grid [columnas]="columnas" [filas]="lineas"
		(eliminar)="eliminar($event)"
		(seleccionar)="seleccionar($event)">
	</dbp-grid>
</form>
