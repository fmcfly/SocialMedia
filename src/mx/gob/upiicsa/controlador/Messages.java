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
 * Servlet implementation class Messages
 */
@WebServlet("/Messages")
public class Messages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Messages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		
		System.out.println("MENSAJE!!!!!!" + request.getParameter("message"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String mensaje = request.getParameter("message");
		HttpSession sesion  = request.getSession();
		System.out.println("ENTRO A POST MESSAGE");
		MensajeBean msjNuevo = new MensajeBean();
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		MensajesDao mensajesDao = new MensajesDao();
		//Este es el ID del usuario Logeado
		msjNuevo.setIdUsuario(usuarioLogeado.getIdUser());
		msjNuevo.setTexto(mensaje);
		
		int idAmigoActual = (Integer)sesion.getAttribute("idAmigo");
		
		if(sesion.getAttribute("idChat") != null) { // SI EL IDCHAT es nul quiere decir que aún no se inicia una conversación
			int idChat = (Integer) sesion.getAttribute("idChat");
			msjNuevo.setIdChat(idChat);
			//guardamos el mensjae
			mensajesDao.guardarMensaje(msjNuevo);
		}else { //Si esta vació el idchat entonces aun no hay una conversación 
			mensajesDao.crearChat(msjNuevo,idAmigoActual);
		}
		//Recargar los mensajes llamando de nuevo a MesnajesServlet
		
		//actualizamos el mensaje esto en caso de que recarguen la pagina chat.jsp
		ArrayList<MensajeBean> mensajesEncontrados = mensajesDao.getMensajes(idAmigoActual, usuarioLogeado.getIdUser());
		sesion.setAttribute("mensajes", mensajesEncontrados);
		//request.getRequestDispatcher("/chat.jsp").forward(request, response);
		 String text = "Se envió el mensaje";

	    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(text); 
	}

}
