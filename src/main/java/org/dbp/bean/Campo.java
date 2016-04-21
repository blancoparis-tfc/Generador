package org.dbp.bean;

import java.lang.reflect.Field;

public class Campo {
	private String campo;
	private Class tipo;
	
	public Campo(Field campo){
		this.campo=campo.getName();
		this.tipo=campo.getType();
	}
	
	public Campo(String campo, Class tipo) {
		super();
		this.campo = campo;
		this.tipo = tipo;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		return true;
	}
	
	
}
