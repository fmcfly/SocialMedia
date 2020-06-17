package mx.gob.upiicsa.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.upiicsa.dao.MensajesDao;
import mx.gob.upiicsa.modelo.MensajeBean;
import mx.gob.upiicsa.modelo.UsuarioBean;

/**
 * Servlet implementation class MensajesServlet
 */
@WebServlet("/MensajesServlet")
public class MensajesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MensajesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		UsuarioBean userLogin = (UsuarioBean) sesion.getAttribute("usuario");
		if(userLogin != null) {
			request.getRequestDispatcher("/usuario.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		//response.getWriter().append("Served at: Mensajes").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//	OBTENER LOS MENSAJES
		HttpSession sesion = request.getSession();

		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario"); 
		
		if(usuarioLogeado != null) {
			int idAmigo = Integer.parseInt( request.getParameter("idAmigo") ); // request.getParameter regresa un tipo de dato String
			MensajesDao mensajes = new MensajesDao();
	
			sesion.setAttribute("idAmigo", idAmigo);
	
			ArrayList<MensajeBean> mensajesEncontrados = mensajes.getMensajes(idAmigo, usuarioLogeado.getIdUser());
			if(mensajesEncontrados.size() == 0) {
			 System.out.println("aun no hay nada");
			 sesion.removeAttribute("idChat");
			 sesion.removeAttribute("mensajes");
			}else {
				int idChat = mensajesEncontrados.get(0).getIdChat();
				
				sesion.setAttribute("idChat", idChat);
				sesion.setAttribute("mensajes", mensajesEncontrados);
			}
			request.getRequestDispatcher("/chat.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}
	}

}
