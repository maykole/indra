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

import portafolio.bean.ProyectoBean;
import portafolio.dao.MysqlProyectoDao;


@Path("/Proyecto")
public class ProyectoBO {
	
	
	@GET
	@Path("/getProyectos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProyectosJSON() {
		
		
		MysqlProyectoDao objProyectoDao = new MysqlProyectoDao();
		//List<ProyectoBean> listaProyecto = new ArrayList<>();
		String json = "";
		json= objProyectoDao.listarProyecto();
	
		//return  listaProyecto;
		return Response.status(200).entity(json).build();
		
	}
	
	@GET
	@Path("/getProyectos/{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getProyectosXidJSON(@PathParam("id") int co_proyecto) {
		
		MysqlProyectoDao objProyectoDao = new MysqlProyectoDao();
		//List<ProyectoBean> listaProyecto = new ArrayList<>();
		//listaProyecto= objProyectoDao.listarProyectoXid(co_proyecto);
		String json = "";
		json= objProyectoDao.listarProyectoXid(co_proyecto);
	
		//return  listaProyecto;
		return Response.status(200).entity(json).build();
		
	}

 @GET
	@Path("/getProyectos/fechas/{param}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getProyectosXidJSON(@PathParam("param") String param) {
		
		String fechas = param; 
		String[] parts = fechas.split("\\|");
		String fecha1 = parts[0]; // 004
		String fecha2 = parts[1]; 
		
		MysqlProyectoDao objProyectoDao = new MysqlProyectoDao();
		//List<ProyectoBean> listaProyecto = new ArrayList<>();
		//listaProyecto= objProyectoDao.listarProyectoXid(co_proyecto);
		//String json = "[{"+"\""+"sdfsdf"+"\""+":"+"\""+part1+"\""+"}]"; 
		String json="";
		System.out.println(fecha1);
		System.out.println(fecha2);
		
		System.out.println(param);
		json= objProyectoDao.listarProyectoXFechas(fecha1, fecha2);
	
		//return  listaProyecto;
		return Response.status(200).entity(json).build();
		
	}
	
	
	
	/*@POST
	@Path("/insertProyectos")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response  postEmployee(ProyectoBean proyecto,@Context UriInfo uriInfo) {
		if(proyecto.getNo_proyecto()!=""){
	        
	        return Response.status(400).build();
	    }
		
		int co_proyecto=0;
		MysqlProyectoDao objProyectoDao = new MysqlProyectoDao();
		co_proyecto = objProyectoDao.insertarProyecto(proyecto);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	    URI createdURI = builder.path(Integer.toString(co_proyecto)).build();
	    return Response.created(createdURI).build();
		
	}*/
	
	@POST
	@Path("/insertProyectos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response  createProyectos(ProyectoBean proyecto)  {
		/*if(proyecto.getNo_proyecto()!=""){
	        
	        return Response.status(400).build();
	    }*/
		
		int co_proyecto=0;
		MysqlProyectoDao objProyectoDao = new MysqlProyectoDao();
		
		co_proyecto = objProyectoDao.insertarProyecto(proyecto);
		String json = "[{"+"\""+"co_proyecto"+"\""+":"+Integer.toString(co_proyecto)+","+"\""+"status"+"\""+":"+"200}]";
		/*UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	    URI createdURI = builder.path(Integer.toString(co_proyecto)).build();
	    return Response.created(createdURI).build();
	    */
		
		//return co_proyecto;
		//return Response.status(200).build(); 
		return Response.status(200).entity(json).build();
		
	}
	
	@PUT
	@Path("/updateProyectos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response  updateProyectos(ProyectoBean proyecto)  {
		/*if(proyecto.getNo_proyecto()!=""){
	        
	        return Response.status(400).build();
	    }*/
		String json="";
		int co_proyecto=0;
		MysqlProyectoDao objProyectoDao = new MysqlProyectoDao();
		
		co_proyecto = objProyectoDao.actualizarProyecto(proyecto);
		
		/*UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	    URI createdURI = builder.path(Integer.toString(co_proyecto)).build();
	    return Response.created(createdURI).build();
	    */
		if (co_proyecto==1){
		 json= "[{"+"\""+"co_proyecto"+"\""+":"+Integer.toString(proyecto.getCo_proyecto())+","+"\""+"status"+"\""+":"+"200}]";
		}
		//return co_proyecto;
		//return Response.status(200).build();
		return Response.status(200).entity(json).build();
		
	}
	
	@DELETE
	@Path("/deleteProyectos/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response  deleteProyectos(@PathParam("id") int co_proyecto)  {
		/*if(proyecto.getNo_proyecto()!=""){
	        
	        return Response.status(400).build();
	    }*/
		
		String json="";
		MysqlProyectoDao objProyectoDao = new MysqlProyectoDao();
		int valor=0;
		valor =objProyectoDao.eliminarProyecto(co_proyecto);
		
		/*UriBuilder builder = uriInfo.getAbsolutePathBuilder();
	    URI createdURI = builder.path(Integer.toString(co_proyecto)).build();
	    return Response.created(createdURI).build();
	    */
		
		//return co_proyecto;
		//return Response.status(200).build();
		if (valor==1){
			 json= "[{"+"\""+"co_proyecto"+"\""+":"+Integer.toString(co_proyecto)+","+"\""+"status"+"\""+":"+"200}]";
			}
			//return co_proyecto;
			//return Response.status(200).build();
			return Response.status(200).entity(json).build();
		
	}
	
}
