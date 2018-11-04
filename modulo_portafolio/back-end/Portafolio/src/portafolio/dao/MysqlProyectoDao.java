package portafolio.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import portafolio.util.*; 
import portafolio.bean.ProyectoBean;
import portafolio.interfaces.ProyectoDao;

public class MysqlProyectoDao  implements ProyectoDao {
	
	/*
	@Override
	public ArrayList<ProyectoBean> listarProyecto() { 
		// TODO Auto-generated method stub
		Connection cn = null; 
	    PreparedStatement ps = null;
	    ResultSet rs = null;
			ArrayList<ProyectoBean> listaProyecto = new ArrayList<ProyectoBean>();
		
		try {
			
			Conexion objCn = new Conexion();
			String sql ="{call getProyecto()}";
			cn=objCn.ConMysql();
			ps=cn.prepareStatement(sql);
			rs= ps.executeQuery(); 
			
			while (rs.next()){
				
				ProyectoBean proy = new ProyectoBean();
				proy.setCo_proyecto(rs.getInt("co_proyecto"));
				proy.setNo_proyecto(rs.getString("no_proyecto"));
				proy.setFe_inicio(rs.getString("fe_inicio"));
				proy.setFe_fin(rs.getString("fe_fin"));
				proy.setNo_programa(rs.getString("no_programa"));
				proy.setNo_estado(rs.getString("no_estado"));
				proy.setNombre(rs.getString("nombre"));
				proy.setNo_prioridadl(rs.getString("no_prioridadl"));
				proy.setNo_categoria(rs.getString("no_categoria"));
				
				listaProyecto.add(proy); 
				
			}
			
			cn.close();
			ps.close();
			rs.close();
			System.out.println("listarProyecto");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.toString() ); 
			//throw e;
		}
		
		return listaProyecto;
	}
*/
	
	@Override
	public String listarProyecto() {
		// TODO Auto-generated method stub
				Connection cn = null; 
			    PreparedStatement ps = null;
			    ResultSet rs = null;
					//ArrayList<ProyectoBean> listaProyecto = new ArrayList<ProyectoBean>();
				 String json="";
				 StringBuilder cadena = new StringBuilder();
				try {
					
					Conexion objCn = new Conexion();
					String sql ="{call indrass_all_proyecto()}";
					cn=objCn.ConMysql();
					ps=cn.prepareStatement(sql);
					rs= ps.executeQuery(); 
					cadena.append("[");
					while (rs.next()){
						
						/*ProyectoBean proy = new ProyectoBean();
						proy.setCo_proyecto(rs.getInt("co_proyecto"));
						proy.setNo_proyecto(rs.getString("no_proyecto"));
						proy.setFe_inicio(rs.getString("fe_inicio"));
						proy.setFe_fin(rs.getString("fe_fin"));
						proy.setNo_programa(rs.getString("no_programa"));
						proy.setNo_estado(rs.getString("no_estado"));
						proy.setNombre(rs.getString("nombre"));
						proy.setNo_prioridadl(rs.getString("no_prioridadl"));
						proy.setNo_categoria(rs.getString("no_categoria"));
						
						listaProyecto.add(proy); */
						cadena.append("{");
						cadena.append("\""+"co_proyecto"+"\""+":"+rs.getString("co_proyecto")+",");
						cadena.append("\""+"no_proyecto"+"\""+":"+"\""+rs.getString("no_proyecto")+"\""+",");
						cadena.append("\""+"fe_inicio"+"\""+":"+"\""+rs.getString("fe_inicio")+"\""+",");
						cadena.append("\""+"fe_fin"+"\""+":"+"\""+rs.getString("fe_termino")+"\""+",");
						cadena.append("\""+"no_categoria"+"\""+":"+"\""+rs.getString("no_categoria")+"\""+",");
						cadena.append("\""+"no_estado"+"\""+":"+"\""+rs.getString("no_estado")+"\""+",");
						cadena.append("\""+"no_prioridad"+"\""+":"+"\""+rs.getString("no_prioridad")+"\""+",");
						cadena.append("\""+"no_programa"+"\""+":"+"\""+rs.getString("no_programa")+"\""+",");
						cadena.append("\""+"nombre"+"\""+":"+"\""+rs.getString("nombre")+"\"");
						cadena.append("},");
						
					}
					cadena.append("]");
					
					json= cadena.toString().replace(",]", "]");
					cn.close();
					ps.close();
					rs.close();
					System.out.println("listarProyecto");
					System.out.println(json);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERROR : " + e.toString() ); 
					//throw e;
				}
				
				return json;
	}
	
	
	/*
	@Override
	public ArrayList<ProyectoBean> listarProyectoXid(int co_proyecto) {
		Connection cn = null; 
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		// TODO Auto-generated method stub
		ArrayList<ProyectoBean> listaProyecto = new ArrayList<ProyectoBean>();
		
	try {
		
		Conexion objCn = new Conexion();
		String sql ="{call getProyectoXid(?)}";
		
		cn=objCn.ConMysql();
		ps=cn.prepareStatement(sql);
		ps.setString(1, Integer.toString( co_proyecto));
		rs= ps.executeQuery(); 
		
		while (rs.next()){
			
			ProyectoBean proy = new ProyectoBean();
			proy.setCo_proyecto(rs.getInt("co_proyecto"));
			proy.setNo_proyecto(rs.getString("no_proyecto"));
			proy.setFe_inicio(rs.getString("fe_inicio"));
			proy.setFe_fin(rs.getString("fe_fin"));
			proy.setNo_programa(rs.getString("no_programa"));
			proy.setNo_estado(rs.getString("no_estado"));
			proy.setNombre(rs.getString("nombre"));
			proy.setNo_prioridadl(rs.getString("no_prioridadl"));
			proy.setNo_categoria(rs.getString("no_categoria"));
			
			listaProyecto.add(proy); 
			
		}
		
		cn.close();
		ps.close();
		rs.close();
		
		System.out.println("listarProyectoXid");
		
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ERROR : " + e.toString() ); 
		//throw e;
	}
	
	return listaProyecto;
	}
*/
	
