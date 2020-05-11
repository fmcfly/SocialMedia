package mx.gob.upiicsa.dao;

import java.sql.*;

public class ConexionBaseDatos {
	
	private Connection con;
	
	public ConexionBaseDatos(){
		try{
			//Class.forName("com");
			String connectionURL = "jdbc:sqlserver://EQUIPO\\SQLEXPRESS:1433;database=socialmedia;user=sa;password=administrador;trustServerCertificate=false;";
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
