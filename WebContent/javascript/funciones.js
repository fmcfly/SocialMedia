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