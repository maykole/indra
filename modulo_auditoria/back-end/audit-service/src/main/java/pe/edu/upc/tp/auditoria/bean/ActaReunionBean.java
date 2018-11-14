package pe.edu.upc.tp.auditoria.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActaReunionBean {

	private int id;
	private String periodo;
    private String titulo;
    private String descripcion;
    private String lugar;
    private Date fecha;
    private String conclusiones;
    private String acuerdos;
    private List<EmpleadoBean> asistentes;
    private List<ProcesoBean> procesos;
    private Error error;

    public ActaReunionBean(){
    }

    public ActaReunionBean(int id){
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

    public List<ProcesoBean> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<ProcesoBean> procesos) {
        this.procesos = procesos;
    }

    public List<EmpleadoBean> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(List<EmpleadoBean> asistentes) {
        this.asistentes = asistentes;
    }

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

    
}
