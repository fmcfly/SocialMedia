Swal.fire({
	title:'Cargando'
});
Swal.showLoading();
let idUsuarioLogueado;
let idChatSeleccionado = 0;
let mensajesActuales = [];

setInterval(() => {
	if(mensajesActuales.length > 0){
		let maxIdMensaje = Math.max.apply(Math, // identifica el valor maxzimo de un arreglo 
				mensajesActuales.map(function(mensaje) {  // esta generando un arreglo con los puros idMensaje
					return mensaje.idMensaje;//regresar el iMensaje del arreglo de mensaje 
				})
		);
		console.log(maxIdMensaje);
		
		$.get("/SocialMedia/MensajesServlet?idChat="+idChatSeleccionado,function(mensajesActualizados){
			console.log(mensajesActualizados);
			
			let maxIdMensajeActualizado = Math.max.apply(Math, // identifica el valor maxzimo de un arreglo 
					mensajesActualizados.map(function(mensaje) {  // esta generando un arreglo con los puros idMensaje
						return mensaje.idMensaje;//regresar el iMensaje del arreglo de mensaje 
					})
			);
			if(maxIdMensajeActualizado > maxIdMensaje){ // en caso de que haya un idMnesaje mayor al de los que ya tenemos
				//buscar el mensaje en el arreglo de mensajes actualizados
				let mensajeNuevo = mensajesActualizados.filter(mensaje => mensaje.idMensaje == maxIdMensajeActualizado)[0];
				console.log(mensajeNuevo);
				//ya que tenemos el mensaje nuevo entonces vamos a insertarlo en nuestro chat
				$("#mensajes").append("<div class='"+(mensajeNuevo.idUsuario == idUsuarioLogueado ? 'mensajeUsuario':'')+"' >"+
						"<div class='card border "+(mensajeNuevo.idUsuario == idUsuarioLogueado ? 'border-success':'border-primary')+"' style='width: 18rem;'>"+
						  "<div class='card-body'>"+
						    "<p class='card-text'>"+mensajeNuevo.texto+"</p>"+
						    "<p class='card-link'>"+mensajeNuevo.fecha+"</p>"+
						  "</div>"+
						"</div>"+
					"</div>");
				mensajesActuales = mensajesActualizados;
			}
			
		});
	}
}, 10000);

const funInicio = (idUser) => {
	idUsuarioLogueado = idUser;
	llenarLista();
	//CHECA si en la liga 
	var url_string = window.location.href; //window.location.href
	var url = new URL(url_string); // API JS
	var idUsuarioPerfil = url.searchParams.get("idUsuario");
	
	if(idUsuarioPerfil!=null){
		console.log("SE INGRESO A UN PERFIL");
		console.log(perfilUsuario);
		$("#nameUserChat").text(perfilUsuario.nombre);
		$("#imageUser").attr("src","img/"+perfilUsuario.image);
		$("#spinner").removeClass("spinner-no");
		$("#spinner").addClass("spinner-si");
		$.post("/SocialMedia/MensajesServlet",function(mensajes){
			console.log(mensajes);
			if(mensajes.length > 0){
				idChatSeleccionado = mensajes[0].idChat;
			}
			mensajesActuales = mensajes;
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
			// lllamamos al metodo animate el cual aplica un scrolltop que cfunciona como paddingTop se indica cuanto 
			// en donde quiere posicionarse, oara ello se obtiene la altura maxima del div que se esta trabjando
			$("#mensajes").animate({ scrollTop: $('#mensajes')[0].scrollHeight}, 1);
			$("#spinner").removeClass("spinner-si");
			$("#spinner").addClass("spinner-no");
		});
	}
}

function llenarLista(){
	$.get("/SocialMedia/ChatServlet",function(listaChats){ // llamar a a servlet por GET
		$("#bodyContactos").html("");
		for(let chat of listaChats){
			//JSON.stringify(datos).replace(/\"/g,"&quot;")
			
			$("#bodyContactos").append("<tr onclick='traerMensajes("+JSON.stringify(chat)+")'><td>"+chat.idChat+"</td><td>"+chat.nombre+"</td>" +
					"<td><img class='imagen-tabla rounded-circle' src='img/"+(chat.image=='' || chat.image == undefined ? 'default.jpg':chat.image)+"'></td></tr>");
		}
		Swal.close();
	});
}

const traerMensajes = (chat) =>{
	if(idChatSeleccionado != chat.idChat){
		idChatSeleccionado = chat.idChat;
		console.log(chat);
		$("#nameUserChat").text(chat.nombre);
		$("#imageUser").attr("src","img/"+(chat.image=='' || chat.image==undefined ? 'default.jpg':chat.image));
		$("#spinner").removeClass("spinner-no");
		$("#spinner").addClass("spinner-si");
		$("#mensajes").html("");
		// PETICIÓN DE LOS MENSAJES
		$.post("/SocialMedia/ChatServlet?idChat="+chat.idChat,function(mensajes){
			
			mensajesActuales = mensajes;
			console.log(mensajesActuales);
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
			// lllamamos al metodo animate el cual aplica un scrolltop que cfunciona como paddingTop se indica cuanto 
			// en donde quiere posicionarse, oara ello se obtiene la altura maxima del div que se esta trabjando
			$("#mensajes").animate({ scrollTop: $('#mensajes')[0].scrollHeight}, 1);
			$("#spinner").removeClass("spinner-si");
			$("#spinner").addClass("spinner-no");
		});
	}
};

const sendMessage = () =>{
	if($("#message").val() != ""){
		$.ajax({
	        url:"/SocialMedia/Messages",
	        method:"POST", //First change type to method here

	        data:{
	        	"message": $("#message").val(),
	        	"idChat":idChatSeleccionado
	        },
	        success:function(response) {
	        	console.log(response);
	        	if(idChatSeleccionado == 0){
	        		idChatSeleccionado = response.idChat;
	        		llenarLista();
	        	}
	        	$("#mensajes").append("<div class='mensajeUsuario'>"+
				        				"<div class='card border border-success' style='width: 18rem;'> " +
					        			  "<div class='card-body'>"+
					        			    "<p class='card-text'>"+response.texto+"</p>"+
					        			    "<p class='card-link'>"+response.fecha+"</p>"+
					        			  "</div>"+
					        			"</div>"+
					        			  "</div>");
	        	mensajesActuales.push(response);
	        	$("#message").val("");
	       },
	       error:function(){
	    	   console.log("error");
	       }

      });
	}
};
