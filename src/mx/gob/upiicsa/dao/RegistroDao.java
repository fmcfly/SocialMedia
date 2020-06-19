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
			
		String checarCorreo = "select id from Usuarios where correo = '"+userNew.getCorreo()+"'";
		String queryUltimoID = "Select MAX(id) as ultimoID  from Usuarios";
		
		try {
			st = con.createStatement();
			
			rs = st.executeQuery(checarCorreo);
			
			
			if(rs.next()) {
				System.out.println("Ya hay una cuenta así papu");
				return -2; //ya hay una cuenta identica de correo
			}else {

				rs = st.executeQuery(queryUltimoID);
				rs.next();
				System.out.println(rs.getInt("ultimoID"));
				int idMax = rs.getInt("ultimoID");
				idMax ++;
				
				String AgregarNewUser = "insert into Usuarios(id,nombres,apellidos,user_name,correo,telefono,password,image) values("+idMax+""
						+ ",'"+userNew.getNombre()+"','"+userNew.getApellido()+"','"+userNew.getNombreUsuario()+"','"+userNew.getCorreo()+"',"+userNew.getTelefono()+",'"+userNew.getPassword()+"','"+userNew.getImage()+"');";
				st = con.createStatement();
				registroInsertado  = st.executeUpdate(AgregarNewUser);
				if(registroInsertado < 1){
					return -1; //no se pudo registrar
				}
			}
			con.close();

		}catch(SQLException sqle) {
			System.out.println("Error de SqlException" + sqle.getMessage());
		}
		return registroInsertado;
	}
}
	
	
