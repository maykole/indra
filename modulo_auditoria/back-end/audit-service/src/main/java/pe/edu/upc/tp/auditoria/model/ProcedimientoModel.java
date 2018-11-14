package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the procedimiento database table.
 * 
 */
@Entity
@Table(name="tbl_procedimiento")
@NamedQuery(name="Procedimiento.findAll", query="SELECT p FROM ProcedimientoModel p")
public class ProcedimientoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="procedimiento_id")
	private int procedimientoId;

	@Column(name="tx_descripcion")
	private String descripcion;

	@Column(name="proceso_id")
	private int procesoId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "procedimiento_id")
	private List<ActividadModel> actividades;

	public int getProcedimientoId() {
		return procedimientoId;
	}

	public void setProcedimientoId(int procedimientoId) {
		this.procedimientoId = procedimientoId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ActividadModel> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadModel> actividades) {
		this.actividades = actividades;
	}

	public int getProcesoId() {
		return procesoId;
	}

	public void setProcesoId(int procesoId) {
		this.procesoId = procesoId;
	}
}