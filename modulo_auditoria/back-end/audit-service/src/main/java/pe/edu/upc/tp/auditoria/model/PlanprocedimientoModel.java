package pe.edu.upc.tp.auditoria.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tbl_planauditoria_procedimiento")
public class PlanprocedimientoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "planprocedimiento_id")
    private int planprocedimientoId;

    @Column(name="tx_descripcion")
    private String descripcion;

    @Column(name="planauditoria_id")
    private int planauditoriaId;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "planprocedimiento_id")
    private List<PlanactividadModel> planactividades;

    public int getPlanprocedimientoId() {
        return planprocedimientoId;
    }

    public void setPlanprocedimientoId(int planprocedimientoId) {
        this.planprocedimientoId = planprocedimientoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PlanactividadModel> getPlanactividades() {
        return planactividades;
    }

    public void setPlanactividades(List<PlanactividadModel> planactividades) {
        this.planactividades = planactividades;
    }

    public int getPlanauditoriaId() {
        return planauditoriaId;
    }

    public void setPlanauditoriaId(int planauditoriaId) {
        this.planauditoriaId = planauditoriaId;
    }
}
