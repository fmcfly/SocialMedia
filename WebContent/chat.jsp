<%@page import="mx.gob.upiicsa.modelo.UsuarioBean"%>
<%@page import="mx.gob.upiicsa.modelo.MensajeBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    HttpSession sesion = request.getSession();
    ArrayList<MensajeBean> mensajes = (ArrayList<MensajeBean>) sesion.getAttribute("mensajes");
    UsuarioBean user = (UsuarioBean) sesion.getAttribute("usuario");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Social Media</title>

<link rel="stylesheet" href="css/estilos.css" type="text/css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script type="text/javascript" src="javascript/funciones.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">SocialMedia</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">About us</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<div class="container">
	<%for(MensajeBean mensaje: mensajes) {
		if(mensaje.getIdUsuario() == user.getIdUser()){ %>
		<div class="mensajeUsuario">
			<div class="card border border-success " style="width: 18rem;">
			  <div class="card-body">
			    <p class="card-text"><%=mensaje.getTexto() %></p>
			    
			    <p class="card-link"><%=mensaje.getFecha() %></p>
			  </div>
			</div>		
		</div>
		<%}else{%>
	
		<div class="card border border-primary" style="width: 18rem;">
		  <div class="card-body">
		    <p class="card-text"><%=mensaje.getTexto() %></p>
		    
		    <p class="card-link"><%=mensaje.getFecha() %></p>
		  </div>
		</div>
	<%}// cierra else
	} //cierrra for%>
</div>
</body>
</html>