package pe.edu.upc.tp.auditoria.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_actareunion")
public class ActareunionModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private int id;

  @Column(name="tx_periodo")
  private String periodo;

  @Column(name="tx_titulo")
  private String titulo;

  @Column(name="tx_descripcion")
  private String descripcion;

  @Column(name="tx_lugar")
  private String lugar;

  @Column(name="fe_reunion")
  private Date fecha;

  @Column(name="tx_conclusiones")
  private String conclusiones;

  @Column(name="tx_acuerdos")
  private String acuerdos;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "actareunion_id")
  private List<ActareunionProcesoModel> actareunionProcesos;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "actareunion_id")
  private List<ActareunionAsistenteModel> actareunionAsistentes;


  public ActareunionModel() {
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getLugar() {
    return lugar;
  }

  public void setLugar(String lugar) {
    this.lugar = lugar;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public String getConclusiones() {
    return conclusiones;
  }

  public void setConclusiones(String conclusiones) {
    this.conclusiones = conclusiones;
  }

  public String getAcuerdos() {
    return acuerdos;
  }

  public void setAcuerdos(String acuerdos) {
    this.acuerdos = acuerdos;
  }

  public List<ActareunionProcesoModel> getActareunionProcesos() {
    return actareunionProcesos;
  }

  public void setActareunionProcesos(List<ActareunionProcesoModel> actareunionProcesos) {
    this.actareunionProcesos = actareunionProcesos;
  }

  public List<ActareunionAsistenteModel> getActareunionAsistentes() {
    return actareunionAsistentes;
  }

  public void setActareunionAsistentes(List<ActareunionAsistenteModel> actareunionAsistentes) {
    this.actareunionAsistentes = actareunionAsistentes;
  }

	public String getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
