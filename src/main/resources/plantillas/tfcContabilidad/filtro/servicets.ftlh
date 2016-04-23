import {Injectable,ElementRef} from 'angular2/core';
import {Http,Response,URLSearchParams} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';
import {${entidad.entidad},${entidad.entidad}Filtro} from '../../model${subPaquete?replace(".","/")}/${entidad.entidad}';

@Injectable()
export class ${entidad.entidad}Service implements IGenericService<${entidad.entidad},string>{

  private url:string='${url}';
  private genericService:GenericService<${entidad.entidad},string>;

  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<${entidad.entidad},string>(http,this.url,mensajeria);
  }

  public crear(${entidad.entidad?uncap_first}:${entidad.entidad},elemento:ElementRef):Observable<Response>{
      return this.genericService.crear(${entidad.entidad?uncap_first},elemento);
  }

  public actualizar(${entidad.entidad?uncap_first}:${entidad.entidad},elemento:ElementRef):Observable<Response>{
      return this.genericService.actualizar(${entidad.entidad?uncap_first},elemento);
  }

  public actualizarLista(${entidad.entidad?uncap_first}:Array<${entidad.entidad}>,elemento:ElementRef):Observable<Response>{
      return this.genericService.actualizarLista(${entidad.entidad?uncap_first},elemento);
  }

  public eliminar(id:string,elemento:ElementRef):Observable<Response>{
      return this.genericService.eliminar(id,elemento);
  }

  public obtenerId(id:string,elemento:ElementRef):Observable<Response>{
    return this.genericService.obtenerId(id,elemento);
  }

  public obtenerTodos(elemento:ElementRef):Observable<Response>{
      return this.genericService.obtenerTodos(elemento);
  }

  public filtrar(filtro:${entidad.entidad}Filtro, elemento:ElementRef):Observable<Response>{
      var search:URLSearchParams=new URLSearchParams();
      <#list entidad.campos as campo>
  	  search.append('${campo.campo}',filtro.${campo.campo});
	  </#list>
      return this.http
          .get(this.url+"/filtro",{search:search})
          .catch((error)=>{return this.genericService.handleError(error,elemento);});
  }

}