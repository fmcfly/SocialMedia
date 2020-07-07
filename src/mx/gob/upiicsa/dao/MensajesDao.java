package mx.gob.upiicsa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import mx.gob.upiicsa.modelo.MensajeBean;
import mx.gob.upiicsa.modelo.UsuarioChat;

public class MensajesDao {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private CallableStatement ctmt;
	public MensajesDao(){}
	
	public ArrayList<MensajeBean> getMensajes(int idAmigo, int idLogin) {
		
		ArrayList<MensajeBean> mensajesEncontrados = new ArrayList<MensajeBean>(); 
		
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		
		String queryMensajes ="select id_message, id_chat, id_usuario, texto, fecha  from Mensajes\r\n" + 
				"	where id_chat in (select id_chat from Rel_Chat_Usuarios where id_usuario = "+idAmigo+" and\r\n" + 
				"	id_chat in (select id_chat from Rel_Chat_Usuarios where id_usuario = "+idLogin+")) order by fecha asc";
		try {
			st = con.createStatement();
			rs = st.executeQuery(queryMensajes);
			while(rs.next()){
				MensajeBean msjBean = new MensajeBean();
				msjBean.setIdMensaje(rs.getInt("id_message"));
				msjBean.setIdChat(rs.getInt("id_chat"));
				msjBean.setIdUsuario(rs.getInt("id_usuario"));
				msjBean.setTexto(rs.getString("texto"));
				msjBean.setFecha(rs.getDate("fecha"));
				mensajesEncontrados.add(msjBean);
			}
			con.close();

		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		return mensajesEncontrados;
	}
	
	public List<MensajeBean> obtenerMensajes(int idChat){
		List<MensajeBean> mensajesEncontrados = new ArrayList<>();
		
		String procMensajes = "{call obtenerMensajes(?)}";
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		try {
			ctmt = con.prepareCall(procMensajes);
			ctmt.setInt(1, idChat);
			rs = ctmt.executeQuery();
			while(rs.next()) {
				MensajeBean msjBean = new MensajeBean();
				msjBean.setIdMensaje(rs.getInt("id_message"));
				msjBean.setIdChat(rs.getInt("id_chat"));
				msjBean.setIdUsuario(rs.getInt("id_usuario"));
				msjBean.setTexto(rs.getString("texto"));
				msjBean.setFecha(rs.getDate("fecha"));
				mensajesEncontrados.add(msjBean);
			}
			con.close();
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return mensajesEncontrados;
	}
	
	public String guardarMensaje(MensajeBean msjNuevo) {
		Gson gson = new Gson();
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		
		String insertar_mensaje = "{call insertar_mensaje (?,?,?)}";
		
		try {
			CallableStatement ctmt = con.prepareCall(insertar_mensaje);
			ctmt.setInt (1,msjNuevo.getIdChat());
			ctmt.setInt (2,msjNuevo.getIdUsuario());
			ctmt.setString(3, msjNuevo.getTexto());
			
			rs = ctmt.executeQuery();		
			if(rs.next()) {
				msjNuevo.setIdMensaje(rs.getInt("id_message"));
				msjNuevo.setFecha(rs.getDate("fecha"));
				msjNuevo.setTexto(rs.getString("texto"));
			}else {
				msjNuevo.setIdMensaje(0);
			}
			con.close();
		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}	
		
		return new String(gson.toJson(msjNuevo));
	}
	
	public void crearChat(MensajeBean msjNuevo,int idAmigo) {
		String queryCrearChat =  "select MAX(id_chat) as ultimochat from chat";
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		try {
			st= con.createStatement();
			
			rs= st.executeQuery(queryCrearChat);
			rs.next();
			System.out.println(rs.getInt("ultimochat"));
			int chatMax = rs.getInt("ultimochat");
			chatMax++;
			// CREAMOS EL ICHAT 
			String queryInsertar = "insert into chat values(" + chatMax +",curdate())";
			int registroInsertar = st.executeUpdate(queryInsertar);
			if(registroInsertar >0) {// SI SE CREO CORRECTAEMNTE CON EL ID PROSEGUIMOS A CREAR LA RELACIÓN DE LOS UAURIOS 
				System.out.println("Se creo chat");
				String queryRelacionLogeado = "insert into Rel_Chat_Usuarios values("+chatMax+","+ msjNuevo.getIdUsuario()+")";
				String queryRelacionAmigo = "insert into Rel_Chat_Usuarios values("+chatMax+","+idAmigo+")";
				
				int insercionLogeado = st.executeUpdate(queryRelacionLogeado);
				int insercionAmigo = st.executeUpdate(queryRelacionAmigo);
				if(insercionAmigo > 0 && insercionLogeado > 0) {
					msjNuevo.setIdChat(chatMax);
					guardarMensaje(msjNuevo);
				}
			}else {
				System.out.println("Algo fallo");
			}
			
			con.close();
		}catch(SQLException sqle) {
			System.out.println("Error de SQLException"+ sqle.getMessage());
		}
	}
	
	public List<UsuarioChat> infoChat(int idLogueado)
	{
		List<UsuarioChat> usuariosChat = new ArrayList<>();
		
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		String infoChatUsuario = "{call info_chat_usuario (?)}";
		try {
			ctmt = con.prepareCall(infoChatUsuario);
			ctmt.setInt(1, idLogueado);
			rs = ctmt.executeQuery();
			while(rs.next()) {
				
				UsuarioChat userChat = new UsuarioChat();
				userChat.setIdChat(rs.getInt("id_chat"));
				userChat.setNombre(rs.getString("nombre"));
				userChat.setImage(rs.getString("image"));
				
				usuariosChat.add(userChat);
			}
			con.close();
		}catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		
		return usuariosChat;
	}
	
}
