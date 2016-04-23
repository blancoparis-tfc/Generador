package org.dbp.bean;

import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;

public class Campo {
	private String campo;
	private Class tipo;
	private String tipoTs;
	
	public Campo(Field campo){
		this(campo.getName(),campo.getType());
	}
	

	
	public Campo(String campo, Class tipo) {
		this(campo,tipo,TipoTs.getTipo(tipo).name());
	}

	public Campo(String campo, Class tipo, String tipoTs) {
		super();
		this.campo = campo;
		this.tipo = tipo;
		this.tipoTs = tipoTs;
	}

	private enum TipoTs{
		string(String.class),
		number(Long.class,Integer.class,Double.class,Float.class),
		any
		;
		private Class[] clases;

		private TipoTs(Class ...clases) {
			this.clases = clases;
		}
		
		public static TipoTs getTipo(Class clase){
			for(TipoTs tipo:TipoTs.values()){
				if(ArrayUtils.contains(tipo.clases,clase)){
					return tipo;
				}
			}
			return TipoTs.any;
		}
	}
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public Class getTipo() {
		return tipo;
	}
	public void setTipo(Class tipo) {
		this.tipo = tipo;
	}
	
	public String getTipoTs() {
		return tipoTs;
	}

	public void setTipoTs(String tipoTs) {
		this.tipoTs = tipoTs;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((tipoTs == null) ? 0 : tipoTs.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campo other = (Campo) obj;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (tipoTs == null) {
			if (other.tipoTs != null)
				return false;
		} else if (!tipoTs.equals(other.tipoTs))
			return false;
		return true;
	}


	
	
}
