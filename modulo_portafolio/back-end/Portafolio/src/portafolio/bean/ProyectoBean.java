/**
 *Esta clase crea instancias de la clase Proyecto.
 * @author Jonathan Tuñon Levano.
 * @version 1.50, 30/10/2018
 * @since 1.4
 */
 
package portafolio.bean;//Nombre del paquete en el que se encuentra la clase.

import javax.xml.bind.annotation.XmlRootElement;

/**Cuando una clase de nivel superior o un tipo de 
 * enumeración se anota con la anotación @XmlRootElement,
 *  su valor se representa como elemento XML en un documento XML.
 */
@XmlRootElement
public class ProyectoBean {
	

	//Variables
	private 	int 	  co_proyecto	; 			//Codigo del proyecto.
	private 	String	  no_proyecto	; 			//Nombre del Proyecto.
	private 	String	  de_proyecto	; 			//Descripcción del Proyecto.
	private 	String	  fe_inicio	;     			//Fecha de inicio del proyecto.
	private 	String	  fe_termino	;     			//Fecha de termino del proyecto.
	private 	int 	  co_programa	; 			//Codigo del programa.
	private 	int 	  co_estado	;     			//Codigo de estado del proyecto.
	private 	int 	  co_usuarioregistro	;	//Codigo del usuario de registro.
	private 	int 	  co_prioridad	;			//Codigo de la prioridad.
	private 	int 	  co_categoria	;			//Codigo de la categoria.
	private 	double 	  po_avance	;			//porcentaje de avance.
	private 	int 	  co_rubro	;			//Codigo de la categoria.
	private 	int 	  co_cliente	;			//Codigo del cliente.
	
	//Constructor
	public ProyectoBean() {
		super();
	}
	
	//Constructor definido
	public ProyectoBean(int co_proyecto, String no_proyecto,String de_proyecto, String fe_inicio,
			String fe_termino, int co_programa, int co_estado, int co_cliente,
			int co_usuarioregistro, int co_prioridad, int co_categoria,double po_avance, int co_rubro) 
	{
			super();
			this.co_proyecto = co_proyecto;
			this.no_proyecto = no_proyecto;
			this.fe_inicio = fe_inicio;
			this.fe_termino = fe_termino;
			this.co_programa = co_programa;
			this.co_estado = co_estado;
			this.co_usuarioregistro = co_usuarioregistro;
			this.co_prioridad = co_prioridad;
			this.co_categoria = co_categoria;
			this.po_avance = po_avance;
			this.co_rubro = co_rubro;
			this.co_cliente = co_cliente;
	}
	
	//Get and set 
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
	public String getDe_proyecto() {
		return de_proyecto;
	}
	public void setDe_proyecto(String de_proyecto) {
		this.de_proyecto = de_proyecto;
	}
	public String getFe_inicio() {
		return fe_inicio;
	}
	public void setFe_inicio(String fe_inicio) {
		this.fe_inicio = fe_inicio;
	}
	
	public String getFe_termino() {
		return fe_termino;
	}

	public void setFe_termino(String fe_termino) {
		this.fe_termino = fe_termino;
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
	public double getPo_avance() {
		return po_avance;
	}
	public void setPo_avance(double po_avance) {
		this.po_avance = po_avance;
	}
	public int getCo_rubro() {
		return co_rubro;
	}
	public void setCo_rubro(int co_rubro) {
		this.co_rubro = co_rubro;
	}
	public int getCo_cliente() {
		return co_cliente;
	}
	public void setCo_cliente(int co_cliente) {
		this.co_cliente = co_cliente;
	}
	
}
