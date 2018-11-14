package pe.edu.upc.tp.auditoria.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_planauditoria_actividad")
@NamedQuery(name="Planactividad.findAll", query="SELECT p FROM PlanactividadModel p")
public class PlanactividadModel {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="planactividad_id")
    private int planactividadId;

    @Column(name="tx_descripcion")
    private String descripcion;

    @Column(name="fl_auditar")
    private boolean auditar;
    
    @Column(name="fe_actividad")
    private Date fecha;

    @Column(name="fl_auditado")
    private boolean auditado;

    @Column(name="fl_accion")
    private boolean accion;

    @Column(name="tx_observacion")
    private String observacion;

    @Column(name="planprocedimiento_id")
    private int planprocedimientoId;

    @Transient
    private String EnlaceAuditar;

    public int getPlanactividadId() {
        return planactividadId;
    }

    public void setPlanactividadId(int planactividadId) {
        this.planactividadId = planactividadId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getPlanprocedimientoId() {
        return planprocedimientoId;
    }

    public void setPlanprocedimientoId(int planprocedimientoId) {
        this.planprocedimientoId = planprocedimientoId;
    }

    public boolean isAuditado() {
        return auditado;
    }

    public void setAuditado(boolean auditado) {
        this.auditado = auditado;
    }

    public boolean isAccion() {
        return accion;
    }

    public void setAccion(boolean accion) {
        this.accion = accion;
    }

	public String getEnlaceAuditar() {
		return EnlaceAuditar;
	}

	public void setEnlaceAuditar(String enlaceAuditar) {
		EnlaceAuditar = enlaceAuditar;
	}

	public boolean isAuditar() {
		return auditar;
	}

	public void setAuditar(boolean auditar) {
		this.auditar = auditar;
	}


    
}
