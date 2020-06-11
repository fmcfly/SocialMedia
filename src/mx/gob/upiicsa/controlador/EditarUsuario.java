package mx.gob.upiicsa.controlador;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.upiicsa.dao.PerfilDao;
import mx.gob.upiicsa.modelo.UsuarioBean;

/**
 * Servlet implementation class EditarUsuario
 */
@WebServlet("/EditarUsuario")
public class EditarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarUsuario() {
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
		System.out.println("POST EDITAR");
		HttpSession sesion = request.getSession();
		
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		
		usuarioLogeado.setFrase(request.getParameter("estadoPerfil"));
		usuarioLogeado.setNombreUsuario(request.getParameter("nombreUsuario"));
		usuarioLogeado.setBirhtdate(request.getParameter("cumple"));
		usuarioLogeado.setPais(request.getParameter("pais"));
		usuarioLogeado.setSexo(request.getParameter("sexo"));
		
		
		
		//request.getParameter("name");
		
		
		PerfilDao perfil = new PerfilDao();
		
		sesion.setAttribute("usuario",perfil.updateUser(usuarioLogeado));
		request.getRequestDispatcher("usuario.jsp").forward(request, response);
		//doGet(request, response);
	}

}
