package org.dbp.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Plantillas implements Serializable{

	private String template;
	private String subPath;
	private String formatNombre;
	private Integer orden;
	private String clasificado;

	
	
	public Plantillas(String template, String subPath, String formatNombre) {
		this(template,subPath,formatNombre,0,"Default");
	}
	
	
	
	public Plantillas(String template, String subPath, String formatNombre,
			Integer orden, String clasificado) {
		super();
		this.template = template;
		this.subPath = subPath;
		this.formatNombre = formatNombre;
		this.orden = orden;
		this.clasificado = clasificado;
	}



	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getSubPath() {
		return subPath;
	}
	public void setSubPath(String subPath) {
		this.subPath = subPath;
	}
	public String getFormatNombre() {
		return formatNombre;
	}
	public void setFormatNombre(String formatNombre) {
		this.formatNombre = formatNombre;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getClasificado() {
		return clasificado;
	}
	public void setClasificado(String clasificado) {
		this.clasificado = clasificado;
	}
	
}
