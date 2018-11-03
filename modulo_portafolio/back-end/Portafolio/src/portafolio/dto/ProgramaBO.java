package portafolio.dto;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import portafolio.bean.ProgramaBean;
import portafolio.bean.ProyectoBean;
import portafolio.dao.MysqlProgramaDao;
import portafolio.dao.MysqlProyectoDao;


@Path("/Programa")
public class ProgramaBO {
	
	
	@GET
	@Path("/getProgramas")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getProyectosJSON() {
		
		
		MysqlProgramaDao objProgramaDao = new MysqlProgramaDao();
		//List<ProyectoBean> listaProyecto = new ArrayList<>();
		String json = "";
		json= objProgramaDao.listarPrograma();
	
		//return  listaProyecto;
		return Response.status(200).entity(json).build();
		 
	}
	
	@GET
	@Path("/getProgramas/{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getProyectosXidJSON(@PathParam("id") int co_programa) {
		
		MysqlProgramaDao objProgramaDao = new MysqlProgramaDao();
		//List<ProyectoBean> listaProyecto = new ArrayList<>();
		//listaProyecto= objProyectoDao.listarProyectoXid(co_proyecto);
		String json = "";
		json= objProgramaDao.listarProgramaXid(co_programa);
	
		//return  listaProyecto;
		return Response.status(200).entity(json).build();
		
	}
	
	@POST
	@Path("/insertProgramas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response  createProgramas(ProgramaBean programa)  {
		/*if(proyecto.getNo_proyecto()!=""){
	        
	        return Response.status(400).build();
	    }*/
		
		int co_programa=0;
		MysqlProgramaDao objProgramaDao = new MysqlProgramaDao();
		
		co_programa = objProgramaDao.insertarPrograma(programa);
		String json = "[{"+"\""+"co_programa"+"\""+":"+Integer.toString(co_programa)+","+"\""+"status"+"\""+":"+"200}]";
		/*UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	    URI createdURI = builder.path(Integer.toString(co_proyecto)).build();
	    return Response.created(createdURI).build();
	    */
		
		//return co_proyecto;
		//return Response.status(200).build(); 
		return Response.status(200).entity(json).build();
		
	}

}
