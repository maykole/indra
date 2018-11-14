package pe.edu.upc.tp.auditoria.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolicitudRegistroInit {

	private String periodo;
	private ActaReunionBean actareunion;
	private EmpleadoBean solicitante;
	private List<ProcesoBean> procesos;
	private Error error;
	
	public EmpleadoBean getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(EmpleadoBean solicitante) {
		this.solicitante = solicitante;
	}
	public List<ProcesoBean> getProcesos() {
		return procesos;
	}
	public void setProcesos(List<ProcesoBean> procesos) {
		this.procesos = procesos;
	}
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}

	public ActaReunionBean getActareunion() {
		return actareunion;
	}

	public void setActareunion(ActaReunionBean actareunion) {
		this.actareunion = actareunion;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}
