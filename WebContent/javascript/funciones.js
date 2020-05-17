function friend(idAmigo){
	console.log(idAmigo);
	//const cuando la variable es constante durante toda la ejecución
	let formChat = document.getElementById("amigosChat");//el metodo getElementById nos sirve para obtener elementos dentro del DOM esto
	//para poder manipular dichos elementos a nuestra voluntad
	
	// ya obtenemos el form
	formChat.action = "/SocialMedia/MensajesServlet?idAmigo="+idAmigo;
	formChat.method = "POST";
	formChat.submit();// enviamos
}

function agregarAmigo(idUsuario){	
	let formAgregar = document.getElementById("agregar");
	formAgregar.action = "/SocialMedia/PerfilServlet?idUsuario="+idUsuario;
	formAgregar.method = "POST";
	formAgregar.submit();// enviamos
}

function sendMessage(idUsuario){
	let formAgregar = document.getElementById("agregar");
	formAgregar.action = "/SocialMedia/MensajesServlet?idAmigo="+idUsuario;
	formAgregar.method = "POST";
	formAgregar.submit();// enviamos
}