<%@page import="mx.gob.upiicsa.modelo.UsuarioBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
//recupero la sesion
	HttpSession sesion = request.getSession();
	UsuarioBean userLogin = (UsuarioBean) sesion.getAttribute("usuario"); 
//instancio un objeto arraylist de tipo UsuarioBean y le asigno lo que este guardando en la sesion con el nombre "perfiles"
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Social Media</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script type="text/javascript" src="javascript/funciones.js"></script>
</head>
<body>
<% if(userLogin != null){
	ArrayList<UsuarioBean> listaPerfiles = (ArrayList<UsuarioBean>) sesion.getAttribute("perfiles");
%>
 <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="login.jsp">SocialMedia</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="usuario.jsp">Perfil</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" onclick="cerrarSesion()">Cerrar Sesi�n</a>
      </li>
    </ul>
    <form action ="PerfilServlet" method="GET"
    class="form-inline my-2 my-lg-0" id="buscar" >
      <input class="form-control mr-sm-2" type="search" name="nombre" placeholder="Buscar" aria-label="Buscar">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<div class="container" id="agregar">
	<div class="form-inline">
		<%for(UsuarioBean usuario:listaPerfiles) { 
		String nombre = usuario.getNombre()+" "+ usuario.getApellido();%>
		<div class="card mx-sm-2 mb-1" style="width: 18rem; ">
		  <img class="card-img-top" src="img/<%=(usuario.getImage() == null ? "default.jpg" : usuario.getImage()) %>" style="height:300px;" alt="Card image cap">
		  <div class="card-body">
		    <h5 class="card-title"><%=nombre %></h5>
		    <p class="card-text"><%=usuario.getCorreo() %></p>
		    <%if (usuario.getIdUser() != userLogin.getIdUser()){
		   			if(usuario.getAmigo() == 0){ %>	
				    	<button onclick="addFriend(<%=usuario.getIdUser()%>,<%=request.getParameter("nombre")%>)"
				    	class="btn btn-primary">Agregar</button>
		    <%		}else{%>
		    			<button onclick="sendMessage(<%=usuario.getIdUser()%>)"
		    			class="btn btn-primary">Mensaje</button>
		    <% 		}
		    }%>	    
		    
		  </div>
		</div>
		
		<%} %>
	</div>
	
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="javascript/autenticacion.js"></script>
<script src="javascript/amigos.js"></script>
<% }
else{%>
<script>
alert("La sesi�n expir�");
window.location.href = "/SocialMedia/login.jsp";
</script>
<% } %>
</body>
</html>