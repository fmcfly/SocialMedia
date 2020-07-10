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
    /* VA a servir para los mensajes que no ha visto*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		UsuarioBean usuarioLoqueado = (UsuarioBean) sesion.getAttribute("usuario");
		MensajesDao mensajes = new MensajesDao();
		int cantidadMensajes = mensajes.mensajesNoVistos(usuarioLoqueado.getIdUser());
		 
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		//wirte recibe una cadena de caracteres no puedes enviar un int 
		//write(escribe la respuesta
		response.getWriter().write(Integer.toString(cantidadMensajes));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GUARDA MENSAJE
		
		HttpSession sesion  = request.getSession();
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		UsuarioBean perfilUsuario = (UsuarioBean) sesion.getAttribute("perfilUsuario");
		
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
			response.getWriter().write(
					mensajesDao.crearChat(msjNuevo,perfilUsuario.getIdUser())
			);
		}
	}

}
