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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mensaje = request.getParameter("mensaje");
		HttpSession sesion  = request.getSession();
		int idChat = (Integer) sesion.getAttribute("idChat");
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		MensajeBean msjNuevo = new MensajeBean();
		msjNuevo.setIdChat(idChat);
		msjNuevo.setIdUsuario(usuarioLogeado.getIdUser());
		msjNuevo.setTexto(mensaje);
		
		MensajesDao mensajesDao = new MensajesDao();
		//guardamos el mensjae
		mensajesDao.guardarMensaje(msjNuevo);
		
		//Recargar los mensajes llamando de nuevo a MesnajesServlet
		/*int idAmigoActual = (Integer)sesion.getAttribute("idAmigo");
		request.getRequestDispatcher("MensajesServlet?idAmigo="+idAmigoActual).forward(request,response);*/
		int idAmigoActual = (Integer)sesion.getAttribute("idAmigo");
		//actualizamos el mensaje
		ArrayList<MensajeBean> mensajesEncontrados = mensajesDao.getMensajes(idAmigoActual, usuarioLogeado.getIdUser());
		sesion.setAttribute("mensajes", mensajesEncontrados);
		request.getRequestDispatcher("/chat.jsp").forward(request, response);
	}

}
