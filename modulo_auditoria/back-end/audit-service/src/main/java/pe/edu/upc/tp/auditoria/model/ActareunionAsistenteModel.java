package pe.edu.upc.tp.auditoria.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_actareunion_asistente")
public class ActareunionAsistenteModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name = "actareunion_id")
	private int actareunionId;

	@OneToOne
	@JoinColumn(name = "empleado_id")
	private EmpleadoModel empleado;

	@Column(name="tx_cargo")
	private String cargo;

	public ActareunionAsistenteModel() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActareunionId() {
		return actareunionId;
	}

	public void setActareunionId(int actareunionId) {
		this.actareunionId = actareunionId;
	}

	public EmpleadoModel getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoModel empleado) {
		this.empleado = empleado;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
