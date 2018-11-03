package portafolio.interfaces;

import portafolio.bean.ProgramaBean;

public interface ProgramaDao {
	
	
	public String listarPrograma();
	public String listarProgramaXid(int co_programa);
	public String listarProgramaXFechas(String fecha1, String fecha2);
	public int insertarPrograma(ProgramaBean objPrograma);

}
