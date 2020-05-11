package mx.gob.upiicsa.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.gob.upiicsa.modelo.MensajeBean;

public class MensajesDao {
	private Connection con;
	
	public ArrayList<MensajeBean> getMensajes(int idAmigo, int idLogin) {
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		ArrayList<MensajeBean> mensajesEncontrados = new ArrayList<MensajeBean>(); 
		
		String queryMensajes ="select id_usuario, texto, fecha from Mensajes where id_chat in "
				+ "(select id_chat from Mensajes where id_usuario ="+ idAmigo+" and id_chat in "
				+ "(select id_chat from Mensajes where id_usuario = "+ idLogin +")) order by fecha asc";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(queryMensajes);
			while(rs.next()){
				MensajeBean msjBean = new MensajeBean();
				msjBean.setIdUsuario(rs.getInt("id_usuario"));
				msjBean.setTexto(rs.getString("texto"));
				msjBean.setFecha(rs.getDate("fecha"));
				mensajesEncontrados.add(msjBean);
			}
		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		return mensajesEncontrados;
	}
	
}
