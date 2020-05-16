package mx.gob.upiicsa.dao;

import mx.gob.upiicsa.modelo.UsuarioBean;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	private Connection con;
	
	public UsuarioBean validar(UsuarioBean user) {
		// PRIMERO EJECUTA EL CONSTRUCTOR!!! que hace la conexi�n al motor de base de datos
		ConexionBaseDatos conexionBD = new ConexionBaseDatos ();
		// DESPU�S QUE HAGA LA CONEXI�N, LA VAMOS A OBTENER POR MEDIO DEL GETTER "getConexion()" 
		con = conexionBD.getConexion();
		try {
			String cadena = "select * from Usuarios where correo ='"+user.getCorreo() +  "' and password = '" + user.getPassword()+"'  ;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(cadena);
			if(rs.next()) {
				user.setIdUser(rs.getInt("id"));
				user.setNombre(rs.getString("nombres"));
				user.setApellido(rs.getString("apellidos"));
				user.setTelefono(rs.getLong("telefono"));
			}else {
				user.setIdUser(-1); // de que no encontr� usuario
			}
		}catch(SQLException sqle) {
			System.out.println("Error de SqlException");
		}
		return user;
	}
}
