function enviarMensaje(){
	console.log("Enviar mensaje");
	let formMensaje = document.getElementById("envio");
	formMensaje.action = "/SocialMedia/EnvioMensajes";
	formMensaje.method = "POST";
	formMensaje.submit();
}