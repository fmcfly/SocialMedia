package mx.gob.upiicsa.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mx.gob.upiicsa.modelo.UsuarioBean;

public class RegistroDao {
	private Connection con;
	private Statement st;
	private ResultSet rs;
		
		
		
	public RegistroDao() {
		ConexionBaseDatos conexionBD = new ConexionBaseDatos();
		con = conexionBD.getConexion();
	}
	//	tipodato  nombreMétodo   (parametro)
	public int crearUsuario(UsuarioBean userNew) {
		int registroInsertado = 0;
			
		String queryUltimoID = "Select MAX(id) as ultimoID  from Usuarios";
			
		try {
			st = con.createStatement();
			rs = st.executeQuery(queryUltimoID);
			rs.next();
			System.out.println(rs.getInt("ultimoID"));
			int idMax = rs.getInt("ultimoID");
			idMax ++;
			
			String AgregarNewUser = "insert into Usuarios(id,nombres,apellidos,correo,telefono,password) values("+idMax+""
					+ ",'"+userNew.getNombre()+"','"+userNew.getApellido()+"','"+userNew.getCorreo()+"',"+userNew.getTelefono()+",'"+userNew.getPassword()+"');";
			st = con.createStatement();
			registroInsertado  = st.executeUpdate(AgregarNewUser);
			if(registroInsertado < 1){
				return -1;
			}
		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		return registroInsertado;
	}
}
	
	
