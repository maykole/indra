package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the programa database table.
 * 
 */
@Entity
@Table(name = "tbl_programacion")
@NamedQuery(name="ProgramaModel.findAll", query="SELECT p FROM ProgramaModel p")
public class ProgramaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="planauditoriaanual_id")
	private int planauditoriaanualId;
	
	@OneToOne
	@JoinColumn(name = "proceso_id")
	private ProcesoModel proceso;

	@OneToOne
	@JoinColumn(name = "auditor_id")
	private EmpleadoModel auditor;

	@Column(name="nu_duracion")
	private int duracion;

	@Temporal(TemporalType.DATE)
	@Column(name="fe_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fe_inicio")
	private Date fechaInicio;

	@Column(name="tx_prioridad")
	private String prioridad;

	@Column(name="tx_estado")
	private String estado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fe_inicio_limite")
	private Date fechaInicioLimite;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fe_fin_limite")
	private Date fechaFinLimite;
	


	public ProgramaModel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public int getPlanauditoriaanualId() {
		return planauditoriaanualId;
	}

	public void setPlanauditoriaanualId(int planauditoriaanualId) {
		this.planauditoriaanualId = planauditoriaanualId;
	}

	public ProcesoModel getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoModel proceso) {
		this.proceso = proceso;
	}

	public Date getFechaInicioLimite() {
		return fechaInicioLimite;
	}

	public void setFechaInicioLimite(Date fechaInicioLimite) {
		this.fechaInicioLimite = fechaInicioLimite;
	}

	public Date getFechaFinLimite() {
		return fechaFinLimite;
	}

	public void setFechaFinLimite(Date fechaFinLimite) {
		this.fechaFinLimite = fechaFinLimite;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public EmpleadoModel getAuditor() {
		return auditor;
	}

	public void setAuditor(EmpleadoModel auditor) {
		this.auditor = auditor;
	}
}