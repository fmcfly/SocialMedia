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
	
	public List<MensajeBean> getMesagesByProfile(int idPerfil, int idLogin) {
		
		List<MensajeBean> mensajesEncontrados = new ArrayList<MensajeBean>(); 
		
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		
		String obtenerMensajesByPerfil = "{call obtener_mensajes_by_perfil(?,?)}";
		try {
			
			ctmt = con.prepareCall(obtenerMensajesByPerfil);
			ctmt.setInt(1, idPerfil);
			ctmt.setInt(2, idLogin);
			rs = ctmt.executeQuery();
			while(rs.next()) {
				MensajeBean mensaje = new MensajeBean();
				mensaje.setIdMensaje(rs.getInt("id_message"));
				mensaje.setIdChat(rs.getInt("id_chat"));
				mensaje.setIdUsuario(rs.getInt("id_usuario"));
				mensaje.setTexto(rs.getString("texto"));
				mensaje.setFecha(rs.getDate("fecha"));
				mensajesEncontrados.add(mensaje);
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
	
	public String crearChat(MensajeBean msjNuevo,int idPerfil) {
		String procCrearChat = "{ call crear_chat(?,?)}";
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		String mensajeEnviado = "";
		try {
			ctmt = con.prepareCall(procCrearChat);
			ctmt.setInt(1, msjNuevo.getIdUsuario());
			ctmt.setInt(2, idPerfil);
			rs = ctmt.executeQuery();
			if(rs.next()) {
				msjNuevo.setIdChat(rs.getInt(1));
				mensajeEnviado = this.guardarMensaje(msjNuevo);
			}
			con.close();
		}catch(SQLException sqle) {
			System.out.println("Error de SQLException"+ sqle.getMessage());
		}
		
		return mensajeEnviado;
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
	
	public int mensajesNoVistos(int idLogueado){
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		String contarMensajes = "{call contar_mensajes(?)}";
		int cantidad = 0;
		try {
			ctmt = con.prepareCall(contarMensajes);
			ctmt.setInt(1,idLogueado);// mandando el valor al proc
			rs = ctmt.executeQuery();
			
			if(rs.next()) {
				cantidad = rs.getInt(1);
			}
			con.close();
		}catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return cantidad;
	}
	
	public int mensajesVistos(int idChatSeleccionado,int idUsuarioLogueado) {
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
		int  listo = 0;
		String mensajeVisto = "{call mensaje_visto(?,?)}";
		try {
			ctmt = con.prepareCall(mensajeVisto);
			ctmt.setInt(1, idChatSeleccionado);
			ctmt.setInt(2, idUsuarioLogueado);

			listo = ctmt.executeUpdate();
			con.close();
		}catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return listo;
	}
}
