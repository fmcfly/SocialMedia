<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	HttpSession sesion = request.getSession();
	String mensaje = "";
	if(sesion.getAttribute("mensaje") != null){
		mensaje = (String) sesion.getAttribute("mensaje");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>CHAT</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" href="css/estilos.css">
		<script>
		
			
		</script>
	</head>
	<body>
		<div class="form">
				<h1>Bienvenido a Social Media</h1>
				<hr width="85%" color="violet">
				<h2>Inicia sesión</h2>
		</div>
		<%if(mensaje != ""){%>
			<div class="alert alert-danger" role="alert">
			  <%=mensaje %>
			</div>
		<%}
		%>
			<form class="container" method="POST" action="LoginServlet">
				  <div class="form-group">
				    <label for="exampleInputEmail1">Correo:</label>
				    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="correo">
				  </div>
				  <div class="form-group">
				    <label for="exampleInputPassword1">Contraseña</label>
				    <input type="password" class="form-control" id="exampleInputPassword1" name="password">
				  </div>
				
				  <button type="submit" class="btn btn-primary">Ingresar</button>
				  <a href="registro.jsp">¿Aún no tienes cuenta?</a>
			</form>


	</body>
</html>