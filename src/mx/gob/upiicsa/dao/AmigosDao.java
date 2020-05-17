package mx.gob.upiicsa.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.gob.upiicsa.modelo.UsuarioBean;

public class AmigosDao {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	
	public AmigosDao() {
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
	}
	
	public ArrayList<UsuarioBean> encontrarAmigos(int idusuario) {
		//Arreglo es un espacio de memoria, y que guarda cierta cantidad de datos de un mismo tipo
		// ArrayList en un arreglo que nos permite guardar objetos en cada uno de sus espacios no es de tipo primitivo, es un objeto
		ArrayList<UsuarioBean> listaAmigos = new ArrayList<UsuarioBean>(); // va a guardar objetos de tipo UsuarioBean 
		
		try {
			String query = "select u.id as id ,u.nombres as nombre, u.apellidos as apellido, u.correo as correo,u.image as image from Usuarios u inner join relationship r on u.id = r.idamigo where r.iduser = "+idusuario;
			st = con.createStatement();
			rs= st.executeQuery(query);
			while(rs.next()) { // rs.next() va por filas 
				UsuarioBean amigo = new UsuarioBean();
				amigo.setIdUser(rs.getInt("id"));
				amigo.setNombre(rs.getString("nombre"));
				amigo.setApellido(rs.getString("apellido"));
				amigo.setCorreo(rs.getString("correo"));
				amigo.setImage(rs.getString("image"));
				listaAmigos.add(amigo);
			}
		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		return listaAmigos;
	}
	
	public ArrayList<UsuarioBean> encontrarlPerfil(String criterioBusqueda,int idUserLogeado){
		ArrayList<UsuarioBean> listaPerfiles = new ArrayList<UsuarioBean>();
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
		}catch(SQLException sql) {
			System.out.println("Error de SQLException"+ sql.getMessage());
		}
		return listaPerfiles;		
	}
	
	public int agregarAmigo(int idLogeado, int idAmigo) {
		int registroInsertado = 0;
		int registroInsertado2 = 0;
		try {
			String queryAgregarAmigo = " insert into relationship values (" + idLogeado +", "+ idAmigo + ");";
			String queryAgregarLogeado =  "insert into relationship values (" + idAmigo +"," + idLogeado + ");";
			
			st = con.createStatement();
			//Cuando ejecutas querys INSERT, UPDATE Y DELETE TE REGRESA UN ENTERO, DE CUANTOS REGISTROS FUERON AFECTADOS
			//Cuando ejecutas querys SELECT, te regresa un RESULTSET.
			registroInsertado = st.executeUpdate(queryAgregarAmigo);
			if(registroInsertado > 0)
				registroInsertado2 = st.executeUpdate(queryAgregarLogeado);
			
		}catch(SQLException sql) {
			System.out.println("Error de SQLException"+ sql.getMessage());
		}
		return registroInsertado2;
	}
}

