Swal.fire({
	title:'Cargando'
});
Swal.showLoading();
let idUsuarioLogueado;

const setUserLogin = (idUser) => {
	idUsuarioLogueado = idUser;
}

$.get("/SocialMedia/ChatServlet",function(listaChats){ // llamar a a servlet por GET
	for(let chat of listaChats){
		//JSON.stringify(datos).replace(/\"/g,"&quot;")
		$("#bodyContactos").append("<tr onclick='traerMensajes("+JSON.stringify(chat)+")'><td>"+chat.idChat+"</td><td>"+chat.nombre+"</td>" +
				"<td><img class='imagen-tabla rounded-circle' src='img/"+(chat.image=='' ? 'default.jpg':chat.image)+"'></td></tr>");
	}
	Swal.close();
});

const traerMensajes = (chat) =>{
	console.log(chat);
	$("#nameUserChat").text(chat.nombre);
	$("#imageUser").attr("src","img/"+(chat.image=='' ? 'default.jpg':chat.image));
	$("#spinner").removeClass("spinner-no");
	$("#spinner").addClass("spinner-si");
	$("#mensajes").html("");
	// PETICIÓN DE LOS MENSAJES
	$.post("/SocialMedia/ChatServlet?idChat="+chat.idChat,function(mensajes){
		console.log(mensajes);
		for(let mensaje of mensajes){
			$("#mensajes").append("<div class='"+(mensaje.idUsuario == idUsuarioLogueado ? 'mensajeUsuario':'')+"' >"+
					"<div class='card border "+(mensaje.idUsuario == idUsuarioLogueado ? 'border-success':'border-primary')+"' style='width: 18rem;'>"+
					  "<div class='card-body'>"+
					    "<p class='card-text'>"+mensaje.texto+"</p>"+
					    "<p class='card-link'>"+mensaje.fecha+"</p>"+
					  "</div>"+
					"</div>"+
				"</div>");
		}
		$("#spinner").removeClass("spinner-si");
		$("#spinner").addClass("spinner-no");
	});
};