package portafolio.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProgramaBean {
	
	public int getCo_programa() {
		return co_programa;
	}
	public void setCo_programa(int co_programa) {
		this.co_programa = co_programa;
	}
	public String getNo_programa() {
		return no_programa;
	}
	public void setNo_programa(String no_programa) {
		this.no_programa = no_programa;
	}
	public String getDe_programa() {
		return de_programa;
	}
	public void setDe_programa(String de_programa) {
		this.de_programa = de_programa;
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
	public int getCo_portafolio() {
		return co_portafolio;
	}
	public void setCo_portafolio(int co_portafolio) {
		this.co_portafolio = co_portafolio;
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
	public int getCo_categoria() {
		return co_categoria;
	}
	public void setCo_categoria(int co_categoria) {
		this.co_categoria = co_categoria;
	}
	public int getCo_prioridad() {
		return co_prioridad;
	}
	public void setCo_prioridad(int co_prioridad) {
		this.co_prioridad = co_prioridad;
	}
	public ProgramaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProgramaBean(int co_programa, String no_programa,
			String de_programa, String fe_inicio,
			String fe_fin, int co_portafolio, int co_estado,
			int co_usuarioregistro, int co_categoria, int co_prioridad) {
		super();
		this.co_programa = co_programa;
		this.no_programa = no_programa;
		this.de_programa = de_programa;
		
		this.fe_inicio = fe_inicio;
		this.fe_fin = fe_fin;
		this.co_portafolio = co_portafolio;
		this.co_estado = co_estado;
		this.co_usuarioregistro = co_usuarioregistro;
		this.co_categoria = co_categoria;
		this.co_prioridad = co_prioridad;
	}
	private	int	co_programa	;
	private	String	no_programa 	;
	private	String	de_programa 	;
	
	private	String	fe_inicio	;
	private	String	fe_fin 	;
	private	int	co_portafolio 	;
	private	int	co_estado	;
	private	int	co_usuarioregistro	;
	private	int	co_categoria	;  
	private	int	co_prioridad	;


}
