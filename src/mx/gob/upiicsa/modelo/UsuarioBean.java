package mx.gob.upiicsa.modelo;

public class UsuarioBean {
	private int idUser;
	private String nombre;
	private String apellido;
	private String correo;
	private String password;
	private long telefono;
	private String image;
	private int amigo;
	/*POLIMORFISMO
	 * 
	 * SOBRECARGA
	 * UTILIZAR EL MISMO NOMBRE DE UN M�TODO O EN ESTE CASO CONSTRUCTOR 
	 * PERO RECIBIENDO DIFERENTES PARAMETROS*/
	public UsuarioBean() {
		
	}
	
	public UsuarioBean(String nombre,String apellido,String correo,long telefono,String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.password = password;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAmigo() {
		return amigo;
	}
	public void setAmigo(int amigo) {
		this.amigo = amigo;
	}
	
	
	
}
