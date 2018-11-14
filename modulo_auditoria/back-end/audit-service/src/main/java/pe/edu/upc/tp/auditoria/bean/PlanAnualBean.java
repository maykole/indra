package pe.edu.upc.tp.auditoria.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanAnualBean {
	
	private ActaReunionBean actareunion;
	private String periodo;
	private List<ProcesoBean> procesos;

	public List<ProcesoBean> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<ProcesoBean> procesos) {
		this.procesos = procesos;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public ActaReunionBean getActareunion() {
		return actareunion;
	}

	public void setActareunion(ActaReunionBean actareunion) {
		this.actareunion = actareunion;
	} 

}
