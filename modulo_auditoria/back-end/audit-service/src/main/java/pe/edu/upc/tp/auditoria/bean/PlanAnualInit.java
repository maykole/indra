package pe.edu.upc.tp.auditoria.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanAnualInit {

    private String periodo;
    private String periodoCompleto;
    private String fechaCreacion;
    private String creadoPor;
    private ActaReunionBean actareunion;
    private List<ProcesoBean> procesos;
    private Error error;

    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getCreadoPor() {
        return creadoPor;
    }
    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
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
	public String getPeriodoCompleto() {
		return periodoCompleto;
	}
	public void setPeriodoCompleto(String periodoCompleto) {
		this.periodoCompleto = periodoCompleto;
	}
	
}
