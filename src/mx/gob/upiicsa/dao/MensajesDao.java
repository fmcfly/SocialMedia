package mx.gob.upiicsa.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.gob.upiicsa.modelo.MensajeBean;

public class MensajesDao {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public MensajesDao(){
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
	}
	
	public ArrayList<MensajeBean> getMensajes(int idAmigo, int idLogin) {
		
		ArrayList<MensajeBean> mensajesEncontrados = new ArrayList<MensajeBean>(); 
		
		String queryMensajes ="select  id_chat, id_usuario, texto, fecha  from mensajes\r\n" + 
				"	where id_chat in (select id_chat from Rel_Chat_Usuarios where id_usuario = "+idAmigo+" and\r\n" + 
				"	id_chat in (select id_chat from Rel_Chat_Usuarios where id_usuario = "+idLogin+")) order by fecha asc";
		try {
			st = con.createStatement();
			rs = st.executeQuery(queryMensajes);
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
			st = con.createStatement();
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
	
	public void crearChat(MensajeBean msjNuevo,int idAmigo) {
		String queryCrearChat =  "select max (id_chat) as ultimochat from Chat";
		
		try {
		st= con.createStatement();
		
		rs= st.executeQuery(queryCrearChat);
		rs.next();
		System.out.println(rs.getInt("ultimochat"));
		int chatMax = rs.getInt("ultimochat");
		chatMax++;
		// CREAMOS EL ICHAT 
		String queryInsertar = "insert into chat values(" + chatMax +",getdate())";
		int registroInsertar = st.executeUpdate(queryInsertar);
		if(registroInsertar >0) {// SI SE CREO CORRECTAEMNTE CON EL ID PROSEGUIMOS A CREAR LA RELACIÓN DE LOS UAURIOS 
			System.out.println("Se creo chat");
			String queryRelacionLogeado = "insert into Rel_Chat_Usuarios values("+chatMax+","+ msjNuevo.getIdUsuario()+")";
			String queryRelacionAmigo = "insert into Rel_Chat_Usuarios values("+chatMax+","+idAmigo+")";
			
			int insercionLogeado = st.executeUpdate(queryRelacionLogeado);
			int insercionAmigo = st.executeUpdate(queryRelacionAmigo);
			if(insercionAmigo > 0 && insercionLogeado  >0) {
				msjNuevo.setIdChat(chatMax);
				guardarMensaje(msjNuevo);
			}
			
			
		}else {
			System.out.println("Algo fallo");
		}
		
		}catch(SQLException sqle) {
			System.out.println("Error de SQLException"+ sqle.getMessage());
		}
	}
}
