package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the proceso database table.
 * 
 */
@Entity
@Table(name = "tbl_proceso")
@NamedQuery(name="Proceso.findAll", query="SELECT p FROM ProcesoModel p")
public class ProcesoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="proceso_id")
	private int procesoId;

	@Column(name="tx_descripcion")
	private String descripcion;

	@Column(name="tx_objetivo")
	private String objetivo;

	@Column(name="tx_alcance")
	private String alcance;

	public ProcesoModel() {
	}
	
	public ProcesoModel(int id) {
		this.procesoId = id;
	}

	public int getProcesoId() {
		return this.procesoId;
	}

	public void setProcesoId(int procesoId) {
		this.procesoId = procesoId;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}
}