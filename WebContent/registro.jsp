<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SocialMedia</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

  <link rel="stylesheet" href="css/estilos.css">
  <script type="text/javascript" src="javascript/validaciones.js"></script>
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
	 
	
	      <label >Correo:</label>
	      <input type="email" class="form-control" id="correoInput" name="Correo">
	      
	      <label >Telefono:</label>
	      <input type="number" id="telefonoInput" class="form-control" name="Telefono">
	
	      <label for="inputPassword6">Contraseña:</label>
	      <input type="password" id="passwordInput" class="form-control " aria-describedby="passwordHelpInline"
	      name="Contrasena">
	      <small id="passwordHelpInline" class="text-muted">Debe tener entre 8 y 20 caracteres.</small>
	      
	      <label >Confirmar Contraseña:</label>
	      <input type="password" id="inputPasswordConfirmacion" class="form-control " aria-describedby="passwordHelpInline">
	      
	      <label for ="myFile">Selecciona una Imagen</label>
		  <input type="file" id="myFile"  name="Imagen">
		  
		  <div class="nav justify-content-end">
		  	<button class="boton" color="violet" onclick="validar()">Registrar</button>
		  </div>
		</form>
	   
	</div>	
</body> 
</html>