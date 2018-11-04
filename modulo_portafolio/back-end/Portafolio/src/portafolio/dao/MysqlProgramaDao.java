package portafolio.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




import portafolio.bean.ProgramaBean;
import portafolio.interfaces.ProgramaDao;
import portafolio.util.Conexion;

public class MysqlProgramaDao implements ProgramaDao{

	@Override
	public String listarPrograma() {
		// TODO Auto-generated method stub
		Connection cn = null; 
	    PreparedStatement ps = null;
	    ResultSet rs = null;
			//ArrayList<ProyectoBean> listaProyecto = new ArrayList<ProyectoBean>();
		 String json="";
		 StringBuilder cadena = new StringBuilder();
		try {
			
			Conexion objCn = new Conexion();
			String sql ="{call indrass_all_programa()}";
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
				cadena.append("\""+"co_programa"+"\""+":"+rs.getString("co_programa")+",");
				cadena.append("\""+"no_programa"+"\""+":"+"\""+rs.getString("no_programa")+"\""+",");
				cadena.append("\""+"de_programa"+"\""+":"+"\""+rs.getString("de_programa")+"\""+",");
				cadena.append("\""+"fe_registro"+"\""+":"+"\""+rs.getString("fe_registro")+"\""+",");
				cadena.append("\""+"fe_inicio"+"\""+":"+"\""+rs.getString("fe_inicio")+"\""+",");
				cadena.append("\""+"fe_fin"+"\""+":"+"\""+rs.getString("fe_fin")+"\""+",");
				cadena.append("\""+"no_categoria"+"\""+":"+"\""+rs.getString("no_categoria")+"\""+",");
				cadena.append("\""+"no_estado"+"\""+":"+"\""+rs.getString("no_estado")+"\""+",");
				cadena.append("\""+"no_prioridad"+"\""+":"+"\""+rs.getString("no_prioridad")+"\""+",");
				//cadena.append("\""+"no_programa"+"\""+":"+"\""+rs.getString("no_programa")+"\""+",");
				cadena.append("\""+"nombre"+"\""+":"+"\""+rs.getString("nombre")+"\"");
				cadena.append("},");
				
			}
			cadena.append("]");
			
			json= cadena.toString().replace(",]", "]");
			cn.close();
			ps.close();
			rs.close();
			System.out.println("listarPrograma");
			System.out.println(json);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.toString() ); 
			//throw e;
		}
		
		return json;
	}

	@Override
	public String listarProgramaXid(int co_programa) {
		// TODO Auto-generated method stub
				Connection cn = null; 
			    PreparedStatement ps = null;
			    ResultSet rs = null;
					//ArrayList<ProyectoBean> listaProyecto = new ArrayList<ProyectoBean>();
				 String json="";
				 StringBuilder cadena = new StringBuilder();
				try {
					
					Conexion objCn = new Conexion();
					String sql ="{call indrass_id_programa(?)}";
					cn=objCn.ConMysql();
					ps=cn.prepareStatement(sql);
					ps.setString(1, Integer.toString( co_programa));
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
						cadena.append("\""+"co_programa"+"\""+":"+rs.getString("co_programa")+",");
						cadena.append("\""+"no_programa"+"\""+":"+"\""+rs.getString("no_programa")+"\""+",");
						cadena.append("\""+"de_programa"+"\""+":"+"\""+rs.getString("de_programa")+"\""+",");
						cadena.append("\""+"fe_registro"+"\""+":"+"\""+rs.getString("fe_registro")+"\""+",");
						cadena.append("\""+"fe_inicio"+"\""+":"+"\""+rs.getString("fe_inicio")+"\""+",");
						cadena.append("\""+"fe_fin"+"\""+":"+"\""+rs.getString("fe_fin")+"\""+",");
						cadena.append("\""+"no_categoria"+"\""+":"+"\""+rs.getString("no_categoria")+"\""+",");
						cadena.append("\""+"no_estado"+"\""+":"+"\""+rs.getString("no_estado")+"\""+",");
						cadena.append("\""+"no_prioridad"+"\""+":"+"\""+rs.getString("no_prioridad")+"\""+",");
						//cadena.append("\""+"no_programa"+"\""+":"+"\""+rs.getString("no_programa")+"\""+",");
						cadena.append("\""+"nombre"+"\""+":"+"\""+rs.getString("nombre")+"\"");
						cadena.append("},");
						
					}
					cadena.append("]");
					
					json= cadena.toString().replace(",]", "]");
					cn.close();
					ps.close();
					rs.close();
					System.out.println("listarProgramaXid");
					System.out.println(json);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ERROR : " + e.toString() ); 
					//throw e;
				}
				
				return json;
	}

	@Override
	public String listarProgramaXFechas(String fecha1, String fecha2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertarPrograma(ProgramaBean objPrograma) {
		// TODO Auto-generated method stub
		int i = 0;
		int co_Programa = 0;
		Connection cn = null; 
		CallableStatement  ps = null;
		try {
			System.out.println("insertarPrograma");
			Conexion objCn = new Conexion();
			String sql ="{call indrasi_programa(?,?,?,?,?,?,?,?,?,?)}";
			
			cn=objCn.ConMysql();
			ps=cn.prepareCall(sql);
			System.out.println(objPrograma.getNo_programa().toString());
			ps.setString(1,objPrograma.getNo_programa());
			ps.setString(2,objPrograma.getDe_programa());
			ps.setString(3,objPrograma.getFe_inicio());
			ps.setString(4,objPrograma.getFe_fin());
			ps.setInt(5,objPrograma.getCo_portafolio());
			ps.setInt(6,objPrograma.getCo_usuarioregistro());
			ps.setInt(7,objPrograma.getCo_estado());
			ps.setInt(8,objPrograma.getCo_categoria());
			ps.setInt(9,objPrograma.getCo_prioridad());
			
			
			ps.registerOutParameter(10, java.sql.Types.INTEGER);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													
			
			i=ps.executeUpdate(); 
			
			co_Programa= ps.getInt(10);
			
			cn.close();
			ps.close();
			
			System.out.println(i);
			System.out.println(co_Programa);
			System.out.println("insertarPrograma");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.toString() ); 
			//throw e;
		}
		
		return co_Programa;
	}

}