	@Override
	public String listarProyectoXid(int co_proyecto) {
		// TODO Auto-generated method stub
		Connection cn = null; 
	    PreparedStatement ps = null;
	    ResultSet rs = null;
			//ArrayList<ProyectoBean> listaProyecto = new ArrayList<ProyectoBean>();
		 String json="";
		 StringBuilder cadena = new StringBuilder();
		try {
			
			Conexion objCn = new Conexion();
			String sql ="{call indrass_id_proyecto(?)}";
			cn=objCn.ConMysql();
			ps=cn.prepareStatement(sql);
			ps.setString(1, Integer.toString( co_proyecto));
			rs= ps.executeQuery(); 
			cadena.append("[");
			while (rs.next()){
				
				/*ProyectoBean proy = new ProyectoBean();
				proy.setCo_proyecto(rs.getInt("co_proyecto"));
				proy.setNo_proyecto(rs.getString("no_proyecto"));
				proy.setFe_inicio(rs.getString("fe_inicio"));
				proy.setFe_fin(rs.getString("fe_fin"));
				proy.setNo_programa(rs.getString("no_programa"));
				proy.setNo_estado(rs.getString("no_estado"));
				proy.setNombre(rs.getString("nombre"));
				proy.setNo_prioridadl(rs.getString("no_prioridadl"));
				proy.setNo_categoria(rs.getString("no_categoria"));
				
				listaProyecto.add(proy); */
				cadena.append("{");
				cadena.append("\""+"co_proyecto"+"\""+":"+rs.getString("co_proyecto")+",");
				cadena.append("\""+"no_proyecto"+"\""+":"+"\""+rs.getString("no_proyecto")+"\""+",");
				cadena.append("\""+"fe_inicio"+"\""+":"+"\""+rs.getString("fe_inicio")+"\""+",");
				cadena.append("\""+"fe_fin"+"\""+":"+"\""+rs.getString("fe_termino")+"\""+",");
				cadena.append("\""+"no_categoria"+"\""+":"+"\""+rs.getString("no_categoria")+"\""+",");
				cadena.append("\""+"no_estado"+"\""+":"+"\""+rs.getString("no_estado")+"\""+",");
				cadena.append("\""+"no_prioridad"+"\""+":"+"\""+rs.getString("no_prioridad")+"\""+",");
				cadena.append("\""+"no_programa"+"\""+":"+"\""+rs.getString("no_programa")+"\""+",");
				cadena.append("\""+"nombre"+"\""+":"+"\""+rs.getString("nombre")+"\""+",");
				cadena.append("\""+"co_programa"+"\""+":"+"\""+rs.getString("co_programa")+"\""+",");
				cadena.append("\""+"co_estado"+"\""+":"+"\""+rs.getString("co_estado")+"\""+",");
				cadena.append("\""+"co_empleado"+"\""+":"+"\""+rs.getString("co_empleado")+"\""+",");
				cadena.append("\""+"co_prioridad"+"\""+":"+"\""+rs.getString("co_prioridad")+"\""+",");
				cadena.append("\""+"co_categoria"+"\""+":"+"\""+rs.getString("co_categoria")+"\"");
				cadena.append("},");
				
			}
			cadena.append("]");
			
			json= cadena.toString().replace(",]", "]");
			cn.close();
			ps.close();
			rs.close();
			System.out.println("listarProyectoXid");
			System.out.println(json);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.toString() ); 
			//throw e;
		}
		
