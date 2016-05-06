<form (ngSubmit)="onSubmit()" #formularioForm="ngForm">
<div  class="page-header">
  <h1>${entidad.entidad}</h1>
</div>
<p class="lead" >Esta la operación del ${entidad.entidad}.</p>
<#assign cerrado = "SI">
<#list entidad.campos as campo>
	<#if (campo?counter % 2) == 1>
	<div class="row">
	</#if>
		<div  class="col-md-6 form-group">
			<label>${campo.campo}</label>
			<input type="text" class="form-control" [(ngModel)]="modelo.${campo.campo}"/>
		</div>
    <#if (campo?counter % 2) ==0 >
	</div>
  	<#assign cerrado = "SI">
  	<#else>
  	<#assign cerrado = "NO">
	</#if>
</#list>  
	<#if cerrado =="SI" >
	<div class="row">
  	</#if>
		<div  class="col-md-6 form-group">
			<br/>
				<button type="submit" class="btn btn-success">{{etiquetaEstado}}</button>
				<button *ngIf="isModificar()" type="button" class="btn btn-danger" (click)="eliminar(modelo.${campoId?uncap_first})"> eliminar </button>
				<button type="button" class="btn btn-info" (click)="volver()">volver</button>
		</div>
	</div>
</form>