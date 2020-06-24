package mx.gob.upiicsa.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    /* VA a servir para la actualziaci�n de los mensajes*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		int idAmigoActual = (Integer)sesion.getAttribute("idAmigo");
		
		ArrayList<MensajeBean> mensajesActuales = (ArrayList<MensajeBean>) sesion.getAttribute("mensajes");
		int indiceMaximoActuales = mensajesActuales.size() - 1;
		MensajeBean ultimoMensajeActual = mensajesActuales.get(indiceMaximoActuales);
		
		MensajesDao mensajesDao = new MensajesDao();
		ArrayList<MensajeBean> mensajesActualizados = mensajesDao.getMensajes(idAmigoActual, usuarioLogeado.getIdUser());
		int indiceMaximo = mensajesActualizados.size() - 1;
		MensajeBean ultimoMensajeActualizado = mensajesActualizados.get(indiceMaximo);
		
		
		// SE MANDO CMO RESPUESTA TEXTO PLANO ATRAV�S DEL objeto response 
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
		//la comparaci�n de mensajes
		if(ultimoMensajeActual.getIdMensaje() != ultimoMensajeActualizado.getIdMensaje()) {
			sesion.setAttribute("mensajes",mensajesActualizados);
			response.getWriter().write(ultimoMensajeActualizado.getTexto());
		}else {
			response.getWriter().write("");
		}
		
		/*List <String> lista = new ArrayList<String>();
		lista.add("elemento1");
		lista.add("elemento2");
		lista.add("elemento3");
		String json = new Gson();*/
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
		
		if(sesion.getAttribute("idChat") != null) { // SI EL IDCHAT es nul quiere decir que a�n no se inicia una conversaci�n
			int idChat = (Integer) sesion.getAttribute("idChat");
			msjNuevo.setIdChat(idChat);
			//guardamos el mensjae
			mensajesDao.guardarMensaje(msjNuevo);
		}else { //Si esta vaci� el idchat entonces aun no hay una conversaci�n 
			mensajesDao.crearChat(msjNuevo,idAmigoActual);
		}
		//Recargar los mensajes llamando de nuevo a MesnajesServlet
		
		//actualizamos el mensaje esto en caso de que recarguen la pagina chat.jsp
		ArrayList<MensajeBean> mensajesEncontrados = mensajesDao.getMensajes(idAmigoActual, usuarioLogeado.getIdUser());
		sesion.setAttribute("mensajes", mensajesEncontrados);
		//request.getRequestDispatcher("/chat.jsp").forward(request, response);
		 String text = "Se envi� el mensaje";

	    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(text); 
	}

}
