package org.dbp.bean;

import java.util.List;

public class MetaDatos {

	private String entidad;
	private List<Campo> campos;

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	
}
