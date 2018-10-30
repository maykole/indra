package portafolio.dto;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

import portafolio.bean.ProyectoBean;
import portafolio.dao.MysqlProyectoDao;


@Path("/Proyecto")
public class ProyectoBO {
	
	
	@GET
	@Path("/getProyectos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProyectoBean> getProyectosJSON() {
		
		
		MysqlProyectoDao objProyectoDao = new MysqlProyectoDao();
		List<ProyectoBean> listaProyecto = new ArrayList<>();
		listaProyecto= objProyectoDao.listarProyecto();
	
		return  listaProyecto;
		
	}

}
