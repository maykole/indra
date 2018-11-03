package portafolio.interfaces;


import portafolio.bean.ProyectoBean;


public interface ProyectoDao {

	/*public ArrayList<ProyectoBean> listarProyecto();
	public ArrayList<ProyectoBean> listarProyectoXid(int co_proyecto);*/
	public String listarProyecto();
	public String listarProyectoXid(int co_proyecto);
	public String listarProyectoXFechas(String fecha1, String fecha2);
	public int insertarProyecto(ProyectoBean objProyecto);
	public int actualizarProyecto(ProyectoBean objProyecto);
	public int eliminarProyecto(int co_proyecto);
	
}
