let idUsuarioLogueado;

const setUserLogin = (idUser) => {
	idUsuarioLogueado = idUser;
}

function sendMessage(idUsuario){
	
	$.post("/SocialMedia/MensajesServlet?idAmigo="+idUsuario,function(){
		window.location.href = "/SocialMedia/chat.jsp";
		//location.reload();
	});	
	/*let formAgregar = document.getElementById("agregar");
	formAgregar.action = "/SocialMedia/MensajesServlet?idAmigo="+idUsuario;
	formAgregar.method = "POST";
	formAgregar.submit();// enviamos*/
}

const mostrarPerfil = (idUsuario,amigo) =>{
	// tiene que traer los datos al perfil.jsp
	if(idUsuarioLogueado == idUsuario){
		window.location.href="/SocialMedia/usuario.jsp";
	}else{
		$.get("/SocialMedia/BusquedaServlet?idUsuario="+idUsuario+"&amigo="+amigo,function(){
			window.location.href="/SocialMedia/perfil.jsp";
		});
	}
	
}