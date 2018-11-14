package pe.edu.upc.tp.auditoria.model;

import pe.edu.upc.tp.auditoria.model.ProcesoModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_actareunion_proceso")
public class ActareunionProcesoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;

	@Column(name = "actareunion_id")
	private int actareunionId;

	@OneToOne
	@JoinColumn(name = "proceso_id")
	private ProcesoModel proceso;

	public ActareunionProcesoModel() {
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

	public ProcesoModel getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoModel proceso) {
		this.proceso = proceso;
	}
}
