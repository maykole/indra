package portafolio.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProyectoBean {

	public ProyectoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProyectoBean(int co_proyecto, String no_proyecto, String fe_inicio,
			String fe_fin, int co_programa, int co_estado,
			int co_usuarioregistro, int co_prioridad, int co_categoria) {
		super();
		this.co_proyecto = co_proyecto;
		this.no_proyecto = no_proyecto;
		this.fe_inicio = fe_inicio;
		this.fe_fin = fe_fin;
		this.co_programa = co_programa;
		this.co_estado = co_estado;
		this.co_usuarioregistro = co_usuarioregistro;
		this.co_prioridad = co_prioridad;
		this.co_categoria = co_categoria;
	}
	public int getCo_proyecto() {
		return co_proyecto;
	}
	public void setCo_proyecto(int co_proyecto) {
		this.co_proyecto = co_proyecto;
	}
	public String getNo_proyecto() {
		return no_proyecto;
	}
	public void setNo_proyecto(String no_proyecto) {
		this.no_proyecto = no_proyecto;
	}
	public String getFe_inicio() {
		return fe_inicio;
	}
	public void setFe_inicio(String fe_inicio) {
		this.fe_inicio = fe_inicio;
	}
	public String getFe_fin() {
		return fe_fin;
	}
	public void setFe_fin(String fe_fin) {
		this.fe_fin = fe_fin;
	}
	public int getCo_programa() {
		return co_programa;
	}
	public void setCo_programa(int co_programa) {
		this.co_programa = co_programa;
	}
	public int getCo_estado() {
		return co_estado;
	}
	public void setCo_estado(int co_estado) {
		this.co_estado = co_estado;
	}
	public int getCo_usuarioregistro() {
		return co_usuarioregistro;
	}
	public void setCo_usuarioregistro(int co_usuarioregistro) {
		this.co_usuarioregistro = co_usuarioregistro;
	}
	public int getCo_prioridad() {
		return co_prioridad;
	}
	public void setCo_prioridad(int co_prioridad) {
		this.co_prioridad = co_prioridad;
	}
	public int getCo_categoria() {
		return co_categoria;
	}
	public void setCo_categoria(int co_categoria) {
		this.co_categoria = co_categoria;
	}
	public String getNo_programa() {
		return no_programa;
	}
	public void setNo_programa(String no_programa) {
		this.no_programa = no_programa;
	}
	public String getNo_estado() {
		return no_estado;
	}
	public void setNo_estado(String no_estado) {
		this.no_estado = no_estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNo_prioridadl() {
		return no_prioridadl;
	}
	public void setNo_prioridadl(String no_prioridadl) {
		this.no_prioridadl = no_prioridadl;
	}
	public String getNo_categoria() {
		return no_categoria;
	}
	public void setNo_categoria(String no_categoria) {
		this.no_categoria = no_categoria;
	}
	private 	int 	  co_proyecto	;
	private 	String	  no_proyecto	;
	private 	String	  fe_inicio	;
	private 	String	  fe_fin	;
	private 	int 	  co_programa	;
	private 	int 	  co_estado	;
	private 	int 	  co_usuarioregistro	;
	private 	int 	  co_prioridad	;
	private 	int 	  co_categoria	;
	private 	String	  no_programa	;
	private 	String	  no_estado	;
	private 	String	  nombre	;
	private 	String	  no_prioridadl	;
	private 	String	  no_categoria	;



	
}
