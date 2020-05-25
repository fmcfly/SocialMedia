package mx.gob.upiicsa.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.upiicsa.dao.AmigosDao;
import mx.gob.upiicsa.dao.LoginDao;
import mx.gob.upiicsa.dao.MensajesDao;
import mx.gob.upiicsa.modelo.UsuarioBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Hola MUNDO ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*System.out.println(request.getParameter("correo"));
		System.out.println(request.getParameter("password"));*/
		//doGet(request, response);
		HttpSession sesion = request.getSession(true);
		
		UsuarioBean user = new UsuarioBean();
		user.setCorreo(request.getParameter("correo"));
		user.setPassword(request.getParameter("password"));
		
		LoginDao login = new LoginDao();
		UsuarioBean usuarioEncontrado =login.validar(user);
		// Estan bien las credenciales
		if(usuarioEncontrado.getIdUser() != -1) {
			//response.getWriter().append("BIENVENIDO");
			
			// SI las crendenciales son validas quieres que posiblemente este usuario tenga amigos
			//Instanciamos un objeto de tipo AmigosDao
			AmigosDao amigos = new AmigosDao();
			// de este objeto vamos a llamar al metodo listaAmigos(idusuario)
			// como en el usuarioEncontrado ya tenemos el ID lo único que tenemos que hacer es enviarle ese dato como parametro al método listaAmigos
			ArrayList<UsuarioBean> listaAmigos = amigos.encontrarAmigos(usuarioEncontrado.getIdUser());
			// ciclo for 
			// EN ARREGLO primitivos si se puede poner .length para saber la longitud,
			// EN ARRAYLIST para saber su tamaño se necesita el método .size()
			/*for(int i = 0; i < listaAmigos.size();i++ ) {
				System.out.println(listaAmigos.get(i).getNombre());
			}*/
			// FOR EACH nos sirve para iterar ArrayList ya que crea un objeto del mismo tipo que viene configurado en el ArrayList
			/*for(UsuarioBean amigo:listaAmigos) {
				System.out.println(amigo.getNombre());
			}*/
			sesion.removeAttribute("mensaje");
			sesion.setAttribute("usuario", usuarioEncontrado);
			sesion.setAttribute("amigos", listaAmigos);
			request.getRequestDispatcher("/principal.jsp").forward(request,response);
		}else {// estan mal las credenciales
			sesion.setAttribute("mensaje","Credenciales invalidas");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
			//response.getWriter().append("Invalido");
		}
		
	}

}
