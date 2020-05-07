package mx.gob.upiicsa.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.upiicsa.dao.ConexionBaseDatos;
import mx.gob.upiicsa.dao.LoginDao;
import mx.gob.upiicsa.modelo.UsuarioBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
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
		UsuarioBean user = new UsuarioBean();
		user.setCorreo(request.getParameter("correo"));
		user.setPassword(request.getParameter("password"));
		
		LoginDao login = new LoginDao();
		UsuarioBean respuesta =login.validar(user);
		if(respuesta.getIdUser() != -1) {
			response.getWriter().append("BIENVENIDO");
		}else {
			response.getWriter().append("Invalido");
		}
		
	}

}
