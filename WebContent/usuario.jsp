<%@page import="java.util.ArrayList"%>
<%@page import="mx.gob.upiicsa.modelo.UsuarioBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% HttpSession sesion = request.getSession();
	UsuarioBean userLogin =(UsuarioBean) session.getAttribute("usuario"); 
	%>    
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
<%if(userLogin != null){
	ArrayList<UsuarioBean> tablaAmigos = (ArrayList<UsuarioBean>) sesion.getAttribute("amigos"); %>
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
					<div id="acciones">
						<h4 onclick="cambiando()" ><i class="fas fa-user-edit lapiz"></i></h4>
					</div>
					 <img src="img/<%=((userLogin.getImage() == null || userLogin.getImage().equals("")) ? "default.jpg" : userLogin.getImage())%>"
					 alt="Foto de perfil" class="rounded-circle" style="width:180px">
					
					<h5 id="nombreUsuario"><%=userLogin.getNombreUsuario() %></h5>
					<p id="pais"class="informacion">Pais : <%=((userLogin.getPais()==null) ? "No hay datos":userLogin.getPais())%></p>
					<div class="row informacion">
						<div class="col-sm-12 col-md-6">
							<p id="birhtdate">Cumpleaños : <%=((userLogin.getBirhtdate()==null) ? "No hay datos" : userLogin.getBirhtdate())%></p>
						</div>
						<div class="col-sm-12 col-md-6">
							<p id="sexo">Sexo : <%=((userLogin.getSexo()== null) ? "No hay datos": userLogin.getSexo())%></p>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		<div class="container">
			<div class="row numeroAmigos" >
				<div class="col-md-4">
					<p class="dato"><%=userLogin.getNumeroAmigos() %></p>
					<p class="info">Amigos</p>
				</div>
				<div class="col-md-4">
					<p class="dato" id="frase"><%=((userLogin.getFrase()==null) ? "No hay datos":userLogin.getFrase())%></p>
				</div>
				<div class="col-md-4" id="messages">
					<i class="far fa-envelope"></i>
					
					<h5 class="rounded-circle numero" id="noVistos"></h5>
					
					
				</div>
			</div>
		</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	let infoUsuario = {
			estado:"<%=((userLogin.getFrase()==null) ? "":userLogin.getFrase())%>",
			username:"<%=((userLogin.getNombreUsuario()==null) ? "":userLogin.getNombreUsuario())%>",
			cumple:"<%=((userLogin.getBirhtdate() ==null) ? "":userLogin.getBirhtdate())%>",
			sexo:"<%=((userLogin.getSexo() ==null) ? "":userLogin.getSexo())%>",
			pais:"<%=((userLogin.getPais()==null) ? "":userLogin.getPais())%>"
	};
	console.log(infoUsuario);
	
	$("#messages").on("click",function(){
		window.location.href= "mensajes.jsp";
	});

	setInterval(() => {
		$.get ("/SocialMedia/Messages", function(cantidad){
			console.log(cantidad);
			$("#noVistos").text(cantidad);
		}	)
	}, 1000);
	
	
	function cambiando(){
		//obteniendo elementos por jQuery
		let checkF = "";
		let checkM = "";
		if(infoUsuario.sexo == 'F'){
			checkF="checked";
		}else if(infoUsuario.sexo == 'M'){
			checkM="checked";
		}
		$("#sexo").html("<div class='form-check form-check-inline'>" +
				 "<label class='form-check-label' for='inlineRadio1'>Sexo</label>"+
				   "</div>"+
					"<div class='form-check form-check-inline'>" +
					 "<input class='form-check-input' type='radio' name='radioSexo' id='inlineRadio1' value='F' "+ checkF +">"+
					 "<label class='form-check-label' for='inlineRadio1'>F</label>"+
				   "</div>"+
				   "<div class='form-check form-check-inline'>"+
					  "<input class='form-check-input' type='radio' name='radioSexo' id='inlineRadio2' value='M' "+checkM+">"+
					  "<label class='form-check-label' for='inlineRadio2'>M</label>"+
			  	   "</div>");
		
		$("#frase").replaceWith("<input class='form-control' type='text' id ='estado' name='estadoPerfil' placeholder='Estado' value='"+infoUsuario.estado+"'>");

		$("#nombreUsuario").replaceWith("<div class='divUser'><input class='form-control mb-2' id='userName' name='nombreUsuario' placeholder='NombreDeUsuario' value="+infoUsuario.username+">"+
				"<input class='form-control mb-2' id='paiss' name='pais' placeholder='Pais' value="+infoUsuario.pais+"></div>");

		$("#birhtdate").replaceWith("<div class='divUser'><input class='form-control' id='cumple' name='cumple' type='date' value="+infoUsuario.cumple+"></div>");

		$("#pais").replaceWith("");
		
		$("#acciones").html("<h4><i class='fas fa-save mx-2' onclick='guardar()'></i><i class='fas fa-user-times' onclick='cancelar()'></i></h4>");
	
	}
	
	function cancelar(){
		//window.location.href = "/SocialMedia/usuario.jsp";
		location.reload();
	}
	
	function guardar(){
		Swal.fire({
			title:'Guardando los cambios'
		});
		Swal.showLoading();
		
		let valorestado = document.getElementById("estado").value;
		console.log(valorestado);
		
		let valorNombreUsuario = document.getElementById("userName").value;
		console.log(valorNombreUsuario);
		if(valorNombreUsuario == ""){
			alert("El nombre de usuario no puede estar vacio");
			Swal.close();
			return;
		}else{
			if(valorNombreUsuario.length < 6){
				alert("El username debe tener al menos 6 caracteres");	
				Swal.close();
				return;
			}
		}
		
		let valorBirhtdate = document.getElementById("cumple").value;
		console.log(valorBirhtdate);
		
		let valorPais = document.getElementById("paiss").value;
		console.log(valorPais);
		
			//esto trae un arreglo de radios
		let radioSexo = document.getElementsByName("radioSexo");
		
		let valorSexo = "";
		
		for(let radio of radioSexo){
			if(radio.checked ==true){
				valorSexo = radio.value;
			}
		}
		console.log(valorestado);
		 $.ajax({
	        url:"/SocialMedia/UsuarioServlet",
	        method:"GET", //First change type to method here

	        data:{
	        	//nombre Propiedad     valor
	        	"estadoPerfil":valorestado,
	        	"nombreUsuario":valorNombreUsuario,
	        	"cumple":valorBirhtdate,
	        	"pais":valorPais,
	        	"sexo":valorSexo
	        },
	        success:function(response) {
	        	if(response == "-1"){
	        		window.location.href ="/SocialMedia/login.jsp";
	        	}
	        	location.reload();
	        	
	       },
	       error:function(){
	    	   alert("Ocurrio un error por favor intentalo mas tarde");
	    	   console.log("error");
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
	
	function eliminarAmigo(idAmigo){
		$.get("/SocialMedia/UsuarioServlet?idAmigo="+idAmigo,function(){
			//$("#"+idAmigo).remove();
			window.location.href="/SocialMedia/usuario.jsp";
		});
		
	}
	
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