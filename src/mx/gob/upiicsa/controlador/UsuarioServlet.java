package mx.gob.upiicsa.controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import mx.gob.upiicsa.dao.AmigosDao;
import mx.gob.upiicsa.dao.LoginDao;
import mx.gob.upiicsa.dao.PerfilDao;
import mx.gob.upiicsa.dao.RegistroDao;
import mx.gob.upiicsa.modelo.UsuarioBean;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		UsuarioBean usuarioLogeado = (UsuarioBean) sesion.getAttribute("usuario");
		if(usuarioLogeado != null) {
			
			System.out.println(request.getParameter("estadoPerfil"));
			usuarioLogeado.setFrase(request.getParameter("estadoPerfil"));
			usuarioLogeado.setNombreUsuario(request.getParameter("nombreUsuario"));
			usuarioLogeado.setBirhtdate(request.getParameter("cumple"));
			usuarioLogeado.setPais(request.getParameter("pais"));
			usuarioLogeado.setSexo(request.getParameter("sexo"));
			System.out.println(usuarioLogeado.toString());
			PerfilDao perfil = new PerfilDao();
			
			usuarioLogeado = perfil.updateUser(usuarioLogeado);
			
			sesion.setAttribute("usuario",usuarioLogeado);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("1");
		}else {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("-1");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession(true);
		/*System.out.println(request.getParameter("Telefono"));
		long telefono = Long.parseLong(request.getParameter("Telefono"));
		RegistroDao newUsuario = new RegistroDao();
		UsuarioBean usuarioNuevo = new UsuarioBean(request.getParameter("Nombre"),request.getParameter("Apellido")
				,request.getParameter("Correo"),telefono,request.getParameter("Contrasena"));
		
		int statusRegistro = newUsuario.crearUsuario(usuarioNuevo);*/
		
		String fileName = null;
		RegistroDao registroDao = new RegistroDao();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if(!isMultipartContent) {
			request.getRequestDispatcher("/registro.jsp").forward(request,response);
		}else {
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try{
				List<FileItem> campos = upload.parseRequest(request); // el upload parsea la petici�n aobjetos de tipo FileItem
				Iterator<FileItem> it = campos.iterator();// creamos un iterador de tipo FileItem para poder iterar la Lista de FileItem
				
				UsuarioBean usuarioNuevo =new UsuarioBean(); //Creamos un usuarioBean para guardar la informaci�n de la request
				
				while(it.hasNext()) {// iterar el Iterator
					FileItem fileItem = it.next(); // se instancia un FileItem con el objeto o bueno el campo que le siga en el Iterator
					boolean isFormField = fileItem.isFormField();// se guarda el fileitem si es un formfield (input,select,radiobutton) y prpegunta si es unao de estos si es as� guarda un true caso contrario guarda un false
					if(isFormField) {// checa si es o no un form field, en caso de que sea entra al if
						System.out.println("Es un FormField");
						System.out.println(fileItem.getFieldName());
						System.out.println(fileItem.getString());
						//EN CADA IF LO QUE HACE ES COMPARAR EL NOMBRE DEL CAMPO CON EL QUE SE LO INDICAMOS 
						if(fileItem.getFieldName().equals("Nombre")) // DEL FILEITEM OBTIENE SU NOMBRE Y SI ES IGUAL AL QUE ESPECIFICAMOS ENTONCES SE GUARDA EL VALOR CON UN SET EN NUESTRO OBJETO USUARIOBEAN
							usuarioNuevo.setNombre(fileItem.getString());// SI EL NOMBRE ES IGUAL AL ESPECIFICADO LE VAS A SETEAR EL VALOR DEL FILEITEM EN SU ATRIBUTO CORRESPONDIENTE DEL USUARIOBEAN
						else if(fileItem.getFieldName().equals("Apellido")) 
							usuarioNuevo.setApellido(fileItem.getString());
						else if(fileItem.getFieldName().equals("userName")) 
							usuarioNuevo.setNombreUsuario(fileItem.getString());
						else if(fileItem.getFieldName().equals("Correo")) 
							usuarioNuevo.setCorreo(fileItem.getString());
						else if(fileItem.getFieldName().equals("Telefono")) 
							usuarioNuevo.setTelefono( Long.parseLong(fileItem.getString()) );
						else if(fileItem.getFieldName().equals("Contrasena")) 
							usuarioNuevo.setPassword(fileItem.getString());
						/*if(fileName == null) {
							if(fileItem.getFieldName().equals("file_name")) {
								fileName = fileItem.getString();
							}
						}*/
					}else {
						System.out.println("No es un formfield");
						
						if(fileItem.getSize() > 0) {// cuando se envia imagen
							usuarioNuevo.setImage(fileItem.getName());
							
							int statusRegistro = registroDao.crearUsuario(usuarioNuevo);
							
							if(statusRegistro > 0) {
								fileItem.write(new File("C:\\Users\\DELL\\Escritorio\\Proyectos_Java\\SocialMedia\\WebContent\\img\\"+fileItem.getName()));
								request.getRequestDispatcher("/login.jsp").forward(request,response);
							}else{
								if(statusRegistro == -2) {
									sesion.setAttribute("mensaje", "Correo registrado");
								}
								else if(statusRegistro == -1) {
									sesion.setAttribute("mensaje", "Nombre de usuario repetido");
								}
								
								request.getRequestDispatcher("/registro.jsp").forward(request,response);
							}
							//fileItem.write(new File("/SocialMedia/WebContent/img/"+fileItem.getName()));
							//fileItem.write(new File("C:\\Users\\DELL\\Escritorio\\Proyectos_Java\\SocialMedia\\WebContent\\img\\"+fileItem.getName()));
						}else {// cuando no se envie la imagen
							usuarioNuevo.setImage(fileItem.getName());
							
							int statusRegistro = registroDao.crearUsuario(usuarioNuevo);
							
							if(statusRegistro > 0) {
								request.getRequestDispatcher("/login.jsp").forward(request,response);
							}else{
								if(statusRegistro == -2) {
									sesion.setAttribute("mensaje", "Correo registrado");
								}
								else if(statusRegistro == -1) {
									sesion.setAttribute("mensaje", "Nombre de usuario repetido");
								}
								
								request.getRequestDispatcher("/registro.jsp").forward(request,response);
							}
						}
					}
					
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		
		
	}

}
