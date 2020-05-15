<%@page import="mx.gob.upiicsa.modelo.UsuarioBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
//recupero la sesion
	HttpSession sesion = request.getSession();
//instancio un objeto arraylist de tipo UsuarioBean y le asigno lo que este guardando en la sesion con el nombre "perfiles"
	ArrayList<UsuarioBean> listaPerfiles = (ArrayList<UsuarioBean>) sesion.getAttribute("perfiles");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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
        <a class="nav-link" href="principal.jsp">Amigos <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="index.html">Cerrar Sesión</a>
      </li>
    </ul>
    <form action ="PerfilServlet" method="GET"
    class="form-inline my-2 my-lg-0" id="buscar" >
      <input class="form-control mr-sm-2" type="search" name="nombre" placeholder="Buscar" aria-label="Buscar">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<div class="container">
	<div class="form-inline">
		<%for(UsuarioBean amigo:listaPerfiles) { 
		String nombre = amigo.getNombre()+" "+ amigo.getApellido();%>
		<div class="card mx-sm-2 mb-1" style="width: 18rem; ">
		  <img class="card-img-top" src="img/<%=amigo.getImage() %>" style="height:300px;" alt="Card image cap">
		  <div class="card-body">
		    <h5 class="card-title"><%=nombre %></h5>
		    <p class="card-text"><%=amigo.getCorreo() %></p>
		    <!--<button onclick="friend(<%=amigo.getIdUser()%>)"
		    class="btn btn-primary">Mensaje</button>-->
		  </div>
		</div>
		
		<%} %>
	</div>
	
</div>
</body>
</html>