package mx.gob.upiicsa.dao;

import java.sql.CallableStatement;
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
	//	tipodato  nombreM�todo   (parametro)
	public int crearUsuario(UsuarioBean userNew) {
		int codigo = 0;
		/*	
		String checarCorreo = "select id from Usuarios where correo = '"+userNew.getCorreo()+"'";
		String queryUltimoID = "Select MAX(id) as ultimoID  from Usuarios";
		
		try {
			st = con.createStatement();
			
			rs = st.executeQuery(checarCorreo);
			
			
			if(rs.next()) {
				System.out.println("Ya hay una cuenta as� papu");
				return -2; //ya hay una cuenta identica de correo
			}else {

				rs = st.executeQuery(queryUltimoID);
				rs.next();
				System.out.println(rs.getInt("ultimoID"));
				int idMax = rs.getInt("ultimoID");
				idMax ++;
				
				String AgregarNewUser = "insert into Usuarios(id,nombres,apellidos,user_name,correo,telefono,password,image) values("+idMax+""
						+ ",'"+userNew.getNombre()+"','"+userNew.getApellido()+"','"+userNew.getNombreUsuario()+"','"+userNew.getCorreo()+"',"+userNew.getTelefono()+",'"+userNew.getPassword()+"','"+userNew.getImage()+"');";
			
				*/
		String alta_usuario = "{call alta_usuario (?,?,?,?,?,?,?) }";
		try {
			CallableStatement ctmt = con.prepareCall(alta_usuario);	
			ctmt.setString(1, userNew.getNombre());
			ctmt.setString(2, userNew.getApellido());
			ctmt.setString(3, userNew.getCorreo());
			ctmt.setLong(4, userNew.getTelefono());
			ctmt.setString(5, userNew.getPassword());
			ctmt.setString(6, userNew.getImage());
			ctmt.setString(7,  userNew.getNombreUsuario());
			
			rs = ctmt.executeQuery();
			if(rs.next()) {
				codigo = rs.getInt("codigo");
			}
			con.close();

		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		return codigo;
	}
}
	
	
