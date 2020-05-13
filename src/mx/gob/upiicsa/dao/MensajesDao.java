package mx.gob.upiicsa.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.gob.upiicsa.modelo.MensajeBean;

public class MensajesDao {
	private Connection con;
	
	public MensajesDao(){
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
	}
	
	public ArrayList<MensajeBean> getMensajes(int idAmigo, int idLogin) {
		
		ArrayList<MensajeBean> mensajesEncontrados = new ArrayList<MensajeBean>(); 
		
		String queryMensajes ="select id_chat, id_usuario, texto, fecha from Mensajes where id_chat in "
				+ "(select id_chat from Mensajes where id_usuario ="+ idAmigo+" and id_chat in "
				+ "(select id_chat from Mensajes where id_usuario = "+ idLogin +")) order by fecha asc";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(queryMensajes);
			while(rs.next()){
				MensajeBean msjBean = new MensajeBean();
				msjBean.setIdChat(rs.getInt("id_chat"));
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
	
	public void guardarMensaje(MensajeBean msjNuevo) {
		String queryInsertar ="insert into Mensajes values ("+ msjNuevo.getIdChat() + "," + msjNuevo.getIdUsuario() + ",'" 
							+ msjNuevo.getTexto() + "',getdate())";
		
		try {
			Statement st = con.createStatement();
			int registrosInsertados = st.executeUpdate(queryInsertar);
			if(registrosInsertados > 0) {
				System.out.println("Todo en orden");
			}else {
				System.out.println("Algo salio mal");
			}
		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		
	}
}
