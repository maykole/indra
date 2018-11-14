package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the planauditoriaanual database table.
 * 
 */
@Entity
@Table(name = "tbl_planauditoria_anual")
@NamedQuery(name="PlanauditoriaanualModel.findAll", query="SELECT p FROM PlanauditoriaanualModel p")
public class PlanauditoriaanualModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="tx_creado")
	private String creadoPor;

	@Temporal(TemporalType.DATE)
	@Column(name="fe_creacion")
	private Date fechaCreacion;

	@Column(name="tx_periodo")
	private String periodo;

	@Column(name="actareunion_id")
	private int actareunionId;
	
	@Transient
	private String fechaCreacionDescripcion;

	@Transient
	private String periodoDescripcion;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "planauditoriaanual_id")
	private List<ProgramaModel> programas;
	
	@Transient
	private pe.edu.upc.tp.auditoria.bean.Error error;

	public PlanauditoriaanualModel() {
	}
	
	public PlanauditoriaanualModel(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreadoPor() {
		return this.creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public List<ProgramaModel> getProgramas() {
		return programas;
	}

	public void setProgramas(List<ProgramaModel> programas) {
		this.programas = programas;
	}

	public String getFechaCreacionDescripcion() {
		return fechaCreacionDescripcion;
	}

	public void setFechaCreacionDescripcion(String fechaCreacionDescripcion) {
		this.fechaCreacionDescripcion = fechaCreacionDescripcion;
	}

	public String getPeriodoDescripcion() {
		return periodoDescripcion;
	}

	public void setPeriodoDescripcion(String periodoDescripcion) {
		this.periodoDescripcion = periodoDescripcion;
	}

	public int getActareunionId() {
		return actareunionId;
	}

	public void setActareunionId(int actareunionId) {
		this.actareunionId = actareunionId;
	}

	public pe.edu.upc.tp.auditoria.bean.Error getError() {
		return error;
	}

	public void setError(pe.edu.upc.tp.auditoria.bean.Error error) {
		this.error = error;
	}



	
}