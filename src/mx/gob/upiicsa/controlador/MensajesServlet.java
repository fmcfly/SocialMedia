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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		HttpSession sesion = request.getSession();
		// Con el tipo de dato envoltorio de int = INTEGER podemos convertir String a enteros con el método parseInt
		int idAmigo = Integer.parseInt( request.getParameter("idAmigo") ); // request.getParameter regresa un tipo de dato String
		//				(convertimos el objeto a UsuarioBean)el método getAttribute regresa un objeto 
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario"); 
		// Instanciaste al objeto MensajesDAO
		MensajesDao mensajes = new MensajesDao();
		// aqui invocas al método getMENSAJES()
		
		// estoy instancaindo un objeto del mismo tipo del que regresa nuestro método getMensajes de la clase MensajesDAO
		ArrayList<MensajeBean> mensajesEncontrados = mensajes.getMensajes(idAmigo, usuarioLogeado.getIdUser());
		sesion.setAttribute("mensajes", mensajesEncontrados);
		// vamos a redirigir a otra pantalla de la vista
		request.getRequestDispatcher("/chat.jsp").forward(request,response);
	}

}
