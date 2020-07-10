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

import com.google.gson.Gson;

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
		int chatSeleccionado = Integer.parseInt(request.getParameter("idChat"));
		
		if(chatSeleccionado != 0) {//entonces comprueba si hay mensajes
			MensajesDao mensajes = new MensajesDao();
			List<MensajeBean> listaMensajes = mensajes.obtenerMensajes(chatSeleccionado);
			Gson gson = new Gson();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(listaMensajes));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//	OBTENER LOS MENSAJES PERO A PARTIR DEL IDPERFIL 
		HttpSession sesion = request.getSession();

		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario"); 
		UsuarioBean perfilUsuario = (UsuarioBean) sesion.getAttribute("perfilUsuario");
		
		if(usuarioLogeado != null) {
			MensajesDao mensajes = new MensajesDao();
			
			List<MensajeBean> mensajesEncontrados = mensajes.getMesagesByProfile(perfilUsuario.getIdUser(), usuarioLogeado.getIdUser());
			Gson gson = new Gson();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(mensajesEncontrados));
		}
		else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
