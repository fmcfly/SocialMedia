<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Social Media</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="css/usuario.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

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
		<div class="container border border-danger">
		<br>
			<div class="row border border-primary fondoAzul">
				<div class="col-sm-4 numAmigos">
					123 AMIGOS
				</div>
				<div class="col-sm-4 frase">
					"Marianita linda super buena onda "
				</div>
				<div class="col-sm-4 imagenPerfil">
					<img src="img/fernando.jpg" alt="Foto de perfil" class="rounded-circle">
					<h5>Fernandito Mcfly</h5>
				</div>
			</div>
			<div class="row border border-primary informacionUsuario fondoRojo">
				<div class="col-sm-4 cumpleaños"> 
				Cumpleaños: 13 Agosto 
				</div>
				
				<div class="col-sm-4 sexo">
				Sexo: M
				</div>
				
				<div class="col-sm-4 pais"  >
				Pais: México
				</div>
			</div>
		 	<div class="row border border-primary fondoAzul" >
		 		<div class="col-sm-4 "> 
				Lista amigos
				</div>
		 	</div>
		</div>
		
		
</body>
</html>