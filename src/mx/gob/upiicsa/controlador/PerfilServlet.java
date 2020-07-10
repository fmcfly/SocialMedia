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
import mx.gob.upiicsa.dao.PerfilDao;
import mx.gob.upiicsa.modelo.UsuarioBean;

/**
 * Servlet implementation class PerfilServlet
 */
@WebServlet("/PerfilServlet")
public class PerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PARA CUANDO SE REALIZA LA BUSQUEDA DESDE EL NAVBAR
		HttpSession sesion = request.getSession();
		UsuarioBean userLogin = (UsuarioBean) sesion.getAttribute("usuario");
		if(userLogin != null) {
			PerfilDao perfilDao = new PerfilDao(); 
			ArrayList<UsuarioBean> perfilesEncontrados = perfilDao.encontrarlPerfil(request.getParameter("nombre"),userLogin.getIdUser());
			sesion.setAttribute("perfiles", perfilesEncontrados);
			request.getRequestDispatcher("/busqueda.jsp").forward(request,response);
		}
		else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//AGREGA AMIGOS
		HttpSession sesion = request.getSession();
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		UsuarioBean perfilUsuario = (UsuarioBean) sesion.getAttribute("perfilUsuario");
		
		AmigosDao amigosDao = new AmigosDao();
		PerfilDao perfilDao = new PerfilDao();
		
		int respuesta = amigosDao.agregarAmigo(usuarioLogeado.getIdUser(), perfilUsuario.getIdUser());
		if(respuesta > 0) {
			perfilUsuario = perfilDao.getInfoUserById(perfilUsuario.getIdUser());
			perfilUsuario.setAmigo(1);
			usuarioLogeado = perfilDao.getInfoUserById(usuarioLogeado.getIdUser());
			
			sesion.setAttribute("usuario", usuarioLogeado);
			sesion.setAttribute("perfilUsuario", perfilUsuario);
			response.setContentType("text/plain");
			response.getWriter().write("1");
		}else {
			perfilUsuario.setAmigo(0);
			response.setContentType("text/plain");
			response.getWriter().write("0");
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Elimina AMIGOS
		
		HttpSession sesion = request.getSession();
		UsuarioBean usuarioLogueado = (UsuarioBean) sesion.getAttribute("usuario");
		UsuarioBean perfilUsuario = (UsuarioBean) sesion.getAttribute("perfilUsuario");
		
		if(usuarioLogueado != null) {
			
			AmigosDao amigoDao = new AmigosDao();
			PerfilDao perfilDao = new PerfilDao();
			int registrosAfectados = amigoDao.eliminarAmigo(usuarioLogueado.getIdUser(), perfilUsuario.getIdUser());
			
			if(registrosAfectados > 0) {
				perfilUsuario = perfilDao.getInfoUserById(perfilUsuario.getIdUser());
				perfilUsuario.setAmigo(0);
				usuarioLogueado = perfilDao.getInfoUserById(usuarioLogueado.getIdUser());
				
				sesion.setAttribute("usuario", usuarioLogueado);
				sesion.setAttribute("perfilUsuario", perfilUsuario);
				response.setContentType("text/plain");
				response.getWriter().write("1");
			}
		}else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}
	
}
