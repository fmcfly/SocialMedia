<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% HttpSession sesion = request.getSession(); 
    	String mensaje = "";
    	if(sesion.getAttribute("mensaje") != null){
    		mensaje = (String) sesion.getAttribute("mensaje");
    	}    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SocialMedia</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

  <link rel="stylesheet" href="css/estilos.css">
  
</head>
<body>

	<div class="form">
	
	<h1>Bienvenido a Social Media</h1>
	<h2>Realiza tu registro</h2>
	<hr width="85%" color="violet">
	</div>
	
	<div class="forma">
		<form id="registroForm" enctype="multipart/form-data">
		      
	      <label >Nombre:</label>
	      <input type="text" class="form-control" id="nombreInput" name="Nombre" required>
	
	      <label >Apellido:</label>
	      <input type="text" class="form-control" id="apellidoInput" name="Apellido"  >
		  
		  
	      <label  >Nombre de usuario:</label>
		  <div class="input-group mb-2">
            <div class="input-group-prepend">
              <div class="input-group-text">@</div>
            </div>
            <input type="text" class="form-control" id="usernameInput" name="userName"  >
          </div>
		  
	      <label >Correo:</label>
	      <input type="email" class="form-control" id="correoInput" name="Correo">
	      
	      <label >Telefono:</label>
	      <input type="number" id="telefonoInput" class="form-control" name="Telefono">
	
	      <label for="inputPassword6">Contraseņa:</label>
	      <input type="password" id="passwordInput" class="form-control " aria-describedby="passwordHelpInline"
	      name="Contrasena">
	      <small id="passwordHelpInline" class="text-muted">Debe tener entre 8 y 20 caracteres.</small>
	      
	      <label >Confirmar Contraseņa:</label>
	      <input type="password" id="inputPasswordConfirmacion" class="form-control " aria-describedby="passwordHelpInline">
	      
	      <label for ="myFile">Selecciona una Imagen</label>
		  <input type="file" id="myFile"  name="Imagen">
		  
		  <div class="nav justify-content-end">
		  	<button class="boton" color="violet" onclick="validar()">Registrar</button>
		  </div>
		</form>
	   <div class="mx-auto" style="width: 200px;">
   			 	    <div class="input-group-prepend">
				       <%if(mensaje != ""){%>
						<div class="alert alert-danger" role="alert">
			  	          <%=mensaje %>
			 			</div>
			  	 	</div>
				  </div>
						  <%}%>
	</div>	
	<script type="text/javascript" src="javascript/validaciones.js"></script>
</body> 
</html>