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
		//response.getWriter().append("Hola MUNDO ").append(request.getContextPath());
		//NOS VA A SERVIR PARA QUITAR LA SESIÓN
		HttpSession sesion = request.getSession();
		sesion.removeAttribute("usuario");
		sesion.removeAttribute("amigos"); 
		System.out.println("Si entro");
		//sesion.invalidate();//Invalida esta sesión y luego desvincula cualquier objeto vinculado a ella.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(true);
		
		UsuarioBean user = new UsuarioBean();
		user.setCorreo(request.getParameter("correo"));
		user.setPassword(request.getParameter("password"));
		
		LoginDao login = new LoginDao();
		UsuarioBean usuarioEncontrado =login.validar(user);
		if(usuarioEncontrado.getIdUser() != -1) {
			AmigosDao amigos = new AmigosDao();
			ArrayList<UsuarioBean> listaAmigos = amigos.encontrarAmigos(usuarioEncontrado.getIdUser());
			sesion.removeAttribute("mensaje");
			sesion.setAttribute("usuario", usuarioEncontrado);
			sesion.setAttribute("amigos", listaAmigos);
			request.getRequestDispatcher("/usuario.jsp").forward(request,response);
		}else {// estan mal las credenciales
			sesion.setAttribute("mensaje","Credenciales invalidas");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
			//response.getWriter().append("Invalido");
		}
		
	}

}
