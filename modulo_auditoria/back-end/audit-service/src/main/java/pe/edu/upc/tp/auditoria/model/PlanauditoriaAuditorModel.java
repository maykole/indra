package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the planauditoria_auditor database table.
 * 
 */
@Entity
@Table(name="tbl_planauditoria_auditor")
@NamedQuery(name="PlanauditoriaAuditor.findAll", query="SELECT p FROM PlanauditoriaAuditorModel p")
public class PlanauditoriaAuditorModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="tx_cargo")
	private String cargo;
	
	@Column(name="planauditoria_id")
	private int planauditoriaId;

	@OneToOne
	@JoinColumn(name = "empleado_id")
	private EmpleadoModel empleado;

	@Column(name="tx_nivel")
	private String nivel;

	public PlanauditoriaAuditorModel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getPlanauditoriaId() {
		return planauditoriaId;
	}

	public void setPlanauditoriaId(int planauditoriaId) {
		this.planauditoriaId = planauditoriaId;
	}

	public EmpleadoModel getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoModel empleado) {
		this.empleado = empleado;
	}


}