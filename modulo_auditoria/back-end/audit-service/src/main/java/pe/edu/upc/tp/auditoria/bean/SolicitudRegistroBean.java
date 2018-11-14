package pe.edu.upc.tp.auditoria.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolicitudRegistroBean {
	
	private int id;
	private String asunto;
	private String motivo;
	private String prioridad;
	private int estadoid;
	private String estado;
	private String periodo;
	private Date fecharegistro;
	private Date fechaevaluacion;
	private ProcesoBean proceso;
	private ActaReunionBean actareunion;
	private EmpleadoBean solicitante;
	private EmpleadoBean evaluador;
	private Error error;
	private boolean bevaluar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public int getEstadoid() {
		return estadoid;
	}
	public void setEstadoid(int estadoid) {
		this.estadoid = estadoid;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	public Date getFechaevaluacion() {
		return fechaevaluacion;
	}
	public void setFechaevaluacion(Date fechaevaluacion) {
		this.fechaevaluacion = fechaevaluacion;
	}
	public ProcesoBean getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoBean proceso) {
		this.proceso = proceso;
	}
	public EmpleadoBean getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(EmpleadoBean solicitante) {
		this.solicitante = solicitante;
	}
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}
	public EmpleadoBean getEvaluador() {
		return evaluador;
	}
	public void setEvaluador(EmpleadoBean evaluador) {
		this.evaluador = evaluador;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public ActaReunionBean getActareunion() {
		return actareunion;
	}

	public void setActareunion(ActaReunionBean actareunion) {
		this.actareunion = actareunion;
	}

	public boolean isBevaluar() {
		return bevaluar;
	}

	public void setBevaluar(boolean bevaluar) {
		this.bevaluar = bevaluar;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	

}
