package pe.edu.upc.tp.auditoria.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramacionBean {

    private int planauditoriaanualId;
    private ProcesoBean proceso;
    private EmpleadoBean auditor;
    private int duracion;
    private String prioridad;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechainicioLimite;
    private Date fechafinLimite;
    private String estado;

    public int getPlanauditoriaanualId() {
        return planauditoriaanualId;
    }

    public void setPlanauditoriaanualId(int planauditoriaanualId) {
        this.planauditoriaanualId = planauditoriaanualId;
    }

    public ProcesoBean getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoBean proceso) {
        this.proceso = proceso;
    }

    public EmpleadoBean getAuditor() {
        return auditor;
    }

    public void setAuditor(EmpleadoBean auditor) {
        this.auditor = auditor;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechainicioLimite() {
        return fechainicioLimite;
    }

    public void setFechainicioLimite(Date fechainicioLimite) {
        this.fechainicioLimite = fechainicioLimite;
    }

    public Date getFechafinLimite() {
        return fechafinLimite;
    }

    public void setFechafinLimite(Date fechafinLimite) {
        this.fechafinLimite = fechafinLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
