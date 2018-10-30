package portafolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import portafolio.util.*;
import portafolio.bean.ProyectoBean;
import portafolio.interfaces.ProyectoDao;

public class MysqlProyectoDao  implements ProyectoDao {
	
	Connection cn = null; 
    PreparedStatement ps = null;
    ResultSet rs = null;

	@Override
	public ArrayList<ProyectoBean> listarProyecto() {
		// TODO Auto-generated method stub
			ArrayList<ProyectoBean> listaProyecto = new ArrayList<ProyectoBean>();
		
		try {
			
			Conexion objCn = new Conexion();
			//String sql = "select id_distrito, distrito from tb_distrito;";
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
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR : " + e.toString() ); 
			//throw e;
		}
		
		return listaProyecto;
	}

}
