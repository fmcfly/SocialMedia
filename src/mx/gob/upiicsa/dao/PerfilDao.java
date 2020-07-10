package mx.gob.upiicsa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.gob.upiicsa.modelo.UsuarioBean;

public class PerfilDao {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public PerfilDao() {}
	
	public UsuarioBean updateUser(UsuarioBean usuario) {
		String procUpdate = "{ call actualizar_Usuario (?,?,?,?,?,?)}";
		
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		
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
			con.close();

		}catch(SQLException sqle) { 
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		return usuario;
	}
		
	public UsuarioBean getInfoUserById(int idUsuario) {
		UsuarioBean user = new UsuarioBean();
		String procedure = "{ call update_login(?)}";
		
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		
		try {
			CallableStatement cllst = con.prepareCall(procedure);
			cllst.setInt(1,idUsuario);
			
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
			con.close();

		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		
		return user;
	}
	
	//DBERÍA IR EN PERFILDAO
	public ArrayList<UsuarioBean> encontrarlPerfil(String criterioBusqueda,int idUserLogeado){
		ArrayList<UsuarioBean> listaPerfiles = new ArrayList<UsuarioBean>();
		
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		
		try {
			//String queryEncontarPerfil = "select u.id as id ,u.nombres as nombre, u.apellidos as apellido, u.correo as correo,u.image as image from Usuarios u where nombres like  '%"+criterioBusqueda+"%' or apellidos like '%"+criterioBusqueda+"%'";
			String queryEncontarPerfil = "select u.id as id ,u.nombres as nombre, u.apellidos as apellido, u.correo as correo,u.image as image,\r\n" + 
					"	CASE\r\n" + 
					"		WHEN idamigo is not null THEN 1\r\n" + 
					"		WHEN idamigo is null THEN 0\r\n" + 
					"	END as amigo\r\n" + 
					"from Usuarios u left join (SELECT idamigo from relationship where idamigo in (SELECT id FROM Usuarios u where nombres like '%"+ criterioBusqueda +"%' or u.apellidos like '%"+criterioBusqueda+"%') and iduser ="+idUserLogeado+") x\r\n" + 
					"on u.id = x.idamigo where nombres like '%"+criterioBusqueda +"%' or u.apellidos like '%"+criterioBusqueda+"%'";
			st = con.createStatement();
			rs = st.executeQuery(queryEncontarPerfil);
			
			while(rs.next()) {
				UsuarioBean usuario = new UsuarioBean();
				usuario.setIdUser(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setImage(rs.getString("image"));
				usuario.setAmigo(rs.getInt("amigo"));
				listaPerfiles.add(usuario);
			}
			con.close();

		}catch(SQLException sql) {
			System.out.println("Error de SQLException"+ sql.getMessage());
		}
		return listaPerfiles;		
	}
}
