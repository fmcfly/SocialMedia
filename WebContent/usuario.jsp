<%@page import="mx.gob.upiicsa.modelo.UsuarioBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% HttpSession sesion = request.getSession();
	UsuarioBean userLogin =(UsuarioBean) session.getAttribute("usuario");%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Social Media</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link href="css/usuario.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
<script>
	function cambiando(){
		let element = document.getElementById("sexo");
		element.innerHTML ="<div class='form-check form-check-inline'>" +
							 "<label class='form-check-label' for='inlineRadio1'>Sexo</label>"+
						   "</div>"+
							"<div class='form-check form-check-inline'>" +
							 "<input class='form-check-input' type='radio' name='radioSexo' id='inlineRadio1' value='F'>"+
							 "<label class='form-check-label' for='inlineRadio1'>F</label>"+
						   "</div>"+
						   "<div class='form-check form-check-inline'>"+
							  "<input class='form-check-input' type='radio' name='radioSexo' id='inlineRadio2' value='M'>"+
							  "<label class='form-check-label' for='inlineRadio2'>M</label>"+
					  	   "</div>";
		
		let elementFrase = document.getElementById("frase");
		elementFrase.innerHTML ="<input class='form-control' id = 'estado' name='estadoPerfil' placeholder='Estado'>";
		
		let elementNombreUsuario = document.getElementById("nombreUsuario");
		elementNombreUsuario.innerHTML ="<input class='form-control' id='userName' name='nombreUsuario' placeholder='NombreDeUsuario'>";
		
		let elementBirhtdate = document.getElementById("birhtdate");
		elementBirhtdate.innerHTML ="<input class='form-control' id='cumple' name='cumple' type='date'>";
		
		let elementPais = document.getElementById("pais");
		elementPais.innerHTML ="<input class='form-control' id='paiss' name='pais' placeholder='Pais'>";
		
		let elementBoton = document.getElementById("btn-editar");
		elementBoton.innerHTML ="<button class='btn btn-outline-primary mx-sm-2' onclick='guardar()'>Guardar</button>"+
		"<button class='btn btn-outline-danger'>Cancelar</button>";
	}
	
	function guardar(){
		/*let valorestado = document.getElementById("estado").value;
		console.log(valorestado);
		
		let valorNombreUsuario = document.getElementById("userName").value;
		console.log(valorNombreUsuario);
		
		let valorBirhtdate = document.getElementById("cumple").value;
		console.log(valorBirhtdate);
		
		let valorPais = document.getElementById("paiss").value;
		console.log(valorPais);*/
			//esto trae un arreglo de radios
		let radioSexo = document.getElementsByName("radioSexo");
		
		let valorSexo = "";
		
		for(let radio of radioSexo){
			if(radio.checked ==true){
				valorSexo = radio.value;
			}
		}
		console.log(valorSexo);
		let formEditar = document.getElementById("infoUser");
		formEditar.action = "/SocialMedia/EditarUsuario?sexo="+valorSexo;
		formEditar.method="POST";
		formEditar.submit();
	}
</script>
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
			        <a class="nav-link" href="usuario.jsp">Perfil<span class="sr-only">(current)</span></a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="principal.jsp">Amigos</a>
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
		<div class="container">
		<br>
			<form id="infoUser">
				<div class="row border border-primary fondoAzul">
					<div class="col-sm-4 numAmigos">
						<p>Numero de Amigos: <%=userLogin.getNumeroAmigos() %></p>
					</div>
					<div class="col-sm-4 frase">
						<p id="frase"><%=((userLogin.getFrase()==null) ? "No hay datos":userLogin.getFrase())%></p>
					</div>
					<div class="col-sm-4 imagenPerfil">
						<img src="img/<%=userLogin.getImage()%>" alt="Foto de perfil" class="rounded-circle" style="width:180px">
						<h5 id="nombreUsuario"><%=userLogin.getNombreUsuario() %></h5>
					</div>
				</div>
				<div class="row informacionUsuario fondoRojo">
					<div class="col-sm-4 cumpleaños"> 
					<!-- <p id="birhtdate">Cumpleaños : <%=((userLogin.getBirhtdate()==null) ? "No hay datos" : userLogin.getBirhtdate())%> </p> -->
					<p id="birhtdate">Cumpleaños : <%=((userLogin.getBirhtdate()==null) ? "No hay datos" : userLogin.getBirhtdate())%> </p> 
					</div>
					<div class="col-sm-4 sexo">
						<p id="sexo">Sexo : <%=((userLogin.getSexo()== null) ? "No hay datos": userLogin.getSexo())%> </p>
					
					</div>
					<div class="col-sm-4 pais">
					<p id="pais">Pais : <%=((userLogin.getPais()==null) ? "No hay datos":userLogin.getPais())%></p>
					</div>
				</div>
			</form>
		 	<div class="  form-inline" >
		 		<div class="tabla">
			 		<table class="table fondoAzul">
					  <thead>
					    <tr>
					      <th scope="col">#</th>
					      <th scope="col">Nombre</th>
					      <th scope="col">Apellido</th>
					      <th scope="col">Correo</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <th scope="row">1</th>
					      <td>Mark</td>
					      <td>Otto</td>
					      <td>@mdo</td>
					    </tr>
					    <tr>
					      <th scope="row">2</th>
					      <td>Jacob</td>
					      <td>Thornton</td>
					      <td>@fat</td>
					    </tr>
					    <tr>
					      <th scope="row">3</th>
					      <td>Larry</td>
					      <td>the Bird</td>
					      <td>@twitter</td>
					    </tr>
					    <tr>
					      <th scope="row">3</th>
					      <td>Larry</td>
					      <td>the Bird</td>
					      <td>@twitter</td>
					    </tr>
					    <tr>
					      <th scope="row">3</th>
					      <td>Larry</td>
					      <td>the Bird</td>
					      <td>@twitter</td>
					    </tr>
					    <tr>
					      <th scope="row">3</th>
					      <td>Larry</td>
					      <td>the Bird</td>
					      <td>@twitter</td>
					    </tr>
					  </tbody>
					</table>
				</div>
				<div class="boton-editar">
					<div id="btn-editar">
						<button class="btn btn-outline-warning" onclick="cambiando()" > Editar</button>
					</div>	
				
				</div>
		 	</div>
		</div>
		
</body>
</html>