package mx.gob.upiicsa.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.upiicsa.dao.RegistroDao;
import mx.gob.upiicsa.modelo.UsuarioBean;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession(true);
		long telefono = Long.parseLong(request.getParameter("Telefono"));
		RegistroDao newUsuario = new RegistroDao();
		UsuarioBean usuarioNuevo = new UsuarioBean(request.getParameter("Nombre"),request.getParameter("Apellido")
				,request.getParameter("Correo"),telefono,request.getParameter("Contrasena"));
		
		newUsuario.crearUsuario(usuarioNuevo);

		doGet(request, response);
		
	}

}
