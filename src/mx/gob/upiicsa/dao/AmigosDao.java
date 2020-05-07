package mx.gob.upiicsa.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.gob.upiicsa.modelo.UsuarioBean;

public class AmigosDao {
	private Connection con;
	
	public ArrayList<UsuarioBean> encontrarAmigos(int idusuario) {
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		//Arreglo es un espacio de memoria, y que guarda cierta cantidad de datos de un mismo tipo
		// ArrayList en un arreglo que nos permite guardar objetos en cada uno de sus espacios no es de tipo primitivo, es un objeto
		ArrayList<UsuarioBean> listaAmigos = new ArrayList<UsuarioBean>(); // va a guardar objetos de tipo UsuarioBean 
		
		con = conexionBD.getConexion();
		try {
			String query = "select u.id as id ,u.nombres as nombre, u.apellidos as apellido, u.correo as correo from usuarios u inner join relationship r on u.id = r.idamigo where r.iduser = "+idusuario;
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery(query);
			while(rs.next()) { // rs.next() va por filas 
				UsuarioBean amigo = new UsuarioBean();
				amigo.setIdUser(rs.getInt("id"));
				amigo.setNombre(rs.getString("nombre"));
				amigo.setApellido(rs.getString("apellido"));
				amigo.setCorreo(rs.getString("correo"));
				listaAmigos.add(amigo);
			}
		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		return listaAmigos;
	}
}

