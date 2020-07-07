package mx.gob.upiicsa.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import mx.gob.upiicsa.dao.MensajesDao;
import mx.gob.upiicsa.modelo.MensajeBean;
import mx.gob.upiicsa.modelo.UsuarioBean;
import mx.gob.upiicsa.modelo.UsuarioChat;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	
	private MensajesDao mensaje;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
        mensaje = new MensajesDao ();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		UsuarioBean userLogin = (UsuarioBean) sesion.getAttribute("usuario");
		
		List<UsuarioChat> usuariosChat =  mensaje.infoChat(userLogin.getIdUser());
		Gson gson = new Gson();//ESTA CLASE NOS VA AYUDAR A CONVERTI R NOTACION JSON
		String jsonUsuariosChat = gson.toJson(usuariosChat);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonUsuariosChat);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//REGRESE LOS MENSAJES
		int idChat = Integer.parseInt(request.getParameter("idChat"));
		System.out.println(idChat);
		List<MensajeBean> mensajesEncontrados = mensaje.obtenerMensajes(idChat);
		
		Gson gson = new Gson();//ESTA CLASE NOS VA AYUDAR A CONVERTI R NOTACION JSON
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(mensajesEncontrados));
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
