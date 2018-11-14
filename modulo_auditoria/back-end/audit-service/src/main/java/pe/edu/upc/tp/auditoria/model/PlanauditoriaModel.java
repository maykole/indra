package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the planauditoria database table.
 * 
 */
@Entity
@Table(name="tbl_planauditoria")
@NamedQuery(name="Planauditoria.findAll", query="SELECT p FROM PlanauditoriaModel p")
public class PlanauditoriaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="tx_alcance")
	private String alcance;

	@Column(name="tx_descripcion")
	private String descripcion;

	@Column(name="tx_estado")
	private String estado;

	@Column(name="nu_duracion")
	private int duracion;

	@Column(name="tx_observacion")
	private String observacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fe_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fe_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fe_inicio")
	private Date fechaInicio;

	@Column(name="tx_objetivo")
	private String objetivo;

	@Column(name="planauditoriaanual_id")
	private int planauditoriaanualId;
	
	@Column(name = "fl_resultado")
	private boolean resultado;

	@OneToOne
	@JoinColumn(name = "proceso_id")
	private ProcesoModel proceso;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "planauditoria_id")
	private List<PlanauditoriaAuditorModel> planauditoriaAuditores;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "planauditoria_id")
	private List<PlanprocedimientoModel> planprocedimientos;

	public PlanauditoriaModel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlcance() {
		return this.alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public int getPlanauditoriaanualId() {
		return this.planauditoriaanualId;
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

	public List<PlanauditoriaAuditorModel> getPlanauditoriaAuditores() {
		return planauditoriaAuditores;
	}

	public void setPlanauditoriaAuditores(List<PlanauditoriaAuditorModel> planauditoriaAuditores) {
		this.planauditoriaAuditores = planauditoriaAuditores;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public List<PlanprocedimientoModel> getPlanprocedimientos() {
		return planprocedimientos;
	}

	public void setPlanprocedimientos(List<PlanprocedimientoModel> planprocedimientos) {
		this.planprocedimientos = planprocedimientos;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}


}