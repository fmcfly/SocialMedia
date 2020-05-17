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
 * Servlet implementation class EnvioMensajes
 */
@WebServlet("/EnvioMensajes")
public class EnvioMensajes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnvioMensajes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: Envio").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mensaje = request.getParameter("mensaje");
		HttpSession sesion  = request.getSession();
		
		MensajeBean msjNuevo = new MensajeBean();
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		MensajesDao mensajesDao = new MensajesDao();
		//Este es el ID del usuario Logeado
		msjNuevo.setIdUsuario(usuarioLogeado.getIdUser());
		msjNuevo.setTexto(mensaje);
		
		/*int idAmigoActual = (Integer)sesion.getAttribute("idAmigo");
		request.getRequestDispatcher("MensajesServlet?idAmigo="+idAmigoActual).forward(request,response);*/
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
		
		//actualizamos el mensaje
		ArrayList<MensajeBean> mensajesEncontrados = mensajesDao.getMensajes(idAmigoActual, usuarioLogeado.getIdUser());
		sesion.setAttribute("mensajes", mensajesEncontrados);
		request.getRequestDispatcher("/chat.jsp").forward(request, response);
	}

}
