package pe.edu.upc.tp.auditoria.model;

import javax.persistence.*;

@Entity
@Table(name="tbl_actividad")
@NamedQuery(name="Actividad.findAll", query="SELECT p FROM ActividadModel p")
public class ActividadModel {

    @Id
    @Column(name="actividad_id")
    private int actividadId;

    @Column(name="tx_descripcion")
    private String descripcion;

    @Column(name="procedimiento_id")
    private int procedimientoId;

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getProcedimientoId() {
        return procedimientoId;
    }

    public void setProcedimientoId(int procedimientoId) {
        this.procedimientoId = procedimientoId;
    }
}
