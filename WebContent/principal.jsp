<%@page import="java.util.ArrayList"%>
<%@page import="mx.gob.upiicsa.modelo.UsuarioBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    //Primero recuperamos la sesión que creamos en el LoginServlet!!!
    HttpSession sesion = request.getSession();
    // Después de la sesion obtenemos el objeto que guardamos con el nombre "usuario"
    								//(UsuarioBean) estamos casteando o conviritendolo a un tipo de datoa Usuario Bean
    UsuarioBean user =(UsuarioBean) sesion.getAttribute("usuario");
    ArrayList<UsuarioBean> listaAmigos = (ArrayList<UsuarioBean>) sesion.getAttribute("amigos");
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

 <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="login.jsp">SocialMedia</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="principal.jsp">Amigos <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="login.jsp">Cerrar Sesión</a>
      </li>
    </ul>
    <form action ="PerfilServlet" method="GET"
    class="form-inline my-2 my-lg-0" id="buscar" >
      <input class="form-control mr-sm-2" type="search" name="nombre" placeholder="Buscar" aria-label="Buscar">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
    </form>
  </div>
</nav>

<form id="amigosChat" class="container">
	<div class="form-inline">
		<%for(UsuarioBean amigo:listaAmigos) { 
		String nombre = amigo.getNombre()+" "+ amigo.getApellido();%>
		<div class="card mx-sm-2 mb-1" style="width: 18rem; ">
		  <img class="card-img-top" src="img/<%=amigo.getImage() %>" style="height:300px;" alt="Card image cap">
		  <div class="card-body">
		    <h5 class="card-title"><%=nombre %></h5>
		    <p class="card-text"><%=amigo.getCorreo() %></p>
		    
		    <button class="btn btn-primary" onclick="friend(<%=amigo.getIdUser()%>)">Mensaje</button>
		  </div>
		</div>
		
		<%} %>
	</div>
	<!-- 
	<table class="table table-dark">
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Nombre</th>
	      <th scope="col">Apellido</th>
	      <th scope="col">Correo</th>
	    </tr>
	  </thead>
	  <tbody>
	  <%
	  for(UsuarioBean amigo:listaAmigos) {
	  %>
		
		 <tr >
		  		 <th scope="row"><%=amigo.getIdUser() %></th>
		  		 <td><%=amigo.getNombre() %></td>
		  		 <td><%=amigo.getApellido() %></td>
		  		 <td><%=amigo.getCorreo() %></td>
		  </tr>
		<%}
	  %>	    
	  </tbody>
	</table> -->
</form>


</body>
</html>