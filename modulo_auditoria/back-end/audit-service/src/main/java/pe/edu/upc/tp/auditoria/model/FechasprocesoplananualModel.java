package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fechasprocesoplananual database table.
 * 
 */
@Entity
@Table(name = "tbl_fechasprocesoplananual")
@NamedQuery(name="Fechasprocesoplananual.findAll", query="SELECT f FROM FechasprocesoplananualModel f")
public class FechasprocesoplananualModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="fe_fin")
	@Temporal(TemporalType.DATE)
	private Date fin;

	@Column(name="fe_inicio")
	@Temporal(TemporalType.DATE)
	private Date inicio;

	public FechasprocesoplananualModel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFin() {
		return this.fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

}