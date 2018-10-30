package portafolio.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	public Connection ConMysql(){
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bdindra","root","yamaha");  
			//cn=DriverManager.getConnection("jdbc:mysql://190.116.71.41:1414/bdindra","cursotp3","@@TP3_curso");  
			cn=DriverManager.getConnection("jdbc:mysql://172.30.180.6:1414/bdindra","cursotp3","@@TP3_curso");  
			System.out.println("La conexion se realizo con exito");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Conexion "+e.toString());
		}
		
		return cn; 
	}

}
