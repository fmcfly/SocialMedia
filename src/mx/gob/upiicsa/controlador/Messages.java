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
    /* VA a servir para la actualziación de los mensajes*/
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
		
		
		// SE MANDO CMO RESPUESTA TEXTO PLANO ATRAVÉS DEL objeto response 
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
		//la comparación de mensajes
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
		HttpSession sesion  = request.getSession();
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		
		String mensaje = request.getParameter("message");
		int idChat = Integer.parseInt(request.getParameter("idChat"));
		
		
		MensajeBean msjNuevo = new MensajeBean();
		
		MensajesDao mensajesDao = new MensajesDao();
		//Este es el ID del usuario Logeado
		msjNuevo.setIdUsuario(usuarioLogeado.getIdUser());
		msjNuevo.setTexto(mensaje);
		msjNuevo.setIdChat(idChat);

		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		if(idChat != 0) { // Ya hay una conversación
			response.getWriter().write(mensajesDao.guardarMensaje(msjNuevo));
		}else { //Si es igual a cero entonces creas el chat  
			//mensajesDao.crearChat(msjNuevo,idAmigoActual);
		}
	}

}