		return json;
	} 
	
	@Override
	public int insertarProyecto(ProyectoBean objProyecto) {
		// TODO Auto-generated method stub
		int i = 0;
		int co_Proyecto = 0;
		Connection cn = null; 
		CallableStatement  ps = null;
		try {
			System.out.println("insertarProyecto");
			Conexion objCn = new Conexion();
			String sql ="{call indrasi_proyecto(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			cn=objCn.ConMysql();
			ps=cn.prepareCall(sql);
			System.out.println(objProyecto.getNo_proyecto().toString());
			ps.setString(1,objProyecto.getNo_proyecto());
			ps.setString(2,objProyecto.getDe_proyecto());
			ps.setString(3,objProyecto.getFe_inicio());
			ps.setString(4,objProyecto.getFe_termino());
			ps.setDouble(5,objProyecto.getPo_avance());
			ps.setInt(6,objProyecto.getCo_usuarioregistro());
			ps.setInt(7,objProyecto.getCo_rubro());
			ps.setInt(8,objProyecto.getCo_estado());
			ps.setInt(9,objProyecto.getCo_categoria());
			ps.setInt(10,objProyecto.getCo_prioridad());
			ps.setInt(11,objProyecto.getCo_cliente());
			ps.setInt(12,objProyecto.getCo_programa());
			
			ps.registerOutParameter(13, java.sql.Types.INTEGER);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													
			
			i=ps.executeUpdate(); 
			
			co_Proyecto= ps.getInt(13);
			
			cn.close();
			ps.close();
			
			System.out.println(i);
			System.out.println(co_Proyecto);
			System.out.println("insertarProyecto");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.toString() ); 
			//throw e;
		}
		
		return co_Proyecto;
	}

	@Override
	public int actualizarProyecto(ProyectoBean objProyecto) {
		// TODO Auto-generated method stub
		int i = 0;
		//int co_Proyecto = 0;
		Connection cn = null; 
		CallableStatement  ps = null;
		try {
			System.out.println("actualizarProyecto");
			Conexion objCn = new Conexion();
			String sql ="{call indrasu_proyecto(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			cn=objCn.ConMysql();
			ps=cn.prepareCall(sql);
			System.out.println(objProyecto.getNo_proyecto().toString());
			ps.setInt(1,objProyecto.getCo_proyecto());
			ps.setString(2,objProyecto.getNo_proyecto());
			ps.setString(3,objProyecto.getDe_proyecto());
			ps.setString(4,objProyecto.getFe_inicio());
			ps.setString(5,objProyecto.getFe_termino());
			ps.setDouble(6,objProyecto.getPo_avance());
			ps.setInt(7,objProyecto.getCo_rubro());
			ps.setInt(8,objProyecto.getCo_usuarioregistro());
			ps.setInt(9,objProyecto.getCo_estado());
			ps.setInt(10,objProyecto.getCo_categoria());
			ps.setInt(11,objProyecto.getCo_prioridad());
			ps.setInt(12,objProyecto.getCo_cliente());
			ps.setInt(13,objProyecto.getCo_programa());
			
			//ps.registerOutParameter(9, java.sql.Types.VARCHAR);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													
			
			i=ps.executeUpdate(); 
			
			//co_Proyecto= ps.getInt(9);
			
			cn.close();
			ps.close();
			
			System.out.println(i);
			//System.out.println(co_Proyecto);
			System.out.println("actualizarProyecto");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.toString() ); 
			//throw e;
		}
		
		return i;
	}

	@Override
	public int eliminarProyecto(int co_proyecto) {
		// TODO Auto-generated method stub
		int i = 0;
		//int co_Proyecto = 0;
		Connection cn = null; 
		CallableStatement  ps = null;
		try {
			System.out.println("deleteProyecto");
			Conexion objCn = new Conexion();
			String sql ="{call indrasd_proyecto(?)}"; 
			
			cn=objCn.ConMysql();
			ps=cn.prepareCall(sql);
			ps.setInt(1,co_proyecto);
			
			//ps.registerOutParameter(9, java.sql.Types.VARCHAR);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													
			
			i=ps.executeUpdate(); 
			
			//co_Proyecto= ps.getInt(9);
			
			cn.close();
			ps.close();
			
			System.out.println(i);
			//System.out.println(co_Proyecto);
			System.out.println("deleteProyecto");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.toString() ); 
			//throw e;
		}
		
		return i;
	}


	@Override
	public String listarProyectoXFechas(String fecha1, String fecha2) {
		// TODO Auto-generated method stub
		Connection cn = null; 
	    PreparedStatement ps = null;
	    ResultSet rs = null;
			//ArrayList<ProyectoBean> listaProyecto = new ArrayList<ProyectoBean>();
		 String json="";
		 StringBuilder cadena = new StringBuilder();
		try {
			
			Conexion objCn = new Conexion();
			String sql ="{call indrass_date_proyecto(?,?)}";
			cn=objCn.ConMysql();
			ps=cn.prepareStatement(sql);
			
			ps.setString(1, fecha1);
			ps.setString(2, fecha2);
		
				  /*ps.setNull(1, Types.INTEGER);
				  ps.setNull(2, Types.DATE);
				  ps.setNull(3, Types.DATE);*/
			
			
			rs= ps.executeQuery(); 
			cadena.append("[");
			while (rs.next()){
				
				/*ProyectoBean proy = new ProyectoBean();
				proy.setCo_proyecto(rs.getInt("co_proyecto"));
				proy.setNo_proyecto(rs.getString("no_proyecto"));
				proy.setFe_inicio(rs.getString("fe_inicio"));
				proy.setFe_fin(rs.getString("fe_fin"));
				proy.setNo_programa(rs.getString("no_programa"));
				proy.setNo_estado(rs.getString("no_estado"));
				proy.setNombre(rs.getString("nombre"));
				proy.setNo_prioridadl(rs.getString("no_prioridadl"));
				proy.setNo_categoria(rs.getString("no_categoria"));
				
				listaProyecto.add(proy); */
				cadena.append("{");
				cadena.append("\""+"co_proyecto"+"\""+":"+rs.getString("co_proyecto")+",");
				cadena.append("\""+"no_proyecto"+"\""+":"+"\""+rs.getString("no_proyecto")+"\""+",");
				cadena.append("\""+"fe_inicio"+"\""+":"+"\""+rs.getString("fe_inicio")+"\""+",");
				cadena.append("\""+"fe_fin"+"\""+":"+"\""+rs.getString("fe_termino")+"\""+",");
				cadena.append("\""+"no_categoria"+"\""+":"+"\""+rs.getString("no_categoria")+"\""+",");
				cadena.append("\""+"no_estado"+"\""+":"+"\""+rs.getString("no_estado")+"\""+",");
				cadena.append("\""+"no_prioridad"+"\""+":"+"\""+rs.getString("no_prioridad")+"\""+",");
				cadena.append("\""+"no_programa"+"\""+":"+"\""+rs.getString("no_programa")+"\""+",");
				cadena.append("\""+"nombre"+"\""+":"+"\""+rs.getString("nombre")+"\"");
				cadena.append("},");
				
			}
			cadena.append("]");
			
			json= cadena.toString().replace(",]", "]");
			cn.close();
			ps.close();
			rs.close();
			System.out.println("listarProyectoXparametro");
			System.out.println(json);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.toString() ); 
			//throw e;
		}
		
		return json;
	}





}
