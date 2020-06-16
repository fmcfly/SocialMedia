package mx.gob.upiicsa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mx.gob.upiicsa.modelo.UsuarioBean;

public class PerfilDao {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public PerfilDao() {
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
	}
	
	public UsuarioBean updateUser(UsuarioBean usuario) {
		String procUpdate = "{ call actualizar_Usuario (?,?,?,?,?,?)}";
		try {
			CallableStatement ctmt = con.prepareCall(procUpdate);
			ctmt.setInt(1, usuario.getIdUser());
			ctmt.setString(2, usuario.getBirhtdate());
			ctmt.setString(3, usuario.getSexo());
			ctmt.setString(4, usuario.getFrase());
			ctmt.setString(5, usuario.getPais());
			ctmt.setString(6, usuario.getNombreUsuario());
			int registrosAfectados = ctmt.executeUpdate();
			
			if(registrosAfectados < 0) {
				usuario.setIdUser(-1);
			}

		}catch(SQLException sqle) { 
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		return usuario;
	}
		
	public UsuarioBean actualizarInfoLogueado(int idUsuarioLogueado) {
		UsuarioBean user = new UsuarioBean();
		String procedure = "{ call update_login(?)}";
		try {
			CallableStatement cllst = con.prepareCall(procedure);
			cllst.setInt(1,idUsuarioLogueado);
			
			ResultSet rs = cllst.executeQuery();
			if(rs.next()) {
				user.setIdUser(rs.getInt("id"));
				user.setNombre(rs.getString("name"));
				user.setApellido(rs.getString("lastname"));
				user.setTelefono(rs.getLong("tel"));
				user.setImage(rs.getString("image"));
				user.setBirhtdate(rs.getString("cumple"));
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
