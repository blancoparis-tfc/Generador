<form (ngSubmit)="onSubmit()" #formularioForm="ngForm">
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
			<button type="button" class="btn btn-success" (click)="consultar()"> Consultar </button>
		</div>
	</div>
	<dbp-grid [columnas]="columnas" [filas]="lineas"></dbp-grid>
</form>
