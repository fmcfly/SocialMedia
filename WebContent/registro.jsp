<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<hr>
	</div>
	
	<div class="row mx-md-n5">
	 <div class="form-group"  >
	     <div class="col-md-6 mb-2">
		     <form method="POST" action="UsuarioServlet"> 
		      <label for="validationDefault01">Nombre</label>
		      <input type="text" class="form-control" id="validationDefault01" name="Nombre" >
		
		      <label for="validationDefault02">Apellido</label>
		      <input type="text" class="form-control" id="validationDefault02" name="Apellido"  >
		 
		
		      <label for="inputEmail3" class="col-sm-2 ">Correo</label>
		      <input type="email" class="form-control" id="inputEmail3" name="Correo">
		
		      <label for="inputPassword6">Contraseña</label>
		      <input type="password" id="inputPassword" class="form-control " aria-describedby="passwordHelpInline"
		      name="Contrasena">
		      <small id="passwordHelpInline" class="text-muted">
		      Must be 8-20 characters long.
		      </small>
		      
		      <label for="inputPassword6">Confirmar Contraseña</label>
		      <input type="password" id="inputPasswordConfirmacion" class="form-control " aria-describedby="passwordHelpInline">
		      </small>
		      
		      <label >Telefono</label>
		      <input type="number"  class="form-control" name="Telefono">
		      
		      <label for ="myFile">Selecciona una Imagen</label>
			  <input type="file" id="myFile"  name="Imagen">
			  <div class="nav justify-content-end">
			  
			  <button class="boton btn-danger">Registrar</button>
			  </div>
			  </form>
		  </div>	  
	  </div>
	</div>	
</body> 
</html>