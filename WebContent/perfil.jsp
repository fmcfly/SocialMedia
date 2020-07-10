<%@page import="java.util.ArrayList"%>
<%@page import="mx.gob.upiicsa.modelo.UsuarioBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% HttpSession sesion = request.getSession();
	UsuarioBean userLogin =(UsuarioBean) session.getAttribute("usuario");
	UsuarioBean perfilUsuario =(UsuarioBean) session.getAttribute("perfilUsuario");
	%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Social Media</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="css/usuario.css" rel="stylesheet">
<link href="css/perfil.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

</head>
<body>
<%if(userLogin != null){%>
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
			        <a class="nav-link" href="mensajes.jsp">Mensajes</a>
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
		<div class="container-fluid fondoMorado">
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-12 imagenPerfil">
					 <img src="img/<%=((perfilUsuario.getImage() == null || perfilUsuario.getImage().equals("")) ? "default.jpg" : perfilUsuario.getImage())%>"
					 alt="Foto de perfil" class="rounded-circle" style="width:180px">
					<h5 id="nombreUsuario"><%=perfilUsuario.getNombreUsuario() %></h5>
					<p id="pais"class="informacion">Pais : <%=((perfilUsuario.getPais()==null) ? "No hay datos":perfilUsuario.getPais())%></p>
					<div class="row informacion">
						<div class="col-sm-12 col-md-6">
							<p id="birhtdate">Cumpleaños : <%=((perfilUsuario.getBirhtdate()==null) ? "No hay datos" : perfilUsuario.getBirhtdate())%></p>
						</div>
						<div class="col-sm-12 col-md-6">
							<p id="sexo">Sexo : <%=((perfilUsuario.getSexo()== null) ? "No hay datos": perfilUsuario.getSexo())%></p>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		<div class="container">
			<div class="row numeroAmigos" >
				<div class="col-md-4">
					<p class="dato"><%=perfilUsuario.getNumeroAmigos() %></p>
					<p class="info">Amigos</p>
				</div>
				<div class="col-md-4">
					<p class="dato" id="frase"><%=((perfilUsuario.getFrase()==null) ? "No hay datos":perfilUsuario.getFrase())%></p>
				</div>
				<div class="col-md-4">
					<%if(perfilUsuario.getAmigo() == 0){ %>
						<i onclick="addFriend()" class="fas fa-user-plus mx-sm-3"></i>
						
					<%}else{ %>
						<i onclick="eliminarAmigo()" class="fas fa-user-times mx-sm-3"></i>
						
					<%} %>
					<i id="MessageIcon" 
					class="far fa-envelope"></i>
					
				</div>
			</div>
		</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	
	const addFriend = () => {
		
		$.post("/SocialMedia/PerfilServlet",function(response){
			window.location.href = "/SocialMedia/perfil.jsp";
			//location.reload();
		});	
	}

	const eliminarAmigo = () => {
		$.ajax({
		    url: '/SocialMedia/PerfilServlet',
		    type: 'DELETE',
		    success: function(result) {
		    	window.location.href="/SocialMedia/perfil.jsp";
		    }
		});
		
	}
//$("h5").text("Marianiata <3");
	function enviarMensaje(idAmigo){
		// esta función viene de jQuery
		$.post("/SocialMedia/MensajesServlet?idAmigo="+idAmigo,function(){
			window.location.href = "/SocialMedia/chat.jsp";
		});
	}
	
	let mensajeIcono = document.getElementById("MessageIcon");
	//Le agregamos el event lsitener a nuestro icono de mensajes
	mensajeIcono.addEventListener("click", () => {
		window.location.href = "/SocialMedia/mensajes.jsp?idUsuario="+<%= perfilUsuario.getIdUser()%>;
	});
	
</script>
<script src="javascript/autenticacion.js"></script>
<script src="https://kit.fontawesome.com/af8d928238.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>	
<% } //ESTA LLAVE CIERRA EL IF QUE VALIDA SI HAY UN USUARIO EN LA SESIÓN
else{%>
<script>
	alert("La sesión expiró");
	window.location.href = "/SocialMedia/login.jsp"
</script>	

<%	
	}
%>	
</body>
</html>