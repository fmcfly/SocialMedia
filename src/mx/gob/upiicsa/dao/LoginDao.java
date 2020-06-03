package mx.gob.upiicsa.dao;

import mx.gob.upiicsa.modelo.UsuarioBean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	private Connection con;
	
	public UsuarioBean validar(UsuarioBean user) {
		// PRIMERO EJECUTA EL CONSTRUCTOR!!! que hace la conexión al motor de base de datos
		ConexionBaseDatos conexionBD = new ConexionBaseDatos ();
		// DESPUÉS QUE HAGA LA CONEXIÓN, LA VAMOS A OBTENER POR MEDIO DEL GETTER "getConexion()" 
		con = conexionBD.getConexion();
		/* try {
			String cadena = "select * from Usuarios where correo ='"+user.getCorreo() +  "' and password = '" + user.getPassword()+"'  ;";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(cadena);
			if(rs.next()) {
				user.setIdUser(rs.getInt("id"));
				user.setNombre(rs.getString("nombres"));
				user.setApellido(rs.getString("apellidos"));
				user.setTelefono(rs.getLong("telefono"));
			}else {
				user.setIdUser(-1); // de que no encontró usuario
			}
		}catch(SQLException sqle) {
			System.out.println("Error de SqlException");
		}*/
		
		String procedure = "{ call validar (?,?)}";
		try {
			CallableStatement cllst = con.prepareCall(procedure);
			cllst.setString(1,user.getCorreo());
			cllst.setString(2,user.getPassword());
			
			ResultSet rs = cllst.executeQuery();
			if(rs.next()) {
				user.setIdUser(rs.getInt("id"));
				user.setNombre(rs.getString("name"));
				user.setApellido(rs.getString("lastname"));
				user.setTelefono(rs.getLong("tel"));
				user.setImage(rs.getString("image"));
				user.setBirhtdate(rs.getDate("cumple"));
				user.setSexo(rs.getString("sexo"));
				user.setFrase(rs.getString("frase"));
				user.setPais(rs.getString("pais"));
				user.setNombreUsuario(rs.getString("user_name"));
				user.setNumeroAmigos(rs.getInt("numeroAmigos"));
				
			}else {
				user.setIdUser(-1); // de que no encontró usuario
			}
		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		
		return user;
	}
}
