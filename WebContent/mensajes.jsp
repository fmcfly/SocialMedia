<%@page import="java.util.ArrayList"%>
<%@page import="mx.gob.upiicsa.modelo.UsuarioBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%HttpSession sesion =request.getSession();
    	UsuarioBean userLogin = (UsuarioBean) session.getAttribute("usuario"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Social Media</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link href="css/mensajes.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>
<%if(userLogin != null){ %>
<body onload="setUserLogin(<%=userLogin.getIdUser()%>)">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="login.jsp">SocialMedia</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	   	  <li class="nav-item active">
	        <a class="nav-link" href="usuario.jsp">Perfil<span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" onclick="cerrarSesion()">Cerrar Sesión</a>
	      </li>
	    </ul>
	    <form action ="PerfilServlet" method="GET"
	    class="form-inline my-2 my-lg-0" id="buscar" >
	      <input class="form-control mr-sm-2" type="search" name="nombre" placeholder="Buscar" aria-label="Buscar">
	      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
	    </form>
	  </div>
	</nav>
	<div class="">
		<div class="row">
			<div class="col-md-3 tablaAmigos">
				<table class="table">
				  <thead class="thead-dark">
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">Nombre</th>
				      <th scope="col"></th>
				    </tr>
				  </thead>
				  <tbody id="bodyContactos">
				  </tbody>
				</table>
			</div>
			<div class="col-md-7 chat">
				<div class="form-inline">
					<h1 class="header mx-sm-3" id="nameUserChat"></h1>
					<img id="imageUser" class="imagen-tabla rounded-circle mx-sm-3">
					<i class="fas fa-spinner fa-spin spinner-no" id="spinner"></i>
				</div>
				
				<div class="mensajes" id="mensajes">
					
				</div>
				
				<div class="texto form-inline">
				
	   				<textarea class="form-control mx-2" id="message" rows="4" name="mensaje" style="width: 85%;" placeholder="Escribe tu mensaje"></textarea>
	   				<div class="botonEnviarMensaje">
	   					<button class="btn-primary" onclick="sendMessage()" style=""><i class="far fa-paper-plane"></i></button>
	   				</div>
	   				
	   				
				</div>
			</div>
		</div>
	</div>
		
		
		
	<script src="javascript/autenticacion.js"></script>
	<script src="https://kit.fontawesome.com/af8d928238.js" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="javascript/chat.js"></script>
<%}else{ %>
<script>
	alert("La sesión expiró");
	window.location.href = "/SocialMedia/login.jsp";
</script>
<%} %>
</body>

</html>