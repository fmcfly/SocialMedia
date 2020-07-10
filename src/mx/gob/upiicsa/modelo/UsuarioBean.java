package mx.gob.upiicsa.modelo;

import java.sql.Date;

public class UsuarioBean {
	private int idUser;
	private String nombre;
	private String apellido;
	private String correo;
	private String password;
	private long telefono;
	private String image;
	private int amigo;
	private String frase;
	private String nombreUsuario;
	private String birhtdate;
	private String sexo;
	private String pais;
	private int numeroAmigos;
	
	
	
	@Override
	public String toString() {
		return "UsuarioBean [idUser=" + idUser + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", password=" + password + ", telefono=" + telefono + ", image=" + image + ", amigo=" + amigo
				+ ", frase=" + frase + ", nombreUsuario=" + nombreUsuario + ", birhtdate=" + birhtdate + ", sexo="
				+ sexo + ", pais=" + pais + ", numeroAmigos=" + numeroAmigos + "]";
	}

	public int getNumeroAmigos() {
		return numeroAmigos;
	}

	public void setNumeroAmigos(int numeroAmigos) {
		this.numeroAmigos = numeroAmigos;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	

	public String getBirhtdate() {
		return birhtdate;
	}

	public void setBirhtdate(String birhtdate) {
		this.birhtdate = birhtdate;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	
	/*POLIMORFISMO
	 * 
	 * SOBRECARGA
	 * UTILIZAR EL MISMO NOMBRE DE UN MÉTODO O EN ESTE CASO CONSTRUCTOR 
	 * PERO RECIBIENDO DIFERENTES PARAMETROS*/
	public UsuarioBean() {
		
	}
	
	public UsuarioBean(String nombre,String apellido,String nombreUsuario, String correo,long telefono,String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nombreUsuario = nombreUsuario;
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
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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
