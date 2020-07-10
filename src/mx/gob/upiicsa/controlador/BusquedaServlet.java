package mx.gob.upiicsa.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.upiicsa.dao.PerfilDao;
import mx.gob.upiicsa.modelo.UsuarioBean;

/**
 * Servlet implementation class BusquedaServlet
 */
@WebServlet("/BusquedaServlet")
public class BusquedaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusquedaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TRAE Y PONER EN LA SESIÓN LA INFORMACIÓN DEL PERFIL
		HttpSession sesion = request.getSession();
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		int amigo= Integer.parseInt(request.getParameter("amigo"));
		
		PerfilDao perfilDao = new PerfilDao();
		
		UsuarioBean usuarioInfo = perfilDao.getInfoUserById(idUsuario);
		usuarioInfo.setAmigo(amigo);
		sesion.setAttribute("perfilUsuario", usuarioInfo);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
