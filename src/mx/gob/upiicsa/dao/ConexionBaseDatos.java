package mx.gob.upiicsa.dao;

import java.sql.*;

public class ConexionBaseDatos {
	
	private Connection con;
	
	public ConexionBaseDatos(){
		try{
			//Class.forName("com");
			String connectionURL = "jdbc:mysql://fermcfly.cgkjurfvbaby.us-east-1.rds.amazonaws.com:3306/SocialMedia?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false&" +"user=admin&password=Starwars.97"; 
			con = DriverManager.getConnection(connectionURL);
			if(con != null){
				System.out.println("Conexion exitosa");
			}else{
				System.out.println("Conexion fallida");
			}
		}catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
	}	

	public Connection getConexion(){
		return this.con;
	}
}
