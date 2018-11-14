package pe.edu.upc.tp.auditoria.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActaReunionInit {

	private String periodo;
    private List<EmpleadoBean> asistentes;
    private List<ProcesoBean> procesos;
    private Error error;

    public List<EmpleadoBean> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(List<EmpleadoBean> asistentes) {
        this.asistentes = asistentes;
    }

    public List<ProcesoBean> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<ProcesoBean> procesos) {
        this.procesos = procesos;
    }

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}
