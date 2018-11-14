package pe.edu.upc.tp.auditoria.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcesoBean {
	
	private int procesoId;
	private String nombre;
	private boolean seleccionado;
	private String prioridad;
	private int duracion;
	
	public int getProcesoId() {
		return procesoId;
	}
	public void setProcesoId(int procesoId) {
		this.procesoId = procesoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombred(String nombre) {
		this.nombre = nombre;
	}
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	
}
