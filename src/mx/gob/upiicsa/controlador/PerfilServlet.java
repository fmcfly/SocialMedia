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

		HttpSession sesion = request.getSession();
		UsuarioBean userLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		AmigosDao amigosDao =new AmigosDao();
		ArrayList<UsuarioBean> perfilesEncontrados = amigosDao.encontrarlPerfil(request.getParameter("nombre"),userLogeado.getIdUser());
		sesion.setAttribute("perfiles", perfilesEncontrados);
		request.getRequestDispatcher("/perfil.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession sesion = request.getSession();
		//OBTENEMOS EL USUARIO DE LA SESIÓN
		UsuarioBean usuarioLogeado = new UsuarioBean();
		usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		//OBTENEMOS EL ID QUE QUIERE AGREGAR EL USUAIO:
		//Convertimos el ripo de dato String idUsuario a entero con la clase envoltorio Integer
		int idAmigo = Integer.parseInt(request.getParameter("idUsuario"));
		
		AmigosDao amigosDao = new AmigosDao();
		int respuesta = amigosDao.agregarAmigo(usuarioLogeado.getIdUser(), idAmigo);
		System.out.println(respuesta);
		if(respuesta > 0) {
			ArrayList<UsuarioBean> listaAmigos = amigosDao.encontrarAmigos(usuarioLogeado.getIdUser());
			sesion.setAttribute("amigos", listaAmigos);
			request.getRequestDispatcher("/principal.jsp").forward(request,response);
		}else {
			request.getRequestDispatcher("/perfil.jsp").forward(request,response);
		}
	}

}
